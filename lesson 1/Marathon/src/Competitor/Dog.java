package Competitor;

/**
 * Класс собака(наследуется от класса животные)
 */
public class Dog extends Animal {
    //конструктор,задающий свои тип, имя и максимальные дистанции для марафона млекопитающего
    public Dog(String name) {
        super("Пес", name, 500, 5, 20);
    }
}
