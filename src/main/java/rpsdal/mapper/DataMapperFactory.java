package rpsdal.mapper;

import model.*;

public class DataMapperFactory {

    private DataMapperFactory(){}

    public static BaseDataMapper getMapper(Class entityType)
    {
        if(entityType.equals(Player.class))
           return new PlayerDataMapper();
        else if(entityType.equals(RoundResult.class))
            return new RoundResultDataMapper();
        else if(entityType.equals(Token.class))
            return new TokenDataMapper();
        return null;
    }

    public static BaseDataMapper getMapper(String simpleType)
    {
        switch(simpleType)
        {
            case "Player":
                return new PlayerDataMapper();
            case "RoundResult":
                return new RoundResultDataMapper();
            case "Token":
                return new TokenDataMapper();
            default:
                return null;
        }
    }
}
