public class Item
{
    private String name;
    private int attackModifier;
    private int defenseModifier;
    private int healthModifier;
    private int luckModifier;
    private int evasionModifier;
    private int minDamage;
    private int maxDamage;
    public Item(String name, int attackModifier, int minDamage,int maxDamage,int defenseModifier, int healthModifier, int luckModifier, int evasionModifier) {
        this.name = name;
        this.attackModifier = attackModifier;
        this.defenseModifier = defenseModifier;
        this.healthModifier = healthModifier;
        this.luckModifier = luckModifier;
        this.evasionModifier = evasionModifier;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
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

    public int getEvasionModifier() {
        return evasionModifier;
    }

    public int getMinDamageModifier()
    {
        return minDamage;
    }

    public int getMaxDamageModifier()
    {
        return maxDamage;
    }
}
