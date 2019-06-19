/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

import BD.ConsultaBD;
import java.io.File;
import java.lang.Exception;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 *
 * @author michael
 */
public class logFile {
    
   
   public static void addReg(String Estado,String detalleEstado,String exception){ //Comentadas linea 1 y 5 luego reparar
        //archivo arch = new archivo(System.getProperty( "java.class.path" ).substring(System.getProperty( "java.class.path" ).lastIndexOf(";")+1,System.getProperty( "java.class.path" ).lastIndexOf("\\"))+"\\"+"log.txt");
       
       ConsultaBD base= new ConsultaBD();
         try {
                //base.AbrirConexion("logFile"+detalleEstado+Estado);
                base.ejecutarSentencia("START TRANSACTION");
                base.ejecutarSentencia("Insert into estadosistema(FechaInicio,Estado,DetalleEstado,exception) values('"+fechaHora.getNow()+"','"+Estado+"','"+detalleEstado+"','"+exception+"')");
                base.ejecutarSentencia("COMMIT");
                //base.cerrarConexion("logFile"+detalleEstado);

        } catch (Exception ex) {
            //Logger.getLogger(logFile.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("problemas con el log, cuando esta intentando insertar esto"+" "+Estado+" "+detalleEstado);
        }
       // arch.escribirLinea(fecha+": "+mensaje);
    }
   public static void addReg(String mensaje)
    {
        String dir = System.getProperty("java.class.path").substring(System.getProperty("java.class.path").lastIndexOf(File.pathSeparator)+1);
        if(dir.lastIndexOf(File.separator)>=0){
            dir = dir.substring(0,dir.lastIndexOf(File.separator)+1);
        }
        archivo arch = new archivo(dir+"log.txt");
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dat = new Date();
        String fecha = formato.format(dat);
        arch.escribirLinea((new StringBuilder()).append(fecha).append(": ").append(mensaje).toString());
    }
}
