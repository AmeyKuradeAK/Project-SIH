import java.util.ArrayList;
import java.util.Scanner;

/**
 * @param name        Instance variables The name of the service
 * @param description A brief description of the service
 * @param price       The price of the service in rupees
 * @param provider    The name of the provider of the service
 * @param rating      The rating of the service from 1 to 5 stars
 */ // A class to represent a legal service
record LegalService(String name, String description, double price, String provider, int rating) {
    // Constructor

    // A method to display the details of the service
    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Price: Rs. " + price);
        System.out.println("Provider: " + provider);
        System.out.println("Rating: " + rating + " stars");
        System.out.println();
    }
}

// A class to represent a customer
class Customer {
    public static void main(String[] args) {
        eMarketplace em = new eMarketplace(); // Create an instance of the eMarketplace class
        em.displayMenu(); // Call the displayMenu method of the eMarketplace class
        // Add more code to handle user input and perform other actions
    }

    // Instance variables
    private final String name; // The name of the customer
    private final String email; // The email address of the customer
    private final String password; // The password of the customer
    private final ArrayList<LegalService> cart; // The list of legal services added to the cart by the customer

    // Constructor
    public Customer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        cart = new ArrayList<LegalService>(); // Initialize the cart as an empty list
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<LegalService> getCart() {
        return cart;
    }

    // A method to add a legal service to the cart
    public void addToCart(LegalService service) {
        cart.add(service); // Add the service to the end of the list
        System.out.println("You have added " + service.name() + " to your cart.");
        System.out.println();
    }

    // A method to remove a legal service from the cart
    public void removeFromCart(LegalService service) {
        if (cart.contains(service)) { // Check if the service is in the cart
            cart.remove(service); // Remove the service from the list
            System.out.println("You have removed " + service.name() + " from your cart.");
            System.out.println();
        } else { // If the service is not in the cart, display an error message
            System.out.println("The service you are trying to remove is not in your cart.");
            System.out.println();
        }

    }

    // A method to view the contents of the cart
    public void viewCart() {
        if (cart.isEmpty()) { // Check if the cart is empty
            System.out.println("Your cart is empty.");
            System.out.println();
        } else { // If the cart is not empty, display the details of each service in the cart
            System.out.println("Your cart contains:");
            for (LegalService service : cart) { // Loop through each service in the cart
                service.display(); // Display the details of the service
            }
        }
    }

    // A method to checkout and pay for the services in the cart
    public void checkout() {
        if (cart.isEmpty()) { // Check if the cart is empty
            System.out.println("You have nothing to checkout.");
            System.out.println();
        } else { // If the cart is not empty, calculate and display the total amount and confirm
                 // payment
            double total = 0; // Initialize the total amount as zero
            for (LegalService service : cart) { // Loop through each service in the cart
                total += service.price(); // Add the price of the service to the total amount
            }
            System.out.println("Your total amount is Rs. " + total);
            System.out.println("Do you want to proceed to payment? (Y/N)");
            Scanner input = new Scanner(System.in); // Create a scanner object to get user input
            String answer = input.nextLine(); // Get the user input as a string
            if (answer.equalsIgnoreCase("Y")) { // Check if the user input is yes (case insensitive)
                System.out.println("Payment successful. Thank you for using our eMarketplace.");
                System.out.println();
                cart.clear(); // Clear the cart after payment
            } else { // If the user input is not yes, display a message and return to the main menu
                System.out.println("Payment cancelled. You can continue browsing our eMarketplace.");
                System.out.println();
            }
        }
    }
}

// A class to represent the eMarketplace
class eMarketplace {
    // Instance variables
    private ArrayList<LegalService> services; // The list of legal services available in the eMarketplace
    private ArrayList<Customer> customers; // The list of customers registered in the eMarketplace
    private Customer currentCustomer; // The current customer who is logged in or null if no one is logged in

    // Constructor
    public eMarketplace() {
        services = new ArrayList<LegalService>(); // Initialize the services as an empty list
        customers = new ArrayList<Customer>(); // Initialize the customers as an empty list
        currentCustomer = null; // Initialize the current customer as null
        populateServices(); // Populate the services list with some sample data
    }

    // A method to populate the services list with some sample data
    public void populateServices() {
        LegalService service1 = new LegalService("Divorce Consultation",
                "Get expert advice on how to file for divorce and settle your disputes.", 5000, "Lawyer ABC", 4);
        LegalService service2 = new LegalService("Will Drafting",
                "Create a legally valid will that ensures your assets are distributed according to your wishes.", 3000,
                "Lawyer XYZ", 5);
        LegalService service3 = new LegalService("Consumer Complaint",
                "File a complaint against a seller or a service provider who has cheated or harassed you.", 2000,
                "Lawyer PQR", 3);
        services.add(service1); // Add service1 to the end of the list
        services.add(service2); // Add service2 to the end of the list
        services.add(service3); // Add service3 to the end of the list
    }

    // A method to display the main menu of the eMarketplace
    public void displayMenu() {
        System.out.println("Welcome to eMarketplace for Law and Legal Services");
        System.out.println("Please choose an option:");
        System.out.println("1. Register as a new customer");
        System.out.println("2. Login as an existing customer");
        System.out.println("3. Browse legal services");
        System.out.println("4. Exit");
        System.out.println();
    }

    // A method to register a new customer in the eMarketplace
    public void registerCustomer() {
        Scanner input = new Scanner(System.in); // Create a scanner object to get user input
        System.out.println("Please enter your name:");
        String name = input.nextLine(); // Get the user input as a string for name
        System.out.println("Please enter your email:");
        String email = input.nextLine(); // Get the user input as a string for email
        System.out.println("Please enter your password:");
        String password = input.nextLine(); // Get the user input as a string for password
        Customer customer = new Customer(name, email, password); // Create a new customer object with the user input
        customers.add(customer); // Add the customer to the end of the list
        System.out.println("You have successfully registered as a new customer.");
        System.out.println();
    }

    // A method to login an existing customer in the eMarketplace
    public void loginCustomer() {
        Scanner input = new Scanner(System.in); // Create a scanner object to get user input
        System.out.println("Please enter your email:");
        String email = input.nextLine(); // Get the user input as a string for email
        System.out.println("Please enter your password:");
        String password = input.nextLine(); // Get the user input as a string for password
        boolean found = false; // Initialize a boolean variable to indicate if the customer is found or not
        for (Customer customer : customers) { // Loop through each customer in the list
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) { // Check if the email
                                                                                                // and password match
                                                                                                // with the customer's
                                                                                                // details
                found = true; // Set found to true if match is found
                currentCustomer = customer; // Set currentCustomer to the matched customer
                break;
            }
        }
    }
}