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

	public Mensaje(Socket socket) {
		super();
		this.socket = socket;
	}

	
	private void escuchar() throws ClassNotFoundException{
		InputStream is = null;
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		List<Conversacion> conversacion = null;
		
		try {
			System.out.println("Conexion recibida!");
			is = socket.getInputStream();
			ois = new ObjectInputStream(is);
			Conversacion mensaje = (Conversacion) ois.readObject();
			Conversacion pregunta = (Conversacion) ois.readObject();
	
				if (pregunta.getNick().equals(mensaje.getDestinatario())) {
					//envia el mensaje
					oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(mensaje);
					oos.flush();
					
				}
			
			
			
			
			/*
			usuarios = new ArrayList<Usuario>();
			Usuario usuario = (Usuario) ois.readObject();
			usuarios.add(usuario);
			
			for (Usuario usuario2 : usuarios) {
				if (conversacion.getDestinatario().equals(usuario.getNick())) {
					InetSocketAddress direccion = new InetSocketAddress(usuario.getIp(), 53203);
					socket = new Socket();
					socket.connect(direccion);
					//envia el mensaje
					oos = new ObjectOutputStream(socket.getOutputStream());
					
				}
			}
			*/
			
			
			
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
