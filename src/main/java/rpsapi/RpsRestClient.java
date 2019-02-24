package rpsapi;

import rpsapi.dto.LoginRequestDto;
import rpsapi.dto.LoginResultDto;

public class RpsRestClient extends BaseRestClient implements IRpsRestClient {

    private final String url = "http://localhost:8095/rps";

    public String getBaseUr()
    {
        return url;
    }

    public String login(String username, String password)
    {
        LoginRequestDto dto = new LoginRequestDto(username, password);
        String query = "/player/login";
        LoginResultDto result =  executeQueryPost(dto, query, LoginResultDto.class);
        return result.getToken();
    }
}
