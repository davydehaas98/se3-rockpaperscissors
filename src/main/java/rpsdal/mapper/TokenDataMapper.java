package rpsdal.mapper;

import model.Token;
import rpsshared.Logging.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TokenDataMapper<T> extends BaseDataMapper<Token> {

    @Override
    public String mapToSqlInternal(Token object) {
        if(object.getEntityId() == 0)
        {
            //new
            return "insert into [tokens] (TokenText, CreationDate, TTL, PlayerId) "
            + "values (' " + object.getTokenText() + "' , '" + object.getCreationDate() + "' , '" + object.getTimeToLive() + "' , '" + object.getPlayerId() +"')";
        }
        else
        {
            //update
            return "update [Tokens] set TokenText=' " + object.getTokenText() + "', TTL='" + object.getTimeToLive() +"' where Id=" + object.getEntityId();
        }
    }

    @Override
    public List<Token> mapFromDatabaseInternal(ResultSet rs) {
        ArrayList<Token> tokens = new ArrayList();

        try {
            while (rs.next()) {
                long id = rs.getLong(1);
                String text = rs.getString(2);
                Date date = new Date(rs.getString(3));
                int ttl = rs.getInt(4);
                long playerId = rs.getLong(5);
                Token t = new Token(text, date, ttl, playerId);
                t.setEntityId(id);
                tokens.add(t);
            }
            return tokens;
        }
        catch(SQLException ex)
        {
            Logger.getInstance().log(ex);
            return tokens;
        }
    }
}
