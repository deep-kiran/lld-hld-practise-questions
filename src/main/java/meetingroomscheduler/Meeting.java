package meetingroomscheduler;

import java.util.Date;
import java.util.List;

public class Meeting {

    private String agenda;
    private Interval interval;

    private List<User> participants;

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }
}
