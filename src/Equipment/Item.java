package Equipment;

public class Item
{
    private String name;
    private int attackModifier;
    private int defenseModifier;
    private int healthModifier;
    private int luckModifier;

    private int minDamage;
    private int maxDamage;
    private String partOfTheEquipment;
    public Item(String name, int attackModifier, int minDamage,int maxDamage,int defenseModifier, int healthModifier, int luckModifier, String partOfTheEquipment) {
        this.name = name;
        this.attackModifier = attackModifier;
        this.defenseModifier = defenseModifier;
        this.healthModifier = healthModifier;
        this.luckModifier = luckModifier;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.partOfTheEquipment = partOfTheEquipment;
    }

    public int getAttackModifier() {
        return attackModifier;
    }

    public int getDefenseModifier() {
        return defenseModifier;
    }

    public int getHealthModifier() {
        return healthModifier;
    }

    public int getLuckModifier() {
        return luckModifier;
    }

    public int getMinDamageModifier()
    {
        return minDamage;
    }

    public int getMaxDamageModifier()
    {
        return maxDamage;
    }

    public String  getPartOfTheEquipment(){return partOfTheEquipment;}
}
