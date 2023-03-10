package meetingroomscheduler;

import java.util.Date;

public class Interval {
    private Date startAtTime;
    private Date endAtTime;

    public Date getStartAtTime() {
        return startAtTime;
    }

    public void setStartAtTime(Date startAtTime) {
        this.startAtTime = startAtTime;
    }

    public Date getEndAtTime() {
        return endAtTime;
    }

    public void setEndAtTime(Date endAtTime) {
        this.endAtTime = endAtTime;
    }
}
