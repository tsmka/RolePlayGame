import java.util.LinkedHashSet;
import java.util.Scanner;

public class Seller {
    LinkedHashSet<Good> goods;

    public Seller(LinkedHashSet<Good> goods) {
        this.goods = goods;
    }

    // Сделка
    public void sell(Player player, Good good){
        // Первым делом проверяем хватает ли золота
        if(good.getPrice() < player.getGold()){
            // buy отвечает за тот факт, купили ли мы зелье
            boolean buy = false;
            // Идем по всем характеристикам, которые улучшает товар
            // Все характеристики указаны у товара в String[] params
            for(int i = 0; i < good.getParams().length; i++ ){
                switch (good.getParams()[i]){
                    case "hp" -> {
                        // Торговец оказался честным и не продает нам зелье, если у нас полное здоровье
                        if (player.getHp() != player.getMaxhp()){
                            // а если нам надо, то здоровье увеличивается на ту величину, которая указана в int[] points
                            player.upHp(good.getPoints()[i]);
                            // исправляем, если здоровье перевалило за максимум
                            player.checkHp();
                            buy = true;
                        } else {
                            System.out.println("У вас полное здоровье!");
                        }

                    }
                    // аналогично с другими характеристиками
                    case "exp" -> {
                        if (player.getLvl() != 5) {
                            player.upExp(good.getPoints()[i]);
                            player.checkLvl();
                            buy = true;
                        } else {
                            System.out.println("У вас максимальный уровень!");
                        }
                    }
                }
            }
            // Если в итоге покупка прошла - списываем у игрока цену товара
            // и показываем новые параметры
            if (buy) {
                player.upGold(-good.getPrice());
                player.getInfo();
            }
        } else {
            System.out.println("У вас недостаточно золота.");
        }
    }

    // Показываем все товары и пункт для выхода
    public void showMenu() {
        System.out.println("\nВ продаже имеются: ");
        for (Good good : goods) {
            System.out.printf("%s. %s за %d золота\n", good.getId(), good.getName(), good.getPrice());
        }
        System.out.println("0. Выход");
    }

    // Выбираем что покупать
    public boolean choiceGood(Player player){
        System.out.println("\nВаши параметры сейчас:");
        player.getInfo();
        showMenu();
        System.out.println("\nВведите 0 если хотите выйти или номер товара, который хотите купить.");
        Scanner scanner = new Scanner(System.in);
        String goodId = scanner.next();

        // Если не ноль - ищем товар
        if (!"0".equals(goodId)) {
            boolean notGood = true;
            // Проходим по всем товарам и ищем с нужным id
            for (Good good : goods) {
                // Если нашли - приступаем к сделке
                if (good.getId().equals(goodId)) {
                    notGood = false;
                    sell(player, good);
                }
            }
            if (notGood) {
                System.out.println("Товар с таким id не найден");
            }
        } else {
            return false;
        }
        return true;
    }

    public void go(Player player) {
        if(choiceGood(player)){
            while(Command.goNext("торговлю") && choiceGood(player)) {
                // Из цикла выйдем только когда бользователь ответит "нет" на вопрос
                // или выберет "0" при выборе товара
            }
        }
    }
}

