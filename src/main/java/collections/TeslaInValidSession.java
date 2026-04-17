package collections;

import java.time.LocalTime;
import java.util.*;

/**
 * A Record is a concise way to create immutable data carriers.
 * It automatically generates the constructor, equals, hashCode, and toString.
 */
record Session(String session_id, String start_time, String end_time, double energy_used, double total_charge) {
    
    // Custom method to calculate rate per kWh
    public double getRatePerKw() {
        if (energy_used == 0) return 0.0;
        return total_charge / energy_used;
    }

    // Overriding the default toString to match your specific JSON-like format
    @Override
    public String toString() {
        return """
            {
              "session_id": "%s",
              "start_time": "%s",
              "end_time": "%s",
              "rate_per_kw": "%.2f"
            }
            """.formatted(session_id, start_time, end_time, getRatePerKw());
    }
}

public class TeslaInValidSession {

    public static void main(String[] args) {
        // Create Objects using the record's canonical constructor
        // Parameters: id, start, end, energy(kw), charge($)
        Session s1 = new Session("A1", "11:00:00", "12:00:00", 15.0, 15.0);
        Session s2 = new Session("A2", "08:00:00", "07:00:00", 15.0, 15.0);

        List<Session> sessions = Arrays.asList(s1, s2);
        /*
        List <Session> sessions = new ArrayList<>();
        sessions.addAll(Arrays.asList(s1,s2)); // Another way to add
        */


        System.out.println("--- Invalid Sessions Found ---");
        List<Session> invalidOnes = findInvalidSessions(sessions);
        
        for (Session s : invalidOnes) {
            System.out.println(s);
        }
    }

    /**
     * Identifies sessions where the end time is not after the start time.
     */
    public static List<Session> findInvalidSessions(List<Session> sessions) {
        List<Session> result = new ArrayList<>();
        
        for (Session s : sessions) {
            try {
                // 1. Check for Null or Empty values in the Record
            if (s.sessionid() == null || s.sessionid().isBlank() || 
                s.startdate() == null || s.enddate() == null) {
                result.add(s);
                continue;
            }
                // Accessing record fields uses the field name as a method: s.start_time()
                LocalTime startTime = LocalTime.parse(s.start_time());
                LocalTime endTime = LocalTime.parse(s.end_time());

                if (!endTime.isAfter(startTime)) {
                    result.add(s);
                    continue;
                }
                if ((s.totalkw() > 0 && s.charge() <= 0) || 
                (s.charge() > 0 && s.totalkw() <= 0)) {
                result.add(s);
                continue;
            }
            } catch (Exception e) {
                // If parsing fails, we treat it as an invalid session entry
                result.add(s);
            }
        }
        return result;
    }
}
