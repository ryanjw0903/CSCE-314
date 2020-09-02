
import java.util.Iterator;
public final class MyNode<E> implements Iterable<E> { // class header needs to be modified also (see below)
    private E data; // data field must be private
    private MyNode<E> next; // next field must also be private
    public MyNode (E val, MyNode<E> node) {
        this.data = val;
        this. next = node;
     } //constructor
     //other necessary methods such as getter/setter and iterator() (see below)
    public E getEl(){
        return data;
    }
    public void setEl(E el){
        this.data = el;
    }
    public MyNode<E> getNext(){
        return next;
    }
    public void setNext(MyNode<E> next){
        this.next = next;
    }
    public Iterator<E> iterator() {
        return new MyNodeIterator<E>(this);
    }
    class MyNodeIterator<E> implements Iterator<E>{
        private MyNode<E> p;
        public MyNodeIterator (MyNode<E> n){p = n;}
        public boolean hasNext(){
           return p!=null;
       }
       public E next(){
           E data = p.getEl();
           p = p.getNext();
           return data;
       }
       public void remove(){
            throw new UnsupportedOperationException(); 
       }
    }
}
