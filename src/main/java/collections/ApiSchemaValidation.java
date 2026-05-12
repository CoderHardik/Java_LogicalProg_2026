/*
Question - How do you verify that an API response is correct beyond just checking the 200 OK status code?"
Or
Validate that json schema of given API response if as expected

Few notes for this prorgam:
1. For following program we are using record class instead of POJO
  - Benifits of record - Records automatically generate the constructor, getters (accessors), equals(), hashCode(), and toString()
  - record's equals() method compares the values inside the object, not the memory address
  - They are immutable (final) plus they are easily readable are other added obvious benifits.

2. We are not using jackson and restassured and using standard java implementation to stay focused on solution and not frame work since it needs external lib. In interview following point can be noted
 - In a real project, I would use Jackson for robust parsing, but for a lightweight script, manual string parsing keeps the footprint small.
 - Limitation of this code - nested json cant be handled
 - 

 3. It uses standard java httpClient and To send the request and perform different API actions. Same was used in mercury interview so should be safe to use

 4. we have getValue method that convert json to string for validation. That is the only tricky part to remember and convert.

json.split("\"" + key + "\":\"?")[1].split("[\",}]")[0]
* Example of how it works:
String json = "{\"session_id\":\"S-101\", \"energy_used\":50.5}";We want to find the value for the key: session_id

Step 1: The first .split()json.split("\"" + key + "\":\"?")What it looks for: It searches for "session_id": followed by an optional quote ".
The Result: It cuts the string into two pieces:[0] : {" (everything before the key)[1] : S-101", "energy_used":50.5} (everything after the key and its colon/quote)
The Code: .split(...)[1] grabs that second piece.

Step 2: The second .split()[1].split("[\",}]")What it looks for: It takes that second piece (S-101", "energy_used":50.5}) and cuts it again at the first occurrence of a double quote, a comma, or a closing bracket.
The Result:
[0] : S-101 (The actual value!)
[1] : , 
[2] :  "energy_used":50.5}
The Code: [0] grabs the very first part, which is your clean value.
 
 Overall:
 Network: Use HttpClient.
 Model: Use Records.
 Parse: Manipulate Strings without a "magic" library.
 Validate: Use value-based equality.
*/


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiTest {

    // Your Record
    record Session(String session_id, String start_time, String end_time, double energy_used) {}

    public static void main(String[] args) throws Exception {
        // 1. Send Request using Standard Java Client
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://example.com"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // 2. Manual Validation (instead of JSON Schema)
        if (response.statusCode() != 200) throw new RuntimeException("Failed!");

        // 3. Manual Mapping (The "Hard" Part without Jackson)
        // Note: For a real interview, you'd usually use a Regex or String.split 
        // to grab values if you aren't allowed to use a JSON library.
        String body = response.body();
        Session actual = new Session(
            getValue(body, "session_id"),
            getValue(body, "start_time"),
            getValue(body, "end_time"),
            Double.parseDouble(getValue(body, "energy_used"))
        );

        // 4. Comparison
        Session expected = new Session("S-101", "10:00", "11:00", 50.5);
        
        // Because it's a RECORD, this compares field values, not memory addresses!
        if (actual.equals(expected)) {
            System.out.println("Validation Passed!");
        } else {
            System.out.println("Validation Failed!");
        }
    }

    // Helper to extract JSON values without a library
    private static String getValue(String json, String key) {
        return json.split("\"" + key + "\":\"?")[1].split("[\",}]")[0];
    }
}
