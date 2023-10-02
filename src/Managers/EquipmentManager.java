package Managers;

import Creature.Creature;
import Equipment.Armor;
import Equipment.EquipmentSlot;
import Equipment.Item;

import java.util.HashMap;
import java.util.Map;
public class EquipmentManager 
{
    Creature creature; // Сущность, которая обладает экипировкой.
    private Map<String, EquipmentSlot> equipmentSlots = new HashMap<>();
    private Boolean canEquipItem = true;
    /**
     * Конструктор класса EquipmentManager.
     *
     * @param creature Сущность, которая будет обладать экипировкой.
     */
    public EquipmentManager(Creature creature)
    {
        this.creature = creature;
        inizializedEquipmentSlots();
    }

    private void inizializedEquipmentSlots()
    {
        // Инициализация слотов для снаряжения
        equipmentSlots.put("Head", new EquipmentSlot());
        equipmentSlots.put("leftHand", new EquipmentSlot());
        equipmentSlots.put("rightHand", new EquipmentSlot());
        equipmentSlots.put("Body", new EquipmentSlot());
        equipmentSlots.put("Legs", new EquipmentSlot());
    }
    /**
     * Метод для экипировки предмета в указанный слот.
     *
     * @param item     Предмет для экипировки.
     * @param slotName Имя слота, в который нужно экипировать предмет.
     */
    public void equipItem(Item item, String slotName) 
    {
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
    /**
     * Метод для снятия предмета из указанного слота.
     *
     * @param slotName Имя слота, из которого нужно снять предмет.
     */
    public void unequipItem(String slotName) 
    {
        EquipmentSlot slot = equipmentSlots.get(slotName);
        if (slot != null) {
            Item item = slot.getEquippedItem();
            if (item != null) {
                slot.unequipItem();
                removeItemEffects(item);
            }
        }
    }

    private void removeItemEffects(Item item) 
    {
        creature.delDefenseModifier(item.getDefenseModifier());
        creature.delMinDamageModifier(item.getMinDamageModifier());
        creature.delMaxDamageModifier(item.getMaxDamageModifier());
        creature.delHealthModifier(item.getHealthModifier());
        creature.delLuckModifier(item.getLuckModifier());
        creature.delAttackModifier(item.getAttackModifier());
    }

    private void applyItemEffects(Item item) 
    {
        creature.setDefenseModifier(item.getDefenseModifier());
        creature.setMinDamageModifier(item.getMinDamageModifier());
        creature.setMaxDamageModifier(item.getMaxDamageModifier());
        creature.setHealthModifier(item.getHealthModifier());
        creature.setLuckModifier(item.getLuckModifier());
        creature.setAttackModifier(item.getAttackModifier());
    }
    /**
     * Метод для получения слотов для снаряжения.
     *
     * @return Слоты для снаряжения.
     */
    public Map<String, EquipmentSlot> getequipmentSlots()
    {
        return equipmentSlots;
    }
    /**
     * Метод для получения сопротивлений, связанных с экипированной броней.
     *
     * @return список сопротивлений.
     */
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
}
