package rpsapi;

import com.google.gson.Gson;
import rpsapi.dto.BaseResultDto;
import rpsapi.dto.LoginResultDto;

public class ResponseHelper {

    private ResponseHelper(){}

    private static final Gson gson = new Gson();

    public static String getErrorResponseString()
    {
        BaseResultDto response = new BaseResultDto();
        response.setSuccess(false);
        return gson.toJson(response);
    }

    public static String getLoginResultDtoResponseString(String token)
    {
        LoginResultDto response = new LoginResultDto();
        response.setSuccess(true);
        response.setToken(token);
        return gson.toJson(response);
    }

}
