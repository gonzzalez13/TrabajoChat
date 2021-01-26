import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Cliente {

	public static void main(String[] args) throws Exception {
		Socket socket = null;
		ServerSocket server = null;
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		
		try {
			InetSocketAddress direccion = new InetSocketAddress(InetAddress.getLocalHost(), 53203);
			socket = new Socket();
			socket.connect(direccion);
			//Escribe la peticion al servidor
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(new Conversacion("Gonzzalez13","Prueba", "192.168.0.15"));
			oos.flush();
			server = new ServerSocket(53203);
			while (true) {
				try {
					socket = server.accept();
					System.out.println("Conexion recibida!");
					ois = new ObjectInputStream(socket.getInputStream());
					Conversacion conversacion = (Conversacion) ois.readObject();
					/* imprimir mensaje */
					System.out.println(conversacion.getNick() +": "+ conversacion.getMensaje());
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			}
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
