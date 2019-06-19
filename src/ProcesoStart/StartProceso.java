/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ProcesoStart;

import BD.ConsultaBD;
import TcpConversor.ClienteMedidor;
import Utils.fechaHora;
import Utils.logFile;

/**
 *
 * @author xion
 */
public class StartProceso implements Runnable {

    protected String[][] numMedidor;
    private boolean Esta1=false;;
    private fechaHora Hora;
    private ClienteMedidor medidor,medidorup;
    protected ConsultaBD base= new ConsultaBD();;
    boolean ControlConexionSocke=true;
    boolean ControlBrokenPipe=true;
     int contador=0;
    
    
    /*
     * Arreglar todo esto mañana!!!!! esta maluenda. como el pico está qué querís que te diga!!
     */


    public void run()
    {

                    
          String hora;//,Esta2=false;
          numMedidor= base.getCargaMedidores();
        try {

            
               
                hora= fechaHora.getNow();
            while(true){

               

               if(contador<=numMedidor.length-1){
               
                    if(Integer.parseInt(numMedidor[contador][1])<26)
                     {
                        if(ControlConexionSocke || medidor.ControlBrokenPipe())
                        {
                            logFile.addReg("Inicio","En ejecucion","0");
                            medidor= new ClienteMedidor("192.168.0.194"); //desde el -1 a 25
                            ControlConexionSocke=false;
                            
                        }
                       
                            medidor.SendData(numMedidor[contador][0]);
                       
                    }
                    else
                    {
                        if(!ControlConexionSocke || medidorup.ControlBrokenPipe())
                        {
                            //medidor.CerrarConexion();
                           medidorup= new ClienteMedidor("192.168.0.193");
                            ControlConexionSocke=true;
                        }
                         
                             medidorup.SendData(numMedidor[contador][0]);

                        
                    }
           

                 contador++;
                }
               else{contador=0; Esta1=true;}


                 if(Esta1)
                 {
                    //tableIsEmpty("estadosistema",20000);// en esta caso la funcion trucara la tabla "estadosistema" si se exceden los registros del limite de 12500
                    Hora= new fechaHora();
                    if(Hora.getHoraEnSegundos(1)==0.0)
                    {
                        logFile.addReg("Stop","El sistema se duerme por 6 horas","ok");
                        medidor.CerrarConexion();
                        Thread.sleep(21600000);
                        
                    }
                    //logFile.addReg("El sistema se duerme por 1 minuto");
                    Thread.sleep(60000);
                    //System.out.println("Se desperto");
                     medidor.CerrarConexion();
                     medidorup.CerrarConexion();
                     Esta1=false;
                     contador=0;
                     run();
                 }
               }

            }
            catch (InterruptedException ex) {
          
            logFile.addReg("Problemas En StartProceso"+ex);
           
        }
      }

}
