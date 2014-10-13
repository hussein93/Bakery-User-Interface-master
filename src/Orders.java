import java.util.*;

/**
 * 
 * @author Jarred Lien & Hussein Abou Nassif Mourad
 * @version Assignment 11
 * Class that represents an order in the bakery
 */
public class Orders extends BossMan {
    /** The Customer */
    private Customer c;
    /** The orderID */
    private int orderID;
    /** List of items */
    ArrayList<BakeryItem> items;
    /** Is the order paid for */
    private boolean paid;
    /** The order date */
    private String orderDate;
    /** The pickup date */
    private String pickUp;
    /** Discount */
    double discount;
    /** Discount */
    private double totalPrice;

    /**
     * Constructor that creates Orders
     * @param c the customer
     * @param o the order 
     * @param p paid or not
     * @param od the order date
     * @param pd the pickup/delivery date
     * @param di the discount
     */
    Orders(Customer c, int o, boolean p, String od, String pd, double di) {
        this.c = c;
        this.orderID = o;
        this.items = new ArrayList<BakeryItem>();
        this.paid = p;
        this.orderDate = od;
        this.pickUp = pd;
        this.discount = di;     
    }

    /**
     * Constructor for the Orders class
     * @param c the customer of this order
     * @param date1 the order date
     * @param date2 the pickup/delivery date
     * @param o the orderID
     * @param i the items in this order
     * @param p paid or not
     * @param price the total price
     */
    Orders(Customer c, String date1, String date2, int o, 
            ArrayList<BakeryItem> i, boolean p, double price) {
        this.c = c;
        this.orderDate = date1;
        this.pickUp = date2;
        this.orderID = o;
        this.items = i;
        this.paid = p;
        this.totalPrice = price;
    }

    /**
     * gettah method that gets this order ID
     * @return this orderID
     */
    int getID() {
        return this.orderID;
    }

    /**
     * settah method that sets this customer to the given one
     * @param cust the customer
     */
    void setCustomer(Customer cust) {
        this.c = cust;
    }


    /**
     * Return pick up date 
     * @return this pickup date
     */
    String getPDate() {
        return this.pickUp;
    }

    /**
     * Set the total price
     * @param p the price to set this price at
     */
    void setPrice(double p) {
        this.totalPrice = p;
    }

    /**
     * method that gets the order Date
     * @return this order Date
     */
    String getODate() {
        return this.orderDate;
    }

    /**
     * Add the bakery item to the items list
     * @param b the bakery item
     */
    void addItems(BakeryItem b) {
        this.items.add(b);
    }


    /**
     * Calculate total price
     * @return the total price
     */
    double totalPrice() {
        double answer = 0;
        for (int i = 0; i < this.items.size(); i++) {
            answer += (this.items.get(i).getPrice() * 
                    this.items.get(i).getQuantity());
        }

        return answer - c.loyalty();

    }

    /**
     * Method that puts this order into a useful string for the console
     * @return a string of this important information
     */
    public String toString() {
        System.out.println();
        String s = "Customer: " + this.c.getName() + "\nOrder ID: " + 
                this.orderID + "\nPaid: " + this.paid + 
                "\nOrderDate: " + this.orderDate + "\nPick Up: " + 
                this.pickUp + "\nOrders: ";

        for (int i = 0; i < this.items.size(); i++) {
            s += ("\n\n" + this.items.get(i));
        }

        if (this.totalPrice() == 0) {
            s += "\n\nTotal Price: " + (this.totalPrice - c.loyalty());
        }
        else {
            s += "\n\nTotal Price: " + (this.totalPrice() - this.totalPrice);
        }


        return s;
    }

}
