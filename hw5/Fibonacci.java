//Ryan Wenham
//627002098
// Acknowledgements - The book, Geeks for geeks
import java.util.Scanner;
import java.util.Formatter;
class SubsetOutputFIb {
    static final int MAX_INDEX = 9;
    /**
    * Print out the first few Fibonacci numbers,
    * marking evens with a '*'
    */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter bottom int: ");
        int fbb = input.nextInt();
        System.out.print("Enter top int: ");
        int fbe = input.nextInt();
        if(fbe < fbb || fbe < 0 || fbb < 0){
            System.out.print("In valid input ");
            System.exit(0);
        }
        int lo = 1;
        int hi = 1;
        String mark;
        if(lo == fbb){
            System.out.println("1: " + lo);
        }
        for (int i = 2; i <= MAX_INDEX; i++) {
            if (hi % 2 == 0)
                mark = " *";
            else
                mark = "";
            if(i>=fbb && i<=fbe){
                System.out.println(i + ": " + hi + mark);
            }
            hi = lo + hi;
            lo = hi - lo;
        }
    }
}
class ImprovedFibonacci {
    static final int MAX_INDEX = 9;
    /**
    * Print out the first few Fibonacci numbers,
    * marking evens with a '*'
    */
    public static void main(String[] args) {
        int lo = 1;
        int hi = 1;
        String mark;
        System.out.printf("1: %d%n" , lo);
        for (int i = 2; i <= MAX_INDEX; i++) {
            if (hi % 2 == 0)
                mark = " *";
            else
                mark = "";
            System.out.printf("%d: ", i);
            System.out.printf("%d", hi);
            System.out.printf("%s\n", mark);
            hi = lo + hi;
            lo = hi - lo;
        }
    }
 }
   
   