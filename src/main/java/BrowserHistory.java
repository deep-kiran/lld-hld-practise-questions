
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

class BrowserHistory {
    
    private Deque<String> backHistory; // stack to contain current webpage and all its back pages, which means webpage on top of this stack is the current webpage we are at
    private Deque<String> forwardHistory; // stack to store all forward webpages of the current webpage we are at
    

    public BrowserHistory(String homepage) {
        backHistory = new ArrayDeque<>();
        forwardHistory = new ArrayDeque<>();
        backHistory.push(homepage);
    }
    
    public void visit(String url) {
        backHistory.push(url);  
        forwardHistory.clear(); // clear all forward history
    }
    
    public String back(int steps) {
        while (!backHistory.isEmpty() && steps > 0) {
            forwardHistory.push(backHistory.pop());
            steps--;
        }
        if (backHistory.isEmpty()) {
            backHistory.push(forwardHistory.pop());
        }
        return backHistory.peek();
    }
    
    public String forward(int steps) {
        while (!forwardHistory.isEmpty() && steps > 0) {
            backHistory.push(forwardHistory.pop());
            steps--;
        }
        return backHistory.peek();
    }


    @Test
    public static void testForwardAndBackWardFunctionality(){
        String webpage2 = "https://www.google.com/images/search=flights&booking";
        String webpage3 = "https://www.google.com/images/search=trains&booking";
        String webpage4 = "https://www.google.com/images/search=hotels&booking";
        String webpage5 = "https://www.google.com/images/search=cabs&booking";
        String webpage6 = "https://www.google.com/images/search=restaurants&booking";
        BrowserHistory browserHistory = new BrowserHistory("https://www.google.com");
        browserHistory.visit(webpage2);
        browserHistory.visit(webpage3);
        browserHistory.visit(webpage4);
        browserHistory.visit(webpage5);
        browserHistory.visit(webpage6);
        Assert.assertEquals(webpage4, (String) (browserHistory.back(2)));
    }


    @Test
    public static void testForwardAndBackWardFunctionalityWhenBackStepsAreMore(){
        String homePage = "https://www.google.com";
        String webpage2 = "https://www.google.com/images/search=flights&booking";
        String webpage3 = "https://www.google.com/images/search=trains&booking";
        String webpage4 = "https://www.google.com/images/search=hotels&booking";
        String webpage5 = "https://www.google.com/images/search=cabs&booking";
        String webpage6 = "https://www.google.com/images/search=restaurants&booking";
        BrowserHistory browserHistory = new BrowserHistory(homePage);
        browserHistory.visit(webpage2);
        browserHistory.visit(webpage3);
        browserHistory.visit(webpage4);
        browserHistory.visit(webpage5);
        browserHistory.visit(webpage6);
        Assert.assertEquals(homePage, (String) (browserHistory.back(7)));
    }

    public static void main(String[] args) {
        testForwardAndBackWardFunctionality();
        testForwardAndBackWardFunctionalityWhenBackStepsAreMore();
    }
}
