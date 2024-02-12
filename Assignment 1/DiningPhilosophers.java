import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DiningPhilosophers {

  public static void main(String[] args) throws InterruptedException, IOException {
    int np = Integer.parseInt(args[0]);
    int nc = Integer.parseInt(args[1]);
    int tt = Integer.parseInt(args[2]);
    int et = Integer.parseInt(args[3]);
    int rl = Integer.parseInt(args[4]);

    Philosopher[] philosophers = new Philosopher[np];
    Chopstick[] chopsticks = new Chopstick[np];

    /**
     * Check if the file already exists and override
     */
    try {
      File traceFile = new File("Trace.txt");
      if (traceFile.createNewFile()) {
        System.out.println("Created a file: " + traceFile.getName());
      }
      else {
        System.out.println("The file already exists");
        traceFile.delete();
        System.out.println("Created a new one");
        traceFile.createNewFile();
        System.out.println("Done.......");
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    FileWriter traceFileWriter = new FileWriter("Trace.txt");

    for (int i = 0; i < np; ++i)
      chopsticks[i] = new Chopstick(i);
    for (int i = 0; i < np; ++i) {
      philosophers[i] = new Philosopher(chopsticks[i], chopsticks[(i + 1) % np], i ,nc ,tt ,et ,rl, traceFileWriter);
      philosophers[i].start();
    }
    for (int i = 0; i < np; ++i)
      philosophers[i].join();
  }
}
