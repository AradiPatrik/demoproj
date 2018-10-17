package hu.csakegysor.imcsb.data.dao;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import javax.inject.Inject;
import java.util.concurrent.Future;


public class UserDaoImpl implements UserDao {
    private static final String WEBAPI_KEY = "AIzaSyBvzhDcnZg9lbvU4lvtInYhsNDzDUhrT6E";
    private static final String AUTH_BASE = "https://www.googleapis.com/identitytoolkit/v3/relyingparty/";
    private static final String SIGN_UP = "signupNewUser";
    private static final String VERIFY_PASSWORD = "verifyPassword";
    private static final String DELETE_ACCOUNT = "deleteAccount";
    private static final String KEY_KEY = "key";
    private static final String CONTENT_TYPE = "Content-type";
    private static final String APPLICATION_JSON = "application/json";
    private final Gson gson;

    @Inject
    UserDaoImpl(Gson gson) {
        this.gson = gson;
    }

    public Future<HttpResponse<JsonNode>> performSignUpRequest(String userEmail, String password) {
        return Unirest.post(AUTH_BASE + SIGN_UP)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .queryString(KEY_KEY, WEBAPI_KEY)
                .body(gson.toJson(new UserCredentials(userEmail, password)))
                .asJsonAsync();
    }

    @Override
    public Future<HttpResponse<JsonNode>> performSignInRequest(String userEmail, String password) {
        return Unirest.post(AUTH_BASE + VERIFY_PASSWORD)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .queryString(KEY_KEY, WEBAPI_KEY)
                .body(gson.toJson(new UserCredentials(userEmail, password)))
                .asJsonAsync();
    }

    @Override
    public Future<HttpResponse<JsonNode>> performUserDeleteRequest(String firebaseId) {
        return Unirest.post(AUTH_BASE + DELETE_ACCOUNT)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .queryString(KEY_KEY, WEBAPI_KEY)
                .body(gson.toJson(new FirebaseId(firebaseId)))
                .asJsonAsync();
    }

    private static final class FirebaseId {
        final String idToken;
        FirebaseId(String idToken) {
            this.idToken = idToken;
        }
    }

    private static final class UserCredentials {
        final String email;
        final String password;
        final boolean returnSecureToken = true;

        private UserCredentials(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }
}
