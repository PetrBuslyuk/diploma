package client;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class company implements Serializable{
    private String name,depo,persent,period;
    private final ArrayList<String>  plus, minus, reinvesting;
    private final ArrayList<Double> depofirst, depolast, resultbuffer;
    private static double result; 
    
    public company(){
        name="";depo="";period="";persent="";
        plus = new ArrayList<>();
        minus = new ArrayList<>();
        reinvesting = new ArrayList<>();
        depofirst = new ArrayList<>();
        depolast = new ArrayList<>();
        resultbuffer = new ArrayList<>();
    }
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
    public void set_result(double res){
        result = res;
    }
    public double get_result(){
        return result;
    }
    public void set_name(String _name){name=_name;}
    public void set_depo(String _depo){depo=_depo;}
    public void set_persent(String _persent){persent=_persent;}
    public void set_period(String _period){period=_period;}
    public void set_plus(String _plus){plus.add(_plus);}
    public void set_minus(String _minus){minus.add(_minus);}
    public void set_reinvesting(String _reinvesting){reinvesting.add(_reinvesting);}
    public void set_plus(int i,String _plus){plus.set(i,_plus);}
    public void set_minus(int i,String _minus){minus.set(i,_minus);}
    public void set_reinvesting(int i,String _reinvesting){reinvesting.set(i,_reinvesting);}
    public void set_depofirst(int i,double df){depofirst.set(i,df);}
    public void set_depolast(int i,double dl){depolast.set(i,dl);}
    public void set_resultbuffer(int i,double rb){resultbuffer.set(i,rb);}
    public ArrayList get_depolast(){return depolast;}
    public double get_depolast(int i){return depolast.get(i);}
    public double get_depofirst(int i){return depofirst.get(i);}
    public double get_resultbuffer(int i){return resultbuffer.get(i);}
    public ArrayList get_depofirst(){return depofirst;}
    public String get_name(){return name;}
    public String get_persent(){return persent;}
    public String get_period(){return period;}
    public String get_depo(){return depo;}
    public String get_plus(int i){return plus.get(i);}
    public String get_minus(int i){return minus.get(i);}
    public boolean get_reinvesting(int i){return Boolean.parseBoolean(reinvesting.get(i));}
    public ArrayList get_plus(){return plus;}
    public ArrayList get_minus(){return minus;}
    public ArrayList get_reinvesting(){return reinvesting;}
    public String get_company_to_string(){
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
     public Object [] get_company_to_object(){
         return new Object[]{name,depo,persent,period};
     }
     public String [] get_company_to_array_in_report(){
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
            // Если в этом периоде есть реинвестирование
            if(Boolean.parseBoolean(reinvesting1.get(i))){ 
                //Устанавливаем начальный деп
                set_depofirst(i,(i==0)?depo1:
                        (get_depolast(i-1)<get_depofirst(i-1)) ? 
                                get_depolast(i-1)
                        :
                    ((Boolean.parseBoolean(reinvesting1.get(i-1)))?
                        BigDecimal.valueOf(get_depolast(i-1)+ plus2)
                                .setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue():
                        BigDecimal.valueOf(get_depofirst(i-1)+ plus2)
                                .setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue()));
                
                //Устанавливаем буфер
                set_resultbuffer(i,get_depofirst(i)*(persent1/100) - minus2);
                
                //Устанавливаем конечный
                set_depolast(i,BigDecimal.valueOf(get_resultbuffer(i)+get_depofirst(i))
                        .setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue());
            }else{
            //Если нет реинвестирования
                //Устанавливаем начальный депозит
                set_depofirst(i,(i==0)?
                        depo1:
                        ((Boolean.parseBoolean(reinvesting1.get(i-1)))?
                                BigDecimal.valueOf(get_depolast(i-1)+ plus2)
                                        .setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue():
                          //Если значение последнего депозита меньше чем начальный за этот период, то
                         (get_depolast(i-1)<get_depofirst(i-1))?
                                 //Устанавливаем конечные даные
                                 get_depolast(i-1)
                                 : 
                                 //Иначе устанавливаем начальный предыдущий депозит
                                 BigDecimal.valueOf(get_depofirst(i-1)+ plus2)
                                .setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue()
                        
                ));
                                
                //Устанавливаем конечный
                set_depolast(i,BigDecimal.valueOf(
                        get_depofirst(i)+get_depofirst(i)*(persent1/100) - minus2)
                        .setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue());
            }
        }
        double difference=0;
        boolean existNoReinv = false;
        boolean existReinv = false;
        int countNoReinv = 0;
        for (int i=0;i<period1;i++){
            //Если нет реинвестирования
            if (!get_reinvesting(i)){   
                //Если это последняя часть
                
                    //Это единичная запись
                    if(period1 == 1 && !get_reinvesting(i) || i==period1-1){
                        difference = get_depolast(Integer.parseInt(get_period())-1); 
                        existNoReinv = true;
                        ++countNoReinv; 
                    }else{
                        difference += (get_depolast(i)<get_depofirst(i))
                        ? 0
                            : get_depolast(i) - get_depofirst(i);
                        existNoReinv = true;
                        ++countNoReinv; 
                    }
                }
            existReinv = true;
        }
        
        double result = 0;
        if (existNoReinv && existReinv){
            result = (get_reinvesting(Integer.parseInt(get_period())-1))?
                    get_depolast(Integer.parseInt(get_period())-1)+difference
                    :get_depolast(Integer.parseInt(get_period())-1);
        }
        if (!existNoReinv && existReinv){
            result = get_depolast(Integer.parseInt(get_period())-1);
        }
        if (existNoReinv && !existReinv){
            if (countNoReinv==1){
                result = get_depolast(0);
            }else{
                result = get_depofirst(Integer.parseInt(get_period())-1) + difference;
            }
            
        }
        
        set_result(BigDecimal.valueOf(result)
                                .setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue());
        return this;
     }
     
     static void log(Object o){
        System.out.println(o);
    }
}
