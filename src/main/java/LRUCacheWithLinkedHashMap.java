
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheWithLinkedHashMap {
    private LinkedHashMap<Integer,Integer> map;
    private int capacityCheck;
    private int capacity;

    public LRUCacheWithLinkedHashMap(int capacity) {
        map=new LinkedHashMap<>();
        capacityCheck=0;
        this.capacity=capacity;
    }
   
    public int get(int key) {
         if(map.containsKey(key))
            {
                int value=map.get(key);
                map.remove(key);
                map.put(key,value);
                return value;
            }
         else
            return -1;
    }
   
    public void set(int key, int value) {
        if(map.containsKey(key))
        {
            map.remove(key);
            map.put(key,value);

        }else {
              if(capacityCheck<capacity){
                    map.put(key,value);
                    capacityCheck++;
               } else{
                    removeFirstElement();
                    map.put(key,value);
               }
           
        }
    }
    private void removeFirstElement() {
       int mapFirstKey=-1;
       Iterator<Map.Entry<Integer,Integer>> it = map.entrySet().iterator();
       if(it.hasNext()) {
           mapFirstKey = it.next().getKey();
           map.remove(mapFirstKey);
       }
    }

    public static void main(String[] args) {

    }
}