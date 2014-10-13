import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 
 * @author Jarred Lien & Hussein Abou Nassif Mourad
 * @version Assignment 11
 * Class that represents the Boss's Interface to interact with the Console
 */
public class BossMan extends Bakery {
    /** The orders  */
    private ArrayList<Customer> orders;

    /** Unique list of IDs of Customers */
    private ArrayList<Integer> keys;

    /** Unique list of IDs of Orders */
    private ArrayList<Integer> keysOrders;

    /** The buffer reader */
    BufferedReader input =
            new BufferedReader(new InputStreamReader(System.in));

    /** The menu of goods */
    ArrayList<BakeryItem> menu;

    /** Empty Constructor */
    BossMan() {
        this.orders = new ArrayList<Customer>();
        this.keys = new ArrayList<Integer>();
        this.keysOrders = new ArrayList<Integer>();
    }

    /** Non-empty Constructor
     * @param m the menu
     *
     */

    BossMan(ArrayList<BakeryItem> m) {
        this.orders = new ArrayList<Customer>();
        this.keys = new ArrayList<Integer>();
        this.keysOrders = new ArrayList<Integer>();
        this.menu = m;
    }

    /** Non-empty Constructor 
     * @param m the menu
     * @param c the customers
     */
    BossMan(ArrayList<BakeryItem> m, ArrayList<Customer> c) {
        this.orders = c;
        this.keys = new ArrayList<Integer>();
        this.keysOrders = new ArrayList<Integer>();
        this.menu = m;
    }

    /**
     * Generate a new unique ID
     * @return unique ID
     */
    private int uniqueID(ArrayList<Integer> alist) {
        boolean done = false;
        int i = 0;
        while (!done) {
            if (alist.contains(i)) {
                i++;
            }
            else {
                alist.add(i);
                done = true;
            }
        }
        /** Return the unique ID */
        return i;
    }

    /**
     * Method that adds a customer based on user input to the console
     */
    private void addCustomer() {

        try {
            System.out.println("----------------------------");
            System.out.println("::::CUSTOMER INFORMATION::::");
            System.out.println();

            /** Last name */
            System.out.println("What is the last name?");
            String lName = this.input.readLine();   

            /** Unique ID */
            int id = this.uniqueID(this.keys);

            /** Address */
            System.out.println();
            System.out.println("What is the address of this customer?");
            String address = this.input.readLine();

            /** City */
            System.out.println();
            System.out.println("What is the city?");
            String city = this.input.readLine();

            /** State */
            System.out.println();
            System.out.println("What is the state?");
            String state = this.input.readLine();

            /** ZIP code */
            System.out.println();
            System.out.println("What is the zip?");
            String z = this.input.readLine();
            int zip = Integer.parseInt(z);

            /** Create a new customer */
            Customer customer = new Customer(lName, id, address, city, state,
                    zip, new ArrayList<Orders>());

            if (this.hasCustomer(customer)) {
                this.addOrder(this.getCustomer(customer), 
                        new ArrayList<BakeryItem>());
            }
            else {

                this.orders.add(customer);

                /** Transition to the order */
                System.out.println("::::ORDER INFORMATION::::");
                System.out.println(); 

                /** Delivery Type */
                System.out.println("Enter 'p' for pickup or 'd' for delivery:");

                /** Add order to this customer */
                this.addOrder(customer, new ArrayList<BakeryItem>());
            }
        }
        catch (IOException e) {
            System.out.println("Goodbye!");
        }
    }

    /**
     * Return the given customer
     * @param c the customer
     * @return the customer
     */
    Customer getCustomer(Customer c) {
        for (int i = 0; i < this.orders.size(); i++) {
            if (this.orders.get(i).equals(c)) {
                return this.orders.get(i);
            }
        }
        throw new RuntimeException("Customer not found");
    }

    /**
     * Method that checks if the customer already exists
     * @param c the customer being checked for
     * @return true if the customer contains it
     */
    boolean hasCustomer(Customer c) {
        for (int i = 0; i < this.orders.size(); i++) {
            if (this.orders.get(i).equals(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that adds an order
     * @param c the customer
     * @param items the bakery items
     */
    private void addOrder(Customer c, ArrayList<BakeryItem> items) {
        try {
            System.out.println();
            System.out.println("Enter Today's Date: "
                    + "month(dd)/day(mm)/year(yy)");

            String todaysDate = this.input.readLine();

            if (!validDate(todaysDate)) {
                System.out.println("Invalid Date: Try Again");
                this.addOrder(c, items);
            }
            System.out.println();
            System.out.println("Enter Order Pickup/Delivery Date: "
                    + "month(dd)/day(mm)/year(yy)");

            String pickupDate = this.input.readLine();

            if (!validDate(pickupDate)) {
                System.out.println("INVALID DATE: Try Again");
                this.addOrder(c, items);
            }
            /** The answer for the orders of this customer */
            String answer = "y";

            /** Add items to the order of this customer */
            while (answer.equals("y") || answer.equals("Y")) {
                /** Item type */
                System.out.println();
                System.out.println("Press 1 - Cakes");
                System.out.println();
                System.out.println("Press 2 - Cupcakes");
                System.out.println();
                System.out.println("Press 3 - Pies");
                System.out.println();
                System.out.println("Press 4 - Cookies");
                System.out.println();
                System.out.println("Press 5 - Pastries");
                System.out.println();
                System.out.println("Press 6 - Exit");
                System.out.println();

                /** Store the answer */
                String response = this.input.readLine();
                int option = Integer.parseInt(response);

                double price = 0;

                /** Go into helper function for the given option */
                if (option == 1) {
                    BakeryItem it = this.type("Cakes");
                    items.add(it);
                    c.setSpendings(it.getPrice() * it.getQuantity());
                    price = c.loyalty();
                    System.out.println(c.getSpendings());
                }
                else if (option == 2) {
                    BakeryItem it = this.type("Cupcakes");
                    items.add(it);
                    c.setSpendings(it.getPrice() * it.getQuantity());
                    price = c.getSpendings() - c.loyalty();
                }
                else if (option == 3) {
                    BakeryItem it = this.type("Pies");
                    items.add(it);
                    c.setSpendings(it.getPrice() * it.getQuantity());
                    price = c.getSpendings() - c.loyalty();
                }
                else if (option == 4) {
                    BakeryItem it = this.type("Cookies");
                    items.add(it);
                    c.setSpendings(it.getPrice() * it.getQuantity());
                    price = c.getSpendings() - c.loyalty();
                }
                else if (option == 5) {
                    BakeryItem it = this.type("Pastries");
                    items.add(it);
                    c.setSpendings(it.getPrice() * it.getQuantity());
                    price = c.getSpendings() - c.loyalty();
                }
                else if (option == 6) {
                    /** Make a new order for this customer */
                    int unique = this.uniqueID(this.keysOrders);

                    Orders ord = new Orders(c, todaysDate, pickupDate,
                            unique, items, true, price);

                    c.setOrder(ord);
                    this.owner();
                }
                else {
                    this.addOrder(c, items);
                }
            }
        }
        catch (IOException e) {
            System.out.println("Goodbye!");
        }
    }

    /**
     * Abstract method for the types of bakery items
     * @param s the type of food
     * @return the bakery item
     */
    BakeryItem type(String s) {
        ArrayList<BakeryItem> goods = new ArrayList<BakeryItem>();

        /** Filter out the type */
        for (int i = 0; i < this.menu.size(); i++) {
            if (this.menu.get(i).equals(s)) {
                goods.add(this.menu.get(i));
            }
        }

        /** If the string is not found */
        if (goods.isEmpty()) {
            System.out.println("We don't have that bakery item available!");
        }

        /** Print out the available goods */
        for (int i = 0; i < goods.size(); i++) {
            System.out.println(goods.get(i));
        }

        try {
            /** Order to be added */
            System.out.println("Enter the ID of the bakery "
                    + "item you wish to order");
            String temp = this.input.readLine();
            int id = Integer.parseInt(temp);

            /** The quantity */
            System.out.println("How many do you want of it?");
            String quan = this.input.readLine();
            int quantity = Integer.parseInt(quan);

            /** Add quantity many items into the items list */
            for (int i = 0; i < this.menu.size(); i++) {
                if (this.menu.get(i).equals(id)) {                
                    this.menu.get(i).setQuantity(quantity);
                    return this.menu.get(i);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }

        throw new RuntimeException("Couldn't Find Item");
    }

    /**
     * Print out all the customers
     */
    void showAllCustomers() {
        int i = 0;
        while (i < this.orders.size()) {
            System.out.println(this.orders.get(i));
            i++;
            System.out.println();
        }
    }

    /**
     * Update the customer information
     * @param n the ID of the customer
     */
    void updateCustomer(Integer n) {
        try {
            System.out.println("Update the customer, or his order? "
                    + "Press C for customer and anything else for Order");
            String answer = this.input.readLine();

            if (answer.equals("C") || answer.equals("c")) {
                System.out.println("What is the new address?");
                String addr = this.input.readLine();
                System.out.println("What is the new city?");
                String city = this.input.readLine();
                System.out.println("What is the new ZIP?");
                String temp = this.input.readLine();
                int zip = Integer.parseInt(temp);

                for (int i = 0; i < this.orders.size(); i++) {
                    if (this.orders.get(i).equals(n)) {
                        this.orders.get(i).updateInfo(addr, city, zip);
                    }
                }
            }
            else {
                Customer c = new Customer();
                for (int i = 0; i < this.orders.size(); i++) {
                    if (this.orders.get(i).equals(n)) {
                        c = this.orders.get(i);
                    }
                }
                for (int i = 0; i < c.getOrders().size(); i++) {
                    System.out.println(c.getOrders().get(i));
                }

                this.addOrder(c, new ArrayList<BakeryItem>());


            }
        }
        catch (IOException e) {
            System.out.println("Goodbye!");
        }

    }

    /**
     * Prints out the owner menu to the Console
     */
    protected void owner() {    
        System.out.println(":::::::::BOSS MAIN MENU::::::::");
        System.out.println();
        System.out.println("PRESS 1 to Add new "
                + "Customers and Orders.");
        System.out.println();
        System.out.println("PRESS 2 to View the "
                + "Customer's & Their Orders.");
        System.out.println();
        System.out.println("PRESS 3 to Update the Customer Info.");
        System.out.println();
        System.out.println("PRESS 4 to Update the "
                + "Bakery Menu Information.");
        System.out.println();
        System.out.println("PRESS 5 to Add Bakery Items");
        System.out.println();
        System.out.println("PRESS 6 to View Sepcific "
                + "Customer with Their Loyalty.");
        System.out.println();
        System.out.println("PRESS 7 to View Orders on a "
                + "Specific Pick-Up Date.");
        System.out.println();
        System.out.println("PRESS 8 to View Orders on a "
                + "Specific Order Date.");
        System.out.println();

        try {
            String s = this.input.readLine();
            int n = Integer.parseInt(s);

            /** Check if customer or owner */
            if (n == 1) {
                System.out.println();
                this.printCustomers();
                this.addCustomer();
                this.owner();
            }
            else if (n == 2) {
                System.out.println();
                /** Output the customers */
                this.showAllCustomers();
                this.owner();
            }  
            else if (n == 3) {
                System.out.println();
                System.out.println("What is the ID of the customer?");
                String answer = this.input.readLine();
                int num = Integer.parseInt(answer);
                /** Output the customers */
                this.updateCustomer(new Integer(num));
                this.owner();
            }   
            else if (n == 4) {
                System.out.println();
                this.printMenu();
                System.out.println("What is the ID of the bakery item?");
                String answer = this.input.readLine();
                int num = Integer.parseInt(answer);

                /** Output the customers */
                this.updateBakeryItem(num);
                this.owner();
            } 
            else if (n == 5) {
                System.out.println();
                this.addItems();
                System.out.println();
                this.owner();
            }
            else if (n == 6) {
                System.out.println();
                this.showAllCustomers();
                System.out.println("What is the Customer ID?");
                int id = Integer.parseInt(this.input.readLine());

                this.viewCustomer(id);
                System.out.println();
                this.owner();
            }
            else if (n == 7) {
                System.out.println();
                System.out.println("What is the Date?");
                String date = this.input.readLine();
                this.viewDate(true, date);
                System.out.println();
                this.owner();
            }
            else if (n == 8) {
                System.out.println();
                System.out.println("What is the Date?");
                String date = this.input.readLine();
                this.viewDate(false, date);
                System.out.println();
                this.owner();
            }
        }
        catch (IOException e) {
            System.out.println("Goodbye!");
        }
    }


    /**
     * Method that views the date
     * @param pUP boolean if its a pickup order
     * @param date the date
     */
    void viewDate(boolean pUP, String date) {
        if (pUP) {
            for (int i = 0; i < this.orders.size(); i++) {
                Customer c = this.orders.get(i);
                for (int j = 0; j < c.getOrders().size(); j++) {
                    if (c.getOrders().get(j).getPDate().equals(date)) {
                        System.out.println(c.getOrders().get(j));
                    }
                }
            }
        }
        else {
            for (int i = 0; i < this.orders.size(); i++) {
                Customer c = this.orders.get(i);
                for (int j = 0; j < c.getOrders().size(); j++) {
                    if (c.getOrders().get(j).getODate().equals(date)) {
                        System.out.println(c.getOrders().get(j));
                    }
                }
            }

        }
    }



    /**
     * Update bakery item based on its ID
     * @param id the bakery item ID
     */
    void updateBakeryItem(int id) {
        try {

            for (int i = 0; i < this.menu.size(); i++) {
                this.menu.get(i).getID();
                if (this.menu.get(i).equals(id)) {
                    BakeryItem b = this.menu.get(i);

                    System.out.println("What is the new Name of this item?");
                    String name = this.input.readLine();
                    System.out.println("What is the new Type of this item?");
                    String type = this.input.readLine();
                    System.out.println("What is the new Price of this item?");
                    String temp = this.input.readLine();
                    double price = Double.parseDouble(temp);

                    /** Update information */
                    b.setName(name);
                    b.setType(type);
                    b.setPrice(price);
                }
            }
        }
        catch (IOException e) {
            System.out.println("Goodbye!");
        }
    }


    /**
     * Method that prints the menu
     */
    void printMenu() {
        int i = 0;
        while (i < this.menu.size()) {
            System.out.println(this.menu.get(i));
            System.out.println();
            i++;
        }
    }

    /**
     * Method that prints the customer
     */
    void printCustomers() {
        int i = 0;
        while (i < this.orders.size()) {
            System.out.println(this.orders.get(i));
            System.out.println();
            i++;
        }
    }



    /***
     * Checks to see if the date put into the system is valid or not
     * @param s the date
     * @return true if it is a valid date
     */
    public boolean validDate(String s) {
        Integer month = Integer.parseInt(s.substring(0, 2));
        Integer day = Integer.parseInt(s.substring(3, 5));
        Integer year = Integer.parseInt(s.substring(6, 8));

        return ((month > 0) && (month < 13)) &&
                ((day > 0) && (day < 32)) &&
                ((year > 0) && (year < 16));
    }

    /**
     * Add Bakery Items
     */
    void addItems() {
        try {
            this.printMenu();
            System.out.println("What is the ID of the bakery item?");
            int id = Integer.parseInt(this.input.readLine());

            if (id < this.menu.size()) {
                System.out.println("Must be greater than: " 
                        + this.menu.size());
            }
            else {
                System.out.println("What is the name of the bakery item?");
                String name = this.input.readLine();

                System.out.println("What is the type of the bakery item?");
                String type = this.input.readLine();

                System.out.println("What is the price of the bakery item?");
                double price = Double.parseDouble(this.input.readLine());

                /** Create a new bakery item */
                BakeryItem b = new BakeryItem(id, name, type, price);

                /** Add the item to the menu */
                this.menu.add(b);
            }

        }
        catch (IOException e) {
            System.out.println("Goodbye!");
        }
    }



    /**
     * View Specific customer
     * @param id the ID of the customer
     */
    void viewCustomer(int id) {
        for (int i = 0; i < this.orders.size(); i++) {
            if (this.orders.get(i).equals(id)) {
                System.out.println(this.orders.get(i));
                System.out.println();
                System.out.println("Loyalty: " + this.orders.get(i).loyalty());
                System.out.println();
            }
        }
    }


}
