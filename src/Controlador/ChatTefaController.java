package Controlador;

import java.awt.Button;
import java.awt.TextField;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import Modelo.Conversacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;


public class ChatTefaController {
	
	
	@FXML
	private TextField txtEnviar;
	
	@FXML
	private TextArea txtAreaChat;
	
	
	
	
	
	@FXML
	public void Onenviar(ActionEvent event) throws ClassNotFoundException, IOException {
		
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
		catch(IOException | ClassNotFoundException e) {
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
