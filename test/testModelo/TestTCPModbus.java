/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testModelo;
import jmodbus.ModbusRTUOverTCPMaster;
import jmodbus.ModbusTCPMaster;

/**
 *
 * @author Michael Venegas, DreamIT Software -> mvenegas@dreamit.cl
 */
public class TestTCPModbus {
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


//import ProcesoStart.StartProceso;
//import TcpConversor.ClienteMedidor;
//import Utils.CCITTcrc;
//import Utils.archivo;
//import Utils.hex;
//import jmodbus.ModbusMaster;
//import jmodbus.ModbusMessage;



/**
 *
 * @author xion
 */

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        String ha = "0001";
        String hb = "0cc9";
        
        String energia = ha+hb;
        Long energLong = Long.parseLong(energia, 16);
        Double energDouble = (double) energLong/10;
        System.out.println("energia Hex: "+energia+", valor Long: "+energLong+", valor real: "+energDouble);
        /*
        System.out.println(Long.highestOneBit(a));
        System.out.println(Long.lowestOneBit(a));
        System.out.println(Long.toBinaryString(a));
        System.out.println(Long.bitCount(a));
        System.out.println(Long.reverse(a));
        System.out.println(Long.reverseBytes(a));
        System.out.println(Integer.reverseBytes(8435));
        System.out.println(Integer.reverseBytes(8435));
        
        /*
        System.out.println(Float.floatToIntBits(a));
        System.out.println(Float.intBitsToFloat(a));
        */
        
        System.exit(0);
        
        //device->{address: int, dataPoints->{address: int, lenght: int}}
        //modbusRTU.getFunctionToExecute(function: int,Device: device){ return: String }
        //modbusRTU->{comienzoTrama: int, address: int, function: int, lenght: int, data: int[], crc: int}
        //SocketTCP::send(String message);        
 
/*        
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
        

        
        System.exit(0);
  */      
        

//	ModbusRTUOverTCPMaster modbus;
	ModbusTCPMaster modbus;
        
        //ModbusMaster modbus;
	String host;
	String function;
	int port;
	int offset;
	int length;
	int[] results;
	boolean retval;
	int[] values;
        
        String argv[] = {"read_output","192.168.0.190","5000","4096","16"};
        
        args = argv;

	if (args.length != 5) {
	    System.out.println("usage: java Master function host port offset length");
	    System.out.println("function: read_output | read_input | write_output");
	    return;
	}

	function = args[0];
	host = args[1];
	port = Integer.parseInt(args[2]);
	offset = Integer.parseInt(args[3]);
	length = Integer.parseInt(args[4]);

	results = new int[length];

	if (!function.equals("read_output") 
	    && !function.equals("read_input") 
	    && !function.equals("write_output")) {
	    System.out.println("Invalid function!");
	    return;
	}

	try {
	    modbus = new ModbusTCPMaster(host, port);
	}
	catch (Exception ex) {
	    System.out.println(ex.getMessage());
	    ex.printStackTrace();
	    return;
	}
        
        

	// setup the values to write
	values = new int[100];
	for (int i=0; i<100; i++) {
	    values[i] = i;
	}

	try {
	    System.out.println("About to send request....");

	    if (function.equals("read_output")) {
		retval = modbus.readMultipleRegisters(1,offset,length,0,results);
	    }
            
	    else if (function.equals("read_input")) {
		retval = modbus.readInputRegisters(0,offset,length,0,results);
	    }

	    else {
		retval = modbus.writeMultipleRegisters(0,offset,length,0,values);
	    }

	    System.out.println("Send function returned");
	    if (retval) {
		System.out.println("Transaction sucedded");

		if (function.equals("read_output")
		    || function.equals("read_input")) {
		    for (int i=0; i<length; i++) {
			System.out.println(Integer.toHexString(results[i]));
		    }
		}

	    }
	    else {
		System.out.println("Transaction failed");
	    }
	}
	catch (Exception ex) {
	    System.out.println(ex.getMessage());
	    ex.printStackTrace();
	}        
        
        
        /*
        System.out.println(Integer.toHexString(4544));
        
        System.out.println(Integer.parseInt("11C0", 16));
        
        
        System.out.println(Integer.decode("#1f7"));
        System.out.println(Integer.valueOf("01f7", 16));
        
        System.out.println(Integer.toString(503, 16));
        System.out.println(hex.convertHexToString(Integer.toString(503, 16)));
        System.exit(20);*/
        //System.out.println(hex.convertHexToString("F0"));        
        

         // TODO code application logic here
        //ClienteMedidor prueba= new ClienteMedidor("192.168.0.194"); //UTF8 //Conver Milenium: 192.168.0.160
        //prueba.SendData("30109868");//30109868// milenium 000001//30111104 //30111089 //22837328 //8192 //java.io.BufferedReader@1ded0fd
        /*
        if(args.length > 2 && args[0].equals("--debug")){
            System.out.println("debug");
            String nSerie = args[1];
            int ipdir = Integer.parseInt(args[2]);
            
            String ip = "192.168.0.194";
            
            if(ipdir == 2) ip = "192.168.0.193";
                
            ClienteMedidor prueba= new ClienteMedidor(ip);
            
            
            archivo arch = new archivo(Utils.commonUtils.getPath()+"dataGetted.txt");
            arch.escribir(prueba.getDataStream(nSerie));
        }
        else {
            if(args.length > 0){
                System.out.println("Usage: medidores.jar --debug [medidor] [vertical]\nExample: medidores.jar --debug 10011101 2");
                System.exit(0);
            }else{
                StartProceso Star= new StartProceso();
                Star.run();
            }
        }
        /*
         * Cambiar la clase conexion cuando se traspase al servidor!!!!!!!!!!! TENER EN CUENTA ESTO
         */
      
       
    }

}
