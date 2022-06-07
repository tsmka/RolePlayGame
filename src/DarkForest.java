public class DarkForest {
    public void fight(Player player, Monster monster, boolean isBoss) {
        Runnable runnable = () -> {

            System.out.println("Бой начнётся через пару секунд..");

            int round = 1;
            // Рандомом определяем кто первый атакует
            int whoAttack = Math.random() < 0.5 ? 0 : 1;
            System.out.println("Первым атакует " + (whoAttack == 1 ? player.getName() : monster.getName()));

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean allAlive = true;
            // Пока все живы, атакуем по очереди
            while (allAlive) {
                System.out.println("\n----Ход " + round + "----");
                if (round % 2 == whoAttack) {
                    allAlive = player.attack(monster);
                } else {
                    allAlive = monster.attack(player);
                }
                round++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Игрок выжил - награждаем, не выжил - конец игры
            if (player.getHp() > 0) {
                if(!isBoss){
                    System.out.println("\nВы победили и получили " +
                            monster.getExp() + " опыта и "+
                            monster.getGold() + " золота");
                    player.upExp(monster.getExp());
                    player.upGold(monster.getGold());
                    player.checkLvl();
                    System.out.println("\nВаши параметры сейчас:");
                    player.getInfo();
                } else {
                    System.out.println("\nПоздравляю!!!\nВы прошли игру!");
                    System.exit(1);
                }
            } else {
                System.out.println("Вы пали в бою :(");
                System.exit(1);
            }
        };

        // Бой в отдельном потоке
        Thread thread = new Thread(runnable);
        thread.start();
        // Пусть мейнтред подождет, пока бой закончится, иначе выкинет менюшку прям во время боя
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // как встретили - начинается битва
    // на самом деле вынес, чтобы не дублировать одинаковый блок кода в DarkForest.go(...)
    public void walkInForest(Player player){
        System.out.println("Перед вами появляется...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Создаем рандомного монстра, выводим его характеристики, запускаем битву
        Monster monster = Math.random() < 0.5 ? new Skeleton() : new Goblin();
        monster.getInfo();
        fight(player, monster, false);
    }

    // Гуляем по лесу, пока не передумаем
    public void go(Player player){
        System.out.println("\nВы добрались до темного леса...");
        walkInForest(player);

        while (Command.goNext("бой")) {
            System.out.println("\nВы идёте дальше по лесу...");
            walkInForest(player);
        }
    }

    // не хватало чувства завершенности, решил добавить финального босса
    public void goBoss(Player player){
        System.out.println("\nВы идете по темному лесу и замечаете странную пещеру...");
        System.out.println("Вы подходите ближе и вдруг перед вами появляется...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Создаем рандомного монстра, выводим его характеристики, запускаем битву
        Monster monster = new Troll();
        monster.getInfo();
        fight(player, monster, true);
    }
}