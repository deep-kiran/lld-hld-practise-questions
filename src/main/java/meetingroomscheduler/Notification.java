package meetingroomscheduler;

import java.util.Date;

public class Notification {
  private int notificationId;
  private String content;
  private Date creationDate;

  public boolean sendNotification(User user){
    return true;
  }
  public boolean cancelNotification(User user){
    return true;
  }
}