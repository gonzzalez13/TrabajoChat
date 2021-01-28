import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;


public class Cliente {
	
	public static void main(String[] args) throws Exception {
		Socket socket = null;
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		String Nick;
		Scanner manuel = new Scanner(System.in);
		
		Nick=manuel.nextLine();
		
		try {
			InetSocketAddress direccion = new InetSocketAddress(InetAddress.getLocalHost(), 53203);
			socket = new Socket();
			socket.connect(direccion);
			//Escribe el Nick y la ip al servidor
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(new Conversacion(Nick,"PruebaA","B"));
			oos.flush();
			Pregunta p = new Pregunta(socket,Nick);
			p.start();
			
			ois = new ObjectInputStream(socket.getInputStream());
			Conversacion mensaje = (Conversacion) ois.readObject();
			System.out.println(mensaje.getNick()+":  "+mensaje.getMensaje());
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