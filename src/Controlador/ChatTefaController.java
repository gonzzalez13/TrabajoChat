package Controlador;

import java.io.IOException;

import Modelo.Conversacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ChatTefaController {
	
	
	@FXML
	public void enviar(ActionEvent event) {
		
		try {
			FXMLLoader cargar = new FXMLLoader (getClass().getResource("/interfaz/LoginTefa.fxml"));
		
		
			Parent root = cargar.load();
			
			LoginTefaController ltc = cargar.getController();
			
			Conversacion c = ltc.getNick();
			
			System.out.println(c.getNick());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		

	}
	
}
