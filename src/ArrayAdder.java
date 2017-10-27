public class ArrayAdder extends Thread{
    private int offset;
    private int length;
    private int sum = 0;
    private ArraySum osum;

    ArrayAdder (ArraySum osum, int offset, int length) {
        this.offset = offset;
        this.length = length;
        this.osum = osum;
    }

    public void run () {
        int array[] = osum.getArray();

        for (int i = offset; i < offset+length; i++) {
            sum += array[i];
        }

        osum.addSum(sum);
    }

    public int getSum () {
        return sum;
    }
}
