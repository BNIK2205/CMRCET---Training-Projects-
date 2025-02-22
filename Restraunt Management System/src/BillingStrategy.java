import java.util.ArrayList;

interface BillingStrategy {
    double calculate(ArrayList<Dish> items);
}
