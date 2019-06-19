/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Medidores;

import BD.ConsultaBD;
import Utils.archivo;
import Utils.logFile;
import java.io.File;
import java.sql.ResultSet;

/**
 *
 * @author Michael Venegas, DreamIT Software -> mvenegas@dreamit.cl
 */
public class medidoresConfig {
    
    public static String getPortConexion(){
        //el puerto por defecto es el 5000
        String port = "5000";
        
        ConsultaBD sql = new ConsultaBD();
        
        try{
            String query = "SELECT port FROM configPort LIMIT 1";
            ResultSet rs = sql.retDatos(query);
            
            if(rs.next()){
                return rs.getString(1);
            }
            
        }catch(Exception e){
            Utils.ExceptionHandler.handleException(e);
        }
        
        return port; 
   }

    public static String getPortConexion(String ip){
        //el puerto por defecto es el 5000
        String port = "5000";
        
        ConsultaBD sql = new ConsultaBD();
        
        try{
            String query = "SELECT port FROM configPort WHERE ip =  "+Utils.seguridad.eliminaInyecciones(ip) +" LIMIT 1";
            ResultSet rs = sql.retDatos(query);
            
            if(rs.next()){
                return rs.getString(1);
            }
            
        }catch(Exception e){
            Utils.ExceptionHandler.handleException(e);
        }
        
        return port; 
    }
    
    public static String getUrlSender(){
       String dir = System.getProperty("java.class.path").substring(System.getProperty("java.class.path").lastIndexOf(File.pathSeparator)+1);
        if(dir.lastIndexOf(File.separator)>=0){
            dir = dir.substring(0,dir.lastIndexOf(File.separator)+1);
        }
       archivo arch = new archivo(dir+"url.conf");
       String content = arch.retContenido();
       if(content.length() == 0){
           content = "url:http://localhost/eclipseProjects/medidorescostaneralyon/test.php.";
           arch.escribir(content);
           logFile.addReg("Archivo de url vacio. Se cargara configuracion por defecto.");
       }
       String dato[]= content.split("\n");
       for(int i=0; i < dato.length; i++ ){
           if(dato[i].indexOf("url:")>=0)
               return dato[i].replace("url:", "").replace(".php.",".php");
       }

       return null;
    }
    
    public static void main(String asdf[]){
        //System.out.println(getPortConexion());
        System.out.println(getUrlSender());
    }
    
}
