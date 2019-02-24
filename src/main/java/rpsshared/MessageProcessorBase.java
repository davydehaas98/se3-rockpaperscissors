package rpsshared;

import com.google.gson.Gson;
import rpsshared.interfaces.IMessageHandlerFactory;

public abstract class MessageProcessorBase {


    private IMessageHandlerFactory messageHandlerFactory;

    public IMessageHandlerFactory getMessageHandlerFactory() {
        return messageHandlerFactory;
    }

    public abstract void processMessage(String sessionId, String type, String data);

    public abstract void handleDisconnect(String sessionId);

    private Gson gson;

    public MessageProcessorBase(IMessageHandlerFactory messageHandlerFactory)
    {
        this.messageHandlerFactory = messageHandlerFactory;
        gson = new Gson();
    }

    public Gson getGson() {
        return gson;
    }


}
