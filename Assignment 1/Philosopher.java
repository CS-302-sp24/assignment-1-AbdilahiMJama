import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

class Philosopher extends Thread {
  private Chopstick right, left;
  private Random random;
  private int thinkCount;
  private int thinkTime;
  private int eatTime;
  private int cycles;
  private int id;
  private int hand;
  private FileWriter newFile;

  public Philosopher(Chopstick right, Chopstick left, int thinkTime, int eatTime, int cycles, int id, int hand, FileWriter newFile) {
    this.right = right; this.left = left;
    this.thinkTime = thinkTime;
    this.eatTime = eatTime;
    this.cycles = cycles;
    this.id = id;
    this.hand = hand;
    this.newFile = newFile;
    random = new Random();

    if (cycles == 0){
      thinkCount = Integer.MIN_VALUE;
    }
  }

  public void run() {
    try {
      // Right Handed Philosophers are designated as 0.
      if (hand == 0){
        while (thinkCount < cycles) {
          ++thinkCount;
          if (thinkCount % 10 == 0)
            newFile.write("Philosopher " + id + " has thought " + thinkCount + " times" + "\n");
          thinkTime = 1000;
          int tt = random.nextInt(thinkTime);
          newFile.write("Philosopher " + id + " thinks for " + tt + " units" + "\n");
          Thread.sleep(tt);     // Think for a while
          newFile.write("Philosopher " + id + " wants right chopstick"+ "\n");
          synchronized(right) {                    // Grab right chopstick
            newFile.write("Philosopher " + id + " has right chopstick"+ "\n");
            newFile.write("Philosopher " + id + " wants left chopstick"+ "\n");
            synchronized(left) {
              newFile.write("Philosopher " + id + " has left chopstick" + "\n");
              // Grab left chopstick
              eatTime = 1000;
              int et = random.nextInt((eatTime));
              newFile.write("Philosopher " + id + " eats for " + et + " units" + "\n");
              Thread.sleep(et); // Eat for a while
            }
            newFile.write("Philosopher " + id + " releases left chopstick" + "\n");
          }
          newFile.write("Philosopher " + id + " releases right chopstick" + "\n");
        }
      }

      // Start with even numbers of philosophers that are right-handed.
      else{
        // Right Handed Philosopher with even numbers.
        if (id % 2 == 0){
          while (thinkCount < cycles) {
            ++thinkCount;
            if (thinkCount % 10 == 0)
              newFile.write("Philosopher " + id + " has thought " + thinkCount + " times" + "\n");
            thinkTime = 1000;
            int tt = random.nextInt(thinkTime);
            newFile.write("Philosopher " + id + " thinks for " + tt + " units" + "\n");
            Thread.sleep(tt);     // Think for a while
            newFile.write("Philosopher " + id + " wants right chopstick"+ "\n");
            synchronized(right) {                    // Grab right chopstick
              newFile.write("Philosopher " + id + " has right chopstick"+ "\n");
              newFile.write("Philosopher " + id + " wants left chopstick"+ "\n");
              synchronized(left) {
                newFile.write("Philosopher " + id + " has left chopstick");
                eatTime = 1000;
                int et = random.nextInt((eatTime));

                newFile.write("Philosopher " + id + " eats for " + et + " units" + "\n");
                Thread.sleep(et); // Eat for a while
              }
              newFile.write("Philosopher " + id + " releases left chopstick" + "\n");
            }
            newFile.write("Philosopher " + id + " releases right chopstick" + "\n");

          }
        }
        // Left Handed Philosopher that are even.
        else if (id % 2 != 0){
          while (thinkCount < cycles) {
            ++thinkCount;
            if (thinkCount % 10 == 0)
              newFile.write("Philosopher " + id + " has thought " + thinkCount + " times" + "\n");
            thinkTime =  1000;
            int tt = random.nextInt(thinkTime);
            newFile.write("Philosopher " + id + " thinks for " + tt + " units" + "\n");
            Thread.sleep(tt);     // Think for a while

            newFile.write("Philosopher " + id + " wants left chopstick"+ "\n");
            synchronized(left) {                    // Grab left chopstick
              newFile.write("Philosopher " + id + " has left chopstick"+ "\n");
              newFile.write("Philosopher " + id + " wants right chopstick"+ "\n");
              synchronized(right) {
                newFile.write("Philosopher " + id + " has right chopstick"+ "\n");
                // Grab right chopstick
                eatTime = 1000;
                int et = random.nextInt((eatTime));

                System.out.println("Philosopher " + id + " eats for " + et + " units" + "\n");
                Thread.sleep(et); // Eat for a while
              }
              newFile.write("Philosopher " + id + " releases right chopstick" + "\n");
            }
            newFile.write("Philosopher " + id + " releases left chopstick" + "\n");
          }

        }
      }
    } catch(InterruptedException e) {} catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
