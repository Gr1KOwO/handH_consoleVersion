package Equipment;
public class EquipmentSlot
{
    private Item equippedItem;// Предмет, который сейчас экипирован в этом слоте.
    /**
     * Конструктор класса EquipmentSlot.
     */
    public EquipmentSlot()
    {
        super();
    }
    /**
     * Метод для экипировки предмета в данный слот.
     *
     * @param item Предмет для экипировки.
     */
    public void equipItem(Item item) {
        this.equippedItem = item;
    }
    /**
     * Метод для снятия предмета из данного слота.
     */
    public void unequipItem() {
        this.equippedItem = null;
    }
    /**
     * Метод для получения предмета, который в данный момент экипирован в слоте.
     *
     * @return Экипированный предмет или null, если слот пуст.
     */
    public Item getEquippedItem() {
        return equippedItem;
    }
}
