package Phonebook;
/**
 * 2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
 * В этот телефонный справочник с помощью метода add() можно добавлять записи.
 * С помощью метода get() искать номер телефона по фамилии. Следует учесть,
 * что под одной фамилией может быть несколько телефонов, тогда при запросе такой фамилии должны выводиться все телефоны.
 */

import java.util.*;

public class main {
    public static void main(String[] args) {


        Phonebook pb = new Phonebook(); //сохдание экземпляра телефонной книги


        //добавление записей в телефонную книгу
        pb.add("Иванов","+7(910)345-34-45");
        pb.add("Иванов","+7(914)235-39-40");
        pb.add("Петров","+7(960)142-00-11");
        pb.add("Сидоров","+7(914)686-12-46");
        pb.add("Кравченко","+7(923)232-33-88");
        pb.add("Сидоров","+7(900)345-13-14");
        pb.add("Сидоров","22-44-55");
        pb.add("Баранов","12-46-35");

        pb.showPhoneBook(); //показываем, что на данный момент в телефонной книге

        System.out.println("Сидоров : " + pb.get("Сидоров")); //смотрим телефоны Сидорова
    }
}