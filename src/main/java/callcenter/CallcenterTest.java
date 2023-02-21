package callcenter;

import org.junit.Test;

public class CallcenterTest {

    CallManager callManager;
    CallHandler callHandler;

    @Test
    public void addCustomerCall(){
        Customer customer = new Customer("c1","c2@gmail.com",2342);
        callManager = CallManager.getInstance();
        callManager.placeCall(customer);
        callHandler = CallHandler.getInstance();
        callHandler.getFreeEmployee(EmployeeType.FRESHER.getRank());
    }
}
