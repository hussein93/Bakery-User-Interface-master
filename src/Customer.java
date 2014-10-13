import java.util.*;

/**
 * jlien15 - hassuni9
 * @author Jarred Lien and Hussein Abou Nassif Mourad
 * @version Assignment 11
 * Representing the Customer Class
 */
public class Customer extends BossMan {
    /** Last name of the customer */
    private String lName;
    /** ID of the customer */
    private int id;
    /** Address of the customer */
    private String address;
    /** City */
    private String city;
    /** Total amount spent */
    private double spendings;
    /** State */
    private String state;
    /** ZIP code */
    private int zip;
    /** List of orders */
    private ArrayList<Orders> orders;

    /**
     * The constructor for the customer class
     * @param l the last name
     * @param i the id 
     * @param a the address
     * @param c the city
     * @param s the state
     * @param z the ZIP code
     * @param o the orders
     */
    Customer(String l, int i, String a, String c, String s, int z, 
            ArrayList<Orders> o) {
        this.lName = l;
        this.id = i;
        this.address = a;
        this.spendings = 0;
        this.city = c;
        this.state = s;
        this.zip = z;
        this.orders = o;
    }

    /**
     * The other empty constructor
     */
    Customer() {
        this.lName = "";
        this.id = 0;
        this.address = "";
        this.city = "";
        this.state = "";
        this.zip = 0;
        this.orders = new ArrayList<Orders>();
    }

    /**
     * Return the Order list
     * @return order list
     */
    ArrayList<Orders> getOrders() {
        return this.orders;
    }
    
    /**
     * Return the Order list
     * @return the spendings of this customer
     */
    double getSpendings() {
        return this.spendings;
    }

    /**
     * Return the ID of this customer
     * @return the ID of this customer
     */
    int getID() {
        return this.id;
    }

    /**
     * Add to the spendings of this customer
     * @param s the customer
     */
    void setSpendings(double s) {
        this.spendings += s;
    }

    /**
     * Set the orders of this customer 
     * @param o the orders for this customer
     */
    void setOrder(Orders o) {
        this.orders.add(o);
    }

    /**
     * Updating the customer
     * @param newA new address
     * @param newC new city
     * @param newZ new ZIP
     */
    void updateInfo(String newA, String newC, int newZ) {
        this.address = newA;
        this.city = newC;
        this.zip = newZ;
    }

    /**
     * Return the name 
     * @return the name
     */
    String getName() {
        return this.lName;
    }

    /**
     * Return the loyalty discount of the member
     * @return the loyalty discount
     */
    int loyalty() {
        int answer = 0;
        while (this.spendings >= 100) {
            this.spendings -= 100;
            answer += 10;
        }

        /** Return the discount */
        return answer;
    }

    /**
     * Check if this customer has the same ID as the given object
     * @param o the object
     * @return boolean if this customer ID is same as given ID
     */
    public boolean equals(Object o) {
        if (o instanceof Integer) {
            return (new Integer(this.id).equals(o));
        }
        else if (o instanceof String) {
            return (this.lName.equals(o));
        }
        else if (o instanceof Customer) {
            Customer temp = (Customer) o;
            return (this.lName.equals(temp.lName));
        }
        else {
            return false;
        }
    }

    /**
     * Output the information of this customer
     * @return the string of information
     */
    public String toString() {
        System.out.println("------------------------------------------------");
        String s = "Name: " + this.lName + "\nID: " + this.id + "\nAddress: " + 
                this.address + "\nCity: " + this.city + "\nState: " + 
                this.state + "\nZIP: " + this.zip + "\nOrders: ";

        for (int i = 0; i < this.orders.size(); i++) {
            s += ("\n\n" + this.orders.get(i));
        }

        /** Return information of this customer */
        return s;
    }
}
