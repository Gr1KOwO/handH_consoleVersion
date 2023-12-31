package Creature;

import Equipment.Item;
import Managers.EquipmentManager;
import Managers.StatusManager;
import Status.Status;

import java.util.List;
import java.util.Random;

public abstract class Creature
{
    // поля и методы
    String name;
    int attack;
    int defense;
    int health;
    int minDamage;
    int maxDamage;
    int speed;
    int luck=0;
    EquipmentManager equipmentManager;
    StatusManager statusManager;

    /**
     * Конструктор класса Creature.
     *
     * @param name       Имя существа.
     * @param attack     Базовая атака существа.
     * @param defense    Базовая защита существа.
     * @param health     Здоровье существа.
     * @param minDamage  Минимальный урон при атаке.
     * @param maxDamage  Максимальный урон при атаке.
     * @param speed      Скорость существа.
     */
    public Creature(String name, int attack, int defense, int health, int minDamage, int maxDamage,int speed)
    {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;

        this.speed = speed;

        equipmentManager = new EquipmentManager(this);
        statusManager = new StatusManager(this);
    }

    /**
     * Метод, который вызывается, когда существо получает урон.
     *
     * @param damage Переданный урон.
     */
    public void takeDamage(int damage)
    {
        health-=damage;
    }

    /**
     * Метод, выполняющий атаку другого существа.
     *
     * @param target Цель атаки.
     */
    public void attack(Creature target)
    {
        int attackModifier = attack - target.getDefense() +1;
        attackModifier = Math.max(1, attackModifier);
        int countDice = attackModifier;
        int totalDamage=0;
        System.out.println("Количество кубиков существа " + this.name + " : "+ countDice);
        Random random = new Random();

        boolean successfulRoll = false;

        for (int i = 0; i < countDice; i++) {
            int roll = random.nextInt(6) + 1;
            // Применяем удачу
            if (random.nextDouble() < (double) luck / 6) {
                roll = 6; // Удачный бросок (максимальное значение на кубике)
            }

            if (roll >= 5)
            {
                successfulRoll = true;
                List<Status> equippedStatuses = statusManager.getEquippedItemStatuses();
                for(Status status : equippedStatuses)
                {
                    double rand = Math.random();
                    if(rand<=status.getChance())
                    {
                        statusManager.applyStatus(status,target);
                    }
                }
                int damage = random.nextInt(maxDamage - minDamage + 1) + minDamage;
                totalDamage += damage;
                target.takeDamage(totalDamage);
                System.out.println(name + " нанес " + target.getName() + " урон равный " + totalDamage);
                break;
            }
        }
        if (!successfulRoll)
        {
            System.out.println(name + " промахнулся");
        }
    }

    /**
     * Метод для экипировки предмета существом в указанный слот.
     *
     * @param item     Предмет для экипировки.
     * @param slotName Название слота, в который будет экипирован предмет.
     */
    public void equipItem(Item item, String slotName)
    {
        equipmentManager.equipItem(item, slotName);
    }

    /**
     * Метод для получения текущего здоровья существа.
     *
     * @return Текущее здоровье существа.
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * Метод для получения защиты существа.
     *
     * @return Защита существа.
     */
    public int getDefense() {
        return defense;
    }
    /**
     * Метод, определяющий, живо ли существо.
     *
     * @return true, если существо живо, иначе false.
     */
    public boolean isAlive()
    {
        return health > 0;
    }

    /**
     * Метод для вывода информации о существе.
     */
    public void printInfo()
    {
        System.out.println("Имя: " + name);
        System.out.println("Здоровье: " + health);
        System.out.println("Атака: " + attack);
        System.out.println("Защита: " + defense);
        System.out.println("Наложенные статусы: ");
        if (!statusManager.getActiveStatus().isEmpty())
        {
            for (Status status : statusManager.getActiveStatus()) {
                System.out.println(status.getName());
                System.out.println("Действие статуса: " + status.getDurationNow());
            }
        }
        System.out.println();
    }
    /**
     * Метод для получения скорости существа.
     *
     * @return Скорость существа.
     */
    public int getSpeed()
    {
        return speed;
    }
    public String getName()
    {
        return name;
    }
    public void setDefenseModifier(int def)
    {
        defense += def;
    }
    public void setMinDamageModifier(int minDam)
    {
        minDamage += minDam;
    }
    public void setMaxDamageModifier(int maxDam)
    {
        maxDamage += maxDam;
    }
    public void setHealthModifier(int dopHealth)
    {
        health += dopHealth;

    }
    // Методы для модификации характеристик существа
    public void setLuckModifier(int dopLuck)
    {
        luck += dopLuck; 
    }
    public void setAttackModifier(int atk)
    {
        attack += atk;
    }
    public void delDefenseModifier(int def)
    {
        defense -= def;
    }
    public void delMinDamageModifier(int minDam)
    {
        minDamage -= minDam;
    }
    public void delMaxDamageModifier(int maxDam)
    {
        maxDamage -= maxDam;
    }
    public void delHealthModifier(int dopHealth)
    {
        health -= dopHealth;
    }
    public void delLuckModifier(int dopLuck)
    {
        luck-= dopLuck; 
    }
    public void delAttackModifier(int atk)
    {
        attack -= atk;
    }

    /**
     * Получение менеджера экипировки существа.
     *
     * @return Менеджер экипировки существа.
     */
    public EquipmentManager getEquipmentManager()
    {
        return equipmentManager;
    }
    /**
     * Получение менеджера статусов существа.
     *
     * @return Менеджер статусов существа.
     */
    public StatusManager getStatusManager()
    {
        return statusManager;
    }
}