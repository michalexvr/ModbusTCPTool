package Utils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
public class seguridad {
    public static String eliminaInyecciones(String valor){
        if(valor != null){
            valor = valor.replace("\'", "");
            valor = valor.replace("\"", "");
            valor = valor.replace("\\", "");
            valor = valor.replace(";", "");
            valor = valor.trim();
        }
        return valor;
    }
    
}
