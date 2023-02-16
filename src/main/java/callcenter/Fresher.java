package callcenter;

public class Fresher extends Employee {
    public Fresher(String name, boolean free) {
        super(name, free);
        this.rank = 1;
    }
    public void escalateCall(Call call) {
        this.setFree(true);
        call.setRank(call.getRank()+1);
        CallManager.getInstance().callHandler(call);
    }
}