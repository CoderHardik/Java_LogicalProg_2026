package collections;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
class Order {
    String orderId;
    String userId;
    double orderAmount;
    double discount;
    double finalAmount;
    String orderTimestamp;

    public Order(String orderId, String userId, double orderAmount,
                 double discount, double finalAmount, String orderTimestamp) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderAmount = orderAmount;
        this.discount = discount;
        this.finalAmount = finalAmount;
        this.orderTimestamp = orderTimestamp;
    }
}

public class OrderProcess{

public static void main(String[] args) {
  Order o1 = new Order ("1", "41", 349, 50, 299, "08:00:00");
  Order o2 = new Order("1", "42", 550, 50, 500, "09:00:00");

  List <Order> olist = new ArrayList<>();
  olist.add(o1);
  olist.add(o2);

  for(String s: findInvalidOrders(olist)){
    System.out.println(s);
  }

}//end of main

public static Set<String> findInvalidOrders(List<Order> orders) {
    Set<String> invalidOrders = new LinkedHashSet<>(); // Using Set to avoid duplicate entries in results
    LocalTime currentTime = LocalTime.now();
    Set<String> seenOrderID = new HashSet<>();

    for (Order o : orders) {
        boolean isInvalid = false;
        // Parsing with a pattern that handles 1 or 2 digit hours (e.g., "8:00:00" or "08:00:00")
        LocalTime orderTime = LocalTime.parse(o.orderTimestamp, DateTimeFormatter.ofPattern("H:mm:ss"));

        if (!seenOrderID.add(o.orderId)) {
            isInvalid = true;
        }

        if (o.orderAmount <= 0 || o.discount < 0 || 
            (Math.abs(o.finalAmount - (o.orderAmount - o.discount)) > 0.001) || 
            orderTime.isAfter(currentTime)) {
            isInvalid = true;
        }

        if (isInvalid) {
            invalidOrders.add(o.orderId);
        }
    }
    return invalidOrders;
}


}
