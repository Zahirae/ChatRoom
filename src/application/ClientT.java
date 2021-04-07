package application;
import java.io.Serializable;
import java.util.function.Consumer;

public class ClientT extends Connexion{
	public String ip;
	public int port;
	public ClientT(String ip,int p,Consumer <Serializable> c)
{
	super(c);
	this.ip=ip;
	this.port=p;
	
}

	@Override
	protected Boolean is_server() {
		return false;
	}

	@Override
	protected String Got_Ip() {
		return ip;
	}

	@Override
	protected int get_port() {
		return port;
	}

}
