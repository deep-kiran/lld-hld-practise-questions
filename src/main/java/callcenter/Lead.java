package callcenter;

public class Lead extends Employee {
    public Lead(String name, boolean free) {
        super(name, free);
        this.rank = 2;
    }
    public void escalateCall(Call call) {
        this.setFree(true);
        call.setRank(call.getRank()+1);
        CallManager.getInstance().callHandler(call);
    }
}