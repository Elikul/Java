package Competitor;

/**
 * Класс человек
 */
public class Human implements Competitor {
    String name;// имя

    int maxRunDistance; //максимальная дистнация на бег
    int maxJumpHeight; //максимальная высота прыжка
    int maxSwimDistance; //максимальная дистнация на заплыв

    boolean active; ////прошёл дистанцию? true-да, false-нет


    //перегрузка метод проверки: проходит дистаницию или нет
    @Override
    public boolean isOnDistance() {
        return active;
    }

    //конструктор
    public Human(String name) {
        this.name = name;
        this.maxRunDistance = 5000;
        this.maxJumpHeight = 30;
        this.maxSwimDistance = 200;
        this.active = true;
    }

    //перегрузка метода бега
    @Override
    public void run(int dist) {
        if (dist <= maxRunDistance) {
            System.out.println(name + " хорошо справился с кроссом");
        } else {
            System.out.println(name + " не справился с кроссом");
            active = false;
        }
    }

    //перегрузка метода прыжка
    @Override
    public void jump(int height) {
        if (height <= maxJumpHeight) {
            System.out.println(name + " удачно перепрыгнул через стену");
        } else {
            System.out.println(name + " не смог перепрыгнуть стену");
            active = false;
        }
    }

    //перегрузка метода заплыва
    @Override
    public void swim(int dist) {
        if (dist <= maxSwimDistance) {
            System.out.println(name + " отлично проплыл");
        } else {
            System.out.println(name + " не смог проплыть");
            active = false;
        }
    }

    //перегрузка метода вывода информации об участниках
    @Override
    public void info() {
        System.out.println(name + " - " + active);
    }
}
