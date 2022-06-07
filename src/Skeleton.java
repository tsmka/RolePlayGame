public class Skeleton extends Monster {
    public Skeleton() {
        super("Скелет-Лучник",
                30 + (int)(Math.random() * 50),
                10 + (int)(Math.random() * 10),
                15 + (int)(Math.random() * 10),
                25 + (int)(Math.random() * 10),
                25 + (int)(Math.random() * 10)
        );
    }
}
