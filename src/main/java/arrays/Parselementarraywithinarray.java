

import java.util.*;

public class Parselementarraywithinarray {
    public static void main(String[] args) {
        // Deeply nested structure: List -> Map -> List -> List -> Map
        List<Object> complexData = List.of(
            "Some String",
            Map.of("meta", List.of(
                List.of(Map.of("sessionid", "TARGET_VALUE"))
            )),
            42 // Unknown integer type
        );

        System.out.println("Found Values: " + findValue(complexData, "sessionid"));
    }

    public static List<Object> findValue(Object input, String targetKey) {
        List<Object> found = new ArrayList<>();

        if (input instanceof Map<?, ?> map) {
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                // If key matches, add the value
                if (entry.getKey().equals(targetKey)) {
                    found.add(entry.getValue());
                } else {
                    // Otherwise, search deeper into the value
                    found.addAll(findValue(entry.getValue(), targetKey));
                }
            }
        } else if (input instanceof List<?> list) {
            // Loop over each element in the array
            for (Object item : list) {
                found.addAll(findValue(item, targetKey));
            }
        }
        // Base case: If it's a primitive (String, int, etc.), do nothing
        return found;
    }
}


/*
Find deepese element use following program, above program is to find target value
Remember that JSOn only has list and map
public static List<Object> getAllLeafValues(Object input) {
List<Object> leaves = new ArrayList<>();

    if (input instanceof Map<?, ?> map) {
        for (Object value : map.values()) {
            leaves.addAll(getAllLeafValues(value)); // Drill into Map values
        }
    } else if (input instanceof List<?> list) {
        for (Object item : list) {
            leaves.addAll(getAllLeafValues(item)); // Drill into List items
        }
    } else if (input != null) {
        // BASE CASE: If it's NOT a Map or List, it's a leaf!
        leaves.add(input); 
    }

    return leaves;
}
*/