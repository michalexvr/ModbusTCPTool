/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

/**
 *
 * @author michael
 */
public class fechaUtil {

    public static String getNombreMes(int indice){
        String meses[] = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
        if(indice >0 && indice <=12)
            return meses[indice-1];
        return null;
    }

    public static String getNumeroMes(String mes){
        String meses[] = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
        for(int i=0; i<meses.length; i++){
            if(meses[i].equals(mes)){
                int indice = i+1;
                if(indice<10) return "0"+indice;
                else return Integer.toString(indice);
            }
        }
        return null;
    }

    public static String invertir(String fecha){
        int i = fecha.indexOf("-");
        int l = fecha.lastIndexOf("-");

        if(i>0 && l>0 && i<l)
            return(fecha.substring(l+1)+"-"+fecha.substring(i+1,l)+"-"+fecha.substring(0,i));
        return null;
    }

    public static long getFechaToSegundos(String fecha){
        long ano = Long.parseLong(fecha.substring(0, fecha.indexOf("-")));
        int mes = Integer.parseInt(fecha.substring(fecha.indexOf("-")+1, fecha.lastIndexOf("-")));
        int dia = Integer.parseInt(fecha.substring(fecha.lastIndexOf("-")+1));

        System.out.println("anio: "+(ano*365*24*3600));
        return (dia*24*3600)+getMesToSegundos(mes)+(ano*365*24*3600);
    }

    public static long getMesToSegundos(int mes){
        int meses[] = {31,28,31,30,31,30,31,31,30,31,30,31};
        long sum = 0;
        mes = mes - 1;
        if(mes > 0){
            for(int i=mes-1; i>=0; i--) sum+=meses[i];
            }
        System.out.println("mes: "+sum*24*3600);
        return sum*24*3600;
    }

    public static String getMonthNext(String mes)
    {
        String nextMes;
        if(Integer.parseInt(getNumeroMes(getNombreMes(Integer.parseInt(mes))))<12)
        {
            nextMes=getNumeroMes(getNombreMes(Integer.parseInt(mes)+1));
        }
        else
        {
            nextMes=getNumeroMes("Enero");
        }
        return nextMes;
    }
    public static String getDayNext(String dia)
    {
        String nextDay = null;
         if(Integer.parseInt(dia)<getNumDayTheMonth(fechaHora.getMonthNow()))
         {
            nextDay= Integer.toString(Integer.parseInt(dia)+1);
         }
         else
         {
           nextDay=dia;
         }

        return nextDay;
    }
    public static int getNumDayTheMonth(String mes)
    {
        int meses[] = {31,28,31,30,31,30,31,31,30,31,30,31};

        return meses[Integer.parseInt(mes)-1];
    }

    public static void main(String argv[]){
        //System.out.println("2011-02-02: "+getFechaToSegundos("2011-02-02"));
        //System.out.println("2011-01-22: "+getFechaToSegundos("2011-01-22"));
        //System.out.println("Diferencia en segundos: "+(getFechaToSegundos("2011-02-02")-getFechaToSegundos("2011-01-22")));
        long a1=getFechaToSegundos("2011-03-29");
        long a2=a1+(20*3600)+(41*60)+36;
        a1 = a1+(20*3600)+(51*60)+49;
        System.out.println(a1-a2);
        /*
        Double d1=10.16;
        Double d2=10.15;
        
        Double res = ((a1*d1)-(a2*d2))/(a1-a2);

        System.out.println(a1*d1);
        System.out.println(a2*d2);
        System.out.println((a1*d1)-(a2*d2));        

        System.out.println(res);
        System.out.println(res.longValue());
         * */

         System.out.println(fechaUtil.getNombreMes(02));
         System.out.println(fechaUtil.getNumeroMes("Mayo"));


    }

}
