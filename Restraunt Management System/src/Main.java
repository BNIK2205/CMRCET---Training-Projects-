import java.util.Scanner;


    public class Main {
        public static void main(String[] args) {

            Restaurant restaurant = new Restaurant("Gourmet Paradise");

            // Create some dishes and drinks
            Dish pizza = new Dish("Pizza", 12.00);
            Drink coke = new Drink("Coca Cola", 3.00, "large");
            Dish pasta = new Dish("Pasta", 10.00);

            restaurant.getMenu().addDish(pizza);
            restaurant.getMenu().addDrink(coke);
            restaurant.getMenu().addDish(pasta);

            Scanner scanner = new Scanner(System.in);

            restaurant.showMenu();

            System.out.print("Enter your name: ");
            String customerName = scanner.nextLine();

            int billingChoice = 0;
            while (true) {
                try {
                    System.out.println("Choose a billing strategy:");
                    System.out.println("1. Normal Billing");
                    billingChoice = Integer.parseInt(scanner.nextLine());
                    if (billingChoice == 1) {
                        break;
                    } else {
                        System.out.println("Invalid choice, please enter 1.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a number.");
                }
            }


            System.out.println("Choose a billing strategy:");
            System.out.println("1. Normal Billing");

            BillingStrategy strategy = new NormalBilling();

            restaurant.createOrder(customerName, strategy);

            System.out.println("Total revenue of the restaurant: $" + restaurant.getTotalRevenue());
        }
    }