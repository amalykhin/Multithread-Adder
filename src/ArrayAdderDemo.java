public class ArrayAdderDemo {
    //ArrayAdder need each minimum 100000 elements to sum in order to outperform the sequential sum.
    final static int ARR_LEN = 100_000_000;
    final static int ARR_DIV = 100_000;

    public static void main (String[] args) {
        long timeStart, timeEnd;
        ArraySum sum;
        int[] array;

        sum = new ArraySum(ARR_LEN);
        array = sum.getArray();

        System.out.println("Sequential sum:");
        timeStart = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            sum.addSum(array[i]);
        }
        timeEnd = System.currentTimeMillis();
        System.out.println("Sum: " + sum.getSum() + ". Time: " + (timeEnd-timeStart) + "ms");

        sum.setSum(0);
        System.out.println("Multithreaded sum:");
        timeStart = System.currentTimeMillis();
        ArrayAdder t = null;
        int i;
        try {
            for (i = 0; i < array.length/ARR_DIV; i++) {
                t = new ArrayAdder(sum, i*ARR_DIV, ARR_DIV);
                t.start();
                t.join();
            }
            t = new ArrayAdder(sum, i*ARR_DIV, array.length % ARR_DIV);
            t.start();
            t.join();
        }
        catch (InterruptedException E) { System.out.println("Thread "+t.getName()+" was interrupted"); }
        timeEnd = System.currentTimeMillis();
        System.out.println("Sum: " + sum.getSum() + ". Time: " + (timeEnd-timeStart) + "ms");

    }
}
