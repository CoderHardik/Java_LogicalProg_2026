/*
Header Respect: Emphasize that the code checks for the standard Retry-After header first. This prevents hammering a server that is explicitly asking for a cool-down period.
Fallback Strategy: If the header is missing, the code gracefully falls back to an exponential backoff strategy (defaultWaitMs *= 2).
Fast Failure: It immediately stops on standard client errors (like 401 Unauthorized or 404 Not Found) since retrying will not change the outcome.

*/

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class RateLimitRetryTest {

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://example.com")).GET().build();

        int maxRetries = 3;
        long defaultWaitMs = 1000; 

        for (int attempt = 0; attempt <= maxRetries; attempt++) {
            try {
                System.out.println("Attempt #" + (attempt + 1));
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                int status = response.statusCode();

                if (status == 200) {
                    System.out.println("Success! Payload: " + response.body());
                    return; // Exit completely on success
                }

                // Handle Rate Limiting (429) or Transient Errors (5xx)
                if (status == 429 || status >= 500) {
                    System.err.println("Limiter/Server hit. Status: " + status);
                    
                    // Look for standard "Retry-After" header (can be seconds or a timestamp)
                    String retryAfter = response.headers().firstValue("Retry-After").orElse("");
                    if (!retryAfter.isEmpty() && attempt < maxRetries) {
                        // Dynamically adjust wait time based on what the server asked
                        defaultWaitMs = Long.parseLong(retryAfter) * 1000; 
                        System.out.println("Server requested specific wait: " + defaultWaitMs + "ms");
                    }
                } else {
                    System.err.println("Fatal Client Error (" + status + "). No retry.");
                    return; 
                }
            } catch (Exception e) {
                System.err.println("Network failure: " + e.getMessage());
            }

            // Execute Backoff if retries remain
            if (attempt < maxRetries) {
                System.out.println("Backing off for " + defaultWaitMs + "ms...");
                Thread.sleep(defaultWaitMs);
                defaultWaitMs *= 2; // Exponential fallback fallback if no header provided
            }
        }
        System.err.println("Test Failed: Max retries exhausted.");
    }
}
