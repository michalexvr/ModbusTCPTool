/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Medidores;
import BD.ConsultaBD;
import HttpSend.httpSender;
import Utils.archivo;
import Utils.seguridad;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Michael Venegas, DreamIT Software -> mvenegas@dreamit.cl
 */
public class medidorControl {
    
    public static ArrayList getMedidores(){
        
        ConsultaBD sql = new ConsultaBD();
        
        ArrayList<medidor>  medidores = new ArrayList();
        
        try{
            String query = "SELECT nSerie, Modelo, ipFuente, puerto FROM medidor ORDER BY ipFuente ASC";
            ResultSet rs = sql.retDatos(query);
            
            while(rs.next()){
                medidores.add(new medidor(rs.getString("nSerie"),rs.getString("ipFuente"),rs.getString("Modelo"), rs.getString("puerto")));
            }
            
        }catch(Exception e){
            Utils.ExceptionHandler.handleException(e);
        }
        
        return medidores;
    }
    
    public static ArrayList getMedidoresIPPort(){
        
        ConsultaBD sql = new ConsultaBD();
        
        ArrayList<medidor>  medidores = new ArrayList();
        
        try{
            String query = "SELECT nSerie, Modelo, ipFuente, puerto FROM medidor WHERE ipFuente IS NOT NULL AND ipFuente != 'N/I' AND puerto != 0 AND puerto IS NOT NULL ORDER BY ipFuente ASC";
            ResultSet rs = sql.retDatos(query);
            
            while(rs.next()){
                medidores.add(new medidor(rs.getString("nSerie"),rs.getString("ipFuente"),rs.getString("Modelo"), rs.getString("puerto")));
            }
            
        }catch(Exception e){
            Utils.ExceptionHandler.handleException(e);
        }
        
        return medidores;
    }
    
    
    
    public static void insertData(String data, String medidor){
        
        String consumo = "0";
        String demanda = "0";
        String demandaHP = "0";
        String potencia = "0";
        
        
        String[] arrayData = data.split("\n");
        
        for(int i=0; i< arrayData.length; i++){
            //1.6.1 -> hora punta
            //1.6.2 -> fuera punta
            //1.8.0 -> consumo
            //3.8.0 -> potencia
            
            if(arrayData[i].indexOf("1.6.1*08(")>=0 && arrayData[i].indexOf("kW)")>=0){
                demandaHP = arrayData[i].replace("1.6.1*08(", "");
                int index = demandaHP.indexOf("*");
                if(index >0) demandaHP = demandaHP.substring(0, index);
                
            }
            
            if(arrayData[i].indexOf("1.6.2*08")>=0){
                
                demanda = arrayData[i].replace("1.6.2*08(", "");
                int index = demanda.indexOf("*");
                if(index >0) demanda = demanda.substring(0, index);

            }

            if(arrayData[i].indexOf("1.8.0(")>=0){
                
                consumo = arrayData[i].replace("1.8.0(", "");
                int index = consumo.indexOf("*");
                if(index >0) consumo = consumo.substring(0, index);
                
            }

            if(arrayData[i].indexOf("3.8.0(")>=0){
                
                potencia = arrayData[i].replace("3.8.0(", "");
                int index = potencia.indexOf("*");
                if(index >0) potencia = potencia.substring(0, index);
                
            }
            
        }
        System.out.println(Utils.fechaHora.getNowI());
        System.out.println("consumo "+ consumo+" kWh");
        System.out.println("demanda "+demanda+" kW");
        System.out.println("demanda HP "+demandaHP+" kW");
        System.out.println("energia Activa "+potencia+" kvarh");

        ConsultaBD sql = new ConsultaBD();
        
        try{
            String query = "INSERT INTO lectura(nSerie,Fecha,TotalEnergiaImport,ImportMaxDemandaTotal,ImportMaxDemandaTarifa1,Estado) VALUES "
                    + "('"+medidor+"',NOW(),'"+consumo+"','"+demanda+"','"+demandaHP+"',1)";
            sql.ejecutarSentencia(query);
        }catch(Exception e){
            Utils.ExceptionHandler.handleException(e);
        }

        
        
    }
    
    public static String[] getLastDataMedidor(String nSerie){
        //0:consumo, 1: demanda, 2: demandaP
        ConsultaBD sql = new ConsultaBD();
        String[] return_ = new String[3];
        try{
            String query = "SELECT TotalEnergiaImport, ImportMaxDemandaTotal, ImportMaxDemandaTarifa1 FROM lectura WHERE DATE(Fecha) = CURDATE() AND nSerie = '"+seguridad.eliminaInyecciones(nSerie) +"' AND estado = 1 ORDER BY Fecha DESC LIMIT 1";
            ResultSet rs = sql.retDatos(query);
            
            if(rs.next()){
                return_[0] = rs.getString("TotalEnergiaImport");
                return_[1] = rs.getString("ImportMaxDemandaTotal");
                return_[2] = rs.getString("ImportMaxDemandaTarifa1");
                
                return return_;
            }
            
        }catch(Exception e){
            Utils.ExceptionHandler.handleException(e);
        }
        
        return null;
    }
    
    
    public static void sendDataMedidoresToServer(String url){
        ArrayList<medidor> medidores = getMedidoresIPPort();
        
        for(int i=0; i<medidores.size(); i++){
            String[] data = getLastDataMedidor(medidores.get(i).getNSerie());
            if(data != null)
                httpSender.sendData(medidores.get(i).getNSerie(), data[0], data[1], data[2], url);
        }
    }
    
    /*
    
    public static void main(String[] argb){
        ArrayList<medidor> asdf = getMedidoresIPPort();
        for(int i=0; i<asdf.size(); i++){
            System.out.println(asdf.get(i).getNSerie());
        }
        System.exit(2);
        archivo arch = new archivo("/home/michael/NetBeansProjects/MedidoresTitanium2.0/dist/dataGetted.txt");
        String content = arch.retContenido();
        insertData(content,"3251832");
        //System.out.println();
    }
    */

}
