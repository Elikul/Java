package barriers;

import Competitor.Competitor;

/**
 * Класс забега(наследуется от класса препятсвий)
 */
public class Cross extends Obstacle {
    int length; // дистанция

    //конструктор
    public Cross(int length) {
        this.length = length;
    }

    //перегрузка метод прохождения марафона
    //забег
    @Override
    public void doIt(Competitor competitor) {
        competitor.run(length);
    }
}
