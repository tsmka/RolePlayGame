public abstract class Fighter{
    private final String name;
    private int hp;
    private int power;
    private int agility;
    private int gold;
    private int exp;

    public Fighter(String name, int hp, int power, int agility, int exp, int gold) {
        this.name = name;
        this.hp = hp;
        this.power = power;
        this.agility = agility;
        this.exp = exp;
        this.gold = gold;
    }

    public int getExp() {
        return exp;
    }

    public void upExp(int exp) {
        this.exp += exp;
    }

    public int getGold() {
        return gold;
    }

    public void upGold(int gold) {
        this.gold += gold;
    }

    public String getName() {
        return name;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public void upHp(int hp) {
        this.hp += hp;
    }

    public void upPower(int power) {
        this.power += power;
    }

    public void upAgility(int agility) {
        this.agility += agility;
    }

    // Вывод характеристик Fighter'a
    public void getInfo(){
        System.out.println("\n[" + name + "]");
        System.out.println("Сила: " + power);
        System.out.println("Ловкость: " + agility);
    }

    // Считаем урон текущей атаки Fighter'a, учитывая силу, ловкость, шанс крита
    public int damage(){
        return (int)(this.power * // Сила
                (Math.random() > 0.3 ? 1 : 1.5) * // Шанс крита 30% крит урон x1.5
                (Math.random() * 100 < this.agility * 3 ? 1 : 0)); // Ловкость
    }

    // Атакуем другого Fighter'a
    // на выходе - жив ли защищающися
    public boolean attack(Fighter defender){
        int damage = this.damage();
        System.out.printf(damage == 0 ? this.getName() + " промахнулся\n" : "%s нанёс %d урона\n", this.name, damage);

        if (defender.getHp() > damage) {
            defender.upHp(-damage);
            System.out.printf("У %s осталось %d здоровья%n", defender.getName(), defender.getHp());
        } else {
            defender.setHp(0);
            System.out.printf("%s убит", defender.getName());
        }
        return defender.getHp() > 0;
    }
}
