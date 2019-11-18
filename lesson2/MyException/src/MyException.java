/**
 * ДЗ с исключениями
 */
public class MyException {

    //исполнение
    public static void main(String[] args) {

        String[][] mas = new String[][]{
                {"1", "2", "3","4"},
                {"5", "6", "7","8"}
        };

        String[][] mas1 = new String[][]{
                {"1", "ваыуа", "3","4"},
                {"5", "6", "7","уауа"}
        };

        String[][] mas2 = new String[][]{
                {"1", "2", "3","4"},
                {"5", "6", "7"}
        };

        try {
            int result = Sum(mas);
            System.out.println("Сумма равна " + result);

//            int result1 = Sum(mas1);
//            System.out.println("Сумма равна " + result1);

//            int result2 = Sum(mas2);
//            System.out.println("Сумма равна " + result2);
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

    }

    //метод суммы
    public static int Sum(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        int result = 0;

        if((arr.length != 4) && (arr[0].length !=4)){
            throw new MyArraySizeException("Неправильный размер двумернного массива! Необходим 4*4.",arr.length,arr[0].length);
        }else  if(arr[1].length !=4){
            throw new MyArraySizeException("Неправильный размер двумернного массива! Необходим 4*4.",arr.length,arr[1].length);
        }


        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++){
                   if(!isInteger(arr[i][j]))
                       throw new MyArrayDataException("Неверные данные в двумернном массиве! Ошибка в ячейке ",i,j);
                    result += Integer.parseInt(arr[i][j]);
            }
        }
        return result;
    }

    //метод: можно ли строку конвертировать в целое число
    public static boolean isInteger(String str){
        try {
            Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

}

/**
 * Исключение неправильный размер двумернного массива
 */
class MyArraySizeException extends Exception{
    private int Nrow,Ncol; //количсетво строк, количество столбцов двумернного массива

    //метод получение количества столбцов
    public int getCol() {
        return Ncol;
    }

    //метод получение количества строк
    public int getRow() {
        return Nrow;
    }

    //конструктор
    public MyArraySizeException(String msg, int row, int col) {
        super(msg);
        this.Nrow = row;
        this.Ncol = col;
    }
}


/**
 * Исключение неправильных данных(не чисел) в двумернном массиве
 */
class MyArrayDataException extends Exception{
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