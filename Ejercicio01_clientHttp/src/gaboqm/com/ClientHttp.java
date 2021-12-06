package gaboqm.com;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientHttp {
	    public static void main(String[] args) {
	 
	    	try {
	    		if(args.length<2) {
	    			System.err.println("Argumentos inválidos");
	    			return;
	    		}
	            Scanner scanner = new Scanner(System.in);
	            scanner.useDelimiter("\n");
	            int port = Integer.parseInt(args[1]);
	            Socket socket = new Socket(args[0], port);
	            DataInputStream input = new DataInputStream(socket.getInputStream());
	            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
	            String mensaje = input.readUTF();
	            System.out.println(mensaje);
	            boolean salir = false;
	            String response;
	            String command = "";

	            while (!salir) {
	                try {
	                    System.out.printf("-> ");
	                    command = scanner.next();
	                    output.writeUTF(command);
	                    response = input.readUTF();
	                    System.out.println("<- "+response);
	                } catch (IOException ex) {
	                   System.err.println(ex.getMessage());
	                }
	            }
	        } catch (IOException ex) {
	        	 System.err.println(ex.getMessage());	        }

	    }
	
}
