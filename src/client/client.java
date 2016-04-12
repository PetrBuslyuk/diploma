package client;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class client{
    public static Socket socket;
    private static clientProfile cg;
    private static companies comp;
    public ObjectMessenger om;
    private String server="localhost";
    protected final static int LOGIN=0,QUIT=1;
    private final int port;
    private static boolean access=true;
    static boolean sendedOurData=false;
        
    client(String server,int port,clientProfile cg){
        this.server = server;
        this.port = port;
        this.cg = cg;
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
        } catch (IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                break;
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
                    } catch (IOException ex) {}
                } catch (ClassNotFoundException ex) {}
            }
        }
    }
}