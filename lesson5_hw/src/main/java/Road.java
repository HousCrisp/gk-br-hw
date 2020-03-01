import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Road extends Stage {
    CountDownLatch finish = new CountDownLatch(1);
    CyclicBarrier start = new CyclicBarrier(1);
    Lock lock = new ReentrantLock();
    boolean isFinish = false;
    static volatile Integer queue=0;
    public Road(int length, CyclicBarrier start, Lock lock) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
        this.start = start;
        this.lock = lock;
    }
    public Road(int length, CountDownLatch finish) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
        this.finish = finish;
        isFinish = true;

    }
    @Override
    public void go(Car c) {
        try {
            start.await();
            lock.tryLock();
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
            finish.countDown();
            if (isFinish && queue++ == 1) System.out.println(c.getName() + " WIN");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch(BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}