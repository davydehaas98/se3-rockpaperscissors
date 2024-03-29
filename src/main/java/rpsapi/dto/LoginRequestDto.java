package rpsapi.dto;

public class LoginRequestDto extends BaseRequestDto {

    private String userName;
    private String hashedPassword;

    public LoginRequestDto(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public LoginRequestDto(String userName, String hashedPassword) {
        this.userName = userName;
        this.hashedPassword = hashedPassword;
    }
}
