package application;

import java.io.Serializable;
import java.util.function.Consumer;

public class ServerT extends Connexion{
	private int port;
public ServerT(int p,Consumer <Serializable> c)
{
	super(c);
	this.port=p;
	
}

@Override
protected Boolean is_server() {
	return true;
}

@Override
protected String Got_Ip() {
	return null;
}

@Override
protected int get_port() {
	return port;
}
}
