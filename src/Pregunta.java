import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Pregunta extends Thread {
	Socket socket;
	String nick;

	public Pregunta(Socket socket,String nick) {
		this.socket = socket;
		this.nick = nick;
	}
	
	public void respuestas() throws IOException, ClassNotFoundException {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			System.out.println("Esta mi mensaje");
			InetSocketAddress direccion = new InetSocketAddress("localhost",53203);
			socket = new Socket();
			socket.connect(direccion);
			//Escribe la peticion al servidor
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(new Conversacion(nick,"pregunta",null));
			oos.flush();
			
			ois = new ObjectInputStream(socket.getInputStream());
			Conversacion mensaje = (Conversacion) ois.readObject();
			System.out.println(mensaje.getNick()+":  "+mensaje.getMensaje());
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		}finally {
			oos.close();
			ois.close();
		}
		
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				this.sleep(5000);
				respuestas();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
}
