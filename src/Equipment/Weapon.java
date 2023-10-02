package Equipment;

import Status.Status;

import java.util.ArrayList;
import java.util.List;
public class Weapon extends Item

/**
 * Конструктор класса Weapon.
 *
 * @param name            Название оружия.
 * @param attackModifier  Модификатор атаки от оружия.
 * @param minDamage       Минимальный урон от оружия.
 * @param maxDamage       Максимальный урон от оружия.
 * @param defenseModifier Модификатор защиты от оружия.
 * @param healthModifier  Модификатор здоровья от оружия.
 * @param luckModifier    Модификатор удачи от оружия.
 * @param partOfTheEquipment Часть экипировки, к которой относится оружие.
 */
{
    private List<Status> statuses;// Список статусов, связанных с этим оружием.
    public Weapon(String name, int attackModifier, int minDamage,int maxDamage,int defenseModifier, int healthModifier, int luckModifier,String partOfTheEquipment)
    {
        super(name, attackModifier,minDamage, maxDamage, defenseModifier, healthModifier, luckModifier,partOfTheEquipment);
        statuses = new ArrayList<>();
    }
    /**
     * Метод для добавления статуса, связанного с оружием.
     *
     * @param status Статус для добавления.
     */
    public void addStatus(Status status)
    {
        statuses.add(status);
    }
    /**
     * Метод для получения списка статусов, связанных с этим оружием.
     *
     * @return Список статусов.
     */
    public List<Status> getStatuses()
    {
        return statuses;
    }
}
