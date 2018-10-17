package hu.csakegysor.imcsb.data.entities;

public class LoginResponse {
    private String kind;
    private String idToken;
    private String email;
    private String refreshToken;
    private String expiresIn;
    private String localID;

    public String getKind() { return kind; }
    public void setKind(String value) { this.kind = value; }

    public String getIDToken() { return idToken; }
    public void setIDToken(String value) { this.idToken = value; }

    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }

    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String value) { this.refreshToken = value; }

    public String getExpiresIn() { return expiresIn; }
    public void setExpiresIn(String value) { this.expiresIn = value; }

    public String getLocalID() { return localID; }
    public void setLocalID(String value) { this.localID = value; }
}


