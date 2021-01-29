package Controlador;
import java.io.IOException;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import Modelo.Conversacion;
import sun.invoke.empty.Empty;



public class Mensaje extends Thread {
	
	
	Socket socket;
	
	List<Conversacion> conversacion;

	public Mensaje(Socket socket,List<Conversacion> conversacion) {
		super();
		this.socket = socket;
		this.conversacion = conversacion;
	}

	

	private void escuchar() throws ClassNotFoundException, IOException{
		
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;

		try {
			System.out.println("Conexion recibida!");
			ois = new ObjectInputStream(socket.getInputStream());
			Conversacion mensaje = (Conversacion) ois.readObject();
			if(!conversacion.isEmpty()) {
				if (mensaje.getMensaje().equals("pregunta")) {
					for (int i = 0; i < conversacion.size(); i++) {
						if(mensaje.getNick().equals(conversacion.get(i).getDestinatario())) {
							mensaje = conversacion.get(i);
						}
					}
				}
			}else {
				conversacion.add(mensaje);
			}
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(mensaje);
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
