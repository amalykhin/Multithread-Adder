import java.util.Random;

public class ArrayAdderDemo {
    final static int ARR_LEN = 100_000_000;
    final static int ARR_DIV = 1000;
    static Integer sum = 0;
    static int[] array;

    public static void main (String[] args) {
        long timeStart, timeEnd;
        array = new int[ARR_LEN];
        Random rand = new Random();

        for (int i = 0; i < ARR_LEN; i++) {
            array[i] = rand.nextInt(1000);
            //System.out.print(array[i]+" ");
        }
        System.out.println();

        System.out.println("Multithreaded sum:");
        timeStart = System.currentTimeMillis();
        ArrayAdder t = null;
        for (int i = 0; i < ARR_LEN; i+=ARR_DIV) {
            t = new ArrayAdder(i, ARR_DIV);
            t.start();
        }
        try {
            t.join();
        }
        catch (InterruptedException E) {}
        timeEnd = System.currentTimeMillis();
        System.out.println("Sum: " + sum + ". Time: " + (timeEnd-timeStart) + "ms");

        System.out.println("Sequential sum:");
        timeStart = System.currentTimeMillis();
        for (int i = 0, sum = 0; i < ARR_LEN; i++)
            sum += array[i];
        timeEnd = System.currentTimeMillis();
        System.out.println("Sum: " + sum + ". Time: " + (timeEnd-timeStart) + "ms");
    }
}
