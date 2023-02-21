import java.util.Map;
import java.util.TreeMap;

class Minplatforms {
	class Train {
		public int arrival, departure;
		public Train(int arrival, int departure) {
			this.arrival = arrival;
			this.departure = departure;
		}
	}

	
	int minPlatforms(Train[] trains) {
		// add your logic here
		
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < trains.length; i++) {
            map.put(trains[i].arrival, map.getOrDefault(trains[i].arrival, 0) + 1);
            map.put(trains[i].departure, map.getOrDefault(trains[i].departure, 0) - 1);
        }
        int platformCount = 0;
        int maxPlatformCount = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int change = entry.getValue();
            platformCount += change;
            maxPlatformCount = Math.max(maxPlatformCount, platformCount);
        }

        return maxPlatformCount;
	}
}