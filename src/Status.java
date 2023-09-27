public class Status
{
    private String name;
    private int duration;
    private  int initialDuration;
    private int damagePerTurn;

    private double chance;

    public Status(String name,int duration, int damagePerTurn,double chance)
    {
        this.name = name;
        this.duration = duration;
        this.damagePerTurn = damagePerTurn;
        this.chance = chance;
        initialDuration = duration;
    }

    public String getName()
    {
        return name;
    }

    public int getDuration()
    {
        return duration;
    }

    public  int getDamagePerTurn()
    {
        return damagePerTurn;
    }
    public void reduceDuration()
    {
        if(duration>0)
        {
            duration--;
        }
    }

    public double getChance()
    {
        return chance;
    }

    public void resetDuration() {
        // Восстанавливаем изначальную длительность
        this.duration = initialDuration;
    }

}
