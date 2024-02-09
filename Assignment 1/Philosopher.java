import java.util.Random;

class Philosopher extends Thread {
  private Chopstick first, second;
  private Random random;
  private int thinkCount;
  private int eatCount;
  private int id;

  public Philosopher(Chopstick left, Chopstick right) {
    if (left.getId() > right.getId()) {
      this.first = right; this.second = left;
    }else {
      this.first = left; this.second = right;
    }

    random = new Random();
  }

  public void run() {
    try {
      while(true) {
        ++thinkCount;
        ++eatCount;
        ++id;
        //if (thinkCount % 10 == 0)
        System.out.println("Philosopher " + id  + " thinks " + thinkCount + " units");
        Thread.sleep(random.nextInt(1000));     // Think for a while
        synchronized(first) {
          // Grab right chopstick
          System.out.println("Philosopher " + id  + " wants right chopstick");
          System.out.println("Philosopher " + id  + " has right chopstick");
          synchronized(second) {
            // Grab left chopstick
            System.out.println("Philosopher " + id  + " wants left chopstick");
            System.out.println("Philosopher " + id + " has left chopstick");
            Thread.sleep(random.nextInt(1000)); // Eat for a while
          }

          System.out.println("Philosopher " + id + " eats for " + eatCount + " units");
          Thread.sleep(random.nextInt(1000));
          System.out.println("Philosopher " + id + " releases left chopstick");
          System.out.println("Philosopher " + id  + " releases right chopstick");
        }
      }
    } catch(InterruptedException e) {}
  }
}
