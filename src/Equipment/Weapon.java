package Equipment;

import Status.Status;

import java.util.ArrayList;
import java.util.List;
public class Weapon extends Item
{
    private List<Status> statuses;
    public Weapon(String name, int attackModifier, int minDamage,int maxDamage,int defenseModifier, int healthModifier, int luckModifier,String partOfTheEquipment)
    {
        super(name, attackModifier,minDamage, maxDamage, defenseModifier, healthModifier, luckModifier,partOfTheEquipment);
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
