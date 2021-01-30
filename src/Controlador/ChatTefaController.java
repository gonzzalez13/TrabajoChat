package Controlador;


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
import javafx.scene.control.TextField;


public class ChatTefaController {
	
	
	@FXML
	private TextField txtEnviar;
	
	@FXML
	private TextArea txtAreaChat;
	
	
	
	@FXML
	public void IniciarSesion(ActionEvent event) {
		
	}
	
	@FXML
	public void OnEnviar(ActionEvent event) throws ClassNotFoundException, IOException {
		
		Socket socket = null;
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		String usuario,mensaje,destinatario;
		
		try {
			InetSocketAddress direccion = new InetSocketAddress(InetAddress.getLocalHost(), 53203);
			socket = new Socket();
			socket.connect(direccion);
			
			//Escribe 
			usuario = "Gonzzalez13";
			mensaje = this.txtEnviar.getText();
			destinatario = "B";
			
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(new Conversacion(usuario,mensaje,destinatario));
			oos.flush();
			
			Pregunta p = new Pregunta(socket,usuario);
			p.start();
			
			ois = new ObjectInputStream(socket.getInputStream());
			Conversacion recibido = (Conversacion) ois.readObject();
			
			
			this.txtAreaChat.setText(recibido.getNick()+": "+recibido.getMensaje()+"/n");
			
			
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
