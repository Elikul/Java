
import static java.lang.Math.cos;
import static java.lang.Math.sin;
/**
 * 1) Создают одномерный длинный массив, например:
 * static final int size = 10000000;
 * static final int h = size / 2;
 * float[] arr = new float[size];
 * 2) Заполняют этот массив единицами;
 * 3) Засекают время выполнения: long a = System.currentTimeMillis();
 * 4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
 * arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
 * 5) Проверяется время окончания метода System.currentTimeMillis();
 * 6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);
 * Отличие первого метода от второго:
 * Первый просто бежит по массиву и вычисляет значения.
 * Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.
 *
 * Пример деления одного массива на два:
 * System.arraycopy(arr, 0, a1, 0, h);
 * System.arraycopy(arr, h, a2, 0, h);
 *
 * Пример обратной склейки:
 * System.arraycopy(a1, 0, arr, 0, h);
 * System.arraycopy(a2, 0, arr, h, h);
 *
 * Примечание:
 * System.arraycopy() копирует данные из одного массива в другой:
 * System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение, откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)
 * По замерам времени:
 * Для первого метода надо считать время только на цикл расчета:
 * for (int i = 0; i < size; i++) {
 * arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
 * }
 * Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.
 */
public class MyThread {
    final int size = 10000000;  //длина одномерного массива
    final int h = size / 2;  //половина длины одномернго массива
    final int threads_count = 5; //количество потоков
    final int part_size = size/threads_count; // определяем размерность двумерного массива

    //метод заполнения одномерного массива единицами
    public void fillArray(float[] mas){
        for (int i = 0; i < mas.length; i++) {
            mas[i] = 1;

        }
    }

    //первый метод (без использованием потоков)
    public void firstMethod(){
        float[] arr = new float[size];
        fillArray(arr);

        long a = System.currentTimeMillis(); //Засекают время выполнения

        //Проходят по всему массиву и для каждой ячейки считают новое значение по формуле
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Время работы первого метода: " + (System.currentTimeMillis() - a)  + " миллисекунд");
        System.out.println();
    }


    //второй метод (с использованием потоков)
    public void SecondMethod(){
        float[] arr = new float[size];
        fillArray(arr);

        long a = System.currentTimeMillis(); //Засекают время выполнения

        // разделяем данные
        final float[][] m = new float[threads_count][part_size];


        // создадим массив потоков
        Thread[] t = new Thread[threads_count];
        for (int i = 0; i < threads_count; i++) {
            // будем копировать в двумерный массив данные из основного потока со сдвигом
            System.arraycopy(arr, part_size * i, m[i], 0, part_size);
            final int u = i;
            t[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    // считаем массив со сдвигом
                    int n = u *part_size;
                    for (int j = 0; j < part_size; j++, n++) {
                        m[u][j] = (float) (m[u][j] * sin(0.2f + n / 5) * cos(0.2f + n / 5) * cos(0.4f + n / 2));
                    }
                }
            });
            t[i].start();
        }


        //ожидает завершения потоков
        try {
            for (int i = 0; i < threads_count; i++) {
                t[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // складываем массив обратно в исходный массив
        for (int i = 0; i < threads_count; i++) {
            System.arraycopy(m[i], 0, arr, i * part_size, part_size);
        }

        System.out.println("Время работы второго метода: " + (System.currentTimeMillis() - a)  + " миллисекунд");
        System.out.println();
    }

    //точка входа
    public static void main(String[] args) {
        MyThread lesson5 = new MyThread();

        lesson5.firstMethod(); //запускаем первый метод
        lesson5.SecondMethod(); //запускаем второй метод
    }

}
