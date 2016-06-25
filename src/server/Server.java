package server;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private int uniqueId;
    public ArrayList<ClientThread> al;
    private static ServerForm sg;
    private static SimpleDateFormat sdf;
    private static boolean keepGoing;
    private static int port;
    private static ListOfUsers lu;
    private ClientThread ct;
    private ServerSocket serverSocket;
    private static Database db;
    public Server(int port, ServerForm sg) {
        try {
            this.sg = sg;
            this.port = port;
            sdf = new SimpleDateFormat("HH:mm:ss");
            al = new ArrayList<>();
            db = new Database();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
          
        }
    }

    void set_list_user(ListOfUsers lu) {
       this.lu = lu;
    }

    public static class ObjectMessenger{
    protected final static int LOGIN=0,QUIT=1,UPDATE=3,ACCESS=4,SAVE_DATA=5,RESTORE_DATA=6;
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
                    //lu.getAllUsers();
                    lu.refresh_list();
                    
            };break;
            case QUIT:{
                lu.set_online(ct.get_email(),false);
                display("Клиент "+ct.get_name()+" "+ct.get_secondname()+" отключился.");
                ct.close();
            };break;
            case SAVE_DATA:{
                    String email = mes[1];
                    display("Клиент "+email+" запросил сохранение данных.");
                    new ServerThreadToSaveData(email).start();
                    display("Данные сохранены.");
            };break;
            case RESTORE_DATA:{
                   String email = mes[1];
                   display("Клиент "+email+" запросил восстановление данных.");
                   new SendDataToClient(email).start();
                   display("Данные высланы.");
            };break;
            default:display("Неверное сообщение: "+ str);break;
        }
     }
    public class SendDataToClient extends Thread {
        String email = "";
        SendDataToClient(String e){
            this.email = e;
        }
        @Override
        public void run(){
            try {
                Socket socket1;
                int portNumber = 1777;
                String str = "";
                System.out.println(email);
                socket1 = new Socket(InetAddress.getLocalHost(), portNumber);
                
                ObjectInputStream oisS = new ObjectInputStream(socket1.getInputStream());
                
                ObjectOutputStream oosS = new ObjectOutputStream(socket1.getOutputStream());
                
                oosS.writeObject(db.getObjectToRestore(email));
                
                while ((str = (String) oisS.readObject()) != null) {
                    System.out.println(str);
                    oosS.writeObject("bye");
                    
                    if (str.equals("bye"))
                        break;
                }
                
                oisS.close();
                oosS.close();
                socket1.close();
            } catch (IOException | ClassNotFoundException ex) {
            } catch (Exception ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    static class ServerThreadToSaveData extends Thread{
        String email="";
        ServerThreadToSaveData(String e){
            this.email = e;
        }
        public  void run() {
            try {
                ServerSocket servSocket;
                Socket fromClientSocket;
                int cTosPortNumber = 1777;
                String str1;
                client.companiesToSent comp;

                servSocket = new ServerSocket(cTosPortNumber);
                System.out.println("Waiting for a connection on " + cTosPortNumber);

                fromClientSocket = servSocket.accept();

                ObjectOutputStream oos = new ObjectOutputStream(fromClientSocket.getOutputStream());

                ObjectInputStream ois = new ObjectInputStream(fromClientSocket.getInputStream());

                while ((comp = (client.companiesToSent) ois.readObject()) != null) {
                    db.insertCompanyData(comp, email);
                    
                    oos.writeObject("bye bye");
                    break;
                }
                oos.close();
                ois.close();
                fromClientSocket.close();
                servSocket.close();
            } catch (IOException | ClassNotFoundException ex) {
            } catch (SQLException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    class ClientThread extends Thread {
        // the socket where to listen/talk
        protected Socket socket;
        private int id;
        protected User user;
        private ObjectMessenger om;
        
        ClientThread(Socket socket) throws Exception {
            // a unique id
            id = ++uniqueId;
            user = new User();
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
        protected User get_user(){
            return user;
        }
    }
}



