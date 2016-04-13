package client;

import java.math.BigDecimal;
import java.util.ArrayList;

class company {
    private String name,depo,persent,period;
    private final ArrayList<String>  plus, minus, reinvesting;
    private final ArrayList<Double> depofirst, depolast, resultbuffer;
         
    public company(String _name,String _depo, String _persent, String _period,
            ArrayList<String> _plus,ArrayList<String> _minus,ArrayList<String> _reinvesting) {
        name=_name;depo=_depo;period=_period;persent=_persent;
        plus = _plus;
        minus = _minus;
        reinvesting = _reinvesting;
        depofirst = new ArrayList<>();
        depolast = new ArrayList<>();
        resultbuffer = new ArrayList<>();
    }

    protected void set_name(String _name){name=_name;}
    protected void set_depo(String _depo){depo=_depo;}
    protected void set_persent(String _persent){persent=_persent;}
    protected void set_period(String _period){period=_period;}
    protected void set_plus(String _plus){plus.add(_plus);}
    protected void set_minus(String _minus){minus.add(_minus);}
    protected void set_reinvesting(String _reinvesting){reinvesting.add(_reinvesting);}
    protected void set_plus(int i,String _plus){plus.set(i,_plus);}
    protected void set_minus(int i,String _minus){minus.set(i,_minus);}
    protected void set_reinvesting(int i,String _reinvesting){reinvesting.set(i,_reinvesting);}
    protected void set_depofirst(int i,double df){depofirst.set(i,df);}
    protected void set_depolast(int i,double dl){depolast.set(i,dl);}
    protected void set_resultbuffer(int i,double rb){resultbuffer.set(i,rb);}
    protected ArrayList get_depolast(){return depolast;}
    protected double get_depolast(int i){return depolast.get(i);}
    protected double get_depofirst(int i){return depofirst.get(i);}
    protected double get_resultbuffer(int i){return resultbuffer.get(i);}
    protected ArrayList get_depofirst(){return depofirst;}
    protected String get_name(){return name;}
    protected String get_persent(){return persent;}
    protected String get_period(){return period;}
    protected String get_depo(){return depo;}
    protected String get_plus(int i){return plus.get(i);}
    protected String get_minus(int i){return minus.get(i);}
    protected boolean get_reinvesting(int i){return Boolean.parseBoolean(reinvesting.get(i));}
    protected ArrayList get_plus(){return plus;}
    protected ArrayList get_minus(){return minus;}
    protected ArrayList get_reinvesting(){return reinvesting;}
    protected String get_company_to_string(){
        log("here");
        StringBuilder str = new StringBuilder();
        str.append("<company>\n")
            .append("\t<name>").append(name).append("</name>\n")
            .append("\t<depo>").append(depo).append("</depo>\n")
            .append("\t<persent>").append(persent).append("</persent>\n")
            .append("\t<period>").append(period).append("</period>\n")
            .append("\t<intervals>\n");
            int period1 = Integer.parseInt(period);
               for(int i=0; i < period1;i++){
                   str.append("\t<i>\n")
                           .append("\t  <plus>");
                           str.append((plus.size()>i)?plus.get(i):"0");
                           str.append("</plus>\n");
                           str.append("\t  <minus>");
                           str.append((minus.size()>i)?minus.get(i):"0");
                           str.append("</minus>\n");
                           str.append("\t  <reinvesting>");
                           str.append((reinvesting.size()>i)?reinvesting.get(i):"false");
                           str.append("</reinvesting>\n");
                   str.append("\t</i>\n");
               }
            str.append("</intervals>\n")
        .append("</company>\n");
        return str.toString();
    }
     protected Object [] get_company_to_object(){
         return new Object[]{name,depo,persent,period};
     }
     protected String [] get_company_to_array_in_report(){
         int size = Integer.parseInt(get_period()) * 6;
         String [] result = new String[size];
         int i = 0,a = 0;
         while(i!=size){
             result[i]=Integer.toString(a);++i;
             result[i]=get_plus(a);++i;
             result[i]=get_minus(a);++i;
             result[i]=Boolean.toString(get_reinvesting(a));++i;
             result[i]=Double.toString(get_depofirst(a));++i;
             result[i]=Double.toString(get_depolast(a));++i;
             ++a;
         }
         return result;
     }
     company getCalculateCompany(){
        double depo1 = Double.parseDouble(get_depo());
        double persent1 = Double.parseDouble(get_persent());
        int period1 = Integer.parseInt(get_period());
        ArrayList<String> plus1 = get_plus();
        ArrayList<String> minus1 = get_minus();
        ArrayList<String> reinvesting1 = get_reinvesting();
        double  plus2,minus2;
        depofirst.clear();
        depolast.clear();
        resultbuffer.clear();
        for(int i=0;i<period1;i++){
            depofirst.add(0.0);
            depolast.add(0.0);
            resultbuffer.add(0.0);
        }
        for(int i=0;i<period1;i++){
            plus2 = (plus1.get(i).equals(""))? 0: Double.parseDouble(plus1.get(i));
            minus2 = (minus1.get(i).equals(""))? 0:Double.parseDouble(minus1.get(i));
            if(Boolean.parseBoolean(reinvesting1.get(i))){
                set_depofirst(i,(i==0)?depo1:
                    ((Boolean.parseBoolean(reinvesting1.get(i-1)))?
                        BigDecimal.valueOf(get_depolast(i-1)+ plus2).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue():
                        BigDecimal.valueOf(get_depofirst(i-1)+ plus2).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue()));
                set_resultbuffer(i,get_depofirst(i)*(persent1/100) - minus2);
                set_depolast(i,BigDecimal.valueOf(get_resultbuffer(i)+get_depofirst(i)).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue());
            }else{
                set_depofirst(i,(i==0)?depo1:
                        ((Boolean.parseBoolean(reinvesting1.get(i-1)))?BigDecimal.valueOf(get_depolast(i-1)+ plus2).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue():
                        BigDecimal.valueOf(get_depofirst(i-1)+ plus2).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue()
                ));
                set_resultbuffer(i,get_depofirst(i)*(persent1/100) - minus2);
                set_depolast(i,BigDecimal.valueOf(get_resultbuffer(i)+get_depofirst(i)+((i==0)?0:get_resultbuffer(i-1))).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue());
            }
        }
        return this;
     }
     
     static void log(Object o){
        System.out.println(o);
    }
}
