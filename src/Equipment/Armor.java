package Equipment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Armor extends Item
{
    // Список сопротивлений, связанных с этой броней.
    private List<ArmorResistance> resistances;
    /**
     * Конструктор класса Armor.
     *
     * @param name            Название брони.
     * @param attackModifier  Модификатор атаки при ношении брони.
     * @param minDamage       Минимальный урон при ношении брони.
     * @param maxDamage       Максимальный урон при ношении брони.
     * @param defenseModifier Модификатор защиты при ношении брони.
     * @param healthModifier  Модификатор здоровья при ношении брони.
     * @param luckModifier    Модификатор удачи при ношении брони.
     * @param partOfTheEquipment Часть экипировки, к которой относится броня (например, "Голова", "Торс" и т. д.).
     */
    public Armor(String name, int attackModifier, int minDamage,int maxDamage,int defenseModifier, int healthModifier, int luckModifier,String partOfTheEquipment) {
        super(name, attackModifier,minDamage, maxDamage, defenseModifier, healthModifier, luckModifier,partOfTheEquipment);

        resistances = new ArrayList<>();
    }
    /**
     * Метод для добавления сопротивления к броне.
     *
     * @param statusName     Имя статуса, к которому добавляется сопротивление.
     * @param resistanceLevel процент сопротивления.
     */
    public void addResistance(String statusName, int resistanceLevel) {
        resistances.add(new ArmorResistance(statusName, resistanceLevel));
    }
    /**
     * Метод для получения сопротивлений, связанных с этой броней, в виде карты (имя статуса -> уровень сопротивления).
     *
     * @return Карта сопротивлений.
     */
    public Map<String, Integer> getResistancesMap() {
        Map<String, Integer> resistanceMap = new HashMap<>();
        for (ArmorResistance resistance : resistances) {
            resistanceMap.put(resistance.getStatusName(), resistance.getResistanceLevel());
        }
        return resistanceMap;
    }
}
