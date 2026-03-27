package collections;
import java.util.*;

public class FindMostrepeatingIpinLog {
    public static void main(String[] args) {
        String[] logs = {
            "192.168.1.1 GET /home",
            "192.168.1.2 GET /login",
            "192.168.1.1 GET /cart",
            "192.168.1.1 POST /checkout"
        };

        findTopIP(logs);
    }

    public static void findTopIP(String[] logs) {
        // 1. Create Map to count occurrences
        HashMap<String, Integer> counts = new HashMap<>();

        for (String log : logs) {
            // Split by space and take the first part (the IP)
            String ip = log.split(" ")[0];
            counts.put(ip, counts.getOrDefault(ip, 0) + 1);
        }

        // 2. Find the IP with the maximum count
        String topIP = "";
        int maxCount = 0;

        // Iterate through entries using Map.Entry
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                topIP = entry.getKey();
            }
        }

        // 3. Print result
        System.out.println("The most frequent IP is: " + topIP + " (appears " + maxCount + " times)");
    }
}
