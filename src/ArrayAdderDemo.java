import java.util.Random;

public class ArrayAdderDemo {
    final static int ARR_LEN = 1000;
    final static int ARR_DIV = 10;
    static int sum = 0;
    static int[] array;

    public static void main (String[] args) {
        array = new int[ARR_LEN];
        Random rand = new Random();

        for (int i = 0; i < ARR_LEN; i++) {
            array[i] = rand.nextInt(1000);
            System.out.print(array[i]+" ");
        }
        System.out.println();

        for (int i = 0; i < ARR_LEN; i+=ARR_DIV) {
            ArrayAdder t = new ArrayAdder(i, ARR_DIV);
            t.start();
            try {
                t.join();
            }
            catch (InterruptedException E) {}
        }



        System.out.println(sum);
    }
}
