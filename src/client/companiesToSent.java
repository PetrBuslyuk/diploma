package client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class companiesToSent extends Thread implements Serializable {
    public Vector companiesToSend;
    public companiesToSent() {
        companiesToSend = new Vector();
    }
    public void setCompanies(ArrayList<company> companiesList){
        companiesList.stream().forEach((c) -> {
            companiesToSend.add(c);
        });
    }
    
    public Iterator getCampanyIterator() {
         return companiesToSend.iterator();
    }
     
    public void printSomething(){
        Iterator i = getCampanyIterator();
        while(i.hasNext()){
            company c = (company) i.next();
         System.out.println(c.get_name());
        }
       
    }
}
