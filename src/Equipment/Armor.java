package Equipment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Armor extends Item
{
    private List<ArmorResistance> resistances;
    public Armor(String name, int attackModifier, int minDamage,int maxDamage,int defenseModifier, int healthModifier, int luckModifier,String partOfTheEquipment) {
        super(name, attackModifier,minDamage, maxDamage, defenseModifier, healthModifier, luckModifier,partOfTheEquipment);

        resistances = new ArrayList<>();
    }

    public void addResistance(String statusName, int resistanceLevel) {
        resistances.add(new ArmorResistance(statusName, resistanceLevel));
    }

    // Метод для получения сопротивления по имени статуса
    public Map<String, Integer> getResistancesMap() {
        Map<String, Integer> resistanceMap = new HashMap<>();
        for (ArmorResistance resistance : resistances) {
            resistanceMap.put(resistance.getStatusName(), resistance.getResistanceLevel());
        }
        return resistanceMap;
    }
}
