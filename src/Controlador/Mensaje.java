package Controlador;
import java.io.IOException;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import Modelo.Conversacion;
import sun.invoke.empty.Empty;



public class Mensaje extends Thread {
	
	public static final String ENVIAR = "enviar";
	public static final String LEER = "leer";
	public static final String ENVIAR_FICHERO = "enviar_fichero";
	public static final String ENVIAR_TODOS = "enviar_todos";
	
	Socket socket;
	Vector<Conversacion> conversacion;

	public Mensaje(Socket socket,Vector<Conversacion> conversacion) {
		super();
		this.socket = socket;
		this.conversacion = conversacion;
	}

	

	private void escuchar() throws ClassNotFoundException, IOException{
		
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;

		try {
			ois = new ObjectInputStream(socket.getInputStream());
			Conversacion mensaje = (Conversacion) ois.readObject();
			//guardo mensaje en la lista
			conversacion.add(mensaje);
			
			//miro si hay algún mensaje para mi
			List<Conversacion> respuestas = new ArrayList();
			Conversacion conv;
			int size = conversacion.size();
			for (int i =size -1; i >= 0; i--) {
				conv = conversacion.get(i);
				if(mensaje.getNick().equals(conv.getDestinatario())) {
					respuestas.add(conv);
					System.out.println(conv);
					
					//eliminarlo de la lista
					conversacion.remove(i);
				}
			}
			
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(respuestas);
			oos.flush();
			
		} catch (IOException e) {
			System.out.println("Error al aceptar conexion "+e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (null != ois) {
					ois.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if (null != oos) {
					oos.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if (null != socket) {
					socket.close();
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
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
