/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import BD.ConsultaBD;
import Utils.logFile;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author michael
 * device->{address: int, dataPoints->{address: int, lenght: int}}
 * modbusRTU.getFunctionToExecute(function: int,Device: device){ return: String }
 * modbusRTU->{comienzoTrama: int, address: int, function: int, lenght: int, data: int[], crc: int}
 * SocketTCP::send(String message);
 */
public class deviceControl {

    
        //esta clase solo trabaja metodos estaticos =S..
    
        public static ArrayList getDevices(){
            ConsultaBD sql = new ConsultaBD();
            
            String query = "SELECT idDev FROM device";
            
            ArrayList _return = new ArrayList();
            
            try{
                ResultSet rs = sql.retDatos(query);
                
                while(rs.next()){
                    
                    device dev = new device(rs.getInt(1));
                    
                    ArrayList<dataPoint> dataPoints = getDataPoints(dev.getAddress());
                    
                    for(int i=0; i<dataPoints.size(); i++)
                        dev.addDataPoint(dataPoints.get(i));
                    
                    _return.add(dev);
                }
                
            }catch(Exception e){
                logFile.addReg("Excepcion registrada al intentar obtener los dispositivos: "+e);
            }
            
            return _return;
        }
        
        
        public static ArrayList getDevicesV(){
            ConsultaBD sql = new ConsultaBD();
            
            String query = "SELECT idDev FROM device";
            
            ArrayList _return = new ArrayList();
            
            try{
                ResultSet rs = sql.retDatos(query);
                
                while(rs.next()){
                    
                    device dev = new device(rs.getInt(1));
                    
                    _return.add(dev);
                }
                
            }catch(Exception e){
                logFile.addReg("Excepcion registrada al intentar obtener los dispositivos: "+e);
            }
            
            return _return;
        }
        
        
        public static ArrayList getDataPoints(int id){
            ConsultaBD sql = new ConsultaBD();
            
            String query = "SELECT idDataPoint, addressH, addressL, lengthH, lengthL FROM dataPoint WHERE idDev = "+id;
            
            ArrayList _return = new ArrayList();
            
            try{
                ResultSet rs = sql.retDatos(query);
                
                while(rs.next()){
                    _return.add(new dataPoint(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5)));
                }
                
            }catch(Exception e){
                logFile.addReg("Excepcion registrada al intentar obtener puntos de datos de los dispositivos: "+e);
            }           
            
            return _return;
        }
        
        public static void insertDeviceData(dataPoint d, int address, int data){
            //se meten los datos a la BD
            ConsultaBD sql = new ConsultaBD();
            
            String query = "INSERT INTO dataDevice(address,data,idDataPoint) VALUES ("+address+","+data+","+d.getIdDataPoint()+")";
            
            ArrayList _return = new ArrayList();
            
            try{
                sql.ejecutarSentencia(query);
                
            }catch(Exception e){
                logFile.addReg("Excepcion registrada al intentar obtener puntos de datos de los dispositivos: "+e);
            }           
            
        }
        
}