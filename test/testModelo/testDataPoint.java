/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testModelo;
import java.util.ArrayList;
import modelo.dataPoint;
import modelo.device;
import modelo.deviceControl;
/**
 *
 * @author michael
 */
public class testDataPoint {

    public static void main(String args[]){

        
        ArrayList<device> d = deviceControl.getDevices();
        
        for(int i=0; i< d.size(); i++){
            device dev = d.get(i);
            System.out.println("Dispositivo: "+dev.getAddress());
            
            ArrayList<dataPoint> points = dev.getDataPoints();
            
            for(int j=0; j< points.size(); j++){
                dataPoint p = points.get(j);
                System.out.println("PuntoH: "+p.getAddressH()+" - "+(p.getLengthH()+p.getAddressH()-1)+", Largo: "+p.getLengthH());
            }

        }
        
        
        int datah[] = {10,20,30,40,50,60,70,80,90,100};
        
        
        //op1
        dataPoint data_point = d.get(0).getDataPointAt(0);
        data_point.setDataH(datah);
        
        //op2
        d.get(0).getDataPointAt(0).setDataH(datah);
        
        d.get(0).setDataPoint(0, data_point);
        //points.get(1).setDataH(datah);
            
        System.out.println(d.get(0).getDataPointAt(0).getDataH(4097));
        
        
        /*
        dataPoint d = new dataPoint(4096,0,10,0);
        
        //int datal[] = {0,1,2,3,4,5,6,7,8,9};
        int datah[] = {10,20,30,40,50,60,70,80,90,100};
        
        //d.setDataL(datal);
        d.setDataH(datah);
        
        System.out.println(d.getDataH(10));
        System.out.println(d.getDataH(15));
        System.out.println(d.getDataH(4105));
        
        /*
        System.out.println(d.getDataL(2));
        System.out.println(d.getDataL(11));
        
        */
        
        
    }
    
}
