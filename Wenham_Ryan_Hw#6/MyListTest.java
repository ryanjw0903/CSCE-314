/* CSCE 314 [Sections 500, 501] Programming Languages Spring 2020
   Homework 6 Problem 2
   class MyListTest: Feel free to "expand" it, but keep the original contents 
                     (i.e., do not completely change the main() method)
   Written by Hyunyoung Lee
*/

import java.util.Iterator;
import java.util.Arrays;
import static java.lang.System.out; 
class Tester {
  public static void test (boolean cond) { test(cond, "unnamed"); }
  public static void test (boolean cond, String testname) {
    String msg = testname + " ";
    if (cond) msg = msg + "OK"; else msg = msg + "failed!";
    out.println(msg);
  }
}

public class MyListTest {
  private static int sum (Iterable<Integer> n) {
    int sum = 0;
    for (int e : n) { sum += e; }
    return sum;
  }

  public static void main (String args[]) {
    MyList<Integer> empty_list = new MyList<Integer>();
    MyList<Integer> list = 
        new MyList<Integer>(Arrays.asList(1,2,3,4));
    MyList<Integer> list1 = 
        new MyList<Integer>(Arrays.asList(2,4,3,1));
    MyList<Integer> list2 = new MyList<Integer>(list.clone()); 

    MyList<Integer> list3 = 
        new MyList<Integer>(Arrays.asList(1,2,3,1));
    MyList<Integer> list4 = 
        new MyList<Integer>(Arrays.asList(1,2,3,1,4));

    out.println("list  = " + list);
    out.println("list1 = " + list1);
    
    if (list == list1) out.println("list == list1 is true");
    else out.println("list == list1 is false");
    
    out.println("list.equals(list1) = " + list.equals(list1));
    out.println("list3 = " + list3);
    out.println("list4 = " + list4);
    out.println("list1.equals(list3) = " + list1.equals(list3));
    out.println("list1.equals(list4) = " + list1.equals(list4));
    out.println("list.compareTo(list1) = " + list.compareTo(list1));
    out.println("list.compareTo(list4) = " + list.compareTo(list4));
    Tester.test(sum(list)==10, "sum==10");
    out.println(empty_list);
    out.println(empty_list.reverse()); 
    out.println(list);
    out.println(list.reverse());
    out.println(list.reverse());
    out.println(list.pop());
    list.push(21);
    list.push(22);
    out.println(list);
    out.println(list.peek());
    out.println(list);
    for (int i : list) {
      list2.push(i); 
      out.println(i + " " + list.pop());
    }
    out.println(list);
    if (list.getLength() != 0) out.println("sum(list) = " + sum(list));
    out.println("list1 = " + list1);
    out.println("list2 = " + list2);
    out.println("list2.compareTo(list1) = " + list2.compareTo(list1));
    out.println("=== end of test");
  
  } // end of main()
}
