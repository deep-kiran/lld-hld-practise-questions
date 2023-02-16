package callcenter;

public abstract class Employee {
    private String name;
    private boolean free;
    protected int rank;
    public Employee(String name, boolean free) {
        this.name = name;
        this.free = free;
    }
    public void receiveCall(Call call) {
        this.free = false;
        System.out.println("Call received by employee "+ this.name + " for customer " + call.getCustomer().getName());
    }
    public void endCall(Call call) {
        System.out.println("Call ended by employee "+ this.name + " for customer " + call.getCustomer().getName());
        this.free = true;
        CallManager.getInstance().callReceived();
    }
    public boolean isFree() {
        return free;
    }
    public void setFree(boolean free) {
        this.free = free;
    }
    public int getRank() {
        return rank;
    }
}