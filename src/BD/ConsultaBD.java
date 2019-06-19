/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BD;

import Utils.logFile;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class ConsultaBD {

   
    protected Connection con;
    public ConsultaBD()
    {
        //conexion= new Conexion();
         con=Conexion.getConnection();
                 
    }
    public void ejecutarSentencia(String consulta)
    {
         if (con != null)
         {
            try {
                //System.out.println("Conexión a base de datos "+url+" ... Ok");
                java.sql.Statement st = con.createStatement();
                //ResultSet res = st.executeQuery("SELECT * FROM Horario");
                st.execute(consulta);
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaBD.class.getName()).log(Level.SEVERE, null, ex);
            }

         }else
         {
            //System.out.println("La conexion es nula"+con);
            logFile.addReg("La conexion es nula"+con);
         }
      }

    public ResultSet retDatos(String consulta)
    {
        ResultSet res;
        if (con != null) {
            try {
                //System.out.println("Conexión a base de datos "+url+" ... Ok");
                java.sql.Statement st = con.createStatement();
                 res = st.executeQuery(consulta);
                //conn.close();
                return res;
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaBD.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
        else
        {
            //System.out.println("Problemas en el retDatos en la consulta: "+consulta);
            logFile.addReg("Problemas en el retDatos en la consulta: "+consulta);
        }
        return null;

    }

    public int contarDatos(String consulta)
    {
        int lectura;

        if (con != null) {
            try {
                //.out.println("Conexión a base de datos "+url+" ... Ok");
                java.sql.Statement st = con.createStatement();
                //ResultSet res = st.executeQuery("SELECT * FROM Horario");
                ResultSet res = st.executeQuery(consulta);
                int i = 1;
                lectura = 0;
                while (res.next()) {
                    Integer.parseInt(res.getString(i)); //res.getString("Num_Sala")+"\n";
                    //System.out.println(lectura);
                    lectura++;
                }
                //lectura=res.getInt(1);
                //lectura=res.getInt(1);
                return lectura;
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaBD.class.getName()).log(Level.SEVERE, null, ex);
            }
         }

        return 0;
      }
    
     public void truncaTabla(String table)
   {
        
        try
        {     
             String consulta="TRUNCATE TABLE "+table+"";
             if(con!=null)
             {
                  java.sql.Statement st =  con.createStatement();
             //ResultSet res = st.executeQuery("SELECT * FROM Horario");
             st.execute(consulta);
             st.close();
             }
        }
        catch(SQLException ex)
        {

        }
   }
    public void tableIsEmpty(String table,long limite)
    {

      if(con!=null){
        try
        {
            ResultSet resA= retDatos("select Id From "+table+" Order by Id desc Limit 1");//SELECT Id FROM `estadosistema` Order by Id desc Limit 1
            long TotalDaTabla=0;
            while(resA.next())
            {
                TotalDaTabla= Long.parseLong(resA.getString(1));
            }
            if(TotalDaTabla>limite)
            {
                truncaTabla(table);
                logFile.addReg("Se trunco la tabla "+table+"","La tabla "+table+" esta vacia","ok");
            }
        }
        catch(Exception ex)
        {
            System.out.println("Problemas en tableIsEmpty"+ex);
        }
    }
}

    public String[][] getCargaMedidores()
    {
         String[][] numMedidor = null;
         int contar=0;

    if(con!=null)
        {
              try {
                  //NOS ASEGURAMOS QUE EL MEDIDOR VIRTUAL NO SEA CONSIDERADO EN EL PROCESO ;) -> EL MEDIDOR VIRTUAL ES EL NUMERO 0
                    ResultSet resT = retDatos("SELECT nSerie,Piso FROM `medidor` WHERE nSerie != 0 Order by Piso asc");//SELECT nSerie,Piso FROM medidor Order by Piso asc" // SELECT nSerie FROM medidor
                    contar=contarDatos("SELECT nSerie,Piso FROM `medidor` WHERE nSerie != 0 Order by Piso asc");
                    //System.out.println("numero de datos"+contar);
                    numMedidor= new String[contar][2];
                    int a=0;
                    while(resT.next())
                    {
                         numMedidor[a][0]= resT.getString(1);
                        numMedidor[a][1]=resT.getString(2);
              //System.out.println(numMedidor[a]);
                        a++;
                    }
            
                } catch (Exception ex)
                    {

                        logFile.addReg("cargam thread padre","problema",""+ex);

                    }
                return numMedidor;
        }
            else
            {
                logFile.addReg("Problemas con la conexion a la base de datos");
                return null;
            }
                //return numMedidor;
    }
    


}
