import java.util.LinkedHashSet;
import java.util.Scanner;

public class World {

    public static void main(String[] args) {

        // Создаем коллекцию товаров и добавляем туда 3 зелья
        LinkedHashSet<Good> goods = new LinkedHashSet<>();
        goods.add(new Good("1", "Зелье здоровья (+25hp)", 20, new String[]{"hp"}, new int[]{25}));
        goods.add(new Good("2", "Зелье опыта (+25exp)", 30, new String[]{"exp"}, new int[]{25}));
        goods.add(new Good("3", "Зелье универсальное (+20hp,+20exp)", 40, new String[]{"hp", "exp"}, new int[]{20, 20}));
        // Создаем торговца и темный лес
        Seller potionSeller = new Seller(goods);
        DarkForest darkForest = new DarkForest();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в игру!\nВведите имя для Вашего персонажа.");

        // Создаем игрока и выводим начальные характеристики
        Player player = new Player(scanner.nextLine(),100, 25, 22);
        player.getInfo();

        boolean next = true;
        while (next){
            System.out.println("""

                    Куда хотите пойти? (Введите цифру от 1 до 3)
                    1. К торговцу
                    2. В тёмный лес
                    3. Финальный босс
                    4. На выход""");
            switch (scanner.next()) {
                case ("1") -> potionSeller.go(player);              // 1. К торговцу
                case ("2") -> darkForest.go(player);                // 2. В тёмный лес
                case ("3") -> darkForest.goBoss(player);            // 3. Финальный босс (Новчкам не стоит пытаться!)
                case ("4") -> next = false;                         // 4. На выход
                default -> System.out.println("Неверная команда");  // остальное
            }
        }
        System.out.println("До встречи!");
    }
}
