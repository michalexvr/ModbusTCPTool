/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author michael
 */
public class device {
        //device->{address: int, dataPoints->{address: int, lenght: int}}
        //modbusRTU.getFunctionToExecute(function: int,Device: device){ return: String }
        //modbusRTU->{comienzoTrama: int, address: int, function: int, lenght: int, data: int[], crc: int}
        //SocketTCP::send(String message);
    
        protected int address;
        protected ArrayList<dataPoint> dataPoints;
        
        public device(int addr){
            address = addr;
            dataPoints = new ArrayList();
        }
        
        public void addDataPoint(dataPoint d){
            dataPoints.add(d);
        }
        
        public void setDataPoint(int addr, dataPoint d){
            dataPoints.set(addr, d);
        }
        
        public int getAddress(){
            return address;
        }
        
        public ArrayList getDataPoints(){
            return dataPoints;
        }
        
        public dataPoint getDataPointAt(int addr){
            return dataPoints.get(addr);
        }
        
        public int countDataPoints(){
            return dataPoints.size();
        }

}
