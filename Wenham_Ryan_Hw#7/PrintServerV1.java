import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
// Ryan Wenham 627002098
public class PrintServerV1 implements Runnable {
        private Lock requestChangeLock;
        private Condition requestCondition;
        private String title;
        private int id;
        private static final int DELAY = 1;
        private static final Queue<String> requests = new LinkedList<String>();
        public PrintServerV1(String s, int x) {
            requestChangeLock = new ReentrantLock();
            requestCondition = requestChangeLock.newCondition(); 
            id = x;
            title = s;
            new Thread(this).start();
            if(title != "manager"){
                printRequest(title);
            }
        }
        public void printRequest(String s) {
            requestChangeLock.lock();
            try{
                requests.add(s);
                requestCondition.signalAll();
            } finally{
                requestChangeLock.unlock();
            }

        }
        public void getRequest() throws InterruptedException{
            requestChangeLock.lock();
            try{
                while(requests.size() == 0){
                    requestCondition.await();
                }
                while(!requests.isEmpty()){
                    realPrint(requests.remove());
                }
                System.exit(0);
            } finally {
                requestChangeLock.unlock();
            }
        }
        public void run() { 
            try{
                getRequest();
            }catch (InterruptedException exception) {}
        }
        private void realPrint(String s) {
            System.out.println(s + " " + id + " " + getClass());
        }
} 
class Version1{
    public static void main(String[] args) { // for PrintServerV1
        // The following invocations of the constructor,
        // the first argument is title and the second argument is ID
        PrintServerV1 m = new PrintServerV1("manager", 1);
        PrintServerV1 c1 = new PrintServerV1("client1", 2);
        PrintServerV1 c2 = new PrintServerV1("client2", 3);
    }
}
     