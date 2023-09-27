import java.util.ArrayList;
import java.util.Random;

public class Player extends Creature
{
    private int maxHealth;
    private int healCount=4;
    private int luck;

    public Player(String name, int attack, int defense, int health, int minDamage, int maxDamage, int luck)
    {
        super(name, attack, defense, health, minDamage, maxDamage);
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

    @Override
    public void attack(Creature target)
    {
        int attackModifier = attack - target.getDefense() +1;
        attackModifier = Math.max(1, attackModifier);
        int countDice = attackModifier;
        int totalDamage=0;
        Random random = new Random();
        ArrayList<Integer> costDice = new ArrayList<Integer>();
        for(int i =0;i<countDice;i++)
        {
            int roll = random.nextInt(6)+1;
            costDice.add(roll);
        }

        System.out.println("Кубики игрока: ");
        for(int i : costDice)
        {
            System.out.println(i);
        }

        if(costDice.contains(5)||costDice.contains(6) || costDice.contains(6-luck))
        {
            int damage = random.nextInt(maxDamage - minDamage + 1) + minDamage;
            totalDamage += damage;
            target.takeDamage(totalDamage);
        }
        else
        {
            System.out.println( name + " промахнулся");
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
