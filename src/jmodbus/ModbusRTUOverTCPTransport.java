/* 
 * The jModbus project is distrubuted under the following license terms
 * 
 * Copyright (c) 2001 by The Java Modbus Project
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions 
 * are met:
 * 
 *  1.  Redistributions of source code must retain the above copyright 
 *      notice, this list of conditions and the following disclaimer. 
 *  2.  Redistributions in binary form must reproduce the above copyright 
 *      notice, this list of conditions and the following disclaimer in 
 *      the documentation and/or other materials provided with the 
 *      distribution. 
 *  3.  Neither the name of the The Java Modbus Project nor the names of 
 *      its contributors may be used to endorse or promote products 
 *      derived from this software without specific prior written 
 *      permission. 
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR 
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE REGENTS OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY 
 * OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


package jmodbus;
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.InetAddress;

/**
 * Class to implement a TCP transport mechanisim for Modbus RTUcommunication.  
 * This will allow a decice to communicate via ModbusRTU over TCP/IP Protocol
 * @author Kelvin Proctor -> Extended, Tuned and Fixed by Michael Venegas
 */
public class ModbusRTUOverTCPTransport implements ModbusTransport {
    /**
     * The TCP port number that Modbus TCP services should operate
     * over.
     */	
    /*
    public static double FRAME_SEPERATOR_LENGTH = 3.5;
    public static int MAX_RTU_MESSAGE_LENGTH = Modbus.MAX_MESSAGE_LENGTH + 1 ;
    private int receieveTimeout;
    private int frameBreakTime;
    //private OutputStream out;
    //private InputStream in;
    */
    
    /**
     * The length (in bytes) of the header of a Modbus TCP packet.
     */
    public static final int HEADER_LENGTH          = 6;
    
    /**
     * The maximum number of bytes in the body of a Modbus TCP packet.
     */
    public static final int DATA_MAX               = 255;             
    
    /**
     * The maximum length (in bytes) of a Modbus TCP packet.
     */	
    public static final int MAX_TRANSACTION_LENGTH = HEADER_LENGTH + DATA_MAX;
    
    /**
     * The protocol identifier for Modbus TCP.  This value is used
     * to confirm that a message is acually a modbus TCP message and
     * not some other data.
     */	
    public static final short PROTOCOL_IDENTIFIER  = (short) 0x0000;
    
    // Socket that the transport will use to communicate via.  This
    // class is passed a socket to allow a slave implementation to
    // easily use ServerSockets to implement a multi threaded server.
    private Socket socket;
    
    // BufferedInputStream used for communication via the socket.
    //private BufferedInputStream in;
    private ObjectInputStream in;
    
    // OutputStream used for communication via the socket.
    private ObjectOutputStream out;
    
    // Small byte arrady for reading the ehader into each time
    //private byte[] receive_header = new byte[HEADER_LENGTH];	
    //private byte[] send_header = new byte[HEADER_LENGTH];
    
    // Counter for reading / writing / parsing byte buffers
    private int count = 0;
    private int recv = 0;
    
    // Protocol identifiers 
    private short protocol_identifier;
    
    // Flag to indicate if the header has been validated
    private boolean header_check = false;
    
    // Request and reply length variables
    private int request_body_length;
    private int reply_length;
    
    /**
     * Constructor for the ModbusTCPTransport.  The class requires
     * a socket to communicate over.  This constructor passes a reference
     * to this socket
     */	
    public ModbusRTUOverTCPTransport(Socket socket) {
        
	this.socket = socket;
	// Setup the inoput and output streams
	try {
	    out = new ObjectOutputStream(socket.getOutputStream());
	    in = new ObjectInputStream(socket.getInputStream());
	}
	catch (IOException ex) {
	    // Print Message if in debug mode
	    if (Modbus.debug >= 1) {
		System.out.println(ex.getMessage());
		ex.printStackTrace();
	    }
	    return;
	}
	
	// Print Message if in debug mode
	if (Modbus.debug >= 1) {
	    System.out.println("ModbusTCPTransport: constructor complete");
	}
    }
    
    /**
     * Constructor for the ModbusTCPTransport.  The class requires
     * a socket to communicate over.  This constructor will create a
     * new socket on the specified port to the specified host. 
     */	
    public ModbusRTUOverTCPTransport(String host, int port) {
	
	// Setup the socket and input and output streams
	try {
	    //socket = new Socket(host, port);
            socket = new Socket(InetAddress.getByName(host),port);
	    out = new ObjectOutputStream(socket.getOutputStream());
	    //in = new ObjectInputStream(socket.getInputStream());
	}
	catch (IOException ex) {
	    // Print Message if in debug mode
	    if (Modbus.debug >= 1) {
		System.out.println(ex.getMessage());
		ex.printStackTrace();
	    }
	    return;
	}
	
	// Print Message if in debug mode
	if (Modbus.debug >= 1) {
	    System.out.println("ModbusTCPTransport: constructor complete");
	}
    }
    
    /**
     * Method to send a Modbus frame via the transport media.  The return 
     * status of the function indicates if the transmission sucedded.
     *
     * @author Kelvin Proctor
     *
     * @param msg The Modbus Message to be sent.
     * @return    Transmission sucess flag, to indicate if the transmission
     *            was sucessful.
     */
    public boolean sendFrame(ModbusMessage msg) {
	
        
	// Print Message if in debug mode
	if (Modbus.debug >= 3) {
	    System.out.println("ModbusTCPTransport: Sending Frame.....");
	}
	
	try {
            
            // First copy the buffer into aux packet
            byte[] fullPacket = new byte[msg.length+2];
            System.arraycopy(msg.buff, 0, fullPacket, 0, msg.length);
            
            
            // Then generate the CRC footer
            Utils.CCITTcrc crc_ = new Utils.CCITTcrc();
            byte[] crc = crc_.calc(msg.buff, msg.length);
            
            // Then add the CRC into aux packet
            fullPacket[msg.length] = crc[0];
            fullPacket[msg.length+1] = crc[1];
            
            if (Modbus.debug >= 4) {
                System.out.println("Message: -> "+ByteUtils.toHex(fullPacket,fullPacket.length));
            }
            //out.write(fullHeader,0,HEADER_LENGTH+2);
            out.flush();
	    out.write(msg.buff,0,msg.length);
	    out.flush();
	}
	catch (IOException ex) {
	    if (Modbus.debug >= 3) {
		System.out.println(ex.getMessage());
		ex.printStackTrace();
	    }
	    return false;
	}
	
	// Print Message if in debug mode
	if (Modbus.debug >= 3) {
	    System.out.println("ModbusTCPTransport: Frame sent");
	}				
	return true;
    }
    
    /**
     * Method to receive a Modbus frame via the transport media.  The return 
     * value indicates the length of the frame.  This method will block until
     * the comminication path is terminated or a frame is sucessfully received.
     *
     * @author Kelvin Proctor
     *
     * @param msg The Modbus Message object for received data to be written into
     * @return    Receive sucess flag, to indicate if the receive was sucessful.
     */
    public boolean receiveFrame(ModbusMessage msg) {
	// Print Message if in debug mode
	if (Modbus.debug >= 3) {
	    System.out.println("ModbusTCPTransport: Receiveing Frame.....");
	}
	try{
            in = new ObjectInputStream(socket.getInputStream());
        }catch(Exception e){
            e.printStackTrace();
        }
        /*
        
	    try {
                byte[] b = new byte[10];
		recv = in.read(b, 1, 2);//in.read();//in.read(receive_header,count,HEADER_LENGTH - count);
	    }
	    catch (IOException ex) {
		if (Modbus.debug >= 3) {
		    System.out.println(ex.getMessage());
		    ex.printStackTrace();
		}
		return false;
	    }
	    if (recv == -1) {
		// Print Message if in debug mode
		if (Modbus.debug >= 2) {
		    System.out.println("ModbusTCPTransport: Stream Closed, receive returning -1");
		}
		return false;
	    }
	
        
        */
	// In Modbus TCP the transaction identifier is ignored
	// and blindly copied from query to response, so we should
	// copy that value to the integet passed to us for the
	// transaction ID now
        
        System.out.println("largo->"+msg.length);
        System.out.println(ByteUtils.toHex(msg.buff, msg.length));
        //System.exit(25);
        
        
	msg.transID = 0;
	
	// Now get the rest of the message
	count = 0;
        //System.out.println("count: "+count+" request_body_length: "+request_body_length); //request_body_length
	while (count < request_body_length) {
            
	    try {
		recv = in.read(msg.buff,
			       count,
			       request_body_length - count);
	    }
	    catch (IOException ex) {
				// Print Message if in debug mode
		if (Modbus.debug >= 3) {
		    System.out.println(ex.getMessage());
		    ex.printStackTrace();
		}
		return false;
	    }
	    if (recv == -1) {
		// Print Message if in debug mode
		if (Modbus.debug >= 2) {
		    System.out.println("ModbusTCPTransport: Stream Closed, receive returning -1");
		}
		return false;
	    }
	    count += recv;
	}
	
	// Set the length field of the ModbusMessage
	msg.length = request_body_length;
		
	// Print Message if in debug mode
	if (Modbus.debug >= 3) {
	    System.out.println("ModbusTCPTransport: Frame receieved");
	}				
	return true;
    }

    public void setTimeout(int tm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}
