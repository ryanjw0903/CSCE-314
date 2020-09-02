import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
public class Time{
    private int sec = 0;
    private Lock timelock;
    private Condition timeCondition;
    public Time(){
        timelock = new ReentrantLock();
        timeCondition = timelock.newCondition();
    }
    public void second(){
        timelock.lock();
        try{ 
            sec++;
            System.out.print(sec + " ");
            timeCondition.signalAll();
        } finally{
            timelock.unlock();
        }
    }
    public void stop(int x) throws InterruptedException{
        timelock.lock();
        try{
            while(sec % x != 0){
                timeCondition.await();
            }
            if(x==5){
                System.out.println("\n--5 second message");
            } else if(x==11){
                System.out.println("\n**** 11 second message");
            }
        } finally{
            timelock.unlock();
        }
    }
    public static void main(String[] args) {
        Time counter = new Time();
        // counter is shared among all three runnables below
        TimePrinting tp = new TimePrinting(counter);
        MessagePrinting mp5 = new MessagePrinting(counter, 5);
        MessagePrinting mp11 = new MessagePrinting(counter, 11);
        }        
}
class MessagePrinting implements Runnable{
    private int val;
    private Time cc;
    private static final int DELAY = 1;
    public MessagePrinting(Time c, int num){
        val = num;
        cc = c;
        new Thread(this).start();
    }
    public void run() {
        try{
            for(int i = 0; i < 5; i ++){
                cc.stop(val);
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException exception) {}

    }
}
class TimePrinting implements Runnable{
    private Time cc;
    private static final int DELAY = 1;
    public TimePrinting(Time c){
        cc = c;
        new Thread(this).start();
    }
    public void run() {
        try{
            for(int i = 0; i < 25; i ++){
                cc.second();
                Thread.sleep(DELAY);
            }
            System.exit(0);
        } catch (InterruptedException exception) {}

    }
}