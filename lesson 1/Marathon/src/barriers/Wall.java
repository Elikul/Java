package barriers;

import Competitor.Competitor;

/**
 * Класс перепрыгивания через стену(наследуется от класса препятсвий)
 */
public class Wall extends Obstacle {
    int height; //высота

    //конструктор
    public Wall(int height) {
        this.height = height;
    }


    //перегрузка метод прохождения марафона
    //перепрыгивание через стену
    @Override
    public void doIt(Competitor competitor) {
        competitor.jump(height);
    }
}
