package Controlador;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import Modelo.Conversacion;

public class Servidor {


	public static void main(String[] args) throws Exception {
		
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(53203);
			Vector<Conversacion> conversacion =  new Vector<Conversacion>();;
			while (true) {
				try {
					Mensaje hilo = new Mensaje(server.accept(),conversacion);
					hilo.start();
					
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

}
