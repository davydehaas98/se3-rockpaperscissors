package rpsserver;

import model.IGame;
import rpsshared.interfaces.IMessageProcessor;

public interface IServerMessageProcessor extends IMessageProcessor {

    void registerGame(IGame game);
}
