package Managers;

import Creature.Creature;
import Equipment.Armor;
import Equipment.EquipmentSlot;
import Equipment.Item;

import java.util.HashMap;
import java.util.Map;
public class EquipmentManager 
{
    Creature creature;
    private Map<String, EquipmentSlot> equipmentSlots = new HashMap<>();
    private Boolean canEquipItem = true;

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

    public Map<String, EquipmentSlot> getequipmentSlots()
    {
        return equipmentSlots;
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
}
