package ServerClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        Socket socket = null; //сокет

        try {
            socket = new Socket("localhost", 8189);

            Scanner in = new Scanner(socket.getInputStream()); //для ввода сообщений
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);  //для выводы сообщений
            Scanner console = new Scanner(System.in); //для вывода сообщений на консоль

            //чтение сообщений с сервера
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String str = in.nextLine();
                        if(str.equals("/end")) {
                            out.println("/end");
                            break;
                        }
                        System.out.println("Server " + str);
                    }
                }
            });
            t1.start();

            //отправка сообщений серверу
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println("Введите сообщение");
                        String str = console.nextLine();
                        System.out.println("Сообщение отправлено!");
                        out.println(str);
                    }
                }
            });
            t2.setDaemon(true);
            t2.start();

            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}