package eventscheduler;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface RoomService {
    
    /**
     * Returns a list of available rooms in the specified building for the given time slot.
     *
     * @param buildingId the ID of the building to search for available rooms in
     * @param startTime the start time of the time slot
     * @param endTime the end time of the time slot
     * @return a list of available rooms in the building for the specified time slot
     */
    List<Room> getAvailableRooms(String buildingId, Date startTime, Date endTime);
}
