import java.util.ArrayList;
import java.util.List;

public class Weapon extends Item
{
    private List<Status> statuses;
    // Конструктор для оружия, который вызывает конструктор Item
    public Weapon(String name, int attackModifier, int minDamage,int maxDamage,int defenseModifier, int healthModifier, int luckModifier, int evasionModifier)
    {
        super(name, attackModifier,minDamage, maxDamage, defenseModifier, healthModifier, luckModifier, evasionModifier);
        statuses = new ArrayList<>();
    }
    public void addStatus(Status status)
    {
        statuses.add(status);
    }

    public List<Status> getStatuses()
    {
        return statuses;
    }
}
