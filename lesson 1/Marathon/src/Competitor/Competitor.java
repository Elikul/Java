package Competitor;

/**
 * Класс участник
 */
public interface Competitor {
    void run(int distance);  //метод бега
    void swim(int distance);  //метод заплыва
    void jump(int height);  //метод прыжков
    boolean isOnDistance();  //метод проверки: проходит дистаницию или нет
    void info(); //метод вывода информации
}
