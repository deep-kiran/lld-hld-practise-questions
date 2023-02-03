package ratelimiter;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class RateLimiter {
    private final int capacity;
    private final int timeToLiveInMinutes;
    // a hashmap where key is client id and value is datetime queue
    private HashMap<Integer, Deque<LocalDateTime>> requests = new HashMap<>();

    private final Map<Integer, ReentrantLock> locks = new HashMap<>();

    public RateLimiter(int capacity, int timeToLiveInMinutes){
        this.capacity = capacity;
        this.timeToLiveInMinutes = timeToLiveInMinutes;
    }

    public boolean rateLimit(int customerId) {
        ReentrantLock lock = locks.get(customerId);
        if (lock == null) {
            lock = new ReentrantLock();
            locks.put(customerId, lock);
        }
        lock.lock();
        try {
            //get queue for the specific customers

            Deque<LocalDateTime> deque = requests.get(customerId);
            if(Objects.nonNull(deque)){
                deque = new LinkedList<>();
            }
            LocalDateTime currentTime = LocalDateTime.now();
            while (!deque.isEmpty() && deque.peekFirst().plusMinutes(timeToLiveInMinutes).isAfter(currentTime)) {
                deque.removeFirst();
            }
            //if the threshold is not hit yet, append the request
            if (deque.size() >= capacity) {
                deque.removeFirst();
            }
            deque.addLast(currentTime);
            requests.put(customerId, deque);
            return true;
        }finally {
            lock.unlock();
        }
    }
}
