package application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public abstract class  Connexion {
private Consumer <Serializable> c;
private ConnexionThread t=new ConnexionThread();
public Connexion(Consumer <Serializable> c)
{
	this.c=c;
	t.setDaemon(true);
}
public void startConnexion()throws Exception
{
	t.start();
}
public void send_Message(Serializable s)throws Exception
{
	 ((ObjectOutputStream) t.out).writeObject(s);
}
public void close()throws Exception
{
	t.soc.close();
}
protected abstract Boolean is_server();
protected abstract String Got_Ip();
protected abstract int get_port();

private class ConnexionThread extends Thread
{
	public Object out;
	private Socket soc;
	private ObjectOutputStream ob=null;
	public void run()
	{
		try
		(
			ServerSocket s =is_server() ? new ServerSocket(get_port()):null;
			Socket soc= is_server()?s.accept():new Socket(Got_Ip(),get_port());
				ObjectOutputStream ob=new ObjectOutputStream(soc.getOutputStream());
				ObjectInputStream oi=new ObjectInputStream(soc.getInputStream());){
			this.soc=soc;
			this.ob=ob;
			soc.setTcpNoDelay(true);
			while(true)
			{
				Serializable data=(Serializable)oi.readObject();
				c.accept(data);
				
			}
		}catch(Exception e)
		{
			c.accept("connexion closed");
		}
		
	}
}


}
