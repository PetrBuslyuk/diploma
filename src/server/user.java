package server;

public class user {
private String name,secondname,tel,email;
private boolean online;
protected int item_number;
    user(boolean online,String name, String secondname, String tel, String email) {
        this.online = online;this.name = name;
        this.secondname = secondname;this.tel = tel;this.email = email;
    }
    user() {
       this.online=false;
       this.name=this.secondname=this.tel=this.email="";
    }
    Object [] get_obj(){
        return new Object []{this.online,this.name,this.secondname,this.tel,
            this.email};
    }
    protected void set_online(boolean str){this.online=str;}
    protected String get_name() {return this.name;}
    protected String get_secondname() {return this.secondname;}    
    protected String get_email(){return this.email;}
    protected String get_telephone(){return this.tel;}
    protected boolean get_online() {return this.online;}
    protected void set_name(String name){this.name=name;}
    protected void set_secondname(String s){secondname=s;}
    protected void set_login(String username,String secondname,
                             String telephone,String email) {
        this.name=username;
        this.secondname=secondname;
        this.email=email;
        this.tel = telephone;
    }
}
