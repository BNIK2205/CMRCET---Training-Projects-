import java.util.ArrayList;

class Order implements Runnable {

    private ArrayList<Dish> items;
    private String customerName;
    private BillingStrategy billingStrategy;

    public Order(String customerName, BillingStrategy billingStrategy) {
        this.items = new ArrayList<>();
        this.customerName = customerName;
        this.billingStrategy = billingStrategy;
    }

    public void addItem(Dish item) {
        items.add(item);
    }

    public double getOrderTotal() {
        return billingStrategy.calculate(items);
    }

    @Override
    public void run() {
        System.out.println("Processing order for " + customerName + "...");
        try {
            Thread.sleep(2000); // Simulate order processing time
        } catch (InterruptedException e) {
            System.out.println("Order processing interrupted.");
        }
        System.out.println("Order for " + customerName + " is ready!");
    }

}
