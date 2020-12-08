public class Plate {
    private int food;

    public Plate(final int food) {
        this.food = food;
    }

    public int getFood() {
        return food;
    }

    public void addFoodToPlate(final int food) {
        if (food > 0) {
            this.food += food;
        }
    }

    public boolean canDecreaseFood(final int amount) {
        return amount >= 0 && food - amount >= 0;
    }

    public void decreaseFood(final int amount) {
        food -= amount;
    }

    public void info() {
        System.out.println("Food: " + food);
    }
}
