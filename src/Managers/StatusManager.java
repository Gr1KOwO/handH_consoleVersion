package Managers;

import Creature.Creature;
import Equipment.EquipmentSlot;
import Equipment.Item;
import Equipment.Weapon;
import Status.Status;

import java.util.*;

public class StatusManager
{
    Creature creature;

    private List<Status> activeStatuses;
    public StatusManager(Creature creature)
    {
        this.creature = creature;
        activeStatuses = new ArrayList<>();
    }
   
    public void applyStatusEffects()
    {
        for(Status status : activeStatuses)
        {
            int damage = status.getDamagePerTurn();
            creature.takeDamage(damage);
            System.out.println("Статус: " + status.getName() + " нанес урон " + damage + " " + creature.getName());
        }
        updateStatuses();
    }

    public void applyStatus(Status status, Creature target)
    {
        boolean setStatus = true;
        // Получаем сопротивления от снаряжения
        Map<String, Integer> equippedArmorResistances = target.getEquipmentManager().getEquippedArmorResistances();

        // Проверяем, есть ли сопротивление для данного статуса
        if (equippedArmorResistances.containsKey(status.getName())) {
            int resistance = equippedArmorResistances.get(status.getName());

            // Генерируем случайное число от 0 до 100
            int randomChance = new Random().nextInt(101);

            if (randomChance <= resistance)
            {
                // Цель сопротивляется статусу
                System.out.println(target.getName() + " устойчив к статусу " + status.getName());
                setStatus = false;
            }
        }

        if (setStatus) {
            // Проверяем, есть ли уже такой статус
            boolean statusExists = false;
            for (Status existingStatus : activeStatuses) {
                if (existingStatus.getName().equals(status.getName())) {
                    existingStatus.resetDuration(); // Сбрасываем длительность статуса
                    statusExists = true;
                    break;
                }
            }

            if (!statusExists) {
                // Если статус не существует, то добавляем его
                System.out.println("На " + target.getName() + " наложился статус " + status.getName());
                target.getStatusManager().addStatus(status);

            }
        }
    }

    public List<Status> getActiveStatus()
    {
        return activeStatuses;
    }

    public void addStatus(Status status)
    {
        activeStatuses.add(status);
    }

    public void updateStatuses() 
    {
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

    public List<Status> getEquippedItemStatuses()
    {
        Map<String, Double> statusChances = new HashMap<>();
        Map<String, Status> statusMap = new HashMap<>();

        for (EquipmentSlot slot : creature.getEquipmentManager().getequipmentSlots().values()) {
            Item equippedItem = slot.getEquippedItem();
            if (equippedItem instanceof Weapon weapon) {
                for (Status status : weapon.getStatuses()) {
                    String statusName = status.getName();
                    double statusChance = status.getChance();

                    if (statusChances.containsKey(statusName)) {
                        // Если статус уже есть в мапе, добавляем шанс к существующему
                        double existingChance = statusChances.get(statusName);
                        statusChances.put(statusName, existingChance * statusChance);
                    } else {
                        // Если статус отсутствует в мапе, просто добавляем его
                        statusChances.put(statusName, statusChance);
                        // Сохраняем статус в мапе для доступа к параметрам duration и damagePerTurn
                        statusMap.put(statusName, status);
                    }
                }
            }
        }

        List<Status> equippedItemStatuses = new ArrayList<>();
        for (Map.Entry<String, Double> entry : statusChances.entrySet()) {
            String statusName = entry.getKey();
            double combinedChance = entry.getValue();
            Status status = statusMap.get(statusName); // Получаем сохраненный статус

            // Создаем новый статус с комбинированным шансом и сохраненными параметрами
            Status newStatus = new Status(statusName, status.getDuration(), status.getDamagePerTurn(), combinedChance);
            equippedItemStatuses.add(newStatus);
        }

        return equippedItemStatuses;
    }

    public void printEquippedItemStatuses() {
        System.out.println("Имя Персонажа: " + creature.getName());
        List<Status> equippedItemStatuses = getEquippedItemStatuses();

        for (Status status : equippedItemStatuses) {
            System.out.println("Имя статуса: " + status.getName());
            System.out.println("Длительность: " + status.getDuration());
            System.out.println("Урон в ход: " + status.getDamagePerTurn());
            System.out.println("Шанс наложения: " + status.getChance());
            System.out.println();
        }
    }
}
