package barriers;

/**
 * Класс препятствий
 */

import Competitor.Competitor;

public abstract class Obstacle {
    //метод прохождения марафона
    public abstract void doIt(Competitor competitor);
}
