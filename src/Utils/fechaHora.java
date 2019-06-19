/*
 *  clase fechaHora usado en ToldosDriver
 *  revisión: se agregan nuevos métodos para obtener la fecha actual y ayer.
 *  autor: Michael Venegas Rodriguez
 *  DreamIT Sistemas
 *  www.dreamit.cl
 */

package Utils;

import static Utils.fechaHora.getAfterDay;
import static Utils.fechaHora.getBeforeDay;
import static Utils.fechaHora.getFirstDayCurrentWeek;
import static Utils.fechaHora.getFirstDayPassWeek;
import static Utils.fechaHora.getLastDayCurrentWeek;
import static Utils.fechaHora.getLastDayPassWeek;
import static Utils.fechaHora.getNow;
import static Utils.fechaHora.getToday;
import static Utils.fechaHora.getYesterday;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class fechaHora
{

    public fechaHora()
    {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dat = new Date();
        FechaHora = formato.format(dat);
        formato = new SimpleDateFormat("yyyy-MM-dd");
        Fecha = formato.format(dat);
        formato = new SimpleDateFormat("HH:mm:ss");
        Hora = formato.format(dat);
    }

    public fechaHora(String FechaHora)
    {
        this.FechaHora = FechaHora;
        Fecha = FechaHora.substring(0, FechaHora.indexOf(" "));
        Hora = FechaHora.substring(FechaHora.indexOf(" ") + 1, FechaHora.lastIndexOf(":") + 2);
    }

    public static String getNow()
    {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dat = new Date();
        return formato.format(dat);
    }
    
    public static String getNowI()
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date dat = new Date();
        return formato.format(dat);
    }    

    public static String getToday(){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date dat = new Date();
        return formato.format(dat);
    }

    public static String getYesterday(){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.DATE, -1);
        return formato.format(cal.getTime());
    }

    public static String getTomorrow(){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.DATE, +1);
        return formato.format(cal.getTime());
    }

    public static String getLastDayPassWeek(){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.DATE, -(cal.get(Calendar.DAY_OF_WEEK)-1));
        return formato.format(cal.getTime());
    }

    public static String getLastDayPassWeek(int anio,int mes,int dia){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(Calendar.DATE, dia);
        cal.set(Calendar.MONTH, (mes-1));
        cal.set(Calendar.YEAR, anio);
        cal.add(java.util.Calendar.DATE, -(cal.get(Calendar.DAY_OF_WEEK)-1));
        return formato.format(cal.getTime());
    }

    public static String getFirstDayPassWeek(){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.DATE, -(cal.get(Calendar.DAY_OF_WEEK)+5));
        return formato.format(cal.getTime());
    }

    public static String getFirstDayPassWeek(int anio, int mes, int dia){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(Calendar.DATE, dia);
        cal.set(Calendar.MONTH, (mes-1));
        cal.set(Calendar.YEAR, anio);
        cal.add(java.util.Calendar.DATE, -(cal.get(Calendar.DAY_OF_WEEK)+5));
        return formato.format(cal.getTime());
    }

    public static String getLastDayCurrentWeek(){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.DATE, +(8-cal.get(Calendar.DAY_OF_WEEK)));
        return formato.format(cal.getTime());
    }

    public static String getLastDayCurrentWeek(int anio, int mes, int dia){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(Calendar.DATE, dia);
        cal.set(Calendar.MONTH, (mes-1));
        cal.set(Calendar.YEAR, anio);
        cal.add(java.util.Calendar.DATE, +(8-cal.get(Calendar.DAY_OF_WEEK)));
        return formato.format(cal.getTime());
    }

    public static String getFirstDayCurrentWeek(){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.DATE, -(cal.get(Calendar.DAY_OF_WEEK)-2));
        return formato.format(cal.getTime());
    }

    public static String getFirstDayCurrentWeek(int anio, int mes, int dia){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(Calendar.DATE, dia);
        cal.set(Calendar.MONTH, (mes-1));
        cal.set(Calendar.YEAR, anio);
        cal.add(java.util.Calendar.DATE, -(cal.get(Calendar.DAY_OF_WEEK))+2);
        return formato.format(cal.getTime());
    }

    public static String getBeforeDay(int anio, int mes, int dia){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(Calendar.DATE, dia);
        cal.set(Calendar.MONTH, (mes-1));
        cal.set(Calendar.YEAR, anio);
        cal.add(java.util.Calendar.DATE, -1);
        return formato.format(cal.getTime());
    }
     public static String getMonthNow()
    {
        String month= getNow();


        return month.substring(5,7);
    }

    public static String getAfterDay(int anio, int mes, int dia){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(Calendar.DATE, dia);
        cal.set(Calendar.MONTH, (mes-1));
        cal.set(Calendar.YEAR, anio);
        cal.add(java.util.Calendar.DATE, +1);
        return formato.format(cal.getTime());
    }

    public String getFechaHora()
    {
        return FechaHora;
    }

    public String getFecha()
    {
        return Fecha;
    }

    public String getHora()
    {
        return Hora;
    }
    public double getHoraEnSegundos(int i) // donde i representa hora, minutos o segundos, si el calculo es completamente 
    {
        double segundos=0;
        String array[]=Hora.split(":");
        if(i==1)//hora: ejemplo 12*60
        {
            segundos=Double.parseDouble(array[0])*60;
        }
        if(i==2) // minutos
        {
            segundos= Double.parseDouble(array[1])*60;
        }
        if(i==3) //segundos
        {
            segundos=Double.parseDouble(array[2]);
        }
        if(i==4)// hora completa a segundos
        {
            segundos=  Double.parseDouble(array[0])*60+Double.parseDouble(array[1])*60+Double.parseDouble(array[2]);
        }
        return segundos;
    }

    public String hora()
    {
        return Hora.substring(0, Hora.indexOf(":"));
    }

    public String minuto()
    {
        return Hora.substring(Hora.indexOf(":") + 1, Hora.lastIndexOf(":"));
    }

    public String segundo()
    {
        return Hora.substring(Hora.lastIndexOf(":") + 1);
    }


    public static void main(String args[])
    {
        fechaHora a = new fechaHora();
        System.out.println("Fecha y hora Actual: "+a.getFechaHora());
        System.out.println("Fecha Actual: "+a.getFecha());
        System.out.println("Fecha Actual invertida: "+fechaUtil.invertir(a.getFecha()));
        System.out.println("Hora Actual: "+a.getHora());
        System.out.println("Hora: "+a.hora());
        System.out.println("Minuto: "+a.minuto());
        System.out.println("Segundo: "+a.segundo());
        System.out.println("Ahora: "+getNow());
        System.out.println("Hoy: "+getToday());
        System.out.println("Ayer: "+getYesterday());
        System.out.println("Ultimo dia de esta semana: "+getLastDayCurrentWeek());
        System.out.println("Primer dia de esta semana: "+getFirstDayCurrentWeek());
        System.out.println("Ultimo dia de la semana pasada: "+getLastDayPassWeek());
        System.out.println("Primer dia de la semana pasada: "+getFirstDayPassWeek());
        System.out.println("Ultimo dia de la semana pasada del 04-03-2011: "+getLastDayPassWeek(2011,03,04));
        System.out.println("Primer dia de la semana pasada del 04-03-2011: "+getFirstDayPassWeek(2011,03,04));
        System.out.println("Ultimo dia de la semana del 25-03-2011: "+getLastDayCurrentWeek(2011,03,25));
        System.out.println("Primer dia de la semana del 25-03-2011: "+getFirstDayCurrentWeek(2011,03,25));
        System.out.println("Primer dia anterior a 25-03-2011: "+getBeforeDay(2011,03,25));
        System.out.println("Primer dia posterior a 25-03-2011: "+getAfterDay(2011,03,25));
    }

    protected String FechaHora;
    protected String Fecha;
    protected String Hora;
}