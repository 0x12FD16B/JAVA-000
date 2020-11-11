package course.java.assignment;

/**
 * 斐波拉契数列计算
 *
 * @author David Liu
 */
public class FibCalculator {

    private volatile static FibCalculator INST;

    private FibCalculator() {

    }

    public static FibCalculator getInst() {
        if (INST == null) {
            synchronized (FibCalculator.class) {
                if (INST == null) {
                    INST = new FibCalculator();
                }
            }
        }

        return INST;
    }

    public int fib(int num) {
        if (num < 2) {
            return 1;
        }
        int n1 = 1, n2 = 1;
        int r = 0;
        for (int i = 2; i <= num; i++) {
            r = n1 + n2;
            n1 = n2;
            n2 = r;
        }
        return r;
    }
}
