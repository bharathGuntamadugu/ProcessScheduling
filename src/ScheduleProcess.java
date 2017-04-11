import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class ScheduleProcess implements Runnable {

    BlockingQueue<Process> highPriority;
    BlockingQueue<Process> midPriority;
    BlockingQueue<Process> lowPriority;

    public ScheduleProcess(BlockingQueue<Process> highPriority,
                           BlockingQueue<Process> midPriority,
                           BlockingQueue<Process> lowPriority)
    {
        this.highPriority = highPriority;
        this.midPriority = midPriority;
        this.lowPriority = lowPriority;
    }
    @Override
    public void run() {
        while(true) {
            Random ran = new Random();
            int randomPriority = ran.nextInt(10) + 1;
            Process runningProcess = null;
            try {
                //50% chances of picking here
                if (randomPriority % 2 == 0) {
                    runningProcess = highPriority.take();
                }
                //20% chances of picking here
                else if (randomPriority % 3 == 0) {
                    runningProcess = lowPriority.take();
                }
                //%30% chances of picking here
                else {
                    runningProcess = midPriority.take();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Running process id : " + runningProcess.getpID() + ". Priority : " + runningProcess.getPriority());
        }
    }
}
