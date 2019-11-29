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
    static final int size = 10000000;  //длина одномерного массива
    static final int h = size / 2;  //половина длины одномернго массива

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

        float[] arr1 = new float[h];
        float[] arr2 = new float[h];

        //деления одного массива на два
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);

        //Проходят по всему массиву и для каждой ячейки считают новое значение по формуле
        //создаём два потока с помощью анонимных классов, один для расчёта новых значений для первого массива, другой - для второго массива
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < arr1.length; i++) {
                    arr1[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < arr2.length; i++) {
                    arr2[i] = (float)(arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });


        //для экспиримента
        //создаём два потока,наследуя  от интерфейса Runnable
//        Thread t1 = new Thread(new MyRunnableClass(arr1));
//        Thread t2 = new Thread(new MyRunnableClass(arr2));

        //создаём два потока,наследуя  от класса Thread
//         Thread t1 = new JThread(arr1);
//         Thread t2 = new JThread(arr2);

        //запуск потоков
        t1.start();
        t2.start();


        //ожидает завершения потоков
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //склйка двух массивов опять в один
        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);

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

//для эксперимента
//Создадаём свой интерфейс Runnable
class MyRunnableClass implements Runnable {
    float[] arr;

    public MyRunnableClass(float[] _arr) {
        this.arr = _arr;
    }

    @Override
    public void run() {
        for (int i = 0; i < arr.length; i++)
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }
}


//Создадаём свой класс на основе Thread
class JThread extends Thread {
    float[] arr;

    JThread(float[] _arr){
        this.arr = _arr;
    }

    @Override
    public void run(){
        for (int i = 0; i < arr.length; i++)
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }
}