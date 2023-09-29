package Equipment;

public class ArmorResistance {
    private String statusName;
    private int resistanceLevel;

    public ArmorResistance(String statusName, int resistanceLevel) {
        this.statusName = statusName;
        this.resistanceLevel = resistanceLevel;
    }

    // Геттеры
    public String getStatusName() {
        return statusName;
    }

    public int getResistanceLevel() {
        return resistanceLevel;
    }
}
