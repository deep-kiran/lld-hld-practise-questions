import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterWithConcurrencyHandeling {
    private Deque<LocalDateTime> q = new LinkedList<>();
    private int capacity;
    private int timeToLiveInMinutes;
    // a hashmap where key is client id and value is datetime queue
    private ConcurrentHashMap<Integer, Deque> requests = new ConcurrentHashMap<>();

    public boolean rateLimit(int customerId)
    {
        //get queue for the specific customers
        Deque<LocalDateTime> q = requests.get(customerId);
        LocalDateTime currentTime = LocalDateTime.now();
        //if the threshold is not hit yet, append the request
        if(requests.get(customerId).size() < capacity){
            q.addLast(currentTime);
            requests.put(customerId, q);
            return true;
        }
        else{
            while(!q.isEmpty() && q.peekFirst().plusMinutes(timeToLiveInMinutes).isAfter(currentTime)){
                q.removeFirst();
            }
            if(requests.get(customerId).size() < capacity){
                q.addLast(currentTime);
                requests.put(customerId, q);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
