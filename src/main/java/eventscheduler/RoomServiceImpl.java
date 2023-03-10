package eventscheduler;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RoomServiceImpl implements RoomService {

    Predicate<List<Room>> predicate = List::isEmpty;
    private final Map<String, List<Room>> buildingRoomsMap;

    public RoomServiceImpl() {
        // initialize the buildingRoomsMap with sample data
        buildingRoomsMap = new HashMap<>();
        buildingRoomsMap.put("building1", Arrays.asList(
            new Room("room1", "10"),
            new Room("room2", "8"),
            new Room("room3", "12")
        ));
        buildingRoomsMap.put("building2", Arrays.asList(
            new Room("room4", "15"),
            new Room("room5", "20"),
            new Room("room6", "25")
        ));
    }

    @Override
    public List<Room> getAvailableRooms(String buildingId, Date startTime, Date endTime) {
        // retrieve the list of rooms for the specified building
        List<Room> rooms = buildingRoomsMap.get(buildingId);
        if (predicate.test(rooms)) {
            throw new IllegalArgumentException("Building with ID " + buildingId + " not found.");
        }

        // filter the list of rooms to only include those that are available for the specified time slot
        List<Room> availableRooms =rooms.stream()
                .filter(room -> room.isAvailable(startTime, endTime))
                .collect(Collectors.toList());

        return availableRooms;
    }
}
