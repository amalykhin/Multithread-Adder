import java.util.Random;

public class ArraySum {
    private int sum = 0;
    private int[] arr;

    ArraySum (int length) {
        Random rand = new Random();

        arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = rand.nextInt(10);
        }
    }

    synchronized int getSum () {
       return sum;
   }

    synchronized  void setSum (int sum) {
        this.sum = sum;
    }

    synchronized void addSum (int delta) {
        sum += delta;
    }

    int[] getArray () {
        return arr;
    }
}
