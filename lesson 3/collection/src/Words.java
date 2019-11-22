import java.util.*;

/**
 * 1. Создать массив с набором слов (10-20 слов, среди которых должны встречаться повторяющиеся).
 * Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
 * Посчитать, сколько раз встречается каждое слово.
 */
public class Words {

    public static void main(String[] args) {

        List<String> words = new LinkedList<>(); //Создаём свзяный список из слов

        //добавляем слова в список
        words.add("Lamborghini");
        words.add("BMW");
        words.add("Chevrolet");
        words.add("Cadillac");
        words.add("Kia");
        words.add("Nissan");
        words.add("Nissan");
        words.add("Nissan");
        words.add("Ford");
        words.add("Lamborghini");
        words.add("Kia");
        words.add("Cadillac");
        words.add("Kia");
        words.add("Cadillac");
        words.add("Kia");
        words.add("Kia");
        words.add("Chevrolet");
        words.add("Chevrolet");
        words.add("Chevrolet");
        words.add("Ford");


        //Создаём множество из заданных слов,т.е. получаем только уникальные слова
        Set<String> uniqueWords = new LinkedHashSet<String>(words);
        for(String unique : uniqueWords){
            int count = 0; //для подсчёта количества повторяющихся слов
            for(String str : words){
                if(unique.equals(str))
                    count++;
            }
            System.out.println(unique + " : " + count); //выводим 'уникальное слово : сколько раз встречается'
        }
    }
}
