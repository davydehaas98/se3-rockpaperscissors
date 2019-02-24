package rpsapi;

import rpsapi.dto.BaseResultDto;
import rpsapi.dto.LoginRequestDto;
import rpsdal.SqlDataContext;
import rpsdal.repositories.IPlayerRepository;
import rpsdal.repositories.ITokenRepository;
import rpsdal.repositories.PlayerRepository;
import rpsdal.repositories.TokenRepository;
import rpsshared.interfaces.IDataContext;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/rps")
public class RpsRestService {

    private IPlayerRepository playerRepository;
    private IDataContext dataContext;
    private ITokenRepository tokenRepository;

    public RpsRestService()
    {
        dataContext = new SqlDataContext();
        playerRepository = new PlayerRepository(dataContext);
        tokenRepository = new TokenRepository(dataContext);
    }

    //FOR TESTING PURPOSES
    public RpsRestService(IPlayerRepository playerRepos, ITokenRepository tokenRepos)
    {
        this.playerRepository = playerRepos;
        this.tokenRepository = tokenRepos;
    }

    @GET
    @Path("/player/get")
    public Response get(){
        return Response.status(200).entity(new BaseResultDto()).build();
    }

    @POST
    @Path("/player/login")
    @Consumes("application/json")
    @Produces("application/json")
    public Response login(LoginRequestDto loginRequest)
    {
        if(loginRequest == null)
        {
            return Response.status(400).entity(ResponseHelper.getErrorResponseString()).build();
        }

        String token = playerRepository.login(tokenRepository, loginRequest.getUserName(), loginRequest.getHashedPassword());
        if(token.equals(""))
        {
            //TODO log failed login requests
            return Response.status(401).entity(ResponseHelper.getErrorResponseString()).build();
        }

        return  Response.status(200).entity(ResponseHelper.getLoginResultDtoResponseString(token)).build();

    }
}
