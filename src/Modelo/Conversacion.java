package Modelo;

public class Conversacion implements java.io.Serializable {
	
	private String Nick;
	private String mensaje;
	private String destinatario;
	private String tipoMensaje;
	
	public Conversacion(String nick, String mensaje,String destinatario) {
		Nick = nick;
		this.mensaje = mensaje;
		this.destinatario = destinatario;
	}

	public Conversacion(String nick,String mensaje) {
		Nick = nick;
		this.mensaje = mensaje;
	}
	
	public Conversacion(String nick) {
		Nick = nick;
	}



	public String getNick() {
		return Nick;
	}


	public void setNick(String nick) {
		Nick = nick;
	}


	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public String getDestinatario() {
		return destinatario;
	}


	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}



	@Override
	public String toString() {
		return "Conversacion [Nick=" + Nick + ", mensaje=" + mensaje + ", destinatario=" + destinatario + "]";
	}
	
}
