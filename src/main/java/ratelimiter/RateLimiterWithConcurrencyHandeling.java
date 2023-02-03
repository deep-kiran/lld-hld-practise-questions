package ratelimiter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterWithConcurrencyHandeling {
    private int capacity;
    private int timeToLiveInMinutes;
    // a hashmap where key is client id and value is datetime queue
    private ConcurrentHashMap<Integer, Deque<LocalDateTime>> requests = new ConcurrentHashMap<>();

    public boolean rateLimit(int customerId)
    {
        //get queue for the specific customers
        Deque<LocalDateTime> deque = requests.get(customerId);
        LocalDateTime currentTime = LocalDateTime.now();
        while(!deque.isEmpty() && deque.peekFirst().plusMinutes(timeToLiveInMinutes).isAfter(currentTime)){
            deque.removeFirst();
        }
        //if the threshold is not hit yet, append the request
        if(deque.size() == capacity){
            deque.removeFirst();
        }
        deque.addLast(currentTime);
        requests.put(customerId, deque);
        return true;
    }


}
