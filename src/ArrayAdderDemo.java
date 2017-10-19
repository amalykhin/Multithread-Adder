import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ArrayAdderDemo {
    //Interesting thing:
    //those numbers are about minimum values for multithreaded solution to run faster.
    //Also, sometimes sums don't match (5 out of 10 times).
    final static int ARR_LEN = 100_000_000;
    final static int ARR_DIV = 100_000;
    static AtomicInteger sum;
    //static Integer sum;
    static int[] array;

    public static void main (String[] args) {
        long timeStart, timeEnd;
        array = new int[ARR_LEN];
        Random rand = new Random();

        for (int i = 0; i < ARR_LEN; i++) {
            array[i] = rand.nextInt(10);
            //System.out.print(array[i]+" ");
        }
        System.out.println();

        System.out.println("Sequential sum:");
        timeStart = System.currentTimeMillis();
        sum = new AtomicInteger(0);
        //sum = 0;
        for (int i = 0; i < ARR_LEN; i++) {
            sum.addAndGet(array[i]);
            //sum += array[i];
        }
        timeEnd = System.currentTimeMillis();
        System.out.println("Sum: " + sum + ". Time: " + (timeEnd-timeStart) + "ms");

        System.out.println("Multithreaded sum:");
        timeStart = System.currentTimeMillis();
        ArrayAdder t = null;
        sum = new AtomicInteger(0);
        //sum = 0;
        int i;
        for (i = 0; i+ARR_DIV < ARR_LEN; i+=ARR_DIV) {
            t = new ArrayAdder(i, ARR_DIV);
            t.start();
        }
        try {
            t = new ArrayAdder(i, ARR_LEN - i);
            t.start();
            t.join();
        }
        catch (InterruptedException E) {}
        timeEnd = System.currentTimeMillis();
        System.out.println("Sum: " + sum + ". Time: " + (timeEnd-timeStart) + "ms");

    }
}
