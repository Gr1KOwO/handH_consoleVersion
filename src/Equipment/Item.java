package Equipment;

public class Item
{
    private String name;  // Название предмета.
    private int attackModifier; // Модификатор атаки от предмета.
    private int defenseModifier;  // Модификатор защиты от предмета.
    private int healthModifier; // Модификатор здоровья от предмета.
    private int luckModifier;  // Модификатор удачи от предмета.
    private int minDamage; // Минимальный урон от предмета.
    private int maxDamage; // Максимальный урон от предмета.
    private String partOfTheEquipment; // Часть экипировки, к которой относится предмет.
    /**
     * Конструктор класса Item.
     *
     * @param name             Название предмета.
     * @param attackModifier   Модификатор атаки от предмета.
     * @param minDamage        Минимальный урон от предмета.
     * @param maxDamage        Максимальный урон от предмета.
     * @param defenseModifier  Модификатор защиты от предмета.
     * @param healthModifier   Модификатор здоровья от предмета.
     * @param luckModifier     Модификатор удачи от предмета.
     * @param partOfTheEquipment Часть экипировки, к которой относится предмет.
     */
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
    /**
     * Метод для получения модификатора атаки от предмета.
     *
     * @return Модификатор атаки.
     */
    public int getAttackModifier() {
        return attackModifier;
    }
    /**
     * Метод для получения модификатора защиты от предмета.
     *
     * @return Модификатор защиты.
     */
    public int getDefenseModifier() {
        return defenseModifier;
    }
    /**
     * Метод для получения модификатора здоровья от предмета.
     *
     * @return Модификатор здоровья.
     */
    public int getHealthModifier() {
        return healthModifier;
    }
    /**
     * Метод для получения модификатора удачи от предмета.
     *
     * @return Модификатор удачи.
     */
    public int getLuckModifier() {
        return luckModifier;
    }
    /**
     * Метод для получения минимального урона от предмета.
     *
     * @return Минимальный урон.
     */
    public int getMinDamageModifier()
    {
        return minDamage;
    }
    /**
     * Метод для получения максимального урона от предмета.
     *
     * @return Максимальный урон.
     */
    public int getMaxDamageModifier()
    {
        return maxDamage;
    }
    /**
     * Метод для получения части экипировки, к которой относится предмет.
     *
     * @return Часть экипировки.
     */
    public String  getPartOfTheEquipment(){return partOfTheEquipment;}
}
