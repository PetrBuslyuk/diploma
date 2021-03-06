package client;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class client {
    public static Socket socket;
    private static clientProfile cg;
    private static companies comp;
    public static ObjectMessenger om;
    private String server="localhost";
    private static String email;
    protected final static int LOGIN=0,QUIT=1,SAVE_DATA=5,RESTORE_DATA=6;
    private final int port;
    private static boolean access=true;
    static boolean sendedOurData=false;
        
    client(String server,int port,clientProfile cg, String e){
        this.server = server;
        this.port = port;
        this.cg = cg;
        this.email = e;
    }
    void set_comp(companies comp){
       this.comp = comp;
    }
    private void display(String msg) {cg.append(msg + "\n");}
    
//Client-server arc
    public boolean start(){
        try {
            // try to connect to the server
            try {socket = new Socket(server, port);}
            catch(Exception ec) {return false;}
            om = new ObjectMessenger(socket);
            // creates the Thread to listen from the server
            new ListenFromServer().start();
            //send username data
                if(check_connect()){
                    sendData();
                }else{
                    sendedOurData = false;
                    disconnect();return false;
                }
        }
        catch(IOException ex) {}
        return true; 
    }
    protected void sendData(){
        try {
            om.sendObject(LOGIN,cg.get_firstname()+";"+cg.get_secondname()+";"
                    +cg.get_telephone()+";"+cg.get_email());
            sendedOurData = true;
        } catch (IOException ex) {}
    }
    
    protected static boolean check_connect(){return socket.isConnected();}
    protected void close_main_window(){
        if(socket != null){
            try {
            om.sendObject(QUIT,"");
            } catch (IOException ex) {}
        }
        System.exit(0);
    }

    void sendDataToServer() {
        new SendDataToServer().start();
    }
    
    void restoreDataFromServer() {
        new ClientThreadSaveData().start();
    }
    
    public static class ObjectMessenger{
        protected static Socket socket;
        protected ObjectOutputStream oos;
        protected ObjectInputStream ois;
        protected ObjectMessenger (Socket socket) throws UnknownHostException, IOException{
            this.socket = socket;
        }
        protected void sendObject (int type,Object object) throws IOException{
            if(check_connect()){
                oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(type+";"+object);
                oos.flush();
            }
        }
        protected String receiveObject() throws IOException, ClassNotFoundException{
            if(check_connect()){
            ois = new ObjectInputStream(socket.getInputStream());
            return (String) ois.readObject();
            }else return QUIT+";";
        }
        protected void setType(String receiveObject) throws IOException {
            String [] mes = receiveObject.split(";");
            switch(Integer.parseInt(mes[0])){
                case QUIT:{
                    disconnect();
                }
                case RESTORE_DATA:{
                    new ClientThreadSaveData().start();
                }
                break;
            }
        }
    }
    static class ClientThreadSaveData extends Thread{
        public  void run() {
            try {
                if (socket==null){
                    JOptionPane.showMessageDialog(null, "Cоединение с сервером закрыто. \nПерезайдите в программу.");
                    return ;
                }else{
                    if (!socket.isBound()){
                        companies.log("few1");
                         JOptionPane.showMessageDialog(null, "Cоединение с сервером закрыто. \nПерезайдите в программу.");
                         return ;
                    }else{
                        companies.log("few");
                    }                   
                }
                ServerSocket servSocket;
                Socket fromClientSocket;
                int cTosPortNumber = 1777;
                String str1;
                companiesToSent comp;
                servSocket = new ServerSocket(cTosPortNumber);
                om.sendObject(RESTORE_DATA,email);
                System.out.println("Waiting for a connection on " + cTosPortNumber);
                
                fromClientSocket = servSocket.accept();
                
                ObjectOutputStream oos = new ObjectOutputStream(fromClientSocket.getOutputStream());

                ObjectInputStream ois = new ObjectInputStream(fromClientSocket.getInputStream());

                while ((comp = (companiesToSent) ois.readObject()) != null) {
                    companies.setCompanies(comp);
                    oos.writeObject("bye bye");
                    break;
                }
                oos.close();
                ois.close();
                fromClientSocket.close();
                servSocket.close();
            } catch (IOException | ClassNotFoundException ex) {
            }
        }
    }
    private static void disconnect() throws IOException {
        if(socket != null) socket.close();
        cg.connectionFailed();
    }
    public class ListenFromServer extends Thread {
        @Override
        public void run(){
             
            while(true){
                try{
                    String msg = om.receiveObject();
                    om.setType(msg);
                    cg.append(msg);
                }catch(IOException e){
                    try {
                        disconnect(); break;
                    } catch (IOException ex) {
                    
                    }
                } catch (ClassNotFoundException ex) {
                
                }
            }
        }
    }
    
    public class SendDataToServer extends Thread {
        @Override
        public void run(){
            try {
                if (socket==null){
                    JOptionPane.showMessageDialog(null, "Cоединение с сервером закрыто. \nСохраните данные локально.");
                    return ;
                }
                om.sendObject(SAVE_DATA,email);
                Socket socket1;
                int portNumber = 1777;
                String str = "";
                
                socket1 = new Socket(InetAddress.getLocalHost(), portNumber);
                
                ObjectInputStream ois = new ObjectInputStream(socket1.getInputStream());
                
                ObjectOutputStream oos = new ObjectOutputStream(socket1.getOutputStream());
                
                companiesToSent companiesSent = new companiesToSent();
                companiesSent.setCompanies(companies.getCompaniesToObject());
                
                oos.writeObject(companiesSent);
                
                while ((str = (String) ois.readObject()) != null) {
                    System.out.println(str);
                    oos.writeObject("bye");
                    
                    if (str.equals("bye"))
                        break;
                }
                
                ois.close();
                oos.close();
                socket1.close();
            } catch (IOException | ClassNotFoundException ex) {
            }
        }
    }
}