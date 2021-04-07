package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Server extends Application{
	private boolean is_server=false;
	TextArea t=new TextArea();
	private Connexion x=is_server ?createSer():createCl();
	private Parent Content()
	{
		t.prefHeight(258);
		TextField f=new TextField();
		f.setOnAction(event->{
			String message =is_server ? "Server :":"Client :";
			message+=f.getText();
			f.clear();
			t.appendText(message+"\n");
			try {
				x.send_Message(message);
			} catch (Exception e) {
				t.appendText("Field To send"+"\n");
			}
		});
		VBox bx=new VBox(28,t,f);
		bx.setPrefSize(600,600);
		return bx;
		
	}
	public void init()throws Exception
	{
		x.startConnexion();
	}
	public void stop()throws Exception
	{
		x.close();
	}
	@Override
	public void start(Stage arg0) throws Exception {

		Scene s=new Scene(Content());
		arg0.setScene(s);
		arg0.show();
		
	}
	private ServerT createSer()
	{
		return new ServerT(1337,data-> {
			Platform.runLater(()->{
				t.appendText(data.toString()+"\n");
			});
		});
	}
	private ClientT createCl()
	{
		return new ClientT("localhost",1337,data-> {
			Platform.runLater(()->{
				t.appendText(data.toString()+"\n");
			});
		});
		
	}
	public static void main(String []args)
	{
		Application.launch(args);
	}

}
