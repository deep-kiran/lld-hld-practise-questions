package callcenter;

public class Manager extends Employee {
    public Manager(String name, boolean free) {
        super(name, free );
        this.employeeType = EmployeeType.MANAGER;
    }
}