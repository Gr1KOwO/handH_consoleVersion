package Equipment;

public class ArmorResistance {
    private String statusName;// Имя статуса, к которому применяется сопротивление.
    private int resistanceLevel;// Процент сопротивления.
    /**
     * Конструктор класса ArmorResistance.
     *
     * @param statusName      Имя статуса, к которому применяется сопротивление.
     * @param resistanceLevel Процент сопротивления.
     */
    public ArmorResistance(String statusName, int resistanceLevel) {
        this.statusName = statusName;
        this.resistanceLevel = resistanceLevel;
    }

    /**
     * Метод для получения имени статуса, к которому применяется сопротивление.
     *
     * @return Имя статуса.
     */
    public String getStatusName() {
        return statusName;
    }
    /**
     * Метод для получения уровня сопротивления.
     *
     * @return Уровень сопротивления.
     */
    public int getResistanceLevel() {
        return resistanceLevel;
    }
}
