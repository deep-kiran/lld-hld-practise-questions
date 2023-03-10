package eventscheduler;

import meetingroomscheduler.Interval;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.TreeMap;

public class Room {
    private String roomId;
    private String buildingId;

    TreeMap<Date,Date> meetingList;
    public Room(String buildingId, String roomId){
        this.buildingId= buildingId;
        this.roomId = roomId;
    }

    public boolean isAvailable(Date startTime, Date endTime){
        Date startOfPreviousMeeting = meetingList.floorKey(startTime);
        Date endOfPreviousMeeting= meetingList.get(startOfPreviousMeeting);
        Date startOfNextMeeting = meetingList.higherKey(endTime);
        if(Objects.isNull(endOfPreviousMeeting) || Objects.nonNull(endOfPreviousMeeting) && endOfPreviousMeeting.before(startTime)){
            if(Objects.isNull(startOfNextMeeting) || endTime.before(startOfNextMeeting)){
                return true;
            }
        }
        return false;
    }

}