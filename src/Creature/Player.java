package Creature;

public class Player extends Creature
{
    private int maxHealth;
    private int healCount=4;


    public Player(String name, int attack, int defense, int health, int minDamage, int maxDamage, int luck,int speed)
    {
        super(name, attack, defense, health, minDamage, maxDamage,speed);
        maxHealth = health;
        this.luck = luck;
    }

    public  int getMaxHealth()
    {
        return maxHealth;
    }
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


    public void setHealth(int health)
    {
        this.health += health;
    }

    public int getHealCount()
    {
        return healCount;
    }
}
