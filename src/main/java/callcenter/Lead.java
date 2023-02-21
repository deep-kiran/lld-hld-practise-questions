package callcenter;

public class Lead extends Employee {
    public Lead(String name, boolean free) {
        super(name, free);
        this.employeeType = EmployeeType.LEAD;
    }
    public void escalateCall(Call call) {
        this.setFree(true);
        call.setRank(call.getRank()+1);
        CallHandler.getInstance().callHandler(call);
    }
}