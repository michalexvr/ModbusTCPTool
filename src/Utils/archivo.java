/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

/**
 *
 * @author michael venegas Destin Chile Ltda.
 *
 */
import java.io.*;
import java.util.StringTokenizer;
//import java.util.*;

public class archivo {
    private File arch;
    private String contenido;
    protected String rutal;
    private FileWriter fw;
    private BufferedWriter bw;
    private StringTokenizer divisor;
    private FileReader lector;
    private BufferedReader buffer_lector;
    private PrintWriter salArch;


    /**
     * @param args
     */
    public archivo(String ruta){
        this.rutal = ruta;
        arch = new File(rutal);


        if (! this.existe() )
            crearArchivo();
    }

    public boolean existe(){
        return ( arch.exists() );
    }


   public void prepararEscritura(){
       try{
           fw = new FileWriter (arch);
           bw = new BufferedWriter (fw);
           salArch = new PrintWriter (bw);
       }catch(IOException e){
           System.err.println("Excepcion preparando escritura: "+e);
       }
   }

   public void addLine(String linea){
           salArch.println(linea);
   }

   public void cerrarEscritura(){
       try{
           salArch.close();
           bw.close();
           fw.close();
       }catch(IOException e){
           System.err.println("Excepcion cerrando escritura: "+e);
       }
   }

    private void crearArchivo(){
        try {
              // A partir del objeto File creamos el fichero físicamente
              arch.createNewFile();
                //System.out.println("El fichero se ha creado correctamente");

                //System.out.println("No ha podido ser creado el fichero");
            } catch (IOException ioe) {
              ioe.printStackTrace();
            }
    }

    public void borrarArchivo(){
        if (!arch.delete())
            arch.deleteOnExit();
    }

    //devuelvo el contenido en un string gigante
    public String retContenido(){
        try{
            lector = new FileReader(this.arch);
            buffer_lector = new BufferedReader(lector);

            String ctemp;
            contenido ="";

            while((ctemp = buffer_lector.readLine()) != null)
                contenido += ctemp+"\r\n";
            lector.close();
            return contenido;
        }catch (IOException e){System.exit(0);}
        return null;
    }

    //devuelvo el contenido en un arreglo de strings
    public String[] LeerLineas(){

        String[] lineas = new String[ NumeroLineas() ];
        int c = 0;
        try{

            divisor = new StringTokenizer ( retContenido(), "\r\n" );

            lineas[c] = divisor.nextToken();

            while ( divisor.hasMoreTokens() ){
                c ++;
                lineas[c] = divisor.nextToken();
            }

            return lineas;

          } catch ( Exception ex ){
              //System.out.println("fallo en lectura de lineas "+ex);
          }

         return null;

    }

    //cuenta las lineas del archivo
    public int NumeroLineas(){

        try{
           /* Creamos los lectores. */
           lector = new FileReader( this.arch );
           buffer_lector = new BufferedReader( lector );
           int c = 0;

           //Contamos.
           while ( buffer_lector.readLine() != null)
              c ++;



           return c;

         } catch ( IOException ex ){
               System.out.println("fallo en lectura de lineas");
         }

        return 0;

   }

   //volca el string gigante sobre el archivo
   public void escribir(String dato) {
        try{
        fw = new FileWriter (arch);
        bw = new BufferedWriter (fw);
        salArch = new PrintWriter (bw);
        salArch.println(dato);
        salArch.close ();
        }catch (IOException e){System.out.println("error de lectura de archivo"); System.exit(1);}

   }

   //retorna las ultimas lineas, seria como un tail
    public String retLastContenido()
    {
        String lineas[] = LeerLineas();
        String content = "";
        int largoArchivo = 0;
        if(lineas != null) largoArchivo = lineas.length;
        int limite = largoArchivo - 100;
        if(limite < 0) limite = 0;
        for(int i = limite; i < largoArchivo; i++)
            content = (new StringBuilder()).append(content).append(lineas[i]).append("\r\n").toString();

        return content;
    }

    public String retLastContenido(int L)
    {
        String lineas[] = LeerLineas();
        String content = "";
        int largo = 0;
        if(lineas != null) largo = lineas.length;
        int limite = largo - L;
        if(limite < 0) limite = 0;
        for(int i = limite; i < largo ; i++){
            content = (content+lineas[i]+"\r\n");
        }
        return content;
    }

   //escribe una linea
   public boolean escribirLinea( String cadena ){
       try{

           /*Creamos las variables.*/
           String ctemp = retLastContenido(1000) + cadena;
           fw = new FileWriter ( arch );
           bw = new BufferedWriter ( fw );
           salArch = new PrintWriter( bw );

           /*Escribimos al archivo */
           salArch.println( ctemp );

           salArch.close();

           return true;

       } catch ( IOException ex ){
           System.out.println("error escribiendo en archivo: "+ex);
       }

       return false;

   }

   public void escribirEnLinea(String dato) {
        try{
        fw = new FileWriter (arch);
        bw = new BufferedWriter (fw);
        salArch = new PrintWriter (bw);
        salArch.print(dato);
        salArch.close ();
        }catch (IOException e){System.out.println("error de lectura de archivo"); System.exit(1);}

   }

   public File getFile(){
	   return arch;
   }

   public void deleteOnExit(){
           arch.deleteOnExit();
   }

   public void delete(){
           arch.delete();
   }
}