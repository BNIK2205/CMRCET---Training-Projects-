class Drink extends Dish {
    private String size;

    public Drink(String name, double price, String size) {
        super(name, price);
        this.size = size;
    }

    @Override
    public double getPrice() {
        switch (size.toLowerCase()) {
            case "medium":
                return super.getPrice() * 1.5;
            case "large":
                return super.getPrice() * 2;
            default:
                return super.getPrice();
        }
    }
}
