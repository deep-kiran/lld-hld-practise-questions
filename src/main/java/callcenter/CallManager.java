package callcenter;

import java.util.ArrayList;
import java.util.List;

public class CallManager {
    private static CallManager instance;
    List<Call> calls;
    private CallManager(){
        calls = new ArrayList<>();
    }
    public static CallManager getInstance() {
        if(instance == null){
            instance = new CallManager();
        }
        return instance;
    }

    public void placeCall(Customer customer ){
        Call call = new Call();
        call.setCustomer(customer);
        call.setRank(EmployeeType.FRESHER.getRank());
        calls.add(call);
    }
}
