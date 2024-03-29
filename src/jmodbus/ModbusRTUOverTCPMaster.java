/*
 * ModbusRTUOverTCPMaster.java
 */

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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmodbus;

import java.net.Socket;

/**
 * Class to implement a Modbus RTU Master device over TCP interface.  This class only 
 * defines what type of transport if to be used, all the work in 
 * generating and sending requests is performed by the methods in the 
 * ModbusMaster class.
 * @author Kelvin Proctor -> Extended, Tuned and Fixed by Michael Venegas
 */

public class ModbusRTUOverTCPMaster extends ModbusMaster{
    /*
    static {
	rtuTransport = new ModbusRTUOverTCPTransport();
    }
    */
    /**
     * Constructor that uses the RTU transport created with 
     * default parameters.  
     */
   public ModbusRTUOverTCPMaster(String host, int port) {
	super(new ModbusRTUOverTCPTransport(host, port));
    }
   
   public ModbusRTUOverTCPMaster(Socket s) {
	super(new ModbusRTUOverTCPTransport(s));
    }
   
    
}
