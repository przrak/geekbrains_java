public class Cat {
    private final String name;
    private boolean satiety;

    public Cat(final String name) {
        this.name = name;
        this.satiety = false;
    }

    public String getName() {
        return name;
    }

    public void eat(final Plate plate, final int food) {
        if (plate.canDecreaseFood(food)) {
            System.out.println("cat " + name + " eat " + food + " food");
            plate.decreaseFood(food);
            satiety = true;
        }
        else {
            System.out.println(String.format("For cat %s not enough food. Food left %s. " +
                    "Food need %s", name, plate.getFood(), food));
        }
    }

    public boolean isSatiety() {
        return satiety;
    }
}
