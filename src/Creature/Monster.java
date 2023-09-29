package Creature;

import java.util.Random;

public class Monster extends Creature{

    double healPercentage;
    private int maxHealth;
    public Monster(String name, int attack, int defense, int health, int minDamage, int maxDamage, int speed,double healPercentage)
    {
        super(name, attack, defense, health, minDamage, maxDamage, speed);
        this.healPercentage = healPercentage;
        maxHealth=health;
    }

    public void desiccation(Creature target)
    {
        int totalDamage = calculateDamage(target);
        target.takeDamage(totalDamage);

        // Восстановление здоровья монстра на основе урона
        int healAmount = (int) (totalDamage * healPercentage);
        setHealth(getHealth() + healAmount);
        System.out.println(name + " восстановил " + healAmount + " здоровья.");
    }

    public void setHealth(int health)
    {
        this.health += health;
    }

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
    public  int getMaxHealth()
    {
        return maxHealth;
    }
}

