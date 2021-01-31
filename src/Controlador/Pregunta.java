package Controlador;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import Modelo.Conversacion;

public class Pregunta extends Thread {
	Socket socket;
	String nick;
	boolean peticion = true;

	public Pregunta(Socket socket,String nick) {
		this.socket = socket;
		this.nick = nick;
	}
	
	public void respuestas() throws IOException, ClassNotFoundException {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			InetSocketAddress direccion = new InetSocketAddress("localhost",53203);
			socket = new Socket();
			socket.connect(direccion);
			//Escribe la peticion al servidor
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(new Conversacion(nick,"pregunta",null));
			oos.flush();
			
			ois = new ObjectInputStream(socket.getInputStream());
			Conversacion mensaje = (Conversacion) ois.readObject();
			if(nick.equals(mensaje.getDestinatario())) {
				peticion = false;
				System.out.println(mensaje.getNick()+":  "+mensaje.getMensaje());
			}
			
			
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
		while (peticion == true) {
			try {
				this.sleep(5000);
				respuestas();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
}
