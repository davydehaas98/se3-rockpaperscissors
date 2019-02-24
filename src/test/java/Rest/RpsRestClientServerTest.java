package Rest;

import org.junit.Assert;
import org.junit.Test;
import rpsapi.RpsRestClient;

public class RpsRestClientServerTest {

    @Test
    public void loginTest()
    {
        RpsRestClient client = new RpsRestClient();
        String token = client.login("player1", "pwhashupdated");
        Assert.assertNotNull(token);
    }
}
