/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BD;

import Utils.logFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class Conexion {
    
    private static String bd;
    private static String login;
    private static String password;
    private static String url;
    private static Connection connT = null;
    
    private Conexion()
    {
       // bd="EnergiaMilenium";
       // login="root";
       // password="";
       // url="jdbc:mysql://localhost/"+bd;
    }
    private synchronized static void  CreateOpenConection()
    {
           String conf[]=config.getConfig();
           bd= conf[3];//"medidorest"; //yo root//proximaBaseDe datos EnergiaMilenium
           login= conf[1]; //"root";
           password= conf[2]; //"kuelule";// yo kuelule// futuro nada //titanium1a2b3c
           url= conf[0]+bd; //"jdbc:mysql://localhost/"+bd;  //yo localhost  //servidor 20.20.20.2

        try
        {
             Class.forName("com.mysql.jdbc.Driver").newInstance();
             connT = DriverManager.getConnection(url,login,password);
            
        }
        catch(Exception ex)
        {
            try {
                logFile.addReg("Problema con la conexion sin parametros:"+ex);
                logFile.addReg("Los parametros son: "+bd+","+login +","+password+ ","+url);
                System.out.println("Problema con la conexion sin parametros:" + ex);
                bd = "medidorest"; //yo root//proximaBaseDe datos EnergiaMilenium
                login = "root";
                password = "titanium1a2b3c"; // yo kuelule// futuro nada //titanium1a2b3c
                url = "jdbc:mysql://localhost/"+bd;
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                } catch (InstantiationException ex1) {
                    logFile.addReg("Problemas con la instance del driver "+ex1);
                } catch (IllegalAccessException ex1) {
                    logFile.addReg("Ilegal acceso "+ex1);
                }
                try {
                    connT = DriverManager.getConnection(url, login, password);
                } catch (SQLException ex1) {
                    //Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex1);
                    logFile.addReg("Exception en la conexion con mysql "+ex1);
                    logFile.addReg("Problemas con los parametros(url,login,password): "+url+","+login+","+password);
                }
            } catch (ClassNotFoundException ex1) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex1);
            }
           
           
            
        }
         
    }
 private synchronized static void  CreateOpenConection(String BD,String log,String Password,String Url)
 {
        bd=BD;
        login=log;
        password=Password;
        url=Url;

    try
    {
             Class.forName("com.mysql.jdbc.Driver").newInstance();
             connT = DriverManager.getConnection(url,login,password);

    }
    catch(Exception ex)
    {
        System.out.println("Problema con la conexion con parametro:"+ex);
    }
 }
 public static Connection getConnection()
 {
    if(connT==null) CreateOpenConection();

    return connT;
 }
 public static Connection getConnection(String BD,String log,String Password,String Url)
 {
    if(connT==null) CreateOpenConection(BD,log,Password,Url);

    return connT;
 }
    public static void closeConectio()
    {
        try
        {
            connT.close();
        }
        catch (SQLException ex)
        {
            System.out.println("Problemas con el cierre de conexion: "+ex);
        }
    }
}
