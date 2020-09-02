/* CSCE 314 [Sections 500, 501] Programming Languages Spring 2020
   Homework 6 Problem 1 
   Skeleton code for MyNodeTest class
   Written by Hyunyoung Lee 
*/

// 20 points for the MyNodeTest class

public class MyNodeTest {

    // 5 points
    private static int int_sum (MyNode<Integer> n) {
      int sum = 0;
      if(n==null){
        return sum;
      }
      for(int e : n){
            sum += e;
      }
      return sum;
    }
  
    // 10 points
    private static double num_sum (MyNode<? extends Number> n) {
      // to be implemented by you
      double sum = 0.0;
      for(Number e : n){
        sum += e.doubleValue();
  }
  return sum;
    }
  
    // 5 points
    private static <T> void print (MyNode<T> n) {
        for(T e : n){
           System.out.println(e);
      }
    }
  
      
    // Feel free to "expand" the main method, but keep whatever provided as it is
    public static void main (String args[]) {
      MyNode<Integer> intlist = 
          new MyNode<Integer>(1, 
            new MyNode<Integer>(22, 
              new MyNode<Integer>(21, 
                new MyNode<Integer>(12, 
                  new MyNode<Integer>(24, 
                    new MyNode<Integer>(17, null))))));
          
      MyNode<Integer> nullintlist = null;
  
      System.out.println("===");
      print(intlist);
      System.out.println("sum of intlist is " + int_sum(intlist));
      System.out.println("sum of null list is " + int_sum(nullintlist)); 
      System.out.println("===");
  
      MyNode<Double> doublelist = 
          new MyNode<Double>(1., 
            new MyNode<Double>(16., 
              new MyNode<Double>(13.72, 
                new MyNode<Double>(5., 
                  new MyNode<Double>(22., 
                    new MyNode<Double>(7.1, null))))));
  
      System.out.println("===");
      print(doublelist);
      System.out.println("sum ints = " + num_sum(intlist));
      System.out.println("sum doubles = " + num_sum(doublelist));
      System.out.println("===");
    }
  }
  