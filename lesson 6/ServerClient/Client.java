package ServerClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 1. Написать консольный вариант клиент\серверного приложения,
 * в котором пользователь может писать сообщения, как на клиентской стороне, так и на серверной.
 * Т.е. если на клиентской стороне написать «Привет», нажать Enter,
 * то сообщение должно передаться на сервер и там отпечататься в консоли.
 * Если сделать то же самое на серверной стороне, то сообщение передается клиенту и печатается у него в консоли.
 * Есть одна особенность, которую нужно учитывать: клиент или сервер может написать несколько сообщений подряд.
 * Такую ситуацию необходимо корректно обработать.
 */

//MainClient
public class Client {
    public static void main(String[] args) {
        CLI cli = new CLI(); //создаём клиента
        System.out.println("Client started. Connecting to localhost: 8189");

        new Thread() {
            public void run() {
                cli.readMSG(); //читаем сообщение
            }
        }.start();

        new Thread() {
            public void run() {
                cli.sendMSG(); //отправляем сообщение
            }
        }.start();
    }
}

//клиент
class CLI {
    Socket socket;  //сокет
    BufferedReader in;  //читает текст из потока ввода символов
    PrintWriter out; //вывода на консоль
    BufferedReader console;  //читает текст из потока ввода символов
    String userMSG, serverMSG;

    //конструктор
    public CLI(){
        try {
            socket = new Socket("localhost",8189);
            in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            console = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //метод отправки сообщения
    void sendMSG(){
        while (true) {
            try {
                if ((userMSG = console.readLine()) != null) {
                    out.println(userMSG);
                    if (userMSG.equalsIgnoreCase("/close") || userMSG.equalsIgnoreCase("/exit")) break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //метод чтения сообщения
    void readMSG(){
        while (true) {
            try {
                if ((serverMSG = in.readLine()) != null){
                    System.out.println(serverMSG);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //метод закрытия выходного и входного потоков, сокета
    void close() {
        try {
            out.close();
            in.close();
            console.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
