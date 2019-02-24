package rpsserver;

import rpsshared.Logging.LogLevel;
import rpsshared.Logging.Logger;
import rpsshared.WebSocketBase;
import rpsshared.interfaces.IMessageProcessor;
import rpsshared.messages.EncapsulatingMessage;
import javax.inject.Singleton;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;

@Singleton
@ServerEndpoint(value="/rockpaperscissors/")
public class ServerWebSocket extends WebSocketBase implements IServerWebSocket {

    private IServerMessageProcessor messageProcessor;

    public void setMessageProcessor(IServerMessageProcessor processor)
    {
        this.messageProcessor = processor;
    }

    public IMessageProcessor getMessageProcessor() {
        return messageProcessor;
    }


    private static ArrayList<Session> sessions = new ArrayList<>();

    @OnOpen
    public void onConnect(Session session) {
        sessions.add(session);
    }

    @OnMessage
    public void onText(String message, Session session) {
        String sessionId = session.getId();
        EncapsulatingMessage msg = getGson().fromJson(message, EncapsulatingMessage.class);
        getMessageProcessor().processMessage(sessionId, msg.getMessageType(), msg.getMessageData());
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        getMessageProcessor().handleDisconnect(session.getId());
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable cause, Session session) {
        Logger.getInstance().log(cause.getMessage(), LogLevel.ERROR);
    }

    public void sendTo(String sessionId, Object object)
    {
        String msg = getEncapsulatingMessageGenerator().generateMessageString(object);
        sendToClient(getSessionFromId(sessionId), msg);
    }

    @Override
    public void stop() {
        //START AND STOP ARE HANDLED ELSEWHERE
    }

    @Override
    public void start() {
        //START AND STOP ARE HANDLED ELSEWHERE
    }

    public Session getSessionFromId(String sessionId)
    {
        for(Session s : sessions)
        {
            if(s.getId().equals(sessionId))
                return s;
        }
        return null;
    }

    public void broadcast(Object object)
    {
        for(Session session : sessions) {
            sendTo(session.getId(), object);
        }
    }

    public void sendToOthers(String sessionId, Object object)
    {
        Session session = getSessionFromId(sessionId);
        for(Session ses : sessions) {
            if(!ses.getId().equals(session.getId()))
                sendTo(ses.getId(), object);
        }
    }

    private void sendToClient(Session session, String message)
    {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            Logger.getInstance().log(e);
        }
    }
}

