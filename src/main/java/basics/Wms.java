package basics;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/*
Question 1- A warehouse receives shipments against Purchase Orders (PO).
Design a system that tracks received inventory per PO and SKU while ignoring duplicate events.

Q2: In a warehouse system, we receive inventory receipt events from a WMS.
Sometimes the same event may be delivered multiple times due to retries or network issues.

Design a service that processes these events and updates inventory counts correctly without double counting duplicates.

Q3- In a fulfillment system, inventory is updated when warehouse receipts arrive.

However:
events may be duplicated
events may arrive concurrently
purchase orders may be partially received

Write a service that maintains the correct inventory count per SKU for each purchase order.

*/

// 1. Data Model representing the WMS Event (The Immutable "Signal")
record WMSReceiptEvent(
    String eventId,        // For Idempotency
    String purchaseOrderId, 
    String sku, 
    int quantityScanned
) {}


// 2. Data Model for current Warehouse State
class InventoryState {
    private final Map<String, Integer> skuCounts = new ConcurrentHashMap<>();

    public void addQuantity(String sku, int qty) {
         // If sku doesn't exist, it puts 'qty'. 
        // If it does exist, it adds 'qty' to the existing value.
        skuCounts.merge(sku, qty, Integer::sum);
    }

    public int getQuantity(String sku) {
        return skuCounts.getOrDefault(sku, 0); // this is .get so just reads map. If not exist return 0
    }
}

public class Wms {

    // In-memory mocks of persistent storage
    //private final Set<String> processedEventIds = Collections.newSetFromMap(new ConcurrentHashMap<>());
    Set<String> processedEventIds = ConcurrentHashMap.newKeySet();
    //With this line: The second time the event arrives, the code checks the Set, sees the ID is already there, and skips the update. called Idempotency.
    private final Map<String, InventoryState> poStore = new ConcurrentHashMap<>(); // purchase id is key, inventory state is value

    /**
     * Core logic Elizabeth would evaluate for "Data-model taste" and "Invariants"
     */
    public void processReceipt(WMSReceiptEvent event) {

    // 1️⃣ Idempotency
    if (!processedEventIds.add(event.eventId())) {
        System.out.println("Duplicate Event " + event.eventId());
        return;
    }

    // 2️⃣ Validation
    if (event.quantityScanned() <= 0) {
        throw new IllegalArgumentException("Quantity must be positive");
    }

    // 3️⃣ Get/Create PO state
    InventoryState state =
        poStore.computeIfAbsent(event.purchaseOrderId(), k -> new InventoryState()); 
        //Gets the existing InventoryState for this order, or creates and stores a new one if it doesn't exist.


    // 4️⃣ Update SKU quantity
    state.addQuantity(event.sku(), event.quantityScanned());

    System.out.printf(
        "Processed PO %s SKU %s total %d%n",
        event.purchaseOrderId(),
        event.sku(),
        state.getQuantity(event.sku())
    );
}

    public static void main(String[] args) {
        Wms service = new Wms();

        // Scenario: WMS sends a partial receipt, then a retry of that same event
        WMSReceiptEvent firstScan = new WMSReceiptEvent("evt_101", "PO_99", "JEANS_01", 5);
        WMSReceiptEvent retryScan = new WMSReceiptEvent("evt_101", "PO_99", "JEANS_01", 5);
        WMSReceiptEvent secondScan = new WMSReceiptEvent("evt_102", "PO_99", "JEANS_01", 10);

        service.processReceipt(firstScan); // Expected: 5
        service.processReceipt(retryScan); // Expected: Ignored (Idempotency)
        service.processReceipt(secondScan); // Expected: 15 (Partial Receipt logic)
    }
}





/*
High level program to memorize:

- Record - event_id, po_id, sku, qty
- Inventory state - Concurrent Map <SKU, QTY> add quantity  or get quantity 
- WMS main logic
    - Private final Concurrent map po_store <PO_ID, inventory_state>  (inventory state is sku, qty)
    - Concurrent set process_event = new ConcurrentHashMap.keySet();
        - Idenpotency - if(process_event.contains(record.event_id))—> Duplicate event
        - If(record.qty<=0) -> new throw IllegalArgumentException
    - InventoryState state = new po_store.get(event_po_id()); // event.po_id() - here bracket due to record. Each var is method
        - if(state-null) —> state = new InventoryState();
        - InventoryState exist = po_store.putifAbsent(event_po_id(), state); 
        - If (!exist=null) -> state=exist
    - state.addqty(record.sku, record.qty)
    - process_event.add(event.event_id)


    Notes:
    1. record here is kind of private and final. immutable
    2. To access each variable from record treat it as method from other class
    i.e. event.purchaseOrderId()
    3. Variables - 
    InventoryState --> skucount<SKU, QTY>
    Main -> POStore<PO_ID, InventoryState>
    InventoryState state = poStore.get(event.POID())
    InventoryState exist = poStore.putIfAbsent(event.POID(), state)

 --- Event_ID  - The Trigger (The Scan)
A truck pulls up with 100 boxes of jeans. An employee grabs a handheld scanner and scans a pallet.
The Action: The employee pulls the trigger on the scanner for "Pallet A."
The Event ID: The scanner software generates a unique ID (e.g., SCAN_99821) the moment that barcode is read.

            WMS Event
              |
              v
      processEvent()
              |
     +--------+--------+
     |                 |
     v                 v
 Idempotency      Validation
  Check            quantity >0
     |                 |
     +--------+--------+
              |
              v
       Get/Create PO
        InventoryState
              |
              v
        Update SKU Count
              |
              v
        Store Event ID


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/*
 Warehouse ASN Workflow Processor

 Demonstrates:
 1. Event-driven architecture
 2. Idempotency
 3. Business invariants (state machine)
 4. Thread-safe data structures
*/

// Event types from warehouse





