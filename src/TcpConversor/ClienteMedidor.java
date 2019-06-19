/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TcpConversor;

import CapturaDatos.captura;
import Utils.logFile;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author Administrador
 */
public class ClienteMedidor {
   private ObjectOutputStream salida;
   private ObjectInputStream entrada;
   private InputStream entra;
   private String mensaje = "";
   //private InputStream in;
   private BufferedInputStream prueba;
   BufferedReader receivedPacket;
   private boolean BrokenPipe=false;
   //private String servidorChat;
   private Socket cliente;
   private String hostIpServer;
   private captura capData;

    public ClienteMedidor(String host) // En este caso se asume un puerto por difenicion el 5000
   {
       boolean itsConection=true;
       int intentos=0;
      do{
            try {
                    hostIpServer = host;
                    //System.out.println("Intentando establecer conexion..");
                    //cliente.setSoTimeout(12000);
                    cliente = new Socket(InetAddress.getByName(hostIpServer),5000);
                    itsConection=false;
                    //System.out.println("Conexion exitosa");
                }
                catch (IOException ex)
                    {
                    //Logger.getLogger(ClienteMedidor.class.getName()).log(Level.SEVERE, null, ex);
                        logFile.addReg("problemas con la conexion del servidor: "+hostIpServer+" "+ex);
                        System.out.println("problemas con la conexion del servidor: "+hostIpServer+" "+ex);
                        if(intentos>200)
                        {
                            logFile.addReg("No respondio el Servidor "+hostIpServer+"porfavor ver si esta conectado");
                            System.out.println("No respondio el Servidor "+hostIpServer+"porfavor ver si esta conectado"+intentos);
                            itsConection=false;
                        }
                        intentos++;
                    }
       }while(itsConection);
   }
   public void CerrarConexion()
   {
    try
    {
        //System.out.println("\n"+"cerrando..");
        //if(salida!=null) salida.close();
        //if(entrada!=null) entrada.close();
        cliente.setSoTimeout(12000);
        if(cliente!=null)
            {
                cliente.close();
                
            }
        //System.out.println("conexion cerrada ok");
    }
    catch(Exception ex)
    {
        /*
         * PROBLEMAS CON EL CIERRE DE CONEXION EL ERROR QUE LANZO FUE QUE LA CONEXION YA ESTABA CERRADA
         * SI LLEGARA HA PASAR OTRA VES TENEMOS QUE CONECTAR EL SERVIDOR Y LUEGO REESTABLECERSERLO PARA PODER TOMAR CONTROL SOBRE EL ERROR.
         */
            try 
            {
               if(salida!=null) salida.close();
               if(cliente!=null) cliente.close();
            } catch (IOException ex1) {
                logFile.addReg("Problemas con la conexion y se fuerza el cierre");
                //Logger.getLogger(ClienteMedidor.class.getName()).log(Level.SEVERE, null, ex1);
            }
        logFile.addReg("Problemas con el cierre de conexion"+ex);
    }
   }
   public ClienteMedidor(String host,String port)
   {
        try {
            hostIpServer = host;
            
            cliente = new Socket(InetAddress.getByName(hostIpServer),Integer.parseInt(port));
        } catch (IOException ex) {
           
                try {
                    if(cliente.isConnected()) CerrarConexion();
                    cliente = new Socket(InetAddress.getByName(hostIpServer), Integer.parseInt(port));
                } catch (IOException ex1)
                {
                    logFile.addReg("Falla el servidor", "Hay problemas con la comunicación con el servidor", "Ip o puerto");
                    //System.out.println("problemas con la conexion del servidor verifique puerto o host:" + " " + ex);
                }
                
           
            
            
        }
   }
   /* esto es una prueba
   public void pruebaClienteMedidor(String dato)
   {
       //String a= new String("");
         String mensaje="/?"+dato+"!\r\n"; // envio datos en milenium /?!F000001\r\n // envio titanium /?30111089!\r\n
          BufferedReader receivedPacket = null;
          boolean termino=true;
          String inputLine="";
          String inputText="";
         System.out.println("en clienteMedidor");
        try {
            
            salida = new ObjectOutputStream(cliente.getOutputStream());
             //entrada= new ObjectInputStream(cliente.getInputStream());
            salida.flush();
            salida.write(mensaje.getBytes());
             ObjectInputStream ent= new ObjectInputStream(cliente.getInputStream());
            salida.flush();
            //ObjectInputStream ent= new ObjectInputStream(cliente.getInputStream());
            //entrada= new ObjectInputStream(cliente.getInputStream());
            //System.out.println("enviando datos");
            //byte arr[]= mensaje.getBytes();
            String aps="";
            
            cliente.setSoTimeout(4500);
           

            do{
                try
                {
                    
                    inputLine= (String)entrada.readObject();
                    System.out.println("Estoy en Do"+inputLine);
                }
                catch(Exception ex)
                {

                }

            }while(!entrada.equals("!"));
            
       }catch(Exception ex)
       {

       }

   }
   */
   public boolean ControlBrokenPipe()
   {
        return BrokenPipe;
   }
   public void SendData(String dato)
   {
         if(cliente!=null)
         {
             
            String a= new String("");
            String mensaje="/?"+dato+"!\r\n"; // envio datos en milenium /?!F000001\r\n // envio titanium /?30111089!\r\n
            // receivedPacket = null;
            boolean termino=true,listo=false;
            //System.out.println("en clienteMedidor");
            try {
                int data=0;
         
            int timeOut=0;
            String inputLine="";
            String inputText="";
            
            
                salida = new ObjectOutputStream(cliente.getOutputStream());
            
                salida.flush();
            
                //System.out.println("enviando datos");
            //byte arr[]= mensaje.getBytes();
                String aps="";
                salida.write(mensaje.getBytes());
                salida.flush();
           
                //1000 =1 segundo, espera 12 segundos medidor
            
                //InputStream in=cliente.getInputStream();
                cliente.setSoTimeout(4500);
                receivedPacket= new BufferedReader(new InputStreamReader(cliente.getInputStream())); //(;
                //BufferedReader receivedPacket= new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                int contador=0;
                //System.out.println("Recibiendo "+dato);
                while(((inputLine=receivedPacket.readLine()).compareTo("!")!=0) && termino)  //((c= mensaje.read())!=-1) data=receivedPacket.read())>=0 receivedPacket.readLine()
                {
                
                 
                    inputText+=inputLine+"\n";
                    contador++;
                
                }
                    //System.out.println(inputText);

                capData= new captura(inputText);
                capData.filtro(Integer.parseInt(dato));
                //capData.show();
                //capData.showgetDatos();
                         
            } catch (IOException ex)
                {
                //Logger.getLogger(ClienteMedidor.class.getName()).log(Level.SEVERE, null, ex);
                    capData= new captura();
                    capData.varificarDato(Integer.parseInt(dato));
                    logFile.addReg("Tiempo Fuera","Falla comunicación Med: "+dato,""+ex);
                    String excep= ""+ex;
                    if(excep.indexOf("java.net.SocketException: Broken pipe")==0){BrokenPipe=true;}
                }
        
            }
         else
        {
            logFile.addReg("Imposible hacer consulta la conexion no esta estableciada");
        }
        
   }

   
   public String getDataStream(String dato)
   {
         if(cliente!=null)
         {
             
            String a= "";
            String mensaje="/?"+dato+"!\r\n"; // envio datos en milenium /?!F000001\r\n // envio titanium /?30111089!\r\n
            // receivedPacket = null;
            boolean termino=true,listo=false;
            //System.out.println("en clienteMedidor");
            try {
                int data=0;
         
            int timeOut=0;
            String inputLine="";
            String inputText="";
            
            
                salida = new ObjectOutputStream(cliente.getOutputStream());
            
                salida.flush();
            
                //System.out.println("enviando datos");
            //byte arr[]= mensaje.getBytes();
                String aps="";
                salida.write(mensaje.getBytes());
                salida.flush();
           
                //1000 =1 segundo, espera 12 segundos medidor
            
                //InputStream in=cliente.getInputStream();
                cliente.setSoTimeout(4500);
                receivedPacket= new BufferedReader(new InputStreamReader(cliente.getInputStream())); //(;
                //BufferedReader receivedPacket= new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                int contador=0;
                //System.out.println("Recibiendo "+dato);
                while(((inputLine=receivedPacket.readLine()).compareTo("!")!=0) && termino)  //((c= mensaje.read())!=-1) data=receivedPacket.read())>=0 receivedPacket.readLine()
                {
                
                 
                    inputText+=inputLine+"\n";
                    contador++;
                
                }
                    //System.out.println(inputText);
                
                return inputText;

                //capData.show();
                //capData.showgetDatos();
                         
            } catch (IOException ex)
                {
                //Logger.getLogger(ClienteMedidor.class.getName()).log(Level.SEVERE, null, ex);
                    return("Falla comunicación"+dato+" -> "+ex);                    
                }
        
            }
         else
        {
            logFile.addReg("Imposible hacer consulta la conexion no esta estableciada");
        }
         return null;
   }
        
      
   
   
}
