package Creature;

import Equipment.*;
import Status.*;

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
    Boolean canEquipItem = true;
    private List<Status> activeStatuses = new ArrayList<>();;
    int luck=0;
    public Creature(String name, int attack, int defense, int health, int minDamage, int maxDamage,int speed)
    {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;

        // Инициализация слотов для снаряжения
        equipmentSlots.put("Head", new EquipmentSlot());
        equipmentSlots.put("leftHand", new EquipmentSlot());
        equipmentSlots.put("rightHand", new EquipmentSlot());
        equipmentSlots.put("Body", new EquipmentSlot());
        equipmentSlots.put("Legs", new EquipmentSlot());

        activeStatuses=new ArrayList<>();
        this.speed = speed;
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
            // Применяем удачу
            if (random.nextDouble() < (double) luck / 6) {
                roll = 6; // Удачный бросок (максимальное значение на кубике)
            }

            if (roll >= 5)
            {
                List<Status> equippedStatuses = getEquippedItemStatuses();
                for(Status status : equippedStatuses)
                {
                    if(Math.random()<=status.getChance())
                    {
                        applyStatus(status,target);
                    }
                }
                int damage = random.nextInt(maxDamage - minDamage + 1) + minDamage;
                totalDamage += damage;
                target.takeDamage(totalDamage);
                System.out.println(name + " нанес " + target.getName() + " урон равный " + totalDamage);
                break;
            }
            else
            {
                System.out.println(name + " промахнулся");
            }
        }
    }
    public void equipItem(Item item, String slotName) {
        EquipmentSlot slot = equipmentSlots.get(slotName);
        if (slot != null) {
            Item existingItem = slot.getEquippedItem();
            if (existingItem != null)
            {
                if(!slotName.contains(item.getPartOfTheEquipment()))
                {
                    System.out.println("Нелья надеть снаряжение на неправильное место");
                    canEquipItem =false;
                }
                else
                {
                    canEquipItem = true;
                }
                if(canEquipItem)
                {
                    removeItemEffects(existingItem);
                }
            }
            if(canEquipItem)
            {
                slot.equipItem(item);
                applyItemEffects(item);
            }
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

    }

    private void removeItemEffects(Item item) {
        attack -= item.getAttackModifier();
        defense -= item.getDefenseModifier();
        minDamage -= item.getMinDamageModifier();
        maxDamage -= item.getMaxDamageModifier();
        health -= item.getHealthModifier();
        luck -= item.getLuckModifier();
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

    public void printInfo()
    {
        System.out.println("Имя: " + name);
        System.out.println("Здоровье: " + health);
        System.out.println("Атака: " + attack);
        System.out.println("Защита: " + defense);
        System.out.println("Наложенные статусы: ");
        if (!activeStatuses.isEmpty())
        {
            for (Status status : activeStatuses) {
                System.out.println(status.getName());
                System.out.println("Действие статуса: " + status.getDurationNow());
            }
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
            System.out.println("Статус: " + status.getName() + " нанес урон " + damage + " " + name);
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
        for (Status status : activeStatuses)
        {
            if (status.getDurationNow() > 0)
            {
                status.reduceDuration(); // Уменьшаем длительность статуса на 1 ход

                if (status.getDurationNow() <= 0)
                {
                    statusesToRemove.add(status); // Добавляем статус для удаления
                }
            }
            else
            {
                statusesToRemove.add(status);
            }
        }

        for (Status status :statusesToRemove)
        {
            status.resetDuration();
        }
        // Удаляем статусы, у которых длительность стала равной 0
        activeStatuses.removeAll(statusesToRemove);
    }

    public Map<String, Integer> getEquippedArmorResistances() {
        Map<String, Integer> equippedResistances = new HashMap<>();

        for (EquipmentSlot slot : equipmentSlots.values()) {
            Item equippedItem = slot.getEquippedItem();

            if (equippedItem instanceof Armor) {
                Armor armor = (Armor) equippedItem;

                // Получаем сопротивления от брони и добавляем их к общему списку
                Map<String, Integer> resistances = armor.getResistancesMap();
                equippedResistances.putAll(resistances);
            }
        }
        return equippedResistances;
    }

    public void applyStatus(Status status, Creature target)
    {
        // Получаем сопротивления от снаряжения
        Map<String, Integer> equippedArmorResistances = target.getEquippedArmorResistances();

        // Проверяем, есть ли сопротивление для данного статуса
        if (equippedArmorResistances.containsKey(status.getName())) {
            int resistance = equippedArmorResistances.get(status.getName());

            // Генерируем случайное число от 0 до 100
            int randomChance = new Random().nextInt(101);

            if (randomChance > resistance)
            {
                Status newStatus = new Status(status.getName(),status.getDuration(),status.getDamagePerTurn(),status.getChance());
                // Цель подвержена статусу
                target.addStatus(newStatus);
            } else {
                // Цель сопротивляется статусу
                System.out.println(target.getName() + " устойчив к статусу " + status.getName());
            }
        } else {
            Status newStatus = new Status(status.getName(),status.getDuration(),status.getDamagePerTurn(),status.getChance());
            // Если нет сопротивления, цель всегда подвержена статусу
            target.addStatus(newStatus);
        }
    }

    public void addStatus(Status status)
    {
        activeStatuses.add(status);
    }

    public String getName()
    {
        return name;
    }
}
