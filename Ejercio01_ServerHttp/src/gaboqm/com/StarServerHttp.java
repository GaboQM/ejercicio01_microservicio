package gaboqm.com;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StarServerHttp {
	private final static Logger LOG=Logger.getLogger(StarServerHttp.class.getName());
	private static int PORT=8080;
	private static ServerSocket server;
	
	private static void start() {
		try {
            server = new ServerSocket(PORT);
            Socket socket;
            LOG.info("SERVER INICIADO EN EL PUERTO: "+PORT);
            while(true){
                // Espero la conexion del cliente
            	socket = server.accept();
                String clientName=socket.getInetAddress()+":"+socket.getPort();
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                output.writeUTF("Conectado...\nEnvía  add (añadir) remove (eliminar) des (desconetar)");
                ClientServer ClientServerThread  = new ClientServer(socket, input, output,clientName);
                ClientServerThread.start();
                LOG.info("Conexión establecida con el cliente "+clientName);
            }
        } catch (IOException ex) {
        	LOG.log(Level.SEVERE, null, ex);
        }
	}
	public static void main(String[] args) {
		 StarServerHttp.start();
	}

}
