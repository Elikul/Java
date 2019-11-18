/**
 * Дз на enum
 */
public enum DayOfWeek {
    MONDAY("Рабочий день.",40), TUESDAY("Рабочий день.",32), WEDNESDAY("Рабочий день.",24),
    THURSDAY("Рабочий день.",16), FRIDAY("Рабочий день.",8), SATURDAY("Выходной день.",0), SUNDAY("Выходной день.",0);

    private int WorkingHours; //рабочие часы
    private String isWorkingDay; //сообщение:рабочий день ии выходной


    //конструктор
    DayOfWeek(String _isWorkingDay,int _WorkingHours){
        this.WorkingHours = _WorkingHours;
        this.isWorkingDay = _isWorkingDay;
    }

    //метод получения рабочих часов
    public int getWorkingHours() {
        return WorkingHours;
    }

    //метод установки рабочих часов
    public void setWorkingHours(int workingHours) {
        WorkingHours = workingHours;
    }

    //метод получения сообщения
    public String getMsg() {
        return isWorkingDay;
    }

    //метод установки рабочих часов
    public void setMsg(String msg) {
        this.isWorkingDay = msg;
    }
}

/**
 * Main
 */
class DayOfWeekMain {

    public static void main(final String[] args) {
        getWorkingHours(DayOfWeek.WEDNESDAY);
    }

    //метод вывода информации о рабочем дне
    private static void getWorkingHours(DayOfWeek day) {
        System.out.println(day.getMsg()+" Осталось: "+day.getWorkingHours() + " часов.");
    }

}