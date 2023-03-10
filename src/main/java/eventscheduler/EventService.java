package eventscheduler;

import java.util.Date;
import java.util.List;

public interface EventService {
    
    /**
     * Creates a new event for the user
     * @param event the event to create
     * @return true if the event is created successfully, false otherwise
     */
    boolean createEvent(Event event);
    
    /**
     * Updates an existing event for the user
     * @param event the event to update
     * @return true if the event is updated successfully, false otherwise
     */
    boolean updateEvent(Event event);
    
    /**
     * Cancels an existing event for the user
     * @param eventId the ID of the event to cancel
     * @return true if the event is cancelled successfully, false otherwise
     */
    boolean cancelEvent(long eventId);
    
    /**
     * Fetches the list of events for the user for the given date range
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return the list of events for the user for the given date range
     */
    List<Event> getEvents(Date startDate, Date endDate);
    
    /**
     * Fetches the details of invitees for the given meeting ID
     * @param meetingId the ID of the meeting
     * @return the list of invitees with their responses (ACCEPT, DECLINE etc.)
     */
//    List<InviteeResponse> getInviteeDetails(long meetingId);
}
