
/**
 * jlien15 - hassuni9
 * @author Jarred Lien and Hussein Abou Nassif Mourad
 * @version Assignment 11
 * Representing the Bakery Items class
 */
public class BakeryItem extends BossMan {
    /** The bakeryItem ID */
    private int itemID;
    /** The name of this item */
    private String name;
    /** Item Category */
    private String type;
    /** Price of this item */
    private double price;
    /** Quantity of this item */
    private int quantity;

    /**
     * The constructor
     * @param id the id of this item
     * @param n the name of this item
     * @param t the type of this item
     * @param p the price of this item
     */
    BakeryItem(int id, String n, String t, double p) {
        this.itemID = id;
        this.name = n;
        this.type = t;
        this.price = p;
        this.quantity = 0;
    }

    /**
     * Set the quantity of this item
     * @param q the quantity to be set
     */
    void setQuantity(int q) {
        this.quantity = q;
    }

    /**
     * Setting new name
     * @param n the new name
     */
    void setName(String n) {
        this.name = n;
    }

    /**
     * The new type of bakery item
     * @param t the new type
     */
    void setType(String t) {
        this.type = t;
    }

    /**
     * To set the new price
     * @param p the new price
     */
    void setPrice(double p) {
        this.price = p;
    }

    /** 
     * Return the quantity of the item
     * @return the quantity of the item
     */
    int getQuantity() {
        return this.quantity;
    }

    /** 
     * Return the price of the item
     * @return the price of the item
     */
    double getPrice() {
        return this.price;
    }

    /**
     * Check that both objects are the same type
     * @param o the object being compared
     * @return whether or not both objects are equal
     */
    public boolean equals(Object o) {
        if (o instanceof String) {
            return this.type.equals(o);
        }
        else if (o instanceof BakeryItem) {
            BakeryItem temp = (BakeryItem) o;
            return this.itemID == temp.itemID &&
                    this.type.equals(temp.type) &&
                    this.name.equals(temp.name) &&
                    this.price == temp.price;
        }
        else {
            return false;
        }
    }

    /**
     * Return the ID of this item
     * @return the ID
     */
    int getID() {
        return this.itemID;
    }

    /**
     * Check that both objects have the same ID
     * @param id the ID
     * @return whether or not they're equal
     */
    public boolean equals(int id) {
        return this.itemID == id;
    }

    /**
     * Print information of this bakery item
     * @return the string output
     */
    public String toString() {
        if (this.quantity == 0) {
            String s = "\nItem ID: " + this.itemID + "\nItem Name: " + 
                    this.name + "\nType: " + this.type + "\nPrice: " +
                    this.price;

            /** Return the information of this bakery item */
            return s;
        }
        else {
            String s = "\nItem ID: " + this.itemID + "\nItem Name: " + 
                    this.name + "\nType: " + this.type + "\nQuantity: " +
                    this.quantity + "\nPrice: " + (this.price * this.quantity);

            /** Return the information of this bakery item */
            return s;
        }
    }
}
