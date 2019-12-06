package Lesson_6.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

public class MainServ {
    private Vector<ClientHandler> clients; //клиенты

    //конструктор
    public MainServ() {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;
        try {
            AuthService.connect(); //подключаем авторизацию
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился!");
                new ClientHandler(this, socket); //создание нового клиента
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect(); //отключаем авторизацию
        }
    }


    //кому и от кого отправляем сообщение
    private void adressMsg(String fromNick, String msg) {
        String[] msgAdr = msg.split(" ");
        if (msgAdr.length < 3) return;

        String toNick = msgAdr[1];
        String mess = msg.substring(toNick.length() + 3);

        for (ClientHandler o : clients) {
            String clientNick = o.getNick();
            if (clientNick.equals(toNick) || clientNick.equals(fromNick))
                o.sendMsg("from: " + fromNick + " to: " + toNick + " " + mess);
        }
    }


    // метод для рассылки сообщения всем клиентам
    public void broadcastMsg(String nick, String msg) {
        //Реализовать отправку личных сообщений /w nick1 Hello
        if (msg.startsWith("/w")) {
            adressMsg(nick, msg);
        }
        else {
            for (ClientHandler o : clients) {
                o.sendMsg(nick + ": " + msg);
            }
        }
    }

    // подписываем клиента и добавляем его в список клиентов
    public void subscribe(ClientHandler client) {
        clients.add(client);
    }

    // отписываем клиента и удаляем его из списка клиентов
    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

    //проверка повторяющихся логинов
    public boolean checkExistClient(String nickName) {
        for (ClientHandler o: clients) {
            if (nickName.equals(o.getNick()))
                return true;
        }
        return false;
    }
}
