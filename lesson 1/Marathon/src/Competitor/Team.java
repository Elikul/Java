package Competitor;


/**
 * Класс команда
 */
public class Team {
    String nameTeam; //название команды
    Competitor[] competitors ={
            new Human("David"),
            new Human("kitty"),
            new Dog("Pit"),
            new Cat ("Funny")
    };//участники команды марафона


    //конструктор
    public Team(String _nameTeam){
        this.nameTeam = _nameTeam;

    }

    //метод получения участников марафона
    public Competitor[] getCompetitors(){
        return competitors;
    }


    //метод вывода информации о членах команды, прошедших дистанцию
    public void SucceedsDistance(){
        for (Competitor obj: competitors) {
            if (obj.isOnDistance())
                obj.info();
        }
    }


    //метод вывода информации обо всех членах команды
    public void ShowResults(){
        for (Competitor obj : competitors) {
            obj.info();
        }
    }



}
