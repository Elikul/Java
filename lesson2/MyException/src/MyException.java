import java.util.Random;

/**
 * ДЗ с исключениями
 */
public class MyException {

    //исполнение
    public static void main(String[] args) {

        Random random = new Random();
        String[][] mas = new String[4][4];
        int result = 0;

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                mas[i][j] = String.valueOf(random.nextInt(50));
            }
        }

        try {
           result = Sum(mas);

        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
        System.out.println("Сумма равна " + result);
    }

    //метод суммы
    public static int Sum(String[][] arr){
        int result = 0;

        if(arr.length != 4) throw new MyArraySizeException();
        for(int i = 0; i < arr.length; i++) {
            if(arr[i].length != 4) throw new MyArraySizeException();
            for(int j = 0; j < arr[0].length; j++) {
                try {
                    result += Integer.parseInt(arr[i][j]);
                }catch (NumberFormatException e) {
                    throw new MyArrayDataException("Неверные данные в двумернном массиве! Ошибка в ячейке ",i, j);
                }
            }
        }
        return result;
    }


}

/**
 * Исключение неправильный размер двумернного массива
 */
class MyArraySizeException extends RuntimeException{

    //конструктор
    public MyArraySizeException() {
        super("Неправильный размер двумернного массива! Необходим 4*4.");
    }
}


/**
 * Исключение неправильных данных(не чисел) в двумернном массиве
 */
class MyArrayDataException extends RuntimeException{
    private int Nrow,Ncol; //номер ячейки,где лежат неверные данные

    //метод получение строки,где лежат неверные данные
    public int getCol() {
        return Ncol;
    }

    //метод получение столбца,где лежат неверные данные
    public int getRow() {
        return Nrow;
    }

    //конструктор
    public MyArrayDataException(String msg, int row, int col) {
        super(msg + " " + row + " " +  col) ;
        this.Nrow = row;
        this.Ncol = col;
    }
}