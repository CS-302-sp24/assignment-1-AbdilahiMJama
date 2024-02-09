public class Driver {
    private static int np;
    private static int nc;
    private static int tt;
    private static int et;
    private static int rl;

    public Driver(int np, int nc, int tt, int et, int rl) {
        this.np = np;
        this.nc = nc;
        this.tt = tt;
        this.et = et;
        this.rl = rl;
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 5) {
            System.out.println("Use: java Driver np nc tt et rl");
        }
        np = Integer.parseInt(args[0]);
        nc = Integer.parseInt(args[1]);
        tt = Integer.parseInt(args[2]);
        et = Integer.parseInt(args[3]);
        rl = Integer.parseInt(args[4]);

        if (rl != 0 && rl != 1) {
            System.out.println("Invalid!, Must be 0 or 1");
        }
        Philosopher[] philosophers = new Philosopher[5];
        Chopstick[] chopsticks = new Chopstick[5];

        for (int i = 0; i < 5; ++i)
            chopsticks[i] = new Chopstick(i);
        for (int i = 0; i < 5; ++i) {
            int leftChopstickIndex = i;
            int rightChopstickIndex = (i + 1) % np;
            Chopstick leftChopstick = chopsticks[leftChopstickIndex];
            Chopstick rightChopstick = chopsticks[rightChopstickIndex];

            if (rl == 0 || (rl == 1 && i % 2 == 0)) {
                philosophers[i] = new Philosopher(chopsticks[rightChopstickIndex], leftChopstick);
            } else   {
                philosophers[i] = new Philosopher(chopsticks[leftChopstickIndex], rightChopstick);
            }

            philosophers[i].start();
        }
        for (int i = 0; i < 5; ++i)
            philosophers[i].join();
    }
}
