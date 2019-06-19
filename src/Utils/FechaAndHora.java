/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Administrador
 * Recordar que el sistema inicia la cuenta desde el 0 es decir
 * el 11 es diciembre. como ejemplo!!!.
 */
public class FechaAndHora {

    protected Date fechahora= new Date();
    protected Calendar c1= Calendar.getInstance();
    protected Calendar c2= new GregorianCalendar();
    protected String hora,fecha,datetime,datetime2= new String(" ");
    protected String[] arrayfecha;
    protected logFile log= new logFile();

     public FechaAndHora()
    {
      hora= Integer.toString(c1.get(Calendar.HOUR_OF_DAY))+":"+Integer.toString(c1.get(Calendar.MINUTE))+":"+Integer.toString(c1.get(Calendar.SECOND));
      fecha= Integer.toString(c1.get(Calendar.YEAR))+"-"+Integer.toString(c1.get(Calendar.MONTH))+"-"+Integer.toString(c1.get(Calendar.DATE));
      datetime= Integer.toString(c1.get(Calendar.YEAR))+"-"+Integer.toString(c1.get(Calendar.MONTH))+"-"+Integer.toString(c1.get(Calendar.DATE))+" "+Integer.toString(c1.get(Calendar.HOUR_OF_DAY))+":"+Integer.toString(c1.get(Calendar.MINUTE))+":"+Integer.toString(c1.get(Calendar.SECOND));
      //System.out.println("directo"+c1.get(Calendar.YEAR)+"-"+c1.get(Calendar.MONTH)+"-"+c1.get(Calendar.DATE));
      //datetime2= Integer.toString(GregorianCalendar.YEAR)+"-"+Integer.toString(GregorianCalendar.MONTH)+"-"+Integer.toString(GregorianCalendar.DATE)+" "+Integer.toString(c2.HOUR_OF_DAY)+":"+Integer.toString(c2.MINUTE)+":"+Integer.toString(c2.SECOND);
      arrayfecha= new String[2];
    }
   
    public String fecha()
    {
        return fecha;
    }
    public String hora()
    {
     return hora;
    }
    public String Datetime()
    {
        return datetime;
    }

    public boolean esBisiesto(int anio){
        return (((anio%100==0) && (anio%400==0)) || ((anio%100!=0) && (anio%4==0)))?true:false;
    }

    public double getDayofMonthsegundos(String numMes,String anio)
    {
        int meses[]= {31,28,31,30,31,30,31,31,30,31,30,31};
        int mesesb[]= {31,29,31,30,31,30,31,31,30,31,30,31};
        int mes= Integer.parseInt(numMes)-1;
        double suma=0;
        int an= Integer.parseInt(anio);

       for(int i=mes;i>=1;i--)
       {
         if(esBisiesto(an))
           {
                suma=suma+mesesb[i];
           }
         else
         {
                suma=suma+meses[i];
                //System.out.println(meses[i]);
         }
       }

       
       return suma*24*3600;// -2 lo retorna cunado el resultado es nulo!!!
    }
    public double DaySegundos(String Dia)
    {
       double dia= Double.parseDouble(Dia);
       return dia*24*3600;
    }
    public double YearSegundos(String anio)
    {
        double año= Double.parseDouble(anio);
        return año*365*24*3600;
    }
    public double[] FechaSeg(String FechaActual,String FechaAnterior)
    {
       double difFecha=0;
       String[] Actual,Anterior;
       double[] FechaSegundo=new double[2];
       
       Actual= FechaActual.split("-");
       Anterior= FechaAnterior.split("-");
       //difFecha= (YearSegundos(Actual[0])+getDayofMonthsegundos(Actual[1],Actual[0])+DaySegundos(Actual[2]))-(YearSegundos(Anterior[0])+getDayofMonthsegundos(Anterior[1],Anterior[0])+DaySegundos(Anterior[2]));
       FechaSegundo[0]= (YearSegundos(Anterior[0])+getDayofMonthsegundos(Anterior[1],Anterior[0])+DaySegundos(Anterior[2]));// anterior[0]
       FechaSegundo[1]=(YearSegundos(Actual[0])+getDayofMonthsegundos(Actual[1],Actual[0])+DaySegundos(Actual[2])); //actual[1]
       
       //sumaMes= (getDayofMonth(Integer.parseInt(arrayFechaActual[1]),Integer.parseInt(arrayFechaActual[0]))*24*3600)-(getDayofMonth(Integer.parseInt(arrayFechaAnterior[1]),Integer.parseInt(arrayFechaAnterior[0]))*24*3600);
       //System.out.println(getDayofMonth(13,2011));
      
       //System.out.println(arrayFechaActual[0]+"_"+arrayFechaActual[1]+"_"+arrayFechaActual[2]);
       //System.out.println(arrayFechaAnterior[0]+"_"+arrayFechaAnterior[1]+"_"+arrayFechaAnterior[2]);
      

       return FechaSegundo;
    }
    public double[] HoraSegundosCalculo(String tiempoA,String tiempoB)
    {
     double dif=0,A=0,B=0;
     double[] tiempoSegundos= new double[2];
     String array2,array3,fechaActual,fechabaseD;
     String[] arrayfechab;
     //if(tiempoA.compareTo(" ")!=0 && tiempoB.compareTo(" ")!=0)
     //{
     try{
            //System.out.println(tiempoA+"y"+tiempoB);
            tiempoA= tiempoA+".";
            fechabaseD= tiempoA.substring(0,10);
            array2= tiempoA.substring(10,19);
            //System.out.println(array2+"|"+fechabaseD);
            arrayfecha=array2.split(":");
            //System.out.println("tiempoA"+" "+arrayfecha[0]+":"+arrayfecha[1]+":"+arrayfecha[2]);
            //System.out.println("tiempoB"+" "+)
            //System.out.println("Array2:"+array2);
            fechaActual= tiempoB.substring(tiempoB.indexOf(""),tiempoB.indexOf(" "));
            //System.out.println("fechaActual########"+fechaActual);
            tiempoB=tiempoB+".";
            array3= tiempoB.substring(tiempoB.indexOf(" "),tiempoB.indexOf("."));
            //System.out.println("#############"+array3);
            //System.out.println("timepoB|horaB"+tiempoB+"|"+fechaActual);
            arrayfechab=array3.split(":");
            //System.out.println("array3:"+array3);
            //System.out.println("tiempoB"+" "+arrayfechab[0]+":"+arrayfechab[1]+":"+arrayfechab[2]);
           // diferenciaFecha(fechaActual,fechabaseD);
            B= (Double.parseDouble(arrayfechab[0])*3600)+(Double.parseDouble(arrayfechab[1])*60)+(Double.parseDouble(arrayfechab[2]));
            A= (Double.parseDouble(arrayfecha[0])*3600)+(Double.parseDouble(arrayfecha[1])*60)+(Double.parseDouble(arrayfecha[2]));
            tiempoSegundos[0]=A; //anterior
            tiempoSegundos[1]=B; // actual
            //System.out.println(B+"--|--"+A);
            //System.out.println("Diferencia de fechas:"+(B-A));
            /*if(A>B)
            {
                dif=A-B;

               dif=diferenciaFecha(fechaActual,fechabaseD)+dif;
                //System.out.println("Diferencia horas:"+dif+"y"+"Fechas:"+diferenciaFecha(fechaActual,fechabaseD));
            }
            else
            {
                dif=B-A;
                dif=diferenciaFecha(fechaActual,fechabaseD)+dif;
                //System.out.println("Diferencia horas:"+dif+"y"+"Fechas:"+diferenciaFecha(fechaActual,fechabaseD));
            }*/
            //dif= B-A;
        }catch(Exception ex)
        {
            //log.addReg("HoraSegundosCalculo","Problema con la transformación de la hora A o B",""+ex);
            System.out.print("falla"+ex);
        }
     //}
    
     //System.out.print(arrayfecha[0]+":"+arrayfecha[1]+":"+arrayfecha[2]);// Hora[0]:Minutos[1]:Segundos[2]
    
     return tiempoSegundos;
    }
    public boolean comparaDia(String Dia) /*#Compara fecha actual con la fecha enviada por parametro
                                           Si retorna un false, la fecha no es igual, si retorna un true la fecha es identica*/
    {
        boolean bandera=false;
        arrayfecha=fecha.split("-");
        //System.out.print(arrayfecha[0]+"_"+arrayfecha[1]+"_"+arrayfecha[2]);
        //System.out.println(arrayfecha[2]+"y"+Dia);
        if(arrayfecha[2].compareTo(Dia)==0)
        {
            bandera=true;
            return bandera;
        }
        else
        {
            return bandera;
        }
    }
    public boolean comparaFecha(String datatime)
    {
        String FechaA="";
        //System.out.println("En FechaAndHora anterior:"+datatime);
        FechaA= datatime.substring(datatime.indexOf(""),10);
        //System.out.print("fecha Actual"+FechaA);
        boolean bandera=true;                            // MODIFICAR PARA QUE NO SOLO COMPARE FECHAS IGUALES, SI NO TAMBIEN MES AÑO
        String[] arrayAcomparar=new String[2];
        arrayfecha=fecha.split("-");
        //System.out.println("fecha Sistema"+fecha);
        
        arrayAcomparar= FechaA.split("-");
        //System.out.println(arrayAcomparar[0]+"|"+arrayAcomparar[1]+"|"+arrayAcomparar[2]);
        /*if(Integer.parseInt(arrayAcomparar[1])>11)
        {
            System.out.println("entro a if repara fecha");
          arrayAcomparar[1]="0";
          arrayAcomparar[0]= Integer.toString(Integer.parseInt(arrayAcomparar[0])+1);
        }
         */

       // System.out.println("############"+Integer.parseInt(arrayAcomparar[1]));
         // System.out.println(arrayAcomparar[0]+"|"+arrayAcomparar[1]+"y"+"  "+arrayfecha[0]+"|"+arrayfecha[1]);
        if((Integer.parseInt(arrayfecha[0])==Integer.parseInt(arrayAcomparar[0])) && (Integer.parseInt(arrayfecha[1])==Integer.parseInt(arrayAcomparar[1])))
        {
           // System.out.println(Integer.parseInt(arrayfecha[0]));
            //System.out.println(Integer.parseInt(arrayAcomparar[0]));

            bandera=false;
            //System.out.println("entro ir compara fechas y retorno"+bandera);
            return bandera;

        }
        else
        {
            return bandera;
        }
        
    }

    public String cutFecha(String fecha,int valor)
    {
      String dato[],capt[];
      capt= new String[3];
      dato=fecha.split("-");
      //System.out.println("prueba:"+fecha);
      capt[0]= dato[2].substring(dato[2].indexOf(""),dato[2].indexOf(" "));
      //System.out.println(capt[0]);

      if(valor== 0) //año
      {

       return dato[0];
      }
      if(valor==1)// mes
      {
       return dato[1];
      }
      if(valor==2) // dia
      {
      return capt[0];
      }

      return "";
      


      
      //return listo;
    }
}