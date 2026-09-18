/*
Problem Statement:
Imagine you are testing a migration. The old service and the new service both output JSON. 
Write a Java/Python method using Maps and recursion to perform a deep comparison

Explain main logic:
1.
if (val1 instanceof Map && val2 instanceof Map) {

The Purpose: instanceof is a Java keyword that checks the data type of an object at runtime.
The JSON Connection: In Java, a JSON object { "key": "value" } is parsed and stored as a Map.
What it is doing here: This line asks: "Are both val1 and val2 actually nested JSON objects (Maps) themselves?"
Example: If your JSON looks like { "user": { "name": "Alice" } }, the value for the key "user" is another JSON object. 
instanceof Map evaluates to true.

2.
if (!deepCompare((Map<String, Object>) val1, (Map<String, Object>) val2)) {
    return false;
}

The Casting (Map<String, Object>): Because val1 and val2 were originally stored as generic Object types, 
Java needs you to explicitly cast them back to Map before passing them into the function.

The Recursion: If both values are maps, the method calls itself (deepCompare). 
It restarts the comparison process for this inner, nested layer.

The Return: If this inner comparison finds any mismatch and returns false, the parent function immediately stops and returns false too.

Step-by-Step Execution LifecyclePhase 1: The Size Gatekeeperjavaif (map1.size() != map2.size()) return false;
Use code with caution.Before wasting time scanning data, the program compares the total number of entries in map1 and map2.If one map holds 5 items and the other holds 4, they cannot be identical. The method exits instantly with false.Phase 2: The Key Gatekeeper (The Loop)javafor (String key : map1.keySet()) {
    if (!map2.containsKey(key)) return false;
Use code with caution.Why it only loops map1 keys: Because Phase 1 already proved both maps are the exact same size, the loop only needs to iterate through the keys of map1. If every key in map1 is also found inside map2, it is mathematically impossible for map2 to have any extra keys.The Gatekeeper Line: if (!map2.containsKey(key)) acts as a shield. It checks if the current key exists in map2. If it is missing, it aborts immediately.Phase 3: Safe Extraction & Value RoutingjavaObject val1 = map1.get(key);
Object val2 = map2.get(key);
Use code with caution.Because the key gatekeeper just proved that this specific key exists in both maps, it is 100% safe to fetch the values (val1 and val2) from both maps simultaneously. No NullPointerException risks.The code then routes these values into one of two paths: The Recursive Path (for sub-maps) or The Flat Comparison Path (for standard data).Phase 4: Path A — The Recursive Drill-Downjavaif (val1 instanceof Map && val2 instanceof Map) {
    if (!deepCompare((Map<String, Object>) val1, (Map<String, Object>) val2)) {
        return false;
    }
}
Use code with caution.If both values are themselves maps, the code forces Java to treat them as such via type casting (Map<String, Object>).It then re-calls itself (deepCompare), passing in these sub-maps as a fresh set of inputs. This fires up a brand new, isolated layer of checks (checking their sizes, keys, and values).If that inner layer returns false (a mismatch was found deep inside), the ! (NOT) operator flips it to true, triggering the if block to abort the entire program and return false.Phase 5: Path B — The Flat Comparisonjavaelse if (!Objects.equals(val1, val2)) {
    return false;
}
Use code with caution.If the values are standard data types (like Strings, Integers, or booleans) instead of maps, it evaluates them using Objects.equals(). This utility method safely handles equality comparisons even if one or both values happen to be null.Phase 6: Total Validation Passjava    }
    return true;
}
Use code with caution.If the loop finishes verifying every single key, nested sub-map, and individual flat value without ever hitting a mismatch, it safely reaches the very end of the method and returns true.


*/


public static boolean deepCompare(Map<String, Object> map1, Map<String, Object> map2) {
    if (map1.size() != map2.size()) return false;

    for (String key : map1.keySet()) {
        if (!map2.containsKey(key)) return false;

        Object val1 = map1.get(key);
        Object val2 = map2.get(key);

        if (val1 instanceof Map && val2 instanceof Map) {
            // Recursive call for nested maps
            if (!deepCompare((Map<String, Object>) val1, (Map<String, Object>) val2)) {
                return false;
            }
        } else if (!Objects.equals(val1, val2)) {
            return false;
        }
    }
    return true;
}
