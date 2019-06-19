/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MedidoresModbusTCP;

import Utils.commonUtils;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import jmodbus.ModbusTCPMaster;


/**
 *
 * @author Michael Venegas -> mvenegas@dreamit.cl using library jmodbus -> http://jmodbus.sourceforge.net/
 */
public class Main {

    /**
     * @param args the command line arguments
     */
	public static void main(String args[]) throws Exception
	{
            Main main = new Main();
        }
        
        public Main(){
            EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Modbus TCP Tool V1.0");
		frame.setLayout(new GridLayout(0, 2));
		JPanel panel = new JPanel(new GridLayout(0, 2));

		JLabel ipLabel = new JLabel("Direccion IP: ");
		final JTextField ipField = new JTextField(15);

		JLabel puertoLabel = new JLabel("Puerto: ");
		final JTextField puertoField = new JTextField(4);

		JLabel commandLabel = new JLabel("Comando: ");
		final JComboBox command = new JComboBox(new String[]
			{
			"Leer Input Registers", "Leer Multiple Registers"
			});

		JLabel idDeviceLabel = new JLabel("ID Dispositivo: ");
		final JTextField idDeviceField = new JTextField(8);

		JLabel idDataPointLabel = new JLabel("Direccion de punto: ");
		final JTextField idDataPointField = new JTextField(8);

		JLabel idDataLengthLabel = new JLabel("Cantidad de caraceres: ");
		final JTextField idDataLengthField = new JTextField(4);

		JButton boton = new JButton("Conectar");

        	//idDataLengthField.setPreferredSize( new Dimension( 60, 24 ) );
		//javax.swing.JOptionPane.showMessageDialog(null, "asdf");
                
                
		panel.add(ipLabel);
		panel.add(ipField);

		panel.add(puertoLabel);
		panel.add(puertoField);

		panel.add(commandLabel);
		panel.add(command);

		panel.add(idDeviceLabel);
		panel.add(idDeviceField);
		panel.add(idDataPointLabel);
		panel.add(idDataPointField);
		panel.add(idDataLengthLabel);
		panel.add(idDataLengthField);

		JPanel panel2 = new JPanel();
		final JTextArea salida = new JTextArea(37, 30);
		salida.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(salida);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		//panel2.add(salida);
		panel2.add(scrollPane);
		panel.add(new JLabel(""));
		panel.add(boton);
		JPanel subpanel = new JPanel();
		panel.add(new JPanel());
		panel.add(new JPanel());
		panel.add(new JPanel());
		panel.add(new JPanel());
		panel.add(new JPanel());
		panel.add(new JPanel());
		panel.add(new JPanel());
		frame.add(panel);
		frame.add(panel2);
                
                PrintStream ps = System.out;
                System.setOut(new PrintStream(new StreamCapturer(salida, ps)));

		boton.addActionListener(new ActionListener()
			{

			public void actionPerformed(ActionEvent e)
				{
					ModbusTCPMaster modbus = null;
					String host = ipField.getText();
					int port = 0;
					int idDev = 0;
					int idPoint = 0;
					int length = 0;
					
                                        if(!commonUtils.isIP(host)){
                                            javax.swing.JOptionPane.showMessageDialog(null, "Dirección Debe ser una IP válida");
                                            host = null;
                                        }
                                        
					try{
						port = Integer.parseInt(puertoField.getText());
					}catch(Exception ex)
					{ javax.swing.JOptionPane.showMessageDialog(null, "Puerto debe ser un número!"); }
					try{
						idDev = Integer.parseInt(idDeviceField.getText());
					}catch(Exception ex)
					{ javax.swing.JOptionPane.showMessageDialog(null, "ID dispositivo debe ser un número!"); }
					
					try{
						idPoint = Integer.parseInt(idDataPointField.getText());
					}catch(Exception ex)
					{ javax.swing.JOptionPane.showMessageDialog(null, "Direccion de punto debe ser un número!"); }
					
					try{
						length = Integer.parseInt(idDataLengthField.getText());
						
					}catch(Exception ex)
					{ javax.swing.JOptionPane.showMessageDialog(null, "Largo debe ser un número!"); }
					 
					
					//javax.swing.JOptionPane.showMessageDialog(null, ipField.getText());
					
					//mount Modbus Client
					if(port >0 && idDev>0 && idPoint >0 && length>0 && host!= null){
						salida.append("conectando a "+host+"... . .");
						try {
							//modbus = new ModbusRTUOverTCPMaster(host, port);
							modbus = new ModbusTCPMaster(host, port);
							modbus.setTimeout(3000);

							int data[] = new int[length];
							
							//segun lo seleccionado ejecutamos el comando
							if(command.getSelectedIndex()==0){
								if(modbus.readInputRegisters(idDev, idPoint, length, 0, data)){
									String dataConcat = "";
									for(int k=0;k<length; k++){
										salida.append(k+" -> "+data[k]);
										salida.append(k+" Hex -> "+Integer.toHexString(data[k]));
										dataConcat+=data[k];
									}
									Long SalidaLong = Long.parseLong(dataConcat, 16);
									Double SalidaDouble = (double) SalidaLong/10;
									salida.append(("Valor Hex: "+dataConcat+", valor Long: "+SalidaLong+", valor real: "+SalidaDouble));
								}
							}else{
								if(modbus.readMultipleRegisters(idDev, idPoint, length, 0, data)){
									String dataConcat = "";
									for(int k=0;k<length; k++){
										salida.append(k+" -> "+data[k]);
										salida.append(k+" Hex -> "+Integer.toHexString(data[k]));
										dataConcat+=data[k];
									}
									Long SalidaLong = Long.parseLong(dataConcat, 16);
									Double SalidaDouble = (double) SalidaLong/10;
									salida.append(("Valor Hex: "+dataConcat+", valor Long: "+SalidaLong+", valor real: "+SalidaDouble));
								}							
							}
						}
						catch (Exception ex) {
							salida.append(ex.toString());
						}

					}
					
				}

			});

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JFrame.setDefaultLookAndFeelDecorated(true);
		frame.pack();
		frame.setBounds(10, 10, 800, 600);
		frame.setLocation(400, 200);
		frame.setVisible(true);                
            }            
        });
        }
        
        
    public class StreamCapturer extends OutputStream {

        private StringBuilder buffer;
        private JTextArea consumer;
        private PrintStream old;

        public StreamCapturer(JTextArea consumer, PrintStream old) {
            
            buffer = new StringBuilder(128);
            this.old = old;
            this.consumer = consumer;
        }

        @Override
        public void write(int b) throws IOException {
            char c = (char) b;
            String value = Character.toString(c);
            buffer.append(value);
            if (value.equals("\n")) {
                consumer.append(buffer.toString());
                buffer.delete(0, buffer.length());
            }
            old.print(c);
        }
    }
}