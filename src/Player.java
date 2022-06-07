public class Player extends Fighter{
    // Массив значений опыта, нужных для перехода на новый уровень
    static int[] expToNewLvl = {60, 80, 110, 150};

    private int maxhp;
    private int lvl;

    public int getMaxhp() {
        return maxhp;
    }

    public int getLvl() {
        return lvl;
    }

    public void upMaxhp(int hp) {
        this.maxhp += hp;
    }

    public Player(String name, int hp, int power, int agility) {
        super(name, hp, power, agility, 0, 0);
        this.lvl = 1;
        this.maxhp = hp;
    }

    // Переопределяем вывод характеристик бойца
    // Выведем еще уровень с опытом, золотишко, и полную информацию о здоровье
    @Override
    public void getInfo(){
        super.getInfo();
        System.out.print("Уровень: " + lvl);
        System.out.println(lvl == 5 ? " (макс.)" : " (опыт: " + super.getExp() + "/" + expToNewLvl[lvl-1] + ")");
        System.out.println("Золото: " + super.getGold());
        System.out.printf("Здоровье: %s из %s%n", getHp(), getMaxhp());
    }

    // Чтобы текущее здоровье не превышало максимальное здоровье бойца
    public void checkHp(){
        if(this.getHp() > maxhp) { this.setHp(maxhp); }
    }

    // Проверяем хватает ли опыта, чтобы перейти на новый уровень
    public void checkLvl(){
        // 5 - максимальный на данный момент
        if(lvl!=5){
            while (super.getExp() >= expToNewLvl[lvl-1]){
                super.upExp( -expToNewLvl[lvl-1]);
                lvl++; // 2 3 4 5 6 - новый уровень
                System.out.println("\nПоздравляю! Вы перешли на уровень " + lvl);
                upMaxhp(50); // увеличиваем здоровье +50
                upPower(2 + lvl * 2); // увеличиваем силу +4 +6 +8 +10 +12
                upAgility(2); // увеличиваем ловкость +2 +2 +2 +2 +2
                if (lvl == 5) {break;}
            }
        }
    }
}
