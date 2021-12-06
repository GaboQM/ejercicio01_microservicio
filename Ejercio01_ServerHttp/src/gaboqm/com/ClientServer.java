package gaboqm.com;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;
import gaboqm.com.util.UtilServer;


public class ClientServer extends Thread {
	private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private final String clientName;
	private final static Logger LOG=Logger.getLogger(StarServerHttp.class.getName());    
	public ClientServer(Socket socket, DataInputStream input, DataOutputStream output, String clientName) {
		super();
		this.socket = socket;
		this.input = input;
		this.output = output;
		this.clientName = clientName;
	}
	
	
	  @Override
	    public void run() {
		  String command;
	        boolean isDisconnect = false;
	        
	        while (!isDisconnect) {
	        	 try {
	             	command = input.readUTF();
	             	switch (command=command.toUpperCase()) {
					case  "ADD": {
						
						UtilServer.getElements().add(UtilServer.randomElement());
						output.writeUTF("OK");
						break;
					}
					case "REMOVE": {
						UtilServer.removeElement();
						output.writeUTF("OK");
						break;
					}
					case "DES": {
						isDisconnect=true;
						output.writeUTF("DESCONECTADO");
						break;
					}
					default:
						output.writeUTF("NO OK");
					}
	             	
	               LOG.info("CLIENT-> "+clientName+" COMMAND-> "+command);
	               UtilServer.printElements();
	             } catch (IOException ex) {
	            	 LOG.warning(ex.getMessage());
	             }
				
			}
	        try {
	            socket.close();
	        } catch (IOException ex) {
	        	LOG.warning(ex.getMessage());
	        }
	        LOG.info("Conexion cerrada con el cliente "+clientName);		  
	  }
    
}
