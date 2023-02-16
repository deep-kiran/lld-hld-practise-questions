package callcenter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CallManager {

    private static CallManager instance = null;

    private CallManager(){

    }
    public static CallManager getInstance() {
        if(instance ==null){
            instance = new CallManager();
        }
        return instance;
    }
    private static int levels = 3;
    private List<Employee>[] employeesList;
    private Queue<Call> callQueue;
    public CallManager(int numberOfFreshers, int numberOfLeads) {
        this.employeesList = new ArrayList[levels];
        this.callQueue = new LinkedList<>();

        // Create list of freshers
        List<Employee> freshersList = IntStream.range(0, numberOfFreshers)
                .mapToObj(i -> new Fresher("Fresher" + i, true))
                .collect(Collectors.toList());
        this.employeesList[0] = freshersList;

        // Create list of leads
        List<Employee> leadsList = IntStream.range(0, numberOfLeads)
                .mapToObj(i -> new Lead("Lead" + i, true))
                .collect(Collectors.toList());
        this.employeesList[1] = leadsList;

        // Create list of managers
        List<Employee> managersList = Stream.of(new Manager("Manager", true))
                .collect(Collectors.toList());
        this.employeesList[2] = managersList;
    }

    public void callHandler(Call call) {
        Optional<Employee> optionalEmployee = this.getFreeEmployee(call.getRank());
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            call.setRank(employee.getRank());
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
        return IntStream.range(rank-1, levels)
                .mapToObj(i -> employeesList[i])
                .flatMap(Collection::stream)
                .filter(Employee::isFree)
                .findFirst();
    }

}
