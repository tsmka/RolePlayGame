public class Goblin extends Monster {
    public Goblin() {
        super("Гоблин",
                50 + (int)(Math.random() * 50),
                5 + (int)(Math.random() * 10),
                20 + (int)(Math.random() * 10),
                10 + (int)(Math.random() * 10),
                30 + (int)(Math.random() * 10)
        );
    }
}
