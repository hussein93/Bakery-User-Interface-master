import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 
 * @author Jarred Lien & Hussein Abou Nassif Mourad
 * @version Assignment 11
 * Main Class that represents the bakery interface
 *
 */
public class Bakery { 
    /** The menu of this bakery */
    private ArrayList<BakeryItem> menu;

    /** The menu of this bakery */
    private ArrayList<Customer> customers;

    /** List of names of customers. To avoid duplicates */
    private ArrayList<String> names;

    /**
     * Run the program - print the menu and then start with the log-in method
     * @param args unused
     */
    public static void main(String[] args) {   
        /** Initializing the two files */
        File file1 = new File("bakeryItems.txt");
        File file2 = new File("orders.txt");

        /** Initialize a new instance of Bakery */
        Bakery b = new Bakery();

        /** Create a new menu and new customer list */
        b.menu = new ArrayList<BakeryItem>();
        b.customers = new ArrayList<Customer>();
        b.names = new ArrayList<String>();

        try {
            /** initialize the scanner with the bakery items file */
            Scanner scan = new Scanner(file1);
            /** initialize the scanner with the orders file */
            Scanner scan2 = new Scanner(file2);

            /** print out the ID, Item, Category, and Price */
            scan.nextLine();

            /** as long as the scanner has a next line, print it */
            while (scan.hasNextLine()) {
                b.extract(scan.nextLine());
            }

            /** print out the ID, Item, Category, and Price */
            scan2.nextLine();
            System.out.println("--------------------------------------------");
            System.out.println("     ::::::::CHAMPION'S BAKERY::::::::");
            System.out.println("--------------------------------------------");

            /** as long as the scanner has a next line, print it */
            while (scan2.hasNextLine()) {
                b.extractOrders(scan2.nextLine());
            }
            System.out.println();

            /** Close the scanner */
            scan.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        /** Interface according to the user */
        b.login();
    }

    /**
     * Print the Customers to the Console
     */
    void printCustomers() {
        for (int i = 0; i < this.customers.size(); i++) {
            System.out.println(this.customers.get(i));
        }
    }

    /**
     * Extract from the Orders file 
     * @param s the orders text file
     */
    void extractOrders(String s) {
        String[] alist = s.split("\\t");

        /** The ID of the customer */
        int id = Integer.parseInt(alist[0]);

        /** The name of the customer */
        String name = alist[1];

        /** Address */
        String address = alist[2];
        /** City */
        String city = alist[3];
        /** State */
        String state = alist[4];
        /** ZIP */
        int zip = Integer.parseInt(alist[5]);
        /** Order ID */
        int orderID = Integer.parseInt(alist[6]);
        /** Paid? */
        String temp = alist[7];
        boolean paid = false;
        if (temp.equals("Yes")) {
            paid = true;
        }
        else {
            paid = false;
        }
        /** Order date */
        String oDate = alist[8];
        /** Pick up date */
        String pDate = alist[9];
        /** Bakery ID */
        int bakeryID = Integer.parseInt(alist[10]);
        /** Total */
        double total = Double.parseDouble(alist[15]);
        /** Discount */
        double discount = Double.parseDouble(alist[16]);
        /** Loyalty */
        double loyalty = Double.parseDouble(alist[19]);

        /** Find the BakeryItem */
        BakeryItem b = this.findItem(bakeryID);

        /** Check if there already is a customer */
        if (this.names.contains(name)) {
            Customer c = this.findCustomer(name);
            Orders order = new Orders(c, orderID, paid, oDate, pDate,
                    discount);
            order.addItems(b);
            order.setPrice(total);
            c.setOrder(order);
        }
        else {
            this.names.add(name);
            Customer c = new Customer(name, id, address, city, state, zip, 
                    new ArrayList<Orders>());
            Orders o = new Orders(c, orderID, paid, oDate, pDate, discount);
            o.addItems(b);
            o.setPrice(total);
            c.setSpendings(loyalty);
            c.setOrder(o);
            this.customers.add(c);
        }

    }

    /** Empty constructor for bakery */
    Bakery() {
        /** Empty Bakery */
    }

    /**
     * Method that extracts the BakeryItems file as the menu
     * @param s the bakery text file
     */
    private void extract(String s) {
        /** Scanner for string */
        String[] alist = s.split("\\t");

        /** Fields of a BakeryItem */
        int id = Integer.parseInt(alist[0]);

        if (id == 32) {
            id = 32;
        }
        else {
            String name = alist[1];
            String category = alist[2];
            double price = Double.parseDouble(alist[3]);

            /** New Bakery Item */
            BakeryItem item = new BakeryItem(id, name, category, price);
            this.menu.add(item);
        }
    }

    /**
     * Run the Bakery based on user input
     */ 
    private void login() {
        BufferedReader input =
                new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter log-in ID Number. \n"
                + "Or Press 0 to View the Menu");
        System.out.println();
        System.out.println("--------------------------------------------");

        try {
            String s = input.readLine();
            int id = Integer.parseInt(s);

            /** Check if customer or owner */
            if (id == 193) {
                System.out.println();
                /** Go into owner interface */
                BossMan baws = new BossMan(this.menu, this.customers);
                baws.owner();
            }
            if (id == 0) {
                this.printMenu();
                this.login();
            }
            else {
                System.out.println();
                System.out.println(":::::::::ACCESS DENIED::::::::::");
                System.out.println();
                this.login();
            }
        }
        catch (IOException e) {
            System.out.println("Goodbye!");
        }
    }

    /**
     * Find the menu item with given ID
     * @param id the given ID
     * @return the BakeryItem
     */
    BakeryItem findItem(int id) {
        for (int i = 0; i < this.menu.size(); i++) {
            if (this.menu.get(i).equals(id)) {
                return this.menu.get(i);
            }
        }
        throw new RuntimeException("Customer not found");
    }

    /** 
     * Find the given customer
     * @param name the name of customer
     * @return the customer with that name
     */
    Customer findCustomer(String name) {
        for (int i = 0; i < this.customers.size(); i++) {
            if (this.customers.get(i).equals(name)) {
                return this.customers.get(i);
            }
        }
        throw new RuntimeException("Customer not found");
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







}
