/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testModelo;
import BD.ConsultaBD;
import CapturaDatos.captura;
import TcpConversor.ClienteMedidor;
import Utils.CCITTcrc;
import Utils.fechaHora;
import Utils.logFile;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import jmodbus.ByteUtils;

/**
 *
 * @author Michael Venegas, DreamIT Software -> mvenegas@dreamit.cl
 */
public class testSocket {

    public static void main(String args[]){
        /*
        byte traza[] = new byte[2];
        traza[0] = (byte)(1 & 0xFF);
        traza[1] = (byte)(17 & 0xFF);
        */
        
        byte traza[] = new byte[6];
        traza[0] = (byte)(1 & 0xFF);
        traza[1] = (byte)(3 & 0xFF);
        traza[2] = (byte)((4096 >> 8) & 0xFF);
        traza[3] = (byte)((4096 >> 0) & 0xFF);
        traza[4] = (byte)((16 >> 8) & 0xFF);
        traza[5] = (byte)((16 >> 0) & 0xFF);
        //traza[CRC] = (byte)(0xFF);
        
        for(int i=0; i<traza.length; i++){
            System.out.println(jmodbus.ByteUtils.toHex(traza[i]));
        }
        
        CCITTcrc crc = new CCITTcrc();
        byte ceerreces[] = crc.calc(traza, traza.length);
        
        
        System.out.println(jmodbus.ByteUtils.toHex(ceerreces[0]));
        System.out.println(jmodbus.ByteUtils.toHex(ceerreces[1]));
        
        //byte[] Packet = {traza[0],traza[1],ceerreces[0],ceerreces[1]};
        byte[] Packet = {traza[0],traza[1],traza[2],traza[3],traza[4],traza[5],ceerreces[0],ceerreces[1]};
        

        String host="192.168.0.190";
        //String host="192.168.0.117";
        int port = 5000;
        
        
        
   //private String servidorChat;
        Socket cliente;
        String hostIpServer;
        
        try {
            /*
            
            String modifiedSentence;
            
            
            //BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
            Socket clientSocket = new Socket(host, port);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            clientSocket.setSoTimeout(1000);
            System.out.println("Buffer Size: "+clientSocket.getSendBufferSize());

            System.out.println("Lo que se envia: "+ByteUtils.toHex(Packet, Packet.length));
            
            outToServer.flush();
            //outToServer.write(Packet);
            outToServer.write(Packet, 0, 8);
            outToServer.flush();
            
            modifiedSentence = inFromServer.readLine();
            System.out.println("FROM SERVER: " + modifiedSentence);
            clientSocket.close();
            
            System.exit(0);
            */
            hostIpServer = host;
            
            //cliente = new Socket(InetAddress.getByName(hostIpServer),port);
            cliente = new Socket(hostIpServer,port);
            
            //cliente.setSoTimeout(1000);
            
            //salida = new ObjectOutputStream(cliente.getOutputStream());
            //DataOutputStream salida_ = new DataOutputStream(cliente.getOutputStream());
            //DataOutputStream salida_ = new DataOutputStream(cliente.getOutputStream());
            BufferedOutputStream salida_ = new BufferedOutputStream(cliente.getOutputStream());
            //ObjectOutputStream salida_ = new ObjectOutputStream(cliente.getOutputStream());
            //OutputStream salida_ = cliente.getOutputStream();

            //entrada= new ObjectInputStream(cliente.getInputStream());
            //OutputStream salida_ = cliente.getOutputStream();
            //BufferedReader receivedPacket= new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            
            System.out.println("enviando packete..");
            System.out.println("Lo que se envia: "+ByteUtils.toHex(Packet, Packet.length));
            
            
            salida_.flush();
            //salida_.write(Packet, 0, Packet.length);
            salida_.write(Packet);
            //salida_.write("/?30111056!\r\n".getBytes());
            salida_.flush();
            
            //salida_.write(Packet);
            //salida_.flush();
            InputStream receivedPacket= cliente.getInputStream();
            
            System.out.println("Esperando recibir algo..");
            
            byte elem;
            byte[] bytes = new byte[4];
            /*
            int bytesRcvd;
            
            int receivedBytesLength = 0;
            while(receivedBytesLength < Packet.length){
                if ((bytesRcvd = receivedPacket.read(Packet, receivedBytesLength, Packet.length - receivedBytesLength)) == -1){
                  System.out.println("Connection closed prematurely");
                  System.exit(2);
                }
                receivedBytesLength += bytesRcvd;
                elem = (byte) bytesRcvd;
                bytes[receivedBytesLength-1] = elem;
            }*/
            int i=0;
            //for(int i=0; i< 1; i++){
            while(true){
                System.out.println("Receiving someone");
                //elem = (byte) receivedPacket.read();
                elem = (byte) receivedPacket.read(bytes, 0, 1);
                //elem = (byte) receivedPacket.read(bytes, int off, int len)
                System.out.println("Someone Received");
                bytes[i] = elem;
                i++;
                System.out.println(ByteUtils.toHex(elem));
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
    }

}
