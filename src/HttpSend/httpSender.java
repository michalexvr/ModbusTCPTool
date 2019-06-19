/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HttpSend;

import Utils.logDB;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Michael Venegas, DreamIT Software -> mvenegas@dreamit.cl
 */
public class httpSender {
    
    public static void sendData(String medidor, String lectura, String demanda, String demandaP, String Url){
                
        String link = Url;
        String urlParameters = "med="+medidor+"&kwh="+lectura+"&dm="+demanda+"&dmhp="+demandaP;
        
        String inputLine;
        String received = "";
        
        try{
            URL url = new URL(link);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setDoOutput(true);
            conexion.setDoInput(true);
            conexion.setInstanceFollowRedirects(false);
            conexion.setRequestMethod("POST");
            conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conexion.setRequestProperty("charset", "utf-8");
            conexion.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
            conexion.setUseCaches (false);
            
            //conexion.setReadTimeout(timeout);
            
            DataOutputStream wr = new DataOutputStream(conexion.getOutputStream ());
            wr.writeBytes(urlParameters);
            wr.flush();
            
            BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            
            while((inputLine = in.readLine())!=null){
                received += inputLine;
            }
            
            wr.close();
            conexion.disconnect();
        }catch(MalformedURLException e){
            Utils.ExceptionHandler.handleException(e);
        }catch(IOException e){
            Utils.ExceptionHandler.handleException(e);
        }

        //System.out.println(received);
        if(!received.equals("OK"))
            logDB.addReg(received, "error", "CnergSender");

    }
    
    public static void sendData(String medidor, String lectura, String demanda, String demandaP){
                
        String link = "http://190.151.85.21/medidorescostaneralyon/test.php";
        
        String urlParameters = "med="+medidor+"&kwh="+lectura+"&dm="+demanda+"&dmhp="+demandaP;
        
        String inputLine;
        String received = "";
        
        try{
            URL url = new URL(link);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setDoOutput(true);
            conexion.setDoInput(true);
            conexion.setInstanceFollowRedirects(false);
            conexion.setRequestMethod("POST");
            conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conexion.setRequestProperty("charset", "utf-8");
            conexion.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
            conexion.setUseCaches (false);
            
            //conexion.setReadTimeout(timeout);
            
            DataOutputStream wr = new DataOutputStream(conexion.getOutputStream ());
            wr.writeBytes(urlParameters);
            wr.flush();
            
            //BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            
            while((inputLine = in.readLine())!=null){
                received += inputLine;
            }
            
            wr.close();
            conexion.disconnect();
        }catch(MalformedURLException e){
            Utils.ExceptionHandler.handleException(e);
        }catch(IOException e){
            Utils.ExceptionHandler.handleException(e);
        }

        //System.out.println(received);
        if(!received.equals("OK"))
            logDB.addReg(received, "error", "CnergSender");
    }
    
    
    
    public static void main(String[] asrs){
        sendData("medidor","lectura","demanda","demandaP");
        //sendData(medidor,lectura, demanda, demandaPunta)
    }

}
