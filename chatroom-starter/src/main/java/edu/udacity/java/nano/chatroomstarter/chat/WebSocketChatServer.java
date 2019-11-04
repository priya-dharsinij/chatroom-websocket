package edu.udacity.java.nano.chatroomstarter.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint(value="/chat/{username}",encoders = {MessageEncoder.class}, decoders = {MessageDecoder.class})
public class WebSocketChatServer {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketChatServer.class);


    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(Message msg) {

        onlineSessions.forEach((user, session) -> {
            synchronized (user) {
                try {
                    session.getBasicRemote().
                            sendObject(msg);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("username") String username) throws IOException {

        onlineSessions.put(username,session);

        Message message = new Message();
        message.setUsername(username);
        message.setContent("Connected!");
        message.setType("SPEAK");
        message.setOnlineCount(String.valueOf(onlineSessions.size()));
        sendMessageToAll(message);
        logger.info("Received a new web socket connection.Username:"+ username );
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, Message message) {

        if(message!=null) {
            message.setType("SPEAK");
            message.setOnlineCount(String.valueOf(onlineSessions.size()));
            sendMessageToAll(message);
        }
    }



    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session,@PathParam("username") String username) {

        if(username != null) {

            if (onlineSessions.containsValue(session)) {
                onlineSessions.remove(username, session);
            }


            Message message = new Message();
            message.setUsername(username);
            message.setContent("Disconnected!");
            message.setType("SPEAK");
            message.setOnlineCount(String.valueOf(onlineSessions.size()));
            sendMessageToAll(message);

            logger.info("User Disconnected : " + username);

        }
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {

        logger.info("Connection Error"+session.getId());

        if(onlineSessions.containsValue(session)){
            onlineSessions.values().remove(session);
        }

        error.printStackTrace();
    }

}
