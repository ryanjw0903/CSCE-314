import java.util.Iterator;
import java.lang.Cloneable; 
public class MyList<E> implements Iterable<E>, Cloneable, Comparable<MyList<E>>{
    private MyNode<E> n;
    private int length;
    public MyList(){
        n = null;
        length =0;
    }
    public MyList(Iterable<E> iterable){
        for(E e : iterable){
            n = new MyNode<E>(e, n);
            length ++;
        }
        reverse();
    }
	public MyList<E> reverse(){
      MyList<E> t = new MyList<E>(); 
      if(this.n == null){
          return null;
      }
      for(E e: this){
            t.push(e);
      }
      return t;
    }
    public String toString(){
        boolean first = false;
        if(this.n == null){
            return "empty list";
        }
        String msg;
        msg = "[(head : ";
        for (E e : this) {
            if (first = false) {
                msg += e + ")";
                first = true;
            } else {
                msg += " ->" + "(" + e + ")";
            }
        }
        msg += "]";
        return msg;
    }
    public void push(E item){
        n = new MyNode<E>(item,n);
        length ++;
    }
    public E pop(){
        E local = n.getEl();
        n = n.getNext();
        length --;
        return local;
    }
    public E peek() {
        return n.getEl();
    }
    public int getLength() { return length; };
    public boolean equals(MyList<E> o){
        if(this.length == o.length){
            return true;
        }
        return false;
    }
    @Override
    public int hashCode(){
        return this.length;
    }
    public Iterator<E> iterator() {
        return n.iterator();
    }
    @Override
    public MyList<E> clone() 
    { 
        MyList<E> temp = new MyList<E>(this);
        return temp;
	}
    @Override
    public int compareTo(MyList<E> o) {
        return this.length - o.length;   
    }
}
 