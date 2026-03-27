package collections;

import java.util.*;

/*
The Logic (validateResponses): It loops through a list of these responses and checks for two specific conditions:
The status must be "SUCCESS".
The response time must be higher than the threshold (in this case, 200ms).

*/

record ApiResponse(int id, String status, int responseTime) {}


public class ApiValidator {
    public static void main(String[] args) {
        // Mocking the API response list
        List<ApiResponse> responses = Arrays.asList(
            new ApiResponse(101, "SUCCESS", 150),
            new ApiResponse(102, "SUCCESS", 250), // Match
            new ApiResponse(103, "FAIL", 300),
            new ApiResponse(104, "SUCCESS", 180),
            new ApiResponse(105, "SUCCESS", 210)  // Match
        );

        int threshold = 200;
        List<Integer> result = validateResponses(responses, threshold);
        
        System.out.println("Slow successful IDs: " + result); 
        // Output: [102, 105]
    }

    public static List<Integer> validateResponses(List<ApiResponse> responses, int threshold) {
        List<Integer> filteredIds = new ArrayList<>();

        for (ApiResponse res : responses) {
            // Validate: SUCCESS status AND time > threshold
            if ("SUCCESS".equalsIgnoreCase(res.status()) && res.responseTime() > threshold) {
                filteredIds.add(res.id());
            }
        }

        return filteredIds;
    }
}
