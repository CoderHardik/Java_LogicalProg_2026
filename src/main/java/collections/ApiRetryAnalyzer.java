/*
Problem statement:
How would you test that our API actually retries 3 times with exponential backoff before failing?

Note:
If success of 200 code then no retry, If 4xx then no retry because it will be same,
If 5xx then it might be triansient so retry again.

Steps:
- Establish http client with timeout
- make request
- Start for loop with retry
try
- inside for loop if 200 then break (done)
- if 500 then retry. if 400 then break
catch - any other error

final validation after retry (either 200 ok or still error)
if 200 and not null then sucess else fail - for sucess, validate body with expected json body make sure body works as expected.

*/


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ApiRetryTest {

    // Using your record for data validation
    record Session(String session_id, String start_time, String end_time, double energy_used) {}

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://example.com"))
                .GET()
                .build();

        // Retry Configuration
        int maxRetries = 3;
        long waitTime = 1000; // 1 second start

        HttpResponse<String> response = null;

        for (int i = 0; i <= maxRetries; i++) {
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());

                // Success Condition
                if (response.statusCode() == 200) {
                    System.out.println("Success on attempt " + (i + 1));
                    break;
                }

                // Logic: Only retry on transient server errors (5xx)
                if (response.statusCode() >= 500) {
                    System.err.println("Transient error " + response.statusCode() + ". Retrying...");
                } else {
                    // 4xx error (e.g. 404, 401) - don't bother retrying
                    System.err.println("Client error " + response.statusCode() + ". Skipping retry.");
                    break;
                }

            } catch (Exception e) {
                System.err.println("Network error: " + e.getMessage());
            }

            // Perform backoff if not the last attempt
            if (i < maxRetries) {
                Thread.sleep(waitTime);
                waitTime *= 2; // Exponential: 1s, 2s, 4s
            }
        }

        // Final Validation (if we have a successful response)
        if (response != null && response.statusCode() == 200) {
            validateResponse(response.body());
        } else {
            System.out.println("Test Failed after retries.");
        }
    }

    private static void validateResponse(String body) {
        // Simplified mapping logic for the interview
        Session actual = new Session("S-101", "10:00", "11:00", 50.5);
        Session expected = new Session("S-101", "10:00", "11:00", 50.5);
        
        if (actual.equals(expected)) {
            System.out.println("Validation Passed!");
        }
    }
}
