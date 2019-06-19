/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CapturaDatos;

import BD.ConsultaBD;
import Utils.*;
import java.io.File;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Administrador
 */
public class captura {
    protected String[] arrayDatos;
    protected String[][] getDatos;
    protected int columna=2;
    protected int fila;
    protected ConsultaBD base;
    protected  double[] PT;
    protected int estado;
    protected FechaAndHora fecha = new FechaAndHora();
    protected String ultimaFecha= "";
    protected logFile log= new logFile();
    protected ResultSet resultado;
    protected boolean veri;
    protected String TotalEnergiaExport; //listo
    protected String EnergiaExporTariff1;//listo
    protected String EnergiaExporTariff2;//listo
    protected String EnergiaExporTariff3;//listo

    protected String TotalEnergiaImport; // listo
    protected String EnergiaImportTariff1; //listo
    protected String EnergiaImportTariff2; //listo
    protected String EnergiaImportTariff3; //liso

    protected String TotalEnergiaExportFacturaPasada; //listo
    protected String EnergiaExportMesPasadoTariff1; //listo
    protected String EnergiaExportMesPasadoTariff2; //listo
    protected String EnergiaExportMesPasadoTariff3; //listo

    protected String TotalEnergiaImportFacturaPasada; //listo
    protected String EnergiaImportMesPasadoTariff1;   //listo
    protected String EnergiaImportMesPasadoTariff2;   //listo
    protected String EnergiaImportMesPasadoTariff3;   //listo

    protected String AcumularActiveMDImportTariff1;  //listo
    protected String AcumularActiveMDImportTariff2;  //listo
    protected String AcumularActiveMDImportTariff3;  //listo
    protected String AcumularActiveMDImportTariff4;   //listo

    protected String AcumularActiveMDExportTariff1;   //listo
    protected String AcumularActiveMDExportTariff2;   //listo
    protected String AcumularActiveMDExportTariff3;   //listo
    protected String AcumularActiveMDExportTariff4;   //listo

    protected String ImportMaxDemandaTotal;           //listo
    protected String ImportMaxDemandaTarifa1;         //listo
    protected String ImportMaxDemandaTarifa2;         //listo
    protected String ImportMaxDemandaTarifa3;         //listo
    protected String ImportMaxDemandaTarifa4;         //listo

    protected String ExportMaxDemandaTotal;
    protected String ExportMaxDemandaTarifa1;
    protected String ExportMaxDemandaTarifa2;
    protected String ExportMaxDemandaTarifa3;
    protected String ExportMaxDemandaTarifa4;
    
    protected String VoltageNominal;
    protected String VoltageL1;
    protected String VoltageL2;
    protected String VoltageL3;
    protected String CurrentL1;
    protected String CurrentL2;
    protected String CurrentL3;
    protected double PotenciaMedia=0;
    protected double Tiempo=0;

            /*
             VoltageNominal 	varchar(50) 	latin1_swedish_ci 		No 	None 		Navegar los valores distintivos 	Cambiar 	Eliminar 	Primaria 	Único 	Índice 	Texto completo
	VoltageL1 	varchar(50) 	latin1_swedish_ci 		No 	None 		Navegar los valores distintivos 	Cambiar 	Eliminar 	Primaria 	Único 	Índice 	Texto completo
	VoltageL2 	varchar(50) 	latin1_swedish_ci 		No 	None 		Navegar los valores distintivos 	Cambiar 	Eliminar 	Primaria 	Único 	Índice 	Texto completo
	VoltageL3 	varchar(50) 	latin1_swedish_ci 		No 	None 		Navegar los valores distintivos 	Cambiar 	Eliminar 	Primaria 	Único 	Índice 	Texto completo
	CurrentL1 	varchar(50) 	latin1_swedish_ci 		No 	None 		Navegar los valores distintivos 	Cambiar 	Eliminar 	Primaria 	Único 	Índice 	Texto completo
	CurrentL2 	varchar(50) 	latin1_swedish_ci 		No 	None 		Navegar los valores distintivos 	Cambiar 	Eliminar 	Primaria 	Único 	Índice 	Texto completo
	CurrentL3
             */
    
   public captura(String dato)
   {
       
     arrayDatos=ClearAndData(dato);
     fila= arrayDatos.length;
     getDatos= new String[fila][columna];
     base =new  ConsultaBD();
     estado=1;
     veri=false;
   
   }
 
   public captura()
   {
    base =new  ConsultaBD();
    veri=false;
    estado=0;
   }

   private String[] retorDatos()
   {
        String[] datos;
        datos= arrayDatos;
        if(datos.length!=0)
        {
            return datos;
        }
        else
        {
            return datos;
        }
   }
  public void show()
    {
     for (int i=0; i<=arrayDatos.length-1;i++)
     {
        System.out.println("pos:"+i+"##"+arrayDatos[i]);
      //System.out.println(arrayDatos[i]);
     }

    }
public void varificarDato(int nSerie)
{
 //System.out.println("cantidad:"+arrayDatos.length);
    //System.out.println("ESTA VERIFICANDO!!!!");
    ultimaFecha= fechaHora.getNow();
    //if(estado==0)
    //{
        //System.out.println("Estado es 0");
       try {
                //System.out.println("estado medidor 0"+nSerie);
                base.ejecutarSentencia("Insert into lectura(nSerie,Fecha,Estado,TotalEnergiaExport,EnergiaExporTariff1,EnergiaExporTariff2,EnergiaExporTariff3,TotalEnergiaImport,EnergiaImportTariff1,EnergiaImportTariff2,EnergiaImportTariff3,TotalEnergiaExportFacturaPasada,EnergiaExportMesPasadoTariff1,EnergiaExportMesPasadoTariff2,EnergiaExportMesPasadoTariff3,TotalEnergiaImportFacturaPasada,EnergiaImportMesPasadoTariff1,EnergiaImportMesPasadoTariff2,EnergiaImportMesPasadoTariff3,AcumularActiveMDImportTariff1,AcumularActiveMDImportTariff2,AcumularActiveMDImportTariff3,AcumularActiveMDImportTariff4,AcumularActiveMDExportTariff1,AcumularActiveMDExportTariff2,AcumularActiveMDExportTariff3,AcumularActiveMDExportTariff4,ImportMaxDemandaTotal,ImportMaxDemandaTarifa1,ImportMaxDemandaTarifa2,ImportMaxDemandaTarifa3,ImportMaxDemandaTarifa4,ExportMaxDemandaTotal,ExportMaxDemandaTarifa1,ExportMaxDemandaTarifa2,ExportMaxDemandaTarifa3,ExportMaxDemandaTarifa4,VoltageNominal,VoltageL1,VoltageL2,VoltageL3,CurrentL1,CurrentL2,CurrentL3,PotenciaMedia,Tiempo) values('"+nSerie+"','"+ultimaFecha+"',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)"); // Medidor no responde.
                //base.conexionClose();
            } catch (Exception ex) {
                System.out.println("Problemas con el insertar verificaDatos");
                log.addReg("verificaDatos","problema en verifica",""+ex);
                
            }
    //}
   

}
private String[] ClearAndData(String data)
{
    
    data=data.trim();
    String arr[]=data.split("\n");
    if(!arr[0].equals("/TRJ5TD876-GT05"))
    {
        arr[0]="/TRJ5TD876-GT05";
    }
    return arr;
}
  public void filtro(int medidor)  //2.8.0(000000.12)
  {  
     int inicioSinBasura = arrayDatos[1].indexOf("2.8.0");
     if(inicioSinBasura==0)
      {
         //arrayDatos[1] = arrayDatos[1].substring(inicioSinBasura);
         veri=true;
         try
         {
            for(int i=1;i <=arrayDatos.length-2 ;i++)
            {
                try{
                getDatos[i][0]=arrayDatos[i].substring(arrayDatos[i].indexOf(""),arrayDatos[i].indexOf("("));
                }
                catch(Exception ex)
                {
                    System.out.println("problemas en el medidor :"+medidor+" con el almacen de cabeceras "+arrayDatos[i]);
                }
                if(i<=28)
                {
                    try
                    {
                        getDatos[i][1]= arrayDatos[i].substring(arrayDatos[i].indexOf("("),arrayDatos[i].indexOf(")")+1);
                    }
                    catch(Exception ex)
                    {
                        System.out.println("problemas en el medidor :"+medidor+" dentro del rango 1 a 28"+arrayDatos[i]);
                    }
                }
                if(i>=29 && i<=38)
                {
                    try
                    {
                        getDatos[i][1]= arrayDatos[i].substring(arrayDatos[i].indexOf("("),30);
                    }
                    catch(Exception ex)
                    {
                        logFile.addReg("problemas en el medidor :"+medidor+" en rango 29 a 38"+arrayDatos[i]);
                    }
                 }
                if(i>=39 && i<=48)
                {
                    try
                    {
                        getDatos[i][1]= arrayDatos[i].substring(arrayDatos[i].indexOf("("),33);
                    }
                    catch(Exception ex)
                    {
                        
                        logFile.addReg("problemas en el medidor :"+medidor+" en rango 29 a 48"+arrayDatos[i]);
                    }

                }
                if(i>=49)
                {
                    try
                    {
                    getDatos[i][1]= arrayDatos[i].substring(arrayDatos[i].indexOf("("),arrayDatos[i].indexOf(")")+1);
                    }
                    catch(Exception ex)
                    {
                        logFile.addReg("problemas en el medidor :"+medidor+" dentro del rango 49 a 147"+arrayDatos[i]);
                    }
                }
            }
                // una vez filtrado el dato se inserta.
               SettingInsert(medidor);
         }
         catch(Exception ex)
         {
             //los datos tiene ruido..
            log.addReg("filtro","problema indexOf","Problemas "+medidor);
            logFile.addReg("Exception"+ex);
            varificarDato(medidor);
            
         }
    }
     else
     {
      log.addReg("filtro","Datos con ruido->MEDIDOR MAL PROGRAMADO","filtro "+medidor);
      tratarDatosConRuido(medidor);
       //varificarDato(medidor);
     }
      
  }
  
  public void tratarDatosConRuido(int medidor){
      for(int i = 0; i<arrayDatos.length; i++){
          arrayDatos[i] = arrayDatos[i].trim();
          
          if(arrayDatos[i].indexOf("1.8.0(")>=0)
            TotalEnergiaImport = arrayDatos[i].replace("1.8.0", "");
          
          if(arrayDatos[i].indexOf("1.6.0(")>=0)
            ImportMaxDemandaTotal = arrayDatos[i].replace("1.6.0", "");

          if(arrayDatos[i].indexOf("1.6.1(")>=0)
            ImportMaxDemandaTarifa1 = arrayDatos[i].replace("1.6.1", "");
          
          if(arrayDatos[i].indexOf("1.6.2(")>=0)
            ImportMaxDemandaTarifa2 = arrayDatos[i].replace("1.6.2", "");
          
          if(arrayDatos[i].indexOf("1.6.3(")>=0)
            ImportMaxDemandaTarifa3 = arrayDatos[i].replace("1.6.3", "");
          
      }
      
      try{
          base.ejecutarSentencia("INSERT INTO lectura(nSerie,Fecha,Estado,TotalEnergiaImport,ImportMaxDemandaTotal,ImportMaxDemandaTarifa1,ImportMaxDemandaTarifa2,ImportMaxDemandaTarifa3) VALUES "
                  + "("+medidor+",NOW(),2,'"+TotalEnergiaImport+"','"+ImportMaxDemandaTotal+"','"+ImportMaxDemandaTarifa1+"','"+ImportMaxDemandaTarifa2+"','"+ImportMaxDemandaTarifa3+"')");
      }catch(Exception exp){
          logFile.addReg("Excepcion al tratar de insertar los datos del medidor "+medidor+": "+exp);
      }
  }
  
  public void showgetDatos()
  {
    for(int i=1;i <=arrayDatos.length-2 ;i++)
    {
        System.out.println("num"+i+" :"+getDatos[i][0]+" "+getDatos[i][1]);
        
    }


  }
private double[] potenciaMed(int medidor,double TotalEnergiaImport,double TotalEnergiaExport,String FechaActual) //SELECT Tiempo,Fecha,TotalEnergiaImport,PotenciaMedia FROM `lectura` WHERE nSerie='30111089' Order by Id_Lectura desc limit 0,1
{
        String Fecha= new String("");
        double Energia=0,numerador=0,denominador=0;
        double[] tiempoS,FechaSegundo;
        
       
        PT= new double[2];
        try //SELECT Tiempo,Fecha,TotalEnergiaImport,PotenciaMedia FROM `lectura` WHERE nSerie='30111089' Order by Id_Lectura desc limit 0,1
        {
            resultado = base.retDatos("SELECT Tiempo,Fecha,TotalEnergiaImport,TotalEnergiaExport,PotenciaMedia FROM lectura WHERE nSerie="+medidor+" AND Estado!=0 Order by Id_Lectura desc limit 0,1");
            while(resultado.next())
            {
              //System.out.print("entro a consulta");
              //Tiempo=Double.parseDouble(resultado.getString(1));
              Fecha=resultado.getString(2);
              try
                {
                    Energia=Double.parseDouble(resultado.getString(3).substring(resultado.getString(3).indexOf("(")+1,resultado.getString(3).indexOf(")")))+Double.parseDouble(resultado.getString(4).substring(resultado.getString(4).indexOf("(")+1,resultado.getString(4).indexOf(")")));
                }catch(Exception Ex)
                {
                    System.out.print("Problemas con el limpieza de Energia, en la funcion potenciaMedia");
                }
            }
                if(Fecha.compareTo("")!=0)
                {
                    tiempoS=fecha.HoraSegundosCalculo(Fecha,FechaActual);
                    try{
                        FechaActual= FechaActual.substring(FechaActual.indexOf(""),FechaActual.indexOf(" "));
                        }catch(Exception ex)
                            {
                                System.out.println("Falla en el substring FechaActual :"+ex);
                            }
                    try{
                        Fecha= Fecha.substring(Fecha.indexOf(""),Fecha.indexOf(" "));
                        }catch(Exception ex)
                            {
                                System.out.println("falla fecha subtring Potencia media :"+Fecha);
                            }
            //System.out.println(Fecha+"|"+FechaActual);
                FechaSegundo= fecha.FechaSeg(FechaActual,Fecha);
                numerador= (((TotalEnergiaImport+TotalEnergiaExport))-((Energia)))*3600;
                denominador= ((FechaSegundo[1]+tiempoS[1])-(FechaSegundo[0]+tiempoS[0]));
                PotenciaMedia= numerador/denominador;
            //System.out.println(numerador+"|"+denominador+"="+PotenciaMedia);
            //System.out.println(Fecha);
            //Tiempo=denominador;
                PT[0]=denominador;
                PT[1]=PotenciaMedia;
          }
          else
          {
            PT[0]=0;
            PT[1]=0;
          }

            //PotenciaMedia= 3600*( )
        } catch (Exception ex) {
            //Logger.getLogger(captura.class.getName()).log(Level.SEVERE, null, ex);
            log.addReg("Falla en el calculo de la potenciaMedia"+ex);
            
            //System.out.println("falla potenciaMedia"+ex);
        }
        
       return PT;
}


private void SettingInsert(int medidor)
{
  
 for(int i=1;i<=arrayDatos.length-2;i++)
 {
    if("2.8.0".compareTo(getDatos[i][0])==0)
    {
         TotalEnergiaExport= getDatos[i][1];
    }
    if("2.8.1".compareTo(getDatos[i][0])==0)
    {
         EnergiaExporTariff1= getDatos[i][1];
    }
    if("2.8.2".compareTo(getDatos[i][0])==0)
    {
         EnergiaExporTariff2= getDatos[i][1];
    }
    if("2.8.3".compareTo(getDatos[i][0])==0)
    {
         EnergiaExporTariff3= getDatos[i][1];
    }
    if("1.8.0".compareTo(getDatos[i][0])==0)
    {
         TotalEnergiaImport= getDatos[i][1];
    }
    if("1.8.1".compareTo(getDatos[i][0])==0)
    {
         EnergiaImportTariff1= getDatos[i][1];
    }
    if("1.8.2".compareTo(getDatos[i][0])==0)
    {
        EnergiaImportTariff2= getDatos[i][1];
    }
    if("1.8.3".compareTo(getDatos[i][0])==0)
    {
        EnergiaImportTariff3= getDatos[i][1];
    }
    if("2.8.0*01".compareTo(getDatos[i][0])==0)
    {
        TotalEnergiaExportFacturaPasada= getDatos[i][1];
    }
    if("2.8.1*01".compareTo(getDatos[i][0])==0)
    {
        EnergiaExportMesPasadoTariff1=getDatos[i][1];
    }
    if("2.8.2*01".compareTo(getDatos[i][0])==0)
    {
        EnergiaExportMesPasadoTariff2=getDatos[i][1];
    }
    if("2.8.3*01".compareTo(getDatos[i][0])==0)
    {
        EnergiaExportMesPasadoTariff3= getDatos[i][1];
    }
    if("1.8.0*01".compareTo(getDatos[i][0])==0)
    {
        TotalEnergiaImportFacturaPasada= getDatos[i][1];
    }
    if("1.8.1*01".compareTo(getDatos[i][0])==0)
    {
        EnergiaImportMesPasadoTariff1=getDatos[i][1];
    }
    if("1.8.2*01".compareTo(getDatos[i][0])==0)
    {
        EnergiaImportMesPasadoTariff2=getDatos[i][1];
    }
    if("1.8.3*01".compareTo(getDatos[i][0])==0)
    {
        EnergiaImportMesPasadoTariff3=getDatos[i][1];
    }
    if("1.2.1".compareTo(getDatos[i][0])==0)
    {
        AcumularActiveMDImportTariff1=getDatos[i][1];
    }
    if("1.2.2".compareTo(getDatos[i][0])==0)
    {
        AcumularActiveMDImportTariff2=getDatos[i][1];
    }
    if("1.2.3".compareTo(getDatos[i][0])==0)
    {
        AcumularActiveMDImportTariff3=getDatos[i][1];
    }
     if("1.2.4".compareTo(getDatos[i][0])==0)
    {
        AcumularActiveMDImportTariff4=getDatos[i][1];
    }
    if("2.2.1".compareTo(getDatos[i][0])==0)
    {
        AcumularActiveMDExportTariff1=getDatos[i][1];
    }
     if("2.2.2".compareTo(getDatos[i][0])==0)
    {
        AcumularActiveMDExportTariff2=getDatos[i][1];
    }
     if("2.2.3".compareTo(getDatos[i][0])==0)
    {
        AcumularActiveMDExportTariff3=getDatos[i][1];
    }
     if("2.2.4".compareTo(getDatos[i][0])==0)
    {
        AcumularActiveMDExportTariff4=getDatos[i][1];
    }
    if("1.6.0".compareTo(getDatos[i][0])==0)
    {
        ImportMaxDemandaTotal= getDatos[i][1];
    }
    if("1.6.1".compareTo(getDatos[i][0])==0)
    {
        ImportMaxDemandaTarifa1= getDatos[i][1];
    }
    if("1.6.2".compareTo(getDatos[i][0])==0)
    {
        ImportMaxDemandaTarifa2=getDatos[i][1];
    }
    if("1.6.3".compareTo(getDatos[i][0])==0)
    {
        ImportMaxDemandaTarifa3=getDatos[i][1];
    }
    if("1.6.4".compareTo(getDatos[i][0])==0)
    {
        ImportMaxDemandaTarifa4=getDatos[i][1];
    }
    if("2.6.0".compareTo(getDatos[i][0])==0)
    {
        ExportMaxDemandaTotal=getDatos[i][1];
    }
    if("2.6.1".compareTo(getDatos[i][0])==0)
    {
        ExportMaxDemandaTarifa1=getDatos[i][1];
    }
    if("2.6.2".compareTo(getDatos[i][0])==0)
    {
        ExportMaxDemandaTarifa2=getDatos[i][1];
    }
    if("2.6.3".compareTo(getDatos[i][0])==0)
    {
        ExportMaxDemandaTarifa3=getDatos[i][1];
    }
    if("2.6.4".compareTo(getDatos[i][0])==0)
    {
        ExportMaxDemandaTarifa4=getDatos[i][1];
    }
    if("N_455".compareTo(getDatos[i][0])==0)
    {
        VoltageNominal= getDatos[i][1];
    }
    if("32.7".compareTo(getDatos[i][0])==0)
    {
        VoltageL1=getDatos[i][1];
    }
    if("52.7".compareTo(getDatos[i][0])==0)
    {
        VoltageL2=getDatos[i][1];
    }
    if("72.7".compareTo(getDatos[i][0])==0)
    {
        VoltageL3=getDatos[i][1];
    }
    if("31.7".compareTo(getDatos[i][0])==0)
    {
        CurrentL1=getDatos[i][1];
    }
    if("51.7".compareTo(getDatos[i][0])==0)
    {
        CurrentL2=getDatos[i][1];
    }
    if("71.7".compareTo(getDatos[i][0])==0)
    {
        CurrentL3=getDatos[i][1];
    }
 }
 CargaBaseDatos(medidor,estado,TotalEnergiaExport,EnergiaExporTariff1,EnergiaExporTariff2,EnergiaExporTariff3,TotalEnergiaImport,EnergiaImportTariff1,EnergiaImportTariff2,EnergiaImportTariff3,TotalEnergiaExportFacturaPasada,EnergiaExportMesPasadoTariff1,EnergiaExportMesPasadoTariff2,EnergiaExportMesPasadoTariff3,TotalEnergiaImportFacturaPasada,EnergiaImportMesPasadoTariff1,EnergiaImportMesPasadoTariff2,EnergiaImportMesPasadoTariff3,AcumularActiveMDImportTariff1,AcumularActiveMDImportTariff2,AcumularActiveMDImportTariff3,AcumularActiveMDImportTariff4,AcumularActiveMDExportTariff1,AcumularActiveMDExportTariff2,AcumularActiveMDExportTariff3,AcumularActiveMDExportTariff4,ImportMaxDemandaTotal,ImportMaxDemandaTarifa1,ImportMaxDemandaTarifa2,ImportMaxDemandaTarifa3,ImportMaxDemandaTarifa4,ExportMaxDemandaTotal,ExportMaxDemandaTarifa1,ExportMaxDemandaTarifa2,ExportMaxDemandaTarifa3,ExportMaxDemandaTarifa4,VoltageNominal,VoltageL1,VoltageL2,VoltageL3,CurrentL1,CurrentL2,CurrentL3);
}
public void CargaBaseDatos(int medidor,int Estado,String TotalEnergiaExport,String EnergiaExporTariff1,String EnergiaExporTariff2,String EnergiaExporTariff3,String TotalEnergiaImport, String EnergiaImportTariff1,String EnergiaImportTariff2,String EnergiaImportTariff3,String TotalEnergiaExportFacturaPasada,String EnergiaExportMesPasadoTariff1,String EnergiaExportMesPasadoTariff2,String EnergiaExportMesPasadoTariff3,String TotalEnergiaImportFacturaPasada,String EnergiaImportMesPasadoTariff1,String EnergiaImportMesPasadoTariff2,String EnergiaImportMesPasadoTariff3,String AcumularActiveMDImportTariff1,String AcumularActiveMDImportTariff2,String AcumularActiveMDImportTariff3,String AcumularActiveMDImportTariff4,String AcumularActiveMDExportTariff1,String AcumularActiveMDExportTariff2,String AcumularActiveMDExportTariff3,String AcumularActiveMDExportTariff4, String ImportMaxDemandaTotal,String ImportMaxDemandaTarifa1,String ImportMaxDemandaTarifa2,String ImportMaxDemandaTarifa3,String ImportMaxDemandaTarifa4,String ExportMaxDemandaTotal,String ExportMaxDemandaTarifa1,String ExportMaxDemandaTarifa2,String ExportMaxDemandaTarifa3,String ExportMaxDemandaTarifa4,String VoltageNominal,String VoltageL1,String VoltageL2,String VoltageL3,String CurrentL1,String CurrentL2,String CurrentL3)
{
        try {
             //FechaAndHora datetime= new FechaAndHora();
             double[] auxPT= new double[2];
             String EnergiaImport= TotalEnergiaImport.substring(TotalEnergiaImport.indexOf("(")+1,TotalEnergiaImport.indexOf(")"));
             String EnergiaExport= TotalEnergiaExport.substring(TotalEnergiaExport.indexOf("(")+1,TotalEnergiaExport.indexOf(")"));
             auxPT=potenciaMed(medidor,Double.parseDouble(EnergiaImport),Double.parseDouble(EnergiaExport),fechaHora.getNow()); //int medidor,double TotalEnergiaImport, double TotalEnergiaExport,String FechaActual
             //auxPT[0]= Tiempo, auxPT[1]= PonteciaMedia(kw).
             //System.out.println(auxPT[0]+auxPT[1]);
             PotenciaMedia= auxPT[1];
             Tiempo=auxPT[0];
             //
              //System.out.println("Insertando"+" "+fechaHora.getNow());
           //base.ejecutarSentencia("Insert into lectura(nSerie,Fecha,Estado,TotalEnergiaExport,EnergiaExporTariff1,EnergiaExporTariff2,EnergiaExporTariff3,TotalEnergiaImport,EnergiaImportTariff1,EnergiaImportTariff2,EnergiaImportTariff3,TotalEnergiaExportFacturaPasada,EnergiaExportMesPasadoTariff1,EnergiaExportMesPasadoTariff2,EnergiaExportMesPasadoTariff3,TotalEnergiaImportFacturaPasada,EnergiaImportMesPasadoTariff1,EnergiaImportMesPasadoTariff2,EnergiaImportMesPasadoTariff3,AcumularActiveMDImportTariff1,AcumularActiveMDImportTariff2,AcumularActiveMDImportTariff3,AcumularActiveMDImportTariff4,AcumularActiveMDExportTariff1,AcumularActiveMDExportTariff2,AcumularActiveMDExportTariff3,AcumularActiveMDExportTariff4,ImportMaxDemandaTotal,ImportMaxDemandaTarifa1,ImportMaxDemandaTarifa2,ImportMaxDemandaTarifa3,ImportMaxDemandaTarifa4,ExportMaxDemandaTotal,ExportMaxDemandaTarifa1,ExportMaxDemandaTarifa2,ExportMaxDemandaTarifa3,ExportMaxDemandaTarifa4,VoltageNominal,VoltageL1,VoltageL2,VoltageL3,CurrentL1,CurrentL2,CurrentL3,PotenciaMedia,Tiempo) values('"+medidor+"','"+fechaHora.getNow()+"','"+Estado+"','"+TotalEnergiaExport+"','"+EnergiaExporTariff1+"','"+EnergiaExporTariff2+"','"+EnergiaExporTariff3+"','"+TotalEnergiaImport+"','"+EnergiaImportTariff1+"','"+EnergiaImportTariff2+"','"+EnergiaImportTariff3+"','"+TotalEnergiaExportFacturaPasada+"','"+EnergiaExportMesPasadoTariff1+"','"+EnergiaExportMesPasadoTariff2+"','"+EnergiaExportMesPasadoTariff3+"','"+TotalEnergiaImportFacturaPasada+"','"+EnergiaImportMesPasadoTariff1+"','"+EnergiaImportMesPasadoTariff2+"','"+EnergiaImportMesPasadoTariff3+"','"+AcumularActiveMDImportTariff1+"','"+AcumularActiveMDImportTariff2+"','"+AcumularActiveMDImportTariff3+"','"+AcumularActiveMDImportTariff4+"','"+AcumularActiveMDExportTariff1+"','"+AcumularActiveMDExportTariff2+"','"+AcumularActiveMDExportTariff3+"','"+AcumularActiveMDExportTariff4+"','"+ImportMaxDemandaTotal+"','"+ImportMaxDemandaTarifa1+"','"+ImportMaxDemandaTarifa2+"','"+ImportMaxDemandaTarifa3+"','"+ImportMaxDemandaTarifa4+"','"+ImportMaxDemandaTotal+"','"+ImportMaxDemandaTarifa1+"','"+ImportMaxDemandaTarifa2+"','"+ImportMaxDemandaTarifa3+"','"+ImportMaxDemandaTarifa4+"','"+VoltageNominal+"','"+VoltageL1+"','"+VoltageL2+"','"+VoltageL3+"','"+CurrentL1+"','"+CurrentL2+"','"+CurrentL3+"',"+PotenciaMedia+","+Tiempo+")");//"+auxPT[1]+","+auxPT[0]+"
             base.ejecutarSentencia("Insert into lectura(nSerie,Fecha,Estado,TotalEnergiaExport,EnergiaExporTariff1,EnergiaExporTariff2,EnergiaExporTariff3,TotalEnergiaImport,EnergiaImportTariff1,EnergiaImportTariff2,EnergiaImportTariff3,TotalEnergiaExportFacturaPasada,EnergiaExportMesPasadoTariff1,EnergiaExportMesPasadoTariff2,EnergiaExportMesPasadoTariff3,TotalEnergiaImportFacturaPasada,EnergiaImportMesPasadoTariff1,EnergiaImportMesPasadoTariff2,EnergiaImportMesPasadoTariff3,AcumularActiveMDImportTariff1,AcumularActiveMDImportTariff2,AcumularActiveMDImportTariff3,AcumularActiveMDImportTariff4,AcumularActiveMDExportTariff1,AcumularActiveMDExportTariff2,AcumularActiveMDExportTariff3,AcumularActiveMDExportTariff4,ImportMaxDemandaTotal,ImportMaxDemandaTarifa1,ImportMaxDemandaTarifa2,ImportMaxDemandaTarifa3,ImportMaxDemandaTarifa4,ExportMaxDemandaTotal,ExportMaxDemandaTarifa1,ExportMaxDemandaTarifa2,ExportMaxDemandaTarifa3,ExportMaxDemandaTarifa4,VoltageNominal,VoltageL1,VoltageL2,VoltageL3,CurrentL1,CurrentL2,CurrentL3,PotenciaMedia,Tiempo) values('"+medidor+"','"+fechaHora.getNow()+"','"+Estado+"','"+TotalEnergiaExport+"','"+EnergiaExporTariff1+"','"+EnergiaExporTariff2+"','"+EnergiaExporTariff3+"','"+TotalEnergiaImport+"','"+EnergiaImportTariff1+"','"+EnergiaImportTariff2+"','"+EnergiaImportTariff3+"','"+TotalEnergiaExportFacturaPasada+"','"+EnergiaExportMesPasadoTariff1+"','"+EnergiaExportMesPasadoTariff2+"','"+EnergiaExportMesPasadoTariff3+"','"+TotalEnergiaImportFacturaPasada+"','"+EnergiaImportMesPasadoTariff1+"','"+EnergiaImportMesPasadoTariff2+"','"+EnergiaImportMesPasadoTariff3+"','"+AcumularActiveMDImportTariff1+"','"+AcumularActiveMDImportTariff2+"','"+AcumularActiveMDImportTariff3+"','"+AcumularActiveMDImportTariff4+"','"+AcumularActiveMDExportTariff1+"','"+AcumularActiveMDExportTariff2+"','"+AcumularActiveMDExportTariff3+"','"+AcumularActiveMDExportTariff4+"','"+ImportMaxDemandaTotal+"','"+ImportMaxDemandaTarifa1+"','"+ImportMaxDemandaTarifa2+"','"+ImportMaxDemandaTarifa3+"','"+ImportMaxDemandaTarifa4+"','"+ExportMaxDemandaTotal+"','"+ExportMaxDemandaTarifa1+"','"+ExportMaxDemandaTarifa2+"','"+ExportMaxDemandaTarifa3+"','"+ExportMaxDemandaTarifa4+"','"+VoltageNominal+"','"+VoltageL1+"','"+VoltageL2+"','"+VoltageL3+"','"+CurrentL1+"','"+CurrentL2+"','"+CurrentL3+"',"+PotenciaMedia+","+Tiempo+")");//"+auxPT[1]+","+auxPT[0]+"

        } catch (Exception ex) {//  Insert into lectura values('30111058','"+fecha+"','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0')
            log.addReg("captura.CargaBaseDatos problema insert"+ex);
        }
}

public static void insertEnergiaModbus(int em, Double energia){
    try{
        String statement = "INSERT INTO lectura(nSerie,Fecha,Estado,TotalEnergiaImport,ImportMaxDemandaTotal,ImportMaxDemandaTarifa1) VALUES ("+em+",NOW(),1,'"+energia+"','0','0')";//";
        ConsultaBD b = new ConsultaBD();
        b.ejecutarSentencia(statement);
    }catch(Exception ex){
        logFile.addReg("Excepcion insertando la energia: "+ex);
    }
}

public static void insertEnergiaModbus(String em, Double energia){
    try{
        String statement = "INSERT INTO lectura(nSerie,Fecha,Estado,TotalEnergiaImport,ImportMaxDemandaTotal,ImportMaxDemandaTarifa1) VALUES ('"+em+"',NOW(),1,'"+energia+"','0','0')";//";
        ConsultaBD b = new ConsultaBD();
        b.ejecutarSentencia(statement);
    }catch(Exception ex){
        logFile.addReg("Excepcion insertando la energia: "+ex);
    }
}


}
/*
 ImportMaxDemandaTotal	varchar(50)	latin1_swedish_ci		No	None
	ImportMaxDemandaTarifa1	varchar(50)	latin1_swedish_ci		No	None
	ImportMaxDemandaTarifa2	varchar(50)	latin1_swedish_ci		No	None
	ImportMaxDemandaTarifa3	varchar(50)	latin1_swedish_ci		No	None
	ImportMaxDemandaTarifa4	varchar(50)	latin1_swedish_ci		No	None
	ExportMaxDemandaTotal	varchar(50)	latin1_swedish_ci		No	None
	ExportMaxDemandaTarifa1	varchar(50)	latin1_swedish_ci		No	None
	ExportMaxDemandaTarifa2	varchar(50)	latin1_swedish_ci		No	None
	ExportMaxDemandaTarifa3	varchar(50)	latin1_swedish_ci		No	None
	ExportMaxDemandaTarifa4
 */
/*
 * 	-Id_Lectura	int(11)			No	None	auto_increment
	-nSerie	int(50)			No	None
	-Fecha	datetime			No	None
	-TotalEnergiaExport	varchar(50)	latin1_swedish_ci		No	None
	-EnergiaExporTariff1	varchar(50)	latin1_swedish_ci		No	None
	-EnergiaExporTariff2	varchar(50)	latin1_swedish_ci		No	None
	-EnergiaExporTariff3	varchar(50)	latin1_swedish_ci		No	None
	-TotalEnergiaImport	varchar(50)	latin1_swedish_ci		No	None
	-EnergiaImportTariff1	varchar(50)	latin1_swedish_ci		No	None
	-EnergiaImportTariff2	varchar(50)	latin1_swedish_ci		No	None
	-EnergiaImportTariff3	varchar(50)	latin1_swedish_ci		No	None
	-TotalEnergiaExportFacturaPasada	varchar(50)	latin1_swedish_ci		No	None
	-EnergiaExportMesPasadoTariff1	varchar(50)	latin1_swedish_ci		No	None
	-EnergiaExportMesPasadoTariff2	varchar(50)	latin1_swedish_ci		No	None
	-EnergiaExportMesPasadoTariff3	varchar(50)	latin1_swedish_ci		No	None
	-TotalEnergiaImportFacturaPasada	varchar(50)	latin1_swedish_ci		No	None
	-EnergiaImportMesPasadoTariff1	varchar(50)	latin1_swedish_ci		No	None
	-EnergiaImportMesPasadoTariff2	varchar(50)	latin1_swedish_ci		No	None
	-EnergiaImportMesPasadoTariff3	varchar(50)	latin1_swedish_ci		No	None
	-AcumularActiveMDImportTariff1	varchar(50)	latin1_swedish_ci		No	None
	-AcumularActiveMDImportTariff2	varchar(50)	latin1_swedish_ci		No	None
	-AcumularActiveMDImportTariff3	varchar(50)	latin1_swedish_ci		No	None
	-AcumularActiveMDImportTariff4	varchar(50)	latin1_swedish_ci		No	None
	AcumularActiveMDExportTariff1	varchar(50)	latin1_swedish_ci		No	None
	AcumularActiveMDExportTariff2	varchar(50)	latin1_swedish_ci		No	None
	AcumularActiveMDExportTariff3	varchar(50)	latin1_swedish_ci		No	None
	AcumularActiveMDExportTariff4	varchar(50)	latin1_swedish_ci		No	None

 *
 */

/*
 *      -EnergiaExporTariff2	varchar(50)	latin1_swedish_ci		No	None
	-EnergiaExporTariff3	varchar(50)	latin1_swedish_ci		No	None
	-TotalEnergiaImport	varchar(50)	latin1_swedish_ci		No	None
	-EnergiaImportTariff1	varchar(50)	latin1_swedish_ci		No	None
	-EnergiaImportTariff2	varchar(50)	latin1_swedish_ci		No	None
	-EnergiaImportTariff3	varchar(50)	latin1_swedish_ci		No	None
	-TotalEnergiaExportFacturaPasada	varchar(50)	latin1_swedish_ci		No	None
	-EnergiaExportMesPasadoTariff1	varchar(50)	latin1_swedish_ci		No	None
	-EnergiaExportMesPasadoTariff2	varchar(50)	latin1_swedish_ci		No	None
	-EnergiaExportMesPasadoTariff3	varchar(50)	latin1_swedish_ci		No	None
	-TotalEnergiaImportFacturaPasada	varchar(50)	latin1_swedish_ci		No	None
	-EnergiaImportMesPasadoTariff1	varchar(50)	latin1_swedish_ci		No	None
	-EnergiaImportMesPasadoTariff2	varchar(50)	latin1_swedish_ci		No	None
	-EnergiaImportMesPasadoTariff3	varchar(50)	latin1_swedish_ci		No	None
	-AcumularActiveMDImportTariff1	varchar(50)	latin1_swedish_ci		No	None
	-AcumularActiveMDImportTariff2	varchar(50)	latin1_swedish_ci		No	None
	-AcumularActiveMDImportTariff3	varchar(50)	latin1_swedish_ci		No	None
	-AcumularActiveMDImportTariff4	varchar(50)	latin1_swedish_ci		No	None
	-AcumularActiveMDExportTariff1	varchar(50)	latin1_swedish_ci		No	None
	-AcumularActiveMDExportTariff2	varchar(50)	latin1_swedish_ci		No	None
	-AcumularActiveMDExportTariff3	varchar(50)	latin1_swedish_ci		No	None
	-AcumularActiveMDExportTariff4
 *
 */