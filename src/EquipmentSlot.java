public class EquipmentSlot
{
    private String name;
    private Item equippedItem;

    public EquipmentSlot(String name) {
        this.name = name;
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
