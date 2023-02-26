package meetingroomscheduler;

import java.util.List;
import java.util.Optional;

public class MeetingsOrchestrator {
        private User organizer;
        private Calendar calendar;
        private List<MeetingRoom> rooms;

        // Scheduler is a singleton class that ensures it will have only one active instance at a time
        private static MeetingsOrchestrator scheduler = null;

        // Created a static method to access the singleton instance of Scheduler class
        public static MeetingsOrchestrator getInstance() {
            if (scheduler == null) {
                scheduler = new MeetingsOrchestrator();
            }
            return scheduler;
        }

        public boolean scheduleMeeting(List<User> users, Interval interval){
                return true;
        }
        public boolean cancelMeeting(List<User> users, Interval interval){
            return true;
        }
        public boolean bookRoom(MeetingRoom room, int numberOfPersons, Interval interval){
                if(room.canBookMeeting(interval, numberOfPersons)){
                        room.setBooked(true);
                        room.bookMeeting(interval);
                        return true;
                }
                return false;
        }
        public boolean releaseRoom(MeetingRoom room, Interval interval){
            return true;
        }
        public MeetingRoom checkRoomsAvailability(int numberOfPersons, Interval interval){
            Optional<MeetingRoom> meetingRoomOptional = getRooms().stream().filter(room -> !room.isBooked())
                    .filter(room -> room.canBookMeeting(interval,numberOfPersons))
                    .findFirst();
            return meetingRoomOptional.isPresent() ? meetingRoomOptional.get() : null;
        }

        public List<MeetingRoom> getRooms() {
                return rooms;
        }

        public void setRooms(List<MeetingRoom> rooms) {
                this.rooms = rooms;
        }
}