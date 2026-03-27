package collections;

import java.util.HashMap;
import java.util.Map;

public class UserLoginActivity {

    public static void main(String[] args) {
        // Input log data
        String[] logs = {
            "user1 login",
            "user2 login",
            "user1 logout",
            "user2 logout",
            "user2 login"
        };

        // Process logs and print results
        Map<String, Integer> results = getActiveSessions(logs);
        System.out.println(results);
    }
    public static Map<String, Integer> getActiveSessions(String[] logs) {
        // Create the map to store <User, SessionCount>
        Map<String, Integer> hm = new HashMap<>();

        for (String st : logs) {
            // Split the string into [user, action]
            String[] parts = st.split(" ");
            String user = parts[0];
            String action = parts[1];

            // 1. Get current session count (default to 0 if new user)
            int currentSession = hm.getOrDefault(user, 0);

            // 2. Update logic: login adds 1, logout subtracts 1
            if (action.equals("login")) {
                hm.put(user, currentSession + 1);
            } else if (action.equals("logout")) {
                hm.put(user, currentSession - 1);
            }
        }

        return hm;
    }
}
