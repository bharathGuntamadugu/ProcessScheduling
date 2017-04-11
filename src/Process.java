import java.util.Random;

public class Process {
    private static long count = 0;
    private long pID;
    private int priority;

    public long getpID() {
        return pID;
    }

    public int getPriority() {
        return priority;
    }

    public static Process createProcess(){
        Random ran = new Random();
        int val = ran.nextInt(100) + 1;
        Process newOne = new Process();
        newOne.pID = count++;
        newOne.priority = val;
        return newOne;
    }
}
