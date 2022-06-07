public abstract class Monster extends Fighter{
    public Monster(String name, int hp, int power, int agility, int exp, int gold) {
        super(name, hp, power, agility, exp, gold);
    }

    // Для монстров будет свой вывод информации о золоте и об опыте
    @Override
    public void getInfo(){
        super.getInfo();
        System.out.println("Здоровье: " + getHp());
        System.out.println("\nЗа победу Вы получите " +
                super.getGold() + " золота и " +
                super.getExp() + " опыта");
    }
}
