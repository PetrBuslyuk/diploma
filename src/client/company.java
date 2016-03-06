package client;

import java.util.ArrayList;

class company {
    private String name,depo,persent,period;
    private ArrayList<String>  plus, minus, reinvesting;
    public company(String name,String depo, String persent, String period,
            ArrayList<String> plus,ArrayList<String> minus,ArrayList<String> reinvesting) {
           this.name=name;this.depo=depo;this.period=period;this.persent=persent;
        this.plus = plus;
        this.minus = minus;
        this.reinvesting = reinvesting;
    }

    company() {this.name=this.depo=this.period=this.persent="";
        this.plus = new ArrayList<>();
        this.minus = new ArrayList<>();
        this.reinvesting = new ArrayList<>();  
    }
    protected void set_name(String name){this.name=name;}
    protected void set_depo(String depo){this.depo=depo;}
    protected void set_persent(String persent){this.persent=persent;}
    protected void set_period(String period){this.period=period;}
    protected void set_plus(String plus){this.plus.add(plus);}
    protected void set_minus(String minus){this.minus.add(minus);}
    protected void set_reinvesting(String reinvesting){this.reinvesting.add(reinvesting);}
    protected void set_plus(int i,String plus){this.plus.set(i,plus);}
    protected void set_minus(int i,String minus){this.minus.set(i,minus);}
    protected void set_reinvesting(int i,String reinvesting){this.reinvesting.set(i,reinvesting);}
    protected String get_name(){return this.name;}
    protected String get_persent(){return this.persent;}
    protected String get_period(){return this.period;}
    protected String get_depo(){return this.depo;}
    protected String get_plus(int i){return this.plus.get(i);}
    protected String get_minus(int i){return this.minus.get(i);}
    protected boolean get_reinvesting(int i){return Boolean.parseBoolean(this.reinvesting.get(i));}
    protected ArrayList get_plus(){return this.plus;}
    protected ArrayList get_minus(){return this.minus;}
    protected ArrayList get_reinvesting(){return this.reinvesting;}
    protected String get_company_to_string(){
        log("here");
        StringBuilder str = new StringBuilder();
        str.append("<company>\n")
            .append("\t<name>").append(name).append("</name>\n")
            .append("\t<depo>").append(depo).append("</depo>\n")
            .append("\t<persent>").append(persent).append("</persent>\n")
            .append("\t<period>").append(period).append("</period>\n")
            .append("\t<intervals>\n");
               for(int i=0; i < Integer.parseInt(period);i++){
                   str.append("\t<i>\n")
                           .append("\t  <plus>");
                           str.append((!plus.get(i).equals(""))?plus.get(i):"0");
                           str.append("</plus>\n");
                           str.append("\t  <minus>");
                           str.append((!minus.get(i).equals(""))?minus.get(i):"0");
                           str.append("</minus>\n");
                           str.append("\t  <reinvesting>");
                           str.append(reinvesting.get(i));
                           str.append("</reinvesting>\n");
                   str.append("\t</i>\n");
               }
            str.append("</intervals>\n")
        .append("</company>\n");
        return str.toString();
    }
     protected Object [] get_company_to_object(){
         return new Object[]{this.name,this.depo,this.persent,this.period};
     }
     
     double getCalculateCompany(){
        double depo1 = Double.parseDouble(this.get_depo());
        double persent1 = Double.parseDouble(this.get_persent());
        int period1 = Integer.parseInt(this.get_period());
        ArrayList<String> plus1 = this.get_plus();
        ArrayList<String> minus1 = this.get_minus();
        ArrayList<String> reinvesting1 = this.get_reinvesting();
        ArrayList<Double> a =new ArrayList<>();
        double  plus2,minus2;
        for(int i=0;i<period1;i++){
            plus2 = (plus1.get(i).equals(""))? 0: Double.parseDouble(plus1.get(i));
            minus2 = (minus1.get(i).equals(""))? 0:Double.parseDouble(minus1.get(i));
            if(Boolean.parseBoolean(reinvesting1.get(i))){
                depo1 += depo1*(persent1/100)+ plus2 - minus2;
            }else{
                a.add(depo1*(persent1/100)+plus2-minus2);
            }
        }
        for (int i=0;i<a.size();i++){
            depo1 += a.get(i);
        }
        log(depo1);
        return depo1;
     }
     
     static void log(Object o){
        System.out.println(o);
    }
}
