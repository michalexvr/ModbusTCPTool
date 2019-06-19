/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;
import BD.ConsultaBD;

/**
 *
 * @author michael
 */
public class logDB {
    public static void addReg(String message){
        String query = "INSERT INTO logSistema(fechaHora,tipo,resumen,uri,aplicacion,usuario,ip,estadoRevision,observaciones) "
                + "VALUES "
                + "(NOW(),'info','"+seguridad.eliminaInyecciones(message)+"','N/A','Runtime','N/A','N/A','Pendiente','')";
        ConsultaBD queryHandler = new ConsultaBD();
        try{
            queryHandler.ejecutarSentencia(query);
        }catch(Exception e){
            logFile.addReg("Error en Query : "+e);
        }
    }


    public static void addReg(String message,String tipo,String aplicacion){
        String query = "INSERT INTO logSistema(fechaHora,tipo,resumen,uri,aplicacion,usuario,ip,estadoRevision,observaciones) "
                + "VALUES "
                + "(NOW(),'"+seguridad.eliminaInyecciones(tipo)+"','"+seguridad.eliminaInyecciones(message)+""
                + "','N/A','"+seguridad.eliminaInyecciones(aplicacion)+"','N/A','N/A','Pendiente','')";
        ConsultaBD queryHandler = new ConsultaBD();
        try{
            queryHandler.ejecutarSentencia(query);
        }catch(Exception e){
            logFile.addReg("Error en Query : "+e);
        }
    }


    public static void addRegError(int errno, String error, String resumen, String archivo, int linea, String aplicacion){

        String query = "INSERT INTO logError(fechaHora,errno,error,resumen,uri,archivo,linea,aplicacion,usuario,ip,estadoRevision,observaciones) "
                + "VALUES "
                + "(NOW(),'"+errno+"','"+seguridad.eliminaInyecciones(error)+"','"+seguridad.eliminaInyecciones(resumen)+""
                + "','N/A','"+seguridad.eliminaInyecciones(archivo)+"',"
                + "'"+linea+"','"+seguridad.eliminaInyecciones(aplicacion)+"','N/A','N/A','Pendiente','')";

        ConsultaBD queryHandler = new ConsultaBD();
        try{
            queryHandler.ejecutarSentencia(query);
        }catch(Exception e){
            logFile.addReg("Error en Query : "+e);
        }
    }
}
