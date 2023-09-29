package Equipment;

import java.util.ArrayList;
import java.util.List;
import Status.Status;
public class Weapon extends Item
{
    private List<Status> statuses;
    // Конструктор для оружия, который вызывает конструктор Equipment.Item
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
