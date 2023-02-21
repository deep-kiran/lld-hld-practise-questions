import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

class KeyValueStore {
    private Map<String, Map<String, Object>> store;

    public KeyValueStore() {
        this.store = new ConcurrentHashMap<>();
    }

    public Map<String, Object> get(String key) {
        return store.get(key);
    }

    public List<String> search(String attributeKey, String attributeValue) {
        if(!store.isEmpty()) {
            return store.entrySet().stream()
                    .filter(entry -> entry.getValue().containsKey(attributeKey) &&
                            entry.getValue().get(attributeKey).equals(attributeValue))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        }
        return null;
    }

    public void put(String key, List<Map.Entry<String, Object>> listOfAttributePairs) {
        Map<String, Object> attributes = new HashMap<>();
        listOfAttributePairs.forEach(pair -> attributes.put(pair.getKey(), pair.getValue()));
        store.put(key, attributes);
    }

    public void delete(String key) {
        store.remove(key);
    }

    public List<String> keys() {
        return store.keySet().stream().collect(Collectors.toList());
    }
}
