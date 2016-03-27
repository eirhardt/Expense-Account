/**
 * CSS 162
 * Rob Nash
 * Author: Jack Lewis
 * Homework 3 - Bill Assignment Driver
 * This exercises various methods of the Bill, Money, and Date classes. 
 */
public class Driver {
    public static void main(String[] args) {
        //Construct some money
        Money money1 = new Money(100.20);
        Money money2 = money1.clone(); 
        System.out.println("Money1's cents value: " + money1.getCents());
        System.out.println(("Money1's dollar value: " + money1.getDollars()));
        System.out.println("Money1's toString(): " + money1.toString());
       // money1.setCents(120); correctly throw an exception
        //more functional exercises with the money class
        money2.setDollars(45);
        System.out.println("money2's toString(): " + money2.toString());
        System.out.println("Calling money1.compareTo(money2): (should return 1).");
        System.out.println(money1.compareTo(money2)); 
        System.out.println("Calling money2.compareTo(money1): (should return -1).");
        System.out.println(money2.compareTo(money1)); 
        money2 = money1.clone(); 
        System.out.println("Money2's new toString(): " + money2.toString()); 
        System.out.println("Calling compareTo() on two equal money objects (should return 0)");
        System.out.println(money1.compareTo(money2));

        System.out.println("Some tests of Date methods:");
        Date testDate1 = new Date (4, 20, 2018);
        System.out.println("testDate1: " + testDate1.toString());
        testDate1.setDate(1, 23, 2023);
        System.out.println("testDate1 with setDate alteration: " + testDate1.toString());
        testDate1.setDay(12);
        System.out.println("testDate1 with setDay alteration: " + testDate1.toString());
        testDate1.getDay(); 
        testDate1.getMonth(); 
        testDate1.getYear(); 

        System.out.println("Some tests of Money methods:");
        Money testMoney1 = new Money(42.87);
        System.out.println("testMoney1 = " + testMoney1.toString());
        testMoney1.add(11.34);
        System.out.println("Adding $11.34 to testMoney1.");
        System.out.println("New value of testMoney1 = " + testMoney1.toString());
        testMoney1.subtract(17.64);
        System.out.println("Using subtract method: testMoney1 = " + testMoney1.toString());

        System.out.println("Money objects output:");
        System.out.println(money1);
        System.out.println(money2);

        //Construct some bills
        Money amount = new Money(20);
        //Date dueDate = new Date(4, 30, 2007);   //correctly produces year error
        Date dueDate = new Date(4, 30, 2017);
        String theOriginator = "Whidbey Telecom"; 
        Bill bill1 = new Bill(amount, dueDate, theOriginator);

        Bill bill2 = bill1.clone(); 
        
        //bill2.setDueDate(new Date(5, 30, 2007)); //another instance of bad year
        bill2.setDueDate(new Date(5, 30, 2020));
        amount.setMoney(31, 99);
        dueDate.setDay(29);
        Bill bill3 = new Bill(amount, dueDate, "The record company");
        Bill bill4 = bill3.clone(); 
        Bill bill5 = bill3.clone(); 
        System.out.println("Bill5's originator: " + bill5.getOriginator());
        System.out.println("Does bill 5 = bill 3? " + bill5.equals(bill3));
        //bill4.setPaid(new Date(6, 8, 2019));  //Correctly throws "Payment is late"
        bill4.setPaid(new Date(6, 8, 2015));
        bill2.setPaid(dueDate);

        System.out.println("Bill objects output:");
        System.out.println("Bill 1 " + bill1);
        System.out.println("Bill 2 " + bill2);
        System.out.println("Bill 3 " + bill3);
        System.out.println("Bill 4 " + bill4);
        System.out.println("Bill 5 " + bill5);
        
        System.out.println("bill 1 compareTo() bill 2: " + bill1.compareTo(bill2));
        System.out.println("bill 3 compareTo() bill 5: " + bill3.compareTo(bill5));
        ArrayList<Bill> myArrayList = new ArrayList<Bill>(); 
        
        
    }
}
