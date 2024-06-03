package src.map;

import java.util.HashMap;
import java.util.Map;

public class _20240603_1415_FlattenNestedDictionary {

    // https://chatgpt.com/share/12084b8e-e12b-496a-b5d9-ffd49d329684
    // To flatten a nested dictionary in Java, you can create a recursive method that traverses the dictionary and constructs the flattened structure

    // Time Complexity : O(N)
    // Space Complexity : O(N + D)
    private static Map<String, Object> flatten(Map<String, Object> nestedDictionary) {
        Map<String, Object> flatMap = new HashMap<>();
        flattenHelper("", nestedDictionary, flatMap);
        return flatMap;
    }

    private static void flattenHelper(String prefix, Map<String, Object> nestedDictionary, Map<String, Object> flatMap) {

        for (Map.Entry<String, Object> entry : nestedDictionary.entrySet()) {

            String key = entry.getKey(); // a
            Object val = entry.getValue(); // 5
            String newKey = prefix.isBlank() ? key : prefix + "." + key; // example -> prefix : foo, key : a, newKey : foo.a

            if (val instanceof Map<?,?>) {
                @SuppressWarnings("unchecked")
                Map<String, Object> subMap = (Map<String, Object>) val;
                flattenHelper(newKey, subMap, flatMap);
            } else {
                flatMap.put(newKey, val); // WRONG : flatMap.put(key, val); - WRONG. we should put values in flatMap using only newKey. Dry run to understand it.
            }
        }
    }

    public static void main(String[] args) {
        Map<String, Object> nestedDict = new HashMap<>();
        nestedDict.put("key", 3);

        Map<String, Object> fooMap = new HashMap<>();
        fooMap.put("a", 5);

        Map<String, Object> barMap = new HashMap<>();
        barMap.put("baz", 8);
        fooMap.put("bar", barMap);

        nestedDict.put("foo", fooMap);

        Map<String, Object> flatMap = flatten(nestedDict);
        flatMap.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
