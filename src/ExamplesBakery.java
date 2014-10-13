import java.util.*;

import tester.Tester;

/**
 * 
 * @author Jarred Lien & Hussein Abou Nassif Mourad
 * @version Assignment 11
 * Examples Class to Test the Bakery
 */
public class ExamplesBakery {

    private BakeryItem bi0 = new BakeryItem(0, "poop", "crap", 100);
    private BakeryItem bi1 = new BakeryItem(1, "chocolate", "crap", 50);
    private BakeryItem bi2 = new BakeryItem(2, "banana", "pizza", 20);
    private BakeryItem bi3 = new BakeryItem(3, "straw", "berry", 10);
    private BakeryItem bi4 = new BakeryItem(4, "vanilla", "v", 2);


    private ArrayList<BakeryItem> menu = 
            new ArrayList<BakeryItem>(Arrays.asList(bi0, bi1, bi2, bi3));

    private Customer empty = new Customer();

    private Orders order1 = new Orders(empty, "01/10/10", "01/10/10",
            1, menu, true, 50);
    private Orders order2 = new Orders(empty, "02/10/10", "02/10/10", 
            2, menu, true, 100);
    private Orders order3 = new Orders(empty, "03/10/10", "03/10/10", 
            3, menu, false, 150);
    private Orders order4 = new Orders(empty, "04/10/10", "04/10/10", 
            4, menu, true, 200);

    private ArrayList<Orders> ordersList = 
            new ArrayList<Orders>(Arrays.asList(order1, order2, 
                    order3, order4));

    private Customer jarred = new Customer("Jarred", 1, "St. Alphonsus", 
            "Boston", "MA", 02120, ordersList);
    private Customer hussein = new Customer("Hussein", 2, "Fenway", "Boston",
            "MA", 02115, ordersList);
    private Customer a = new Customer("A", 3, "B", "C", "D", 22222, 
            ordersList);

    /**
     * Method that initializes a customer for an order
     */
    private void init() {
        order1.setCustomer(jarred);
        order2.setCustomer(hussein);
        order3.setCustomer(a);
    }

    private BossMan bossman = new BossMan();
    //    private BossMan bossman2 = new BossMan(menu);


    /**
     * Tests the methods for a customer
     * @param t the tester
     */
    public void testCustomer(Tester t) {
        init();
        t.checkExpect(order1.getID(), 1);
        t.checkExpect(jarred.getID(), 1);
        t.checkExpect(jarred.getName(), "Jarred");
        t.checkExpect(jarred.getSpendings(), 0.0);
        t.checkExpect(empty.toString(),
                "Name: " + "\nID: 0" + "\nAddress: " + "\nCity: " 
                        +  "\nState: " + "\nZIP: 0"  + "\nOrders: ");
        t.checkExpect(jarred.equals(jarred));
        t.checkExpect(!jarred.equals(hussein));

        jarred.setSpendings(50);
        t.checkExpect(jarred.getSpendings() == 50);

        jarred.setOrder(order1);
        jarred.updateInfo("A", "B", 022);

        t.checkExpect(jarred.loyalty(), 0);
    }

    /**
     * Tests the methods for a bakery item
     * @param t the tester
     */
    public void testBakeryItems(Tester t) {

        init();

        t.checkExpect(bi0.getQuantity(), 0);
        t.checkExpect(bi0.getPrice(), 100.0);
        t.checkExpect(bi1.getQuantity(), 0);
        t.checkExpect(bi1.getPrice(), 50.0);
        t.checkExpect(bi2.getID(), 2);

        t.checkExpect(!bi2.equals(bi3));
        t.checkExpect(!bi3.equals(bi2));
        t.checkExpect(bi1.equals(bi1));
        t.checkExpect(bi1.equals("crap"));
        t.checkExpect(!bi1.equals(50000));
        t.checkExpect(bi1.equals(1));
        t.checkExpect(!bi1.equals("JOEY"));

        t.checkExpect(bi1.toString(), "\nItem ID: 1" + 
                "\nItem Name: chocolate" + 
                "\nType: crap" + "\nPrice: 50.0");

        bi1.setQuantity(5);
        t.checkExpect(bi1.getQuantity(), 5);

        t.checkExpect(bi1.toString(), "\nItem ID: 1" + 
                "\nItem Name: chocolate" + 
                "\nType: crap" + "\nQuantity: 5" + "\nPrice: 250.0");

        bi1.setName("A");
        bi1.setPrice(1000);
        t.checkExpect(bi1.getPrice(), 1000.0);
        bi1.setType("B");


    }

    /**
     * Tests the methods for Orders
     * @param t the tester
     */
    public void testOrders(Tester t) {
        t.checkExpect(order1.getID(), 1);
        t.checkExpect(order2.getID(), 2);
        t.checkExpect(order3.getID(), 3);

        t.checkExpect(order1.getPDate(), "01/10/10");
        t.checkExpect(order2.getPDate(), "02/10/10");

        t.checkExpect(order3.getODate(), "03/10/10");
        t.checkExpect(order4.getODate(), "04/10/10");

        order1.addItems(bi4);
        t.checkExpect(order1.items.contains(bi4));

        t.checkExpect(order1.totalPrice(), 5000.0);
    }

    /**
     * Tests the boss man helper methods
     * @param t the tester
     */
    public void testBossMan(Tester t) {
        t.checkExpect(bossman.validDate("10/10/10"));
        t.checkExpect(!bossman.validDate("20/20/20"));
    }



















}
