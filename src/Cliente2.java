import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import Modelo.Conversacion;

public class Cliente2 {
	
	public static void main(String[] args) throws Exception {
		Socket socket = null;
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		String Nick = "B";
		
		try {
			InetSocketAddress direccion = new InetSocketAddress(InetAddress.getLocalHost(), 53203);
			socket = new Socket();
			socket.connect(direccion);
			//Escribe el Nick y la ip al servidor
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(new Conversacion(Nick,"PruebaB","A"));
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
