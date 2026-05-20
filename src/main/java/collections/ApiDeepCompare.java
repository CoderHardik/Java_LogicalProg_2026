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
