import java.util.ArrayList;

class NormalBilling implements BillingStrategy {
    @Override
    public double calculate(ArrayList<Dish> items) {
        double total = 0;
        for (Dish item : items) {
            total += item.getPrice();
        }
        return total;
    }
}
