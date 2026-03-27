package collections;
import java.util.*;
import java.time.*;
import java.util.stream.Collectors;

/*
You are given a list of login attempts.

Each login attempt has:
userId (String)
ipAddress (String)
timestamp (long – epoch millis)
success (boolean)

🚫 A User Is Suspicious If:
1. They have 3 or more failed login attempts within any 5-minute window
2. They log in successfully from two different IP addresses within 2 minutes
3. Timestamps are not in increasing order for same user (data integrity issue)

*/


import java.util.*;
import java.util.stream.Collectors;

/**
 * A Record is a compact class for immutable data.
 * It automatically provides: fields, constructor, toString, equals, and hashCode.
 */
record LoginAttempt(String userId, String ipAddress, long timestamp, boolean success) {}

public class SuspiciousDetector {

    public static void main(String[] args) {
        List<LoginAttempt> attempts = Arrays.asList(
            new LoginAttempt("user1", "192.168.1.1", 100000, false),
            new LoginAttempt("user1", "192.168.1.1", 200000, false),
            new LoginAttempt("user1", "192.168.1.1", 300000, false), // Rule 1: 3 failed
            new LoginAttempt("user2", "1.1.1.1", 500000, true),
            new LoginAttempt("user2", "2.2.2.2", 550000, true), // Rule 2: 2 IPs + Success
            new LoginAttempt("user3", "3.3.3.3", 1000000, true),
            new LoginAttempt("user3", "3.3.3.3", 900000, true)  // Rule 3: Out of order
        );

        Set<String> results = findSuspiciousUsers(attempts);
        System.out.println("Suspicious Users Identified: " + results);
    }

    public static Set<String> findSuspiciousUsers(List<LoginAttempt> attempts) {
        Set<String> suspiciousUsers = new HashSet<>();

        // 1. Grouping by userId using Java Streams (replaces the manual for-loop)
        Map<String, List<LoginAttempt>> userHistory = attempts.stream()
                .collect(Collectors.groupingBy(LoginAttempt::userId));

        // 2. Process each user group
        for (Map.Entry<String, List<LoginAttempt>> entry : userHistory.entrySet()) {
            String userId = entry.getKey();
            List<LoginAttempt> history = entry.getValue();

            // Rule 3: Data Integrity Check (Check if unsorted originally)
            for (int i = 0; i < history.size() - 1; i++) {
                if (history.get(i).timestamp() > history.get(i + 1).timestamp()) {
                    suspiciousUsers.add(userId);
                    break; 
                }
            }

            // Sort by timestamp using the record accessor
            history.sort(Comparator.comparingLong(LoginAttempt::timestamp));

            // 3. Sliding Window Checks
            for (int i = 0; i < history.size(); i++) {
                if (suspiciousUsers.contains(userId)) break;

                LoginAttempt current = history.get(i);
                int failedCount = current.success() ? 0 : 1;

                for (int j = i - 1; j >= 0; j--) {
                    LoginAttempt prev = history.get(j);
                    long diff = current.timestamp() - prev.timestamp();

                    // Optimization: if we are past the largest window (5 mins), stop looking back
                    if (diff > 300000) break;

                    // Rule 1: 3+ failed in 5 mins (300,000ms)
                    if (diff <= 300000 && !prev.success()) {
                        failedCount++;
                    }

                    // Rule 2: 2 different IPs + Success in 2 mins (120,000ms)
                    if (diff <= 120000 && current.success() && prev.success()) {
                        if (!current.ipAddress().equals(prev.ipAddress())) {
                            suspiciousUsers.add(userId);
                        }
                    }

                    if (failedCount >= 3) suspiciousUsers.add(userId);
                }
            }
        }
        return suspiciousUsers;
    }
}
