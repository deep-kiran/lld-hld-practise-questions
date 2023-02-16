package callcenter;

public enum EmployeeType {
    FRESHER(1),
    LEAD(2),
    MANAGER(3);
    private int rank;
    EmployeeType(int rank){
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }
    public static EmployeeType getEmployeeType(int rank){
        for (EmployeeType employeeType : EmployeeType.values()){
            if(employeeType.getRank()==rank){
                return employeeType;
            }
        }
        return null;
    }
}
