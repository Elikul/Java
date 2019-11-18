package barriers;


import Competitor.Competitor;
import Competitor.Team;

/**
 * полоса препятствий
 */
public class Course {
    //массив препятствий
    Obstacle[] line;

    public Course(Obstacle... _line)
    {
        this.line = _line;
    }


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
