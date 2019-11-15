package Competitor;

/**
 * Класс кот(наследуется от класса животные)
 */
public class Cat extends Animal {
    //конструктор,задающий свои тип, имя и максимальные дистанции для марафона млекопитающего
    public Cat(String name) {
        super("Кот", name, 200, 20, 0);
    }
}
