/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BD;

import Utils.archivo;
import Utils.commonUtils;
import Utils.logFile;
import java.io.File;

/**
 *
 * @author xion
 */
public class config {

    protected static String url;
    protected static String usuario;
    protected static String password;
    protected static String bd;
    protected static String host;
    protected static archivo configuration=null;
    protected static String conFig[]=null;
/*
 * Recordar siempre cargar los datos sobre el archivo con un punto '.' al final
 */


   private synchronized static void  CreateArchConfig()
   {
       String dir = System.getProperty("java.class.path").substring(System.getProperty("java.class.path").lastIndexOf(File.pathSeparator)+1);
        if(dir.lastIndexOf(File.separator)>=0){
            dir = dir.substring(0,dir.lastIndexOf(File.separator)+1);
        }
       configuration = new archivo(dir+"config.conf");
       String content = configuration.retContenido();
       if(content.length() == 0){
           content = "url:jdbc:mysql://localhost/.\nusuario:root.\npassword:ho upe.\nbd:medidoresmonroy.\nhost:localhost.";
           configuration.escribir(content);
           logFile.addReg("Archivo de configuracion vacio. Se cargara configuracion por defecto.");
       }
       String dato[]= content.split("\n");
       //System.out.println(configuration.retContenido().length());
       conFig= new String[dato.length];
       conFig[0]=url=dato[0].substring(dato[0].indexOf(":")+1,dato[0].indexOf("."));
       conFig[1]=usuario=dato[1].substring(dato[1].indexOf(":")+1,dato[1].indexOf("."));
       conFig[2]=password=dato[2].substring(dato[2].indexOf(":")+1,dato[2].indexOf("."));
       conFig[3]=bd=dato[3].substring(dato[3].indexOf(":")+1,dato[3].indexOf("."));
       conFig[4]=host=dato[4].substring(dato[4].indexOf(":")+1,dato[4].indexOf("."));
       
   }
    public static String[] getConfig()
    {
        if(configuration==null) CreateArchConfig();

        return conFig;
    }
    public static String getUrl()
    {
        return url;
    }
    public static String getUsuario()
    {
        return usuario;
    }

    public static String getBD()
    {
        return bd;
    }
    public static String gethost()
    {
        return host;
    }
}
