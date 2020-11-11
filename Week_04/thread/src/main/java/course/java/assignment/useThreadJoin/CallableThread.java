package course.java.assignment.useThreadJoin;

import course.java.assignment.FibCalculator;

/**
 * 维护一个返回值变量
 *
 * @author David Liu
 */
public class CallableThread extends Thread {

    private Object result;

    @Override
    public void run() {
        result = FibCalculator.getInst().fib(35);
    }

    public Object getResult() {
        return result;
    }
}
