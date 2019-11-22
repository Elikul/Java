package Phonebook;

/**
 * 2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
 * В этот телефонный справочник с помощью метода add() можно добавлять записи.
 * С помощью метода get() искать номер телефона по фамилии. Следует учесть,
 * что под одной фамилией может быть несколько телефонов, тогда при запросе такой фамилии должны выводиться все телефоны.
 */

import java.util.*;

public class Phonebook {
    private static Map<String, Set<String>> phoneBook; //телефонная книга


    //конструктор
    Phonebook(){
        phoneBook = new TreeMap<>();
    }


    //метод добавления записей в телефонную книгу
    public void add(String _surname,String _phone){
        Set<String> phones = phoneBook.get(_surname);
        if (phones == null)
            phones = new HashSet<String>();
        phones.add(_phone);
        phoneBook.put(_surname,phones);
    }

    //метод получения номеров телефонов человека по его фамилии
    public Set<String> get(String _surname){
        return phoneBook.get(_surname);
    }


    //показать всю телефонную книгу
    public  void showPhoneBook(){
        System.out.println(phoneBook);
    }
}
