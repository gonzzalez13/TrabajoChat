import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Modelo.Conversacion;

public class Servidor {


	public static void main(String[] args) throws Exception {
		
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(53203);
			List<Conversacion> conversacion =  new ArrayList<Conversacion>();;
			while (true) {
				try {
					Socket conexion = server.accept();
					Mensaje hilo = new Mensaje(conexion,conversacion);
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
