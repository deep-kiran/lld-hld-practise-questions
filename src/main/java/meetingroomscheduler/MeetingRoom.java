package meetingroomscheduler;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

public class MeetingRoom {
    private String meetingRoom;
    private TreeMap<Date, Date> meetingList;

    private int capacity;
    private boolean isBooked;

    MeetingRoom(){
        meetingList = new TreeMap<>();
    }

    public boolean canBookMeeting(Interval interval, int numberOfPersons){
        if(numberOfPersons> capacity){
            return false;
        }
        Date startOfPreviousMeeting = meetingList.floorKey(interval.getStartAtTime());
        Date endOfPreviousMeeting= meetingList.get(startOfPreviousMeeting);
        Date startOfNextMeeting = meetingList.ceilingKey(interval.getEndAtTime());
        if(Objects.isNull(endOfPreviousMeeting) || Objects.nonNull(endOfPreviousMeeting) && endOfPreviousMeeting.before(interval.getStartAtTime()) ){
            if(Objects.isNull(startOfNextMeeting) || interval.getEndAtTime().before(startOfNextMeeting)){
                return true;
            }
        }
        return false;
    }

    public String getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(String meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public TreeMap<Date, Date> getMeetingList() {
        return meetingList;
    }

    public void setMeetingList(TreeMap<Date, Date> meetingList) {
        this.meetingList = meetingList;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public void bookMeeting(Interval interval) {
        meetingList.put(interval.getStartAtTime(),interval.getEndAtTime());
    }
}
