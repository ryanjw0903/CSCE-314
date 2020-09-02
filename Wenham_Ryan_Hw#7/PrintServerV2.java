import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
// Ryan Wenham 627002098
public class PrintServerV2 implements Runnable {
        private Lock requestChangeLock;
        private Condition requestCondition;
        private String title;
        private int id;
        private static final Queue<String> requests = new LinkedList<String>();
        public PrintServerV2(String s, int x) {
            requestChangeLock = new ReentrantLock();
            requestCondition = requestChangeLock.newCondition(); 
            id = x;
            title = s;
            new Thread(this).start();
        }
        public synchronized void printRequest(String s) {
                requests.add(s);
                notifyAll();
            }
        public synchronized void getRequest() throws InterruptedException{
                while(requests.size() == 0){
                    wait();
                }
                while(!requests.isEmpty()){
                    realPrint(requests.remove());
                }
                System.exit(0);
        }
        public void run() {
            try{
                if(title != "manager"){
                    printRequest(title);
                }
                getRequest();
            }catch (InterruptedException exception){}
        }
        private void realPrint(String s) {
            System.out.println(s + " " + id + " " + getClass());
        }
} 
class Version2{
    public static void main(String[] args) { // for PrintServerV1
        // The following invocations of the constructor,
        // the first argument is title and the second argument is ID
        PrintServerV2 m = new PrintServerV2("manager", 1);
        PrintServerV2 c1 = new PrintServerV2("client1", 2);
        PrintServerV2 c2 = new PrintServerV2("client2", 3);
    }
        
}
     