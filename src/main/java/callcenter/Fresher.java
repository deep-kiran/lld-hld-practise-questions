package callcenter;

public class Fresher extends Employee {
    public Fresher(String name, boolean free) {
        super(name, free);
        this.employeeType = EmployeeType.FRESHER;
    }
    public void escalateCall(Call call) {
        this.setFree(true);
        call.setRank(call.getRank()+1);
        CallHandler.getInstance().callHandler(call);
    }
}