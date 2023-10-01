package Equipment;



public class EquipmentSlot
{

    private Item equippedItem;

    public EquipmentSlot()
    {
        super();
    }

    public void equipItem(Item item) {
        this.equippedItem = item;
    }

    public void unequipItem() {
        this.equippedItem = null;
    }

    public Item getEquippedItem() {
        return equippedItem;
    }
}
