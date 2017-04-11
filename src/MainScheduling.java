import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MainScheduling {

    public static void main(String[] args) {
        MainScheduling.runSimulation();
    }


    private final static int HIGHPRIORITYLEVEL = 60;
    private final static int MIDPRIORITYLEVEL = 30;
    private final static int LOWPRIORITYLEVEL = 1;

    public static void runSimulation(){
        BlockingQueue<Process> processQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Process> highPriority = new LinkedBlockingQueue<>();
        BlockingQueue<Process> midPriority = new LinkedBlockingQueue<>();
        BlockingQueue<Process> lowPriority = new LinkedBlockingQueue<>();

        Thread processCreateThread = new Thread(new CreateProcess(processQueue));
        processCreateThread.start();

        Thread schedulingThread = new Thread(new ScheduleProcess(highPriority, midPriority, lowPriority));
        schedulingThread.start();

        while(true) {
            try {
                Process current = processQueue.take();
                if (current.getPriority() >= HIGHPRIORITYLEVEL) {
                    highPriority.put(current);
                } else if (current.getPriority() >= MIDPRIORITYLEVEL) {
                    midPriority.put(current);
                } else if (current.getPriority() >= LOWPRIORITYLEVEL) {
                    lowPriority.put(current);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
