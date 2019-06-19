/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

/**
 *
 * @author michael
 */
public class ExceptionHandler {
    public static void handleException(Exception e){
            String traza = "";
            int errno = 0;
            String error = e.toString();
            int indiceTraza = error.indexOf(":");
            if(indiceTraza > 0)
                    error = error.substring(0,indiceTraza).replace(".", " ");
            String resumen = e.getLocalizedMessage();
            StackTraceElement el[] = e.getStackTrace();

            for(int i=0; i<el.length; i++){
                traza += el[i].getFileName()+"\n "+el[i].getLineNumber()+"\n\n";
            }

            logDB.addRegError(errno, error, resumen, traza, 0, "Runtime");
    }
}
