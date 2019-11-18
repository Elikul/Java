package Main;


import Competitor.*;
import Competitor.Team;
import barriers.Course;
import barriers.Cross;
import barriers.Wall;
import barriers.Water;

public class Main {
    public static void main(String[] args) {

        Course c = new Course(new Cross(80),new Wall(5),new Water(100)); // Создаем полосу препятствий
        Team team = new Team("Cool grand",new Human("David"),new Human("Kitty"), new Cat("Funny"), new Dog("Pit")); // Создаем команду
        c.TeamOnDistance(team); // Просим команду пройти полосу
        team.ShowResults(); // Показываем результаты
    }
}
