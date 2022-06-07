public class Troll extends Monster {
    public Troll() {
        super("Тролль (Босс)",
                300 + (int)(Math.random() * 200),
                35 + (int)(Math.random() * 20),
                15 + (int)(Math.random() * 12),
                1000,
                500
        );
    }
}
