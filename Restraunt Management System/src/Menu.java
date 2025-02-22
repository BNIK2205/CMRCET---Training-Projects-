import java.util.ArrayList;

class Menu {
    private ArrayList<Dish> dishes;
    private ArrayList<Drink> drinks;

    public Menu() {
        this.dishes = new ArrayList<>();
        this.drinks = new ArrayList<>();
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void showMenu() {
        System.out.println("Menu:");
        int index = 1;
        for (Dish dish : dishes) {
            System.out.println(index++ + ". " + dish.getName() + " - $" + dish.getPrice());
        }
        for (Drink drink : drinks) {
            System.out.println(index++ + ". " + drink.getName() + " (" + drink.getPrice() + ")");
        }
    }

    public ArrayList<Dish> getItems() {
        ArrayList<Dish> items = new ArrayList<>(dishes);
        items.addAll(drinks);
        return items;
    }
}
