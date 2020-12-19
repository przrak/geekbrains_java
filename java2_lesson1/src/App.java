import action.Actionable;
import block.Blockage;
import block.Treadmill;
import block.Wall;

import java.util.Random;

/**
 * @author Dmitriy Bokach
 */
public class App {
    public static void main(final String[] args) {
        App app = new App();
        Blockage[] blockages = new Blockage[8];
        app.initBlockagesArray(blockages);
        Actionable[] actionables = new Actionable[6];
        app.initActionableArray(actionables);
        app.runBlockages(blockages, actionables);
    }

    private void initBlockagesArray(final Blockage[] blockages) {
        final Random random = new Random();
        int variant;
        for (int i = 0; i < blockages.length; i++) {
            variant = random.nextInt(2);
            switch (variant) {
                case 0:
                    blockages[i] = new Wall(random.nextInt(3));
                    break;
                case 1:
                    blockages[i] = new Treadmill(random.nextInt(10));
                    break;
                default:
                    break;
            }
        }
    }

    private void initActionableArray(final Actionable[] actionables) {
        final Random random = new Random();
        int variant;
        for (int i = 0; i < actionables.length; i++) {
            variant = random.nextInt(3);
            switch (variant) {
                case 0:
                    actionables[i] = new Human(random.nextInt(10),
                            random.nextInt(3), "Человек" + i);
                    break;
                case 1:
                    actionables[i] = new Cat(random.nextInt(10),
                            random.nextInt(3), "Котик" + i);
                    break;
                case 2:
                    actionables[i] = new Robot(random.nextInt(10),
                            random.nextInt(3), "Робот" + i);
                    break;
                default:
                    break;
            }
        }
    }

    private void runBlockages(final Blockage[] blockages, final Actionable[] actionables) {
        for (final Actionable actionable : actionables) {
            for (Blockage blockage : blockages) {
                if (blockage instanceof Treadmill) {
                    if (actionable.getLength() <= ((Treadmill) blockage).getLength()) {
                        System.out.println("DEBUG actionable length: " + actionable.getLength() + " blockage length: " + ((Treadmill) blockage).getLength());
                        this.stopMessage(actionable);
                        break;
                    }
                    actionable.run();
                } else if (blockage instanceof Wall) {
                    if (actionable.getHeight() <= ((Wall) blockage).getHeight()) {
                        System.out.println("DEBUG actionable height: " + actionable.getHeight() + " blockage height: " + ((Wall) blockage).getHeight());
                        this.stopMessage(actionable);
                        break;
                    }
                    actionable.jump();
                }
            }
        }
    }

    private void stopMessage(Actionable actionable) {
        System.out.println("Дальше " + actionable.getName() + " не бежит и не прыгает");
    }
}
