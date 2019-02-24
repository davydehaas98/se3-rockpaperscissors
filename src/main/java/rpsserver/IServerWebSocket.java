package rpsserver;

import rpsshared.interfaces.IWebSocket;

public interface IServerWebSocket extends IWebSocket {

    void sendTo(String sessionId, Object object);

    void broadcast(Object object);

    void sendToOthers(String sessionId, Object object);

    void setMessageProcessor(IServerMessageProcessor processor);

}
