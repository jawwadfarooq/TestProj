package websocket;

//import javax.ejb.Stateless;
//import javax.enterprise.concurrent.ManagedExecutorService;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value="/example")
//@Stateless
public class WebSocketEndpoint {
    
//    @Resource
//    ManagedExecutorService mes;
    
    @OnMessage
    public String receiveMessage(String message, Session session) {
        System.out.println("Received : "+ message + ", session:" + session.getId());
        return "Response from the server";
    }
    
    @OnOpen
    public void open(Session session) {
        System.out.println("Open session:" + session.getId());
        final Session s = session;
        
        try {
            // Send 3 messages to the client every 5 seconds
            int sentMessages = 0;
            while(sentMessages < 3){
              Thread.sleep(5000);
              session.getBasicRemote().
                sendText("This is an intermediate server message. Count: " 
                  + sentMessages);
              sentMessages++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
//        mes.execute(new Runnable() {
//            @Override
//            public void run() {             
//                try {
//                    for (int i=0;i<3;i++) {
//                     Thread.sleep(10000);
//                     s.getBasicRemote().sendText("Message from server");
//                    } 
//                } catch (InterruptedException | IOException e) {
//                    e.printStackTrace();
//                }
//            }           
//        });
    }
    
    @OnClose
    public void close(Session session, CloseReason c) {
        System.out.println("Closing:" + session.getId());
    }
}