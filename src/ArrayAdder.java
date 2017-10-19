public class ArrayAdder extends Thread{
    private int offset;
    private int length;
    //private Integer sum;

    ArrayAdder (int offset, int length) {
       this.offset = offset;
       this.length = length;
    }

    public void run () {
        int sum = 0;
        for (int i = offset; i < offset+length; i++) {
            sum += ArrayAdderDemo.array[i];
        }

        //System.out.println("Halfsum " + sum);
        ArrayAdderDemo.sum.addAndGet(sum);
        /*synchronized (ArrayAdderDemo.sum) {
            ArrayAdderDemo.sum += sum;
        }*/
    }
}
