package Creature;

public class Player extends Creature
{
    private int maxHealth;
    // Количество исцелений, доступных игроку.
    private int healCount=4;

    /**
     * Конструктор класса Player.
     *
     * @param name       Имя игрока.
     * @param attack     Базовая атака игрока.
     * @param defense    Базовая защита игрока.
     * @param health     Здоровье игрока.
     * @param minDamage  Минимальный урон при атаке игрока.
     * @param maxDamage  Максимальный урон при атаке игрока.
     * @param luck       Уровень удачи игрока.
     * @param speed      Скорость игрока.
     */
    public Player(String name, int attack, int defense, int health, int minDamage, int maxDamage, int luck,int speed)
    {
        super(name, attack, defense, health, minDamage, maxDamage,speed);
        maxHealth = health;
        this.luck = luck;
    }
    /**
     * Метод для исцеления игрока.
     * Игрок может исцелиться, если у него есть доступные исцеления, его здоровье не полное и он жив.
     */
    public void heal()
    {
        if(healCount>0 && getHealth()>0 && getHealth()<maxHealth)
        {
            int healAmount = (int)(0.3*maxHealth);
            if (getHealth() + healAmount > maxHealth)
            {
                healAmount = maxHealth - getHealth();
            }
            setHealth(healAmount);
            healCount--;
        }
        else
        {
            System.out.println("Вы не можете исцелиться в данный момент.");
        }
    }

    /**
     * Метод для установки здоровья игрока.
     *
     * @param health Значение здоровья для установки.
     */
    public void setHealth(int health)
    {
        this.health += health;
    }
    /**
     * Метод для получения количества доступных исцелений игрока.
     *
     * @return Количество доступных исцелений.
     */
    public int getHealCount()
    {
        return healCount;
    }
}
