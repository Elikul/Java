package Competitor;

/**
 * Класс животные
 */
public class Animal implements Competitor {
    String type; //тип млекопитающего(например, человек, кот и т.д)
    String name;  //имя млекопитающего

    int maxRunDistance; //максимальная дистнация на бег
    int maxJumpHeight; //максимальная высота прыжка
    int maxSwimDistance;  //максимальная дистнация на заплыв

    boolean onDistance; //прошёл дистанцию? true-да, false-нет

    //перегрузка метод проверки: проходит дистаницию или нет
    @Override
    public boolean isOnDistance() {
        return onDistance;
    }


    //конструктор
    public Animal(String type, String name, int maxRunDistance, int maxJumpHeight, int maxSwimDistance) {
        this.type = type;
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.maxSwimDistance = maxSwimDistance;
        this.onDistance = true;
    }


    //перегрузка метода бега
    @Override
    public void run(int dist) {
        if (dist <= maxRunDistance) {
            System.out.println(type + " " + name + " хорошо справился с кроссом");
        } else {
            System.out.println(type + " " + name + " не справился с кроссом");
            onDistance = false;
        }
    }


    //перегрузка метода прыжка
    @Override
    public void jump(int height) {
        if (height <= maxJumpHeight) {
            System.out.println(type + " " + name + " удачно перепрыгнул через стену");
        } else {
            System.out.println(type + " " + name + " не смог перепрыгнуть стену");
            onDistance = false;
        }
    }


    //перегрузка метода заплыва
    @Override
    public void swim(int dist) {
        if (maxSwimDistance == 0) {
            System.out.println(type + " " + name + " не умеет плавать");
            onDistance = false;
            return;
        }
        if (dist <= maxSwimDistance) {
            System.out.println(type + " " + name + " отлично проплыл");
        } else {
            System.out.println(type + " " + name + " не смог проплыть");
            onDistance = false;
        }
    }

    //перегрузка метода вывода информации об участниках-животных
    @Override
    public void info() {
        System.out.println(type + " " + name + " - " + onDistance);
    }
}
