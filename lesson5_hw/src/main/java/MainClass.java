import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Lock lock = new ReentrantLock();
        final CountDownLatch cdlFinish = new CountDownLatch(CARS_COUNT);
        CyclicBarrier cbStart = new CyclicBarrier(CARS_COUNT+1);
        Race race = new Race(new Road(60, cbStart,lock), new Tunnel(), new Road(40, cdlFinish));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            lock.lock();
            cbStart.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
        try {
            cdlFinish.await();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}