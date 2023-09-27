import java.util.*;

public abstract class Creature
{
    // поля и методы
    private Map<String, EquipmentSlot> equipmentSlots = new HashMap<>();
    String name;
    int attack;
    int defense;
    int health;
    int minDamage;
    int maxDamage;
    int speed;

    private List<Status> activeStatuses;

    int luck=0;
    int evasion=0;
    public Creature(String name, int attack, int defense, int health, int minDamage, int maxDamage)
    {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;

        // Инициализация слотов для снаряжения
        equipmentSlots.put("head", new EquipmentSlot("head"));
        equipmentSlots.put("leftHand", new EquipmentSlot("leftHand"));
        equipmentSlots.put("rightHand", new EquipmentSlot("rightHand"));
        equipmentSlots.put("body", new EquipmentSlot("body"));
        equipmentSlots.put("legs", new EquipmentSlot("legs"));

        activeStatuses=new ArrayList<>();
    }

    public void takeDamage(int damage)
    {
        health-=damage;
    }
    public void attack(Creature target)
    {
        int attackModifier = attack - target.getDefense() +1;
        attackModifier = Math.max(1, attackModifier);
        int countDice = attackModifier;
        int totalDamage=0;
        Random random = new Random();
        for (int i = 0; i < countDice; i++) {
            int roll = random.nextInt(6) + 1;
            // Применяем удачу на основе псевдорандома
            if (random.nextDouble() < (double) luck / 6) {
                roll = 6; // Удачный бросок (максимальное значение на кубике)
            }

            if (roll >= 5) {
                List<Status> equippedStatuses = getEquippedItemStatuses();
                for(Status status : equippedStatuses)
                {
                    if(Math.random()<=status.getChance())
                    {
                        status.resetDuration();
                        activeStatuses.add(status);
                    }
                }
                applyStatusEffects();
                int damage = random.nextInt(maxDamage - minDamage + 1) + minDamage;
                totalDamage += damage;
                target.takeDamage(totalDamage);
                break;
            } else {
                System.out.println(name + " промахнулся");
            }
        }
    }
    public void equipItem(Item item, String slotName) {
        EquipmentSlot slot = equipmentSlots.get(slotName);
        if (slot != null) {
            Item existingItem = slot.getEquippedItem();
            if (existingItem != null) {
                removeItemEffects(existingItem);
            }
            slot.equipItem(item);
            applyItemEffects(item);
        }
    }

    public void unequipItem(String slotName) {
        EquipmentSlot slot = equipmentSlots.get(slotName);
        if (slot != null) {
            Item item = slot.getEquippedItem();
            if (item != null) {
                slot.unequipItem();
                removeItemEffects(item);
            }
        }
    }

    private void applyItemEffects(Item item) {
        attack += item.getAttackModifier();
        defense += item.getDefenseModifier();
        minDamage += item.getMinDamageModifier();
        maxDamage += item.getMaxDamageModifier();
        health += item.getHealthModifier();
        luck += item.getLuckModifier();
        evasion += item.getEvasionModifier();
    }

    private void removeItemEffects(Item item) {
        attack -= item.getAttackModifier();
        defense -= item.getDefenseModifier();
        minDamage -= item.getMinDamageModifier();
        maxDamage -= item.getMaxDamageModifier();
        health -= item.getHealthModifier();
        luck -= item.getLuckModifier();
        evasion -= item.getEvasionModifier();
    }

    public int getHealth()
    {
        return health;
    }

    public int getDefense() {
        return defense;
    }
    public boolean isAlive()
    {
        return health > 0;
    }

    public void printInfo() {
        System.out.println("Имя: " + name);
        System.out.println("Атака: " + attack);
        System.out.println("Защита: " + defense);
        System.out.println("Здоровье: " + health);
        System.out.println("Минимальный урон: " + minDamage);
        System.out.println("Максимальный урон: " + maxDamage);
        System.out.println("Удача: " + luck);
        System.out.println("Уворот: " + evasion);
        System.out.println("Наложенные статусы: ");
        for(Status status : activeStatuses)
        {
            System.out.println(status.getName());
            System.out.println("Действие статуса: " + status.getDuration());
        }
        System.out.println();
    }

    public int getSpeed()
    {
        return speed;
    }
    public void applyStatusEffects()
    {
        for(Status status : activeStatuses)
        {

            int damage = status.getDamagePerTurn();
            takeDamage(damage);

        }

        updateStatuses();
    }

    public List<Status> getEquippedItemStatuses()
    {
        List<Status> equippedItemStatuses = new ArrayList<>();
        for(EquipmentSlot slot : equipmentSlots.values())
        {
            Item equippedItem = slot.getEquippedItem();
            if(equippedItem instanceof Weapon weapon)
            {
                equippedItemStatuses.addAll(weapon.getStatuses());
            }
        }

        return equippedItemStatuses;
    }

    public void updateStatuses() {
        List<Status> statusesToRemove = new ArrayList<>();

        for (Status status : activeStatuses) {
            if (status.getDuration() > 0) {
                status.reduceDuration(); // Уменьшаем длительность статуса на 1 ход

                if (status.getDuration() == 0) {
                    statusesToRemove.add(status); // Добавляем статус для удаления
                }
            }
        }



        // Удаляем статусы, у которых длительность стала равной 0
        activeStatuses.removeAll(statusesToRemove);
    }
}
