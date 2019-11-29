import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

/**
 *Необходимо из консоли считать пароль и проверить валидность,
 * результат будет true или false
 *
 * Требования:
 * 1. Пароль должен быть не менее 8ми символов.
 * 2. В пароле должно быть число
 * 3. В пароле должна быть хотя бы 1 строчная буква
 * 4. В пароле должна быть хотя бы 1 заглавная буква
 * 5. Не должно быть пробелов
 */
public class password {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("Введите пароль:");
            System.out.println("1. Пароль должен быть не менее 8ми символов."); //.{8,20}
            System.out.println("2. В пароле должно быть число");  //(?=.*[0-9])
            System.out.println("3. В пароле должна быть хотя бы 1 строчная буква"); //(?=.*[a-z])
            System.out.println("4. В пароле должна быть хотя бы 1 заглавная буква"); //(?=.*[A-Z])
            System.out.println("5. Не должно быть пробелов"); //(?=\\S+$) - берёт строку до пробела, всё что после пробела не учитывается
        }while(!checkPassword(scanner.next()));
        System.out.println("Пароль валидный!");

}

    //метод проверки пароля на валидность
    public static boolean checkPassword(String str) {
        boolean result;
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}";
        result = str.matches(pattern);
        return result;
    }

}