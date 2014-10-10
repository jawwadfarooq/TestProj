package websocket;

import java.io.IOException;

import javax.websocket.*;
import javax.websocket.server.*;

@ServerEndpoint(value="/ws")
public class SocketEndPoint extends Endpoint
{
	@Override
	public void onOpen (Session session, EndpointConfig config) {
		System.out.println("Connection opened");
		final RemoteEndpoint.Basic remote = session.getBasicRemote();
		session.addMessageHandler(new MessageHandler.Whole<String>() {
			public void onMessage (String text) {
				try {
					remote.sendText("Got your message (" + text + "). Thanks!");
				} catch (IOException ioex) {
					System.out.println("onMessage error: "+ioex.getMessage());
				}
			}
		});
	}

	@Override
	public void onClose (Session session, CloseReason reason) {
		System.out.println("Connection closed: "+reason);
	}

	@Override
	public void onError (Session session, Throwable thr) {
		System.out.println("Error: "+thr.getMessage());
		thr.printStackTrace();
	}
}
