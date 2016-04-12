package server;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class server {
    private int uniqueId;
    public ArrayList<ClientThread> al;
    private static serverForm sg;
    private static SimpleDateFormat sdf;
    private static boolean keepGoing;
    private static int port;
    private static listOfUsers lu;
    private ClientThread ct;
    private ServerSocket serverSocket;
    public server(int port, serverForm sg) {
            this.sg = sg;
            this.port = port;
            sdf = new SimpleDateFormat("HH:mm:ss");
            al = new ArrayList<>();
    }

    void set_list_user(listOfUsers lu) {
       this.lu = lu;
    }

    public static class ObjectMessenger{
    protected final static int LOGIN=0,QUIT=1,UPDATE=3,ACCESS=4,FILESNAME=5,FILES=6;
    protected static Socket socket;
    protected ObjectOutputStream oos;
    protected ObjectInputStream ois;
    
    public ObjectMessenger(Socket socket){
        this.socket = socket;
    }
    protected void sendObject(String object) throws Exception{   
        oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(object);
        oos.flush();
    }
    protected String receiveObject() throws Exception{
        ois = new ObjectInputStream(socket.getInputStream());
        return (String) ois.readObject();
    }
   
    protected void typeObject(String str,ClientThread ct) throws IOException, Exception{
        String [] mes = str.split(";");
        System.out.println("3");
        switch(Integer.parseInt(mes[0])){
            case LOGIN:{
                System.out.println("9");
                ct.set_login(mes[1],mes[2],mes[3],mes[4]); //имя,фамилия,телефон,email,mac
                if(lu.check_user_in_list(ct.get_email())){
                    sendObject(ACCESS+";"+true);
                    display("Клиент "+ct.get_name()+" "+ct.get_secondname()+" подключился.");
                    lu.set_online(ct.get_email(),true);
                }else{
                    //sendObject(ACCESS+";"+false);
                    //ct.close();
                    display("Новый клиент "+ct.get_name()+" "+ct.get_secondname()+" добавился.");}
                    lu.add_new_user(ct.get_user());
            };break;
            case QUIT:{
                lu.set_online(ct.get_email(),false);
                display("Клиент "+ct.get_name()+" "+ct.get_secondname()+" отключился.");
                ct.close();
            };break;
            default:display("Неверное сообщение: "+ str);break;
        }
     }
    }
    
    protected void start() throws Exception {
        if(serverSocket != null) stop();
        keepGoing = true;
        try{
            serverSocket = new ServerSocket(port);
            while(keepGoing){
                display("Ждем клиентов на порту "+port+".");
                Socket socket = serverSocket.accept();// accept connection
                if(!keepGoing)  break;
                ClientThread t = new ClientThread(socket);
                al.add(t);// save it in the ArrayList
                t.start();
            }
            stop();
        }
        catch (IOException e) {
        display("Сервер остановлен.");
        }
    }		

    protected void stop() throws IOException {
        keepGoing = false;
        serverSocket.close();
        for(int i = 0; i < al.size(); ++i) {
            ClientThread tc = al.get(i);
            tc.close();
        }
    }

    private static void display(String msg) {
        sg.appendEvent(sdf.format(new Date()) + " " + msg + "\n");
    }

    // for a client who logoff
    synchronized void remove(int id) {
        for(int i = 0; i < al.size(); ++i) {
            ct = al.get(i);
            if(ct.id == id) {al.remove(i);return;}
        }
    }

    class ClientThread extends Thread {
        // the socket where to listen/talk
        protected Socket socket;
        private int id;
        protected user user;
        private ObjectMessenger om;
        
        ClientThread(Socket socket) throws Exception {
            // a unique id
            id = ++uniqueId;
            user = new user();
            this.socket = socket;
            try{  
               om = new ObjectMessenger(socket);
               om.typeObject(om.receiveObject(),this);
            }
            catch (IOException e) {
                display("Ошибка записи/чтения в/из поток(-а): " + e);
            }
            catch (ClassNotFoundException e) {}
        }
        
        // what will run forever
        @Override
        public void run() {
            // to loop until LOGOUT
            boolean keepGoing = true;
            while(keepGoing) {
                // read a String
                try {
                    om.typeObject(om.receiveObject(),this);
                }
                catch (IOException e) {
                    user.set_online(false);
                    display(get_name() + " отключился или произошел сбой.");
                    break;				
                }
                catch (Exception ex) {}
            }
            try {remove(id);close();} catch (IOException ex) {}
        }
        String get_name(){return user.get_name();}
        String get_secondname(){return user.get_secondname();}
        String get_telephone(){return user.get_telephone();}
        String get_email(){return user.get_email();}
        // try to close everything
        private void close() throws IOException {
            if(socket != null) socket.close();
        }
        private void set_login(String username,String secondname,
        String telephone,String email) {
            user.set_login(username,secondname,telephone,email);
        }
        protected user get_user(){
            return user;
        }
    }
}



