import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;



public class Mensaje extends Thread {
	
	
	Socket socket;
	Socket socketenvio;
	List<Conversacion> conversacion;

	public Mensaje(Socket socket,List<Conversacion> conversacion) {
		super();
		this.socket = socket;
		this.conversacion = conversacion;
	}

	
	private void escuchar() throws ClassNotFoundException{
		InputStream is = null;
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;

		try {
			System.out.println("Conexion recibida!");
			is = socket.getInputStream();
			ois = new ObjectInputStream(is);
			Conversacion mensaje = (Conversacion) ois.readObject();
			conversacion = new ArrayList<Conversacion>();
			conversacion.add(mensaje);

	
				if (mensaje.getMensaje().equals("prueba2")) {
					for (int i = 0; i < conversacion.size(); i++) {
						if(mensaje.getNick().equals(mensaje.getDestinatario())) {
							
						}
						
					}
				}
				
			
		} catch (IOException e) {
			System.out.println("Error al aceptar conexion "+e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				ois.close();
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if (null != socket) {
					socket.close();
				}
				if (null != socketenvio) {
					socketenvio.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	
	@Override
	public void run() {
		try {
			escuchar();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	

}
