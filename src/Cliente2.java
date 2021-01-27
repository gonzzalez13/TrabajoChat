import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.jndi.cosnaming.IiopUrl.Address;

public class Cliente2 {
	
	public static void main(String[] args) throws Exception {
		Socket socket = null;
		ObjectOutputStream oos = null;
		String Nick = "Gonzzalez13";
		
		try {
			InetSocketAddress direccion = new InetSocketAddress(InetAddress.getLocalHost(), 53203);
			socket = new Socket();
			socket.connect(direccion);
			//Escribe el Nick y la ip al servidor
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(new Conversacion(Nick,"Prueba","Gonzzalez13"));
			oos.flush();
			Pregunta p = new Pregunta(socket,Nick);
			p.start();
		}
		catch(IOException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			if (null != oos) {
				oos.close();
			}
			if (null != socket) {
				socket.close();
			}
		}
	}	

}
