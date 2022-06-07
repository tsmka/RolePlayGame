public record Good(String id, String name, int price, String[] params, int[] points) {

    // Товары
    // Так как все параметры были private и final, IntelliJ предложил переделать класс в рекорд

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String[] getParams() {
        return params;
    }

    public int[] getPoints() {
        return points;
    }

}
