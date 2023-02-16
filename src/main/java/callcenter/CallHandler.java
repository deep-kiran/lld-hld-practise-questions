package callcenter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CallHandler {

    private static CallHandler instance = null;

    private CallHandler(){
        initializeCallHandler(10,5);
    }
    public static CallHandler getInstance() {
        if(instance ==null){
            instance = new CallHandler();
        }
        return instance;
    }
    private static int levels = 3;
    private HashMap<EmployeeType,List<Employee>> employeeTypeListHashMap;
    private Queue<Call> callQueue;

    public void initializeCallHandler(int numberOfFreshers, int numberOfLeads) {
        this.employeeTypeListHashMap = new HashMap<>();
        this.callQueue = new LinkedList<>();

        // Create list of freshers
        List<Employee> freshersList = IntStream.range(0, numberOfFreshers)
                .mapToObj(i -> new Fresher("Fresher" + i, true))
                .collect(Collectors.toList());
        this.employeeTypeListHashMap.put(EmployeeType.FRESHER,freshersList) ;

        // Create list of leads
        List<Employee> leadsList = IntStream.range(0, numberOfLeads)
                .mapToObj(i -> new Lead("Lead" + i, true))
                .collect(Collectors.toList());
        this.employeeTypeListHashMap.put(EmployeeType.LEAD,leadsList);

        // Create list of managers
        List<Employee> managersList = Stream.of(new Manager("Manager", true))
                .collect(Collectors.toList());
        this.employeeTypeListHashMap.put(EmployeeType.MANAGER,managersList);
    }

    public void callHandler(Call call) {
        Optional<Employee> optionalEmployee = this.getFreeEmployee(call.getRank());
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            call.setRank(employee.employeeType.getRank());
            employee.receiveCall(call);
        } else {
            callQueue.add(call);
        }
    }
    public void handleCallFromQueue() {
        if (callQueue.size() > 0) {
            Call call = callQueue.peek();
            int callRank = call.getRank();
            Optional<Employee> employee = getFreeEmployee(callRank);
            if (employee.isPresent()) {
                callQueue.remove();
                employee.get().receiveCall(call);
            }
        }
    }
    public void callReceived() {
        this.handleCallFromQueue();
    }

    public Optional<Employee> getFreeEmployee(int rank) {
        for (int i = rank; i< levels+1; i++) {
            List<Employee> employees  = employeeTypeListHashMap.get(EmployeeType.getEmployeeType(i));
            Optional<Employee> employee = employees.stream().filter(Employee::isFree).findFirst();
            if(employee.isPresent()){
                return employee;
            }
        }
        return Optional.ofNullable(null);
    }

}
