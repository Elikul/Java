package barriers;


import Competitor.Competitor;
import Competitor.Team;

/**
 * полоса препятствий
 */
public class Course {
    //массив препятствий
    Obstacle[] line = {
            new Cross(80),
            new Wall(2),
            new Water(60)
    };


    //метод, который  просит команду пройти всю полосу
    public void TeamOnDistance(Team _command){
        for (Competitor obj:_command.getCompetitors()) {
            for (Obstacle sub : line) {
                sub.doIt(obj);
                if (!obj.isOnDistance()) break;
            }
        }
    }
}
