package Creature;

import java.util.Random;

public class Monster extends Creature{

    double healPercentage;
    private int maxHealth;

    /**
     * Конструктор класса Monster.
     *
     * @param name          Имя монстра.
     * @param attack        Базовая атака монстра.
     * @param defense       Базовая защита монстра.
     * @param health        Здоровье монстра.
     * @param minDamage     Минимальный урон при атаке монстра.
     * @param maxDamage     Максимальный урон при атаке монстра.
     * @param speed         Скорость монстра.
     * @param healPercentage Процент восстановления здоровья при атаке монстра.
     */
    public Monster(String name, int attack, int defense, int health, int minDamage, int maxDamage, int speed,double healPercentage)
    {
        super(name, attack, defense, health, minDamage, maxDamage, speed);
        this.healPercentage = healPercentage;
        maxHealth=health;
    }
    /**
     * Метод, который выполняет "иссушение" (вытягивание жизни) из другого существа.
     *
     * @param target Цель атаки монстра.
     */
    public void desiccation(Creature target)
    {
        int totalDamage = calculateDamage(target);
        target.takeDamage(totalDamage);

        // Восстановление здоровья монстра на основе урона
        int healAmount = (int) (totalDamage * healPercentage);
        setHealth(healAmount);
        System.out.println(name + " восстановил " + healAmount + " здоровья.");
    }
    /**
     * Метод для установки здоровья монстра.
     *
     * @param health Значение здоровья для установки.
     */
    public void setHealth(int health)
    {
        this.health += health;
    }
    /**
     * Метод для вычисления урона, который наносит монстр цели при иссушении.
     *
     * @param target Цель атаки монстра.
     * @return Величина урона, наносимого монстром цели.
     */
    private int calculateDamage(Creature target)
    {
        int attackModifier = attack - target.getDefense()+1;
        attackModifier = Integer.max(1,attackModifier);
        Random random = new Random();
        int totalDamage =0;
        int damage = (random.nextInt(maxDamage - minDamage + 1) + minDamage) - attackModifier;
        totalDamage += damage;
        return totalDamage;
    }

    /**
     * Метод для получения максимального здоровья монстра.
     *
     * @return Максимальное здоровье монстра.
     */
    public  int getMaxHealth()
    {
        return maxHealth;
    }
}

