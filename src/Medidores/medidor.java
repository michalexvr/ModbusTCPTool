/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Medidores;

/**
 *
 * @author Michael Venegas, DreamIT Software -> mvenegas@dreamit.cl
 */
public class medidor {
    
    protected String nSerie;
    protected String ipFuente;
    protected String modelo;
    protected String puerto;
    
    public medidor(String nserie, String ipfuente, String modelo, String puerto){
        nSerie = nserie;
        ipFuente = ipfuente;
        this.modelo = modelo;
        this.puerto = puerto;
    }
    
    public void setNSerie(String data){
        nSerie = data;
    }
    
    public void setIPFuente(String data){
        ipFuente = data;
    }

    public void setModelo(String data){
        modelo = data;
    }
    
    public void setPuerto(String data){
        puerto = data;
    }    
    
    public String getNSerie(){
        return nSerie;
    }
    
    public String getIPFuente(){
        return ipFuente;
    }

    public String getModelo(){
        return modelo;
    }

    public String getPuerto(){
        return puerto;
    }

}
