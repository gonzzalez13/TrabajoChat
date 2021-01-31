package Controlador;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

import Modelo.Conversacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class ChatTefaController {
	
	
	@FXML
	private TextField txtEnviar;
	
	@FXML
	private TextArea txtAreaChat;
	
	@FXML
	private TextArea conectar;
	
	@FXML
	private Pane ventana;
	
	@FXML
	private TextField txtNombre;
	
	
	@FXML
	private ListView<String> usuarios;
	
	String destinatario;
	
	@FXML
	public void IniciarSesion(ActionEvent event) {
		if(!txtNombre.getText().equals("")) {
			conectar.setText(txtNombre.getText()+" ha iniciado sesión");
			usuarios.getItems().add(txtNombre.getText());
			
		
		}
		else {
			
		}
		ventana.setVisible(false);
		
	}
	
	@FXML
	public void OnEnviar(ActionEvent event) throws ClassNotFoundException, IOException {
		
		Socket socket = null;
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		String usuario,mensaje;
		
		try {
			InetSocketAddress direccion = new InetSocketAddress(InetAddress.getLocalHost(), 53203);
			socket = new Socket();
			socket.connect(direccion);
			
			//Escribe 
			usuario = this.txtNombre.getText();
			mensaje = this.txtEnviar.getText();
			
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(new Conversacion(usuario,mensaje,this.destinatario));
			oos.flush();
			
//			Pregunta p = new Pregunta(socket,usuario);
//			p.start();
			
			ois = new ObjectInputStream(socket.getInputStream());
			List<Conversacion> recibido = (List<Conversacion>) ois.readObject();
			String respuesta = this.txtAreaChat.getText();
			for(Conversacion conv: recibido) {
				respuesta = conv.getNick()+": "+conv.getMensaje() + "\n";
			}			
			
			this.txtAreaChat.setText(respuesta);
			
			
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
