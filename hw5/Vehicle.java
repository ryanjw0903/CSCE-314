//Ryan Wenham
//627002098
// Acknowledgements - The book, Geeks for geeks
import static java.lang.System.out;
class Vehicle{
    private int currspeed;
    private int currdir;
    private String owner;
    private  int ID;
    private static int next_ID = 0;
    Vehicle(){
        currspeed = next_ID++;
        currspeed = 500;
        currdir = 90;
        owner = "ryan";
        ID = 23567;
    }
    Vehicle(String o){
        this();
        owner = o;
    }
    public void setSpeed(int x){ currspeed = x; }
    public int getSpeed(){ return currspeed;}
    public void setDir(int x){ currdir = x; }
    public int getDir(){return currdir; }
    public void setOwner(String x){ owner = x; }
    public String getOwner(){return owner; }
    public void setID(int x){ ID = x; }
    public int getID(){ return ID;}
    public int getNext(){return next_ID;}
    public String toString(){
        String desc = currspeed + "/" + currdir + "/" + owner + "/" + ID + "/" + next_ID;
        return desc;
    }
    public void changespeed(int ns){currspeed = ns;}
    public void stop(){ currspeed= 0;}
    public void turn_set_deg(int d){ 
        currdir += d;
        if(currdir > 359){
            currdir -= 360;
        }
    }
    public void TURN_LEFT(){
        currdir -= 90;
        if(currdir < 0){
            currdir  += 360;
        }
    }
    public void TURN_RIGHT(){
        currdir += 90;
        if(currdir > 359){
            currdir  -= 360 ;
        }
    }
}
class VehicleTestP4{
     public static void main(String[] args) {
        Vehicle tesla = new Vehicle();
        tesla.setSpeed(500);
        out.println(tesla.getSpeed());
        Vehicle audi = new Vehicle();
        audi.setDir(90);
        out.println(audi.getDir());
        Vehicle ford = new Vehicle();
        ford.setOwner("Tom");
        out.println(ford.getOwner());
        Vehicle fiat = new Vehicle();
        fiat.setID(23567);
        out.println(fiat.getID());
        Vehicle five = new Vehicle();
        five.setSpeed(8);
        out.println(five.getNext());
    }
}
class VehicleTest{
    public static void main(String[] args) {
        Vehicle tesla = new Vehicle();
        tesla.turn_set_deg(60);
        out.println("Vehicle " + tesla);
        Vehicle audi = new Vehicle();
        audi.changespeed(32);
        out.println("Vehicle " + audi);
        Vehicle ford = new Vehicle("Tom");
        ford.TURN_LEFT();
        out.println("Vehicle " + ford);
        Vehicle fiat = new Vehicle();
        fiat.stop();
        out.println("Vehicle " + fiat);
        Vehicle five = new Vehicle();
        five.TURN_RIGHT();
        out.println("Vehicle " + five);
    }
}