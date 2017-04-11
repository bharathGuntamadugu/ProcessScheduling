import java.util.concurrent.BlockingQueue;

public class CreateProcess implements Runnable {
    BlockingQueue<Process> processQueue;
    public CreateProcess(BlockingQueue<Process> processQueue){
        this.processQueue = processQueue;
    }

    @Override
    public void run() {
        while(true){
            Process addNewOne = Process.createProcess();
            try {
                processQueue.put(addNewOne);
                Thread.sleep(1*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
