import java.net.InetAddress;

public class Usuario implements java.io.Serializable {
	
	private String nick;
	private InetAddress ip;
	
	
	public Usuario(String nick, InetAddress ip) {
		super();
		this.nick = nick;
		this.ip = ip;
	}


	public String getNick() {
		return nick;
	}


	public void setNick(String nick) {
		this.nick = nick;
	}


	public InetAddress getIp() {
		return ip;
	}


	public void setIp(InetAddress ip) {
		this.ip = ip;
	}
	
	
	

}
