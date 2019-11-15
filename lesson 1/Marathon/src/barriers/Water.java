package barriers;

import Competitor.Competitor;

/**
 * Класс заплыва(наследуется от класса препятсвий)
 */
public class Water extends Obstacle {
    int length; //дистанция

    //конструктор
    public Water(int length) {
        this.length = length;
    }


    //перегрузка метод прохождения марафона
    //заплыв
    @Override
    public void doIt(Competitor competitor) {
        competitor.swim(length);
    }
}
