package rpsserver;

import model.IGame;
import rpsshared.MessageProcessorBase;
import rpsshared.interfaces.IMessageHandler;
import rpsshared.interfaces.IMessageHandlerFactory;

public class ServerMessageProcessor extends MessageProcessorBase implements IServerMessageProcessor {

    private IGame game;

    public ServerMessageProcessor(IMessageHandlerFactory messageHandlerFactory)
    {
        super(messageHandlerFactory);
    }

    public void registerGame(IGame game)
    {
        this.game = game;
    }

    public IGame getGame()
    {
        return game;
    }

    @Override
    public void processMessage(String sessionId, String type, String data) {
        //Get the last part from the full package and type name
        String simpleType = type.split("\\.")[type.split("\\.").length - 1];

        IMessageHandler handler = getMessageHandlerFactory().getHandler(simpleType, getGame());
        handler.handleMessage(data, sessionId);
    }

    public void handleDisconnect(String sessionId)
    {
        getGame().processClientDisconnect(sessionId);
    }
}
