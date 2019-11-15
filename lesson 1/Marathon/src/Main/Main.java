package Main;


import Competitor.Competitor;
import Competitor.Team;
import barriers.Course;

public class Main {
    public static void main(String[] args) {

        Course c = new Course(); // Создаем полосу препятствий
        Team team = new Team("Cool grand"); // Создаем команду
        c.TeamOnDistance(team); // Просим команду пройти полосу
        team.ShowResults(); // Показываем результаты
    }
}
