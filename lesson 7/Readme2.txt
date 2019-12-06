3. Реализовать проверку, занят ли ник:

//проверка повторяющихся логинов
    public boolean checkExistClient(String nickName) {
        for (ClientHandler o: clients) {
            if (nickName.equals(o.getNick()))
                return true;
        }
        return false;
    }


if (newNick != null) {
                                    //Чтобы логины не повторялись
                                    if(serv.checkExistClient(newNick)){
                                        sendMsg("Клиент уже авторизирован!");
                                        continue;
                                    }
                                    sendMsg("/authok");
                                    nick = newNick;
                                    serv.subscribe(ClientHandler.this);
                                    break;
                                }


2. Реализовать личные сообщения так: если клиент пишет «/w nick3 Привет», то только клиенту с ником nick3 должно прийти сообщение «Привет»:


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


//метод получения ника
    public String getNick() {
        return nick;
    }
