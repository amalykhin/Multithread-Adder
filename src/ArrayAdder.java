public class ArrayAdder extends Thread{
    private int offset;
    private int length;
    private int sum;

    ArrayAdder (int offset, int length) {
       this.offset = offset;
       this.length = length;
    }

    public void run () {
        for (int i = offset; i < offset+length; i++) {
            sum += ArrayAdderDemo.array[i];
        }

        //System.out.println("Halfsum " + sum);
        ArrayAdderDemo.sum += sum;
    }
}
