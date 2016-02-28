package client;

class company {
    private String name,depo,persent,period;
    
    public company(String name,String depo, String persent, String period) {
        this.name=name;this.depo=depo;this.period=period;this.persent=persent;
    }

    company() {this.name="";this.depo="";this.period="";this.persent="";}
    protected void set_name(String name){this.name=name;}
    protected void set_depo(String depo){this.depo=depo;}
    protected void set_persent(String persent){this.persent=persent;}
    protected void set_period(String period){this.period=period;}
    protected String get_name(){return this.name;}
    protected String get_persent(){return this.persent;}
    protected String get_period(){return this.period;}
    protected String get_depo(){return this.depo;}
    protected String get_company_to_string(){
        StringBuilder str = new StringBuilder();
        str.append("<company>\n")
            .append("\t<name>").append(name).append("</name>\n")
            .append("\t<depo>").append(depo).append("</depo>\n")
            .append("\t<persent>").append(persent).append("</persent>\n")
            .append("\t<period>").append(period).append("</period>\n")
        .append("</company>\n");
        return str.toString();
    }
     protected Object [] get_company_to_object(){
         return new Object[]{this.name,this.depo,this.persent,this.period};
     }
     
}
