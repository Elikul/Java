package ServerClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
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

//MainServer
public class Server {
    public static void main(String[] args) {
        SRV s = new SRV(); //создаём сервер
        s.start(); //запускаем сервер
        s.catchClient(); //подключаем клиента
        new Thread() {
            public void run() {
                while (true) {
                    String txt = null;
                    try {
                        txt = s.in.readLine(); //считываем сообщение из консоли
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (txt != null) {
                        s.sendMessage(txt); //отправляем сообщение
                    }
                }
            }
        }.start();

        new Thread() {
            public void run() {
                s.writeToConsole(); //выводим сообщение на консоль
            }
        }.start();

    }
}

//север
class SRV {
    BufferedReader in = null;  //читает текст из потока ввода символов
    PrintWriter out = null;   //вывода на консоль
    ServerSocket serverSocket = null;  //создает несвязанный сокет сервера
    Socket socket = null;   //сокет
    BufferedReader console = null; //читает текст из потока ввода символов

    //метод запуска сервера
    void start() {
        try {
            serverSocket = new ServerSocket(8189);
        } catch (IOException e) {
            System.out.println("Can't open port 8189");
            System.exit(1);
        }
        System.out.print("Server started. Waiting for a client...");
    }

    //метод подключения клиента
    void catchClient() {
        try {
            socket = serverSocket.accept();
            System.out.println("Client connected");
        } catch (IOException e) {
            System.out.println("Can't accept");
            System.exit(1);
        }

        try {
            in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Wait for messages...");
    }

    //метод отправки сообщений
    void sendMessage(String msg) {
        if (msg.equalsIgnoreCase("/exit")) {
            close();
        }
        out.println("Server ::: " + msg);
        System.out.println(msg);
    }

    //метод закрытия выходного и входного потоков, сокета и сервера
    void close() {
        try {
            in.close();
            out.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //метод вывода на консоль сообщения
    void writeToConsole() {
        while (true) {
            console = new BufferedReader(new InputStreamReader(System.in));
            String txt = null;
            try {
                txt = console.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            sendMessage(txt);
        }
    }
}
