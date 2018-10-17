package hu.csakegysor.imcsb.data.dao;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class UserDaoImplTest {

    private static final int HTTP_OK = 200;
    private static final int HTTP_ERROR = 400;
    private static final String AUTH_TOKEN_KEY = "idToken";
    private static final String TEST_USER_EMAIL = "testEmail@gmail.com";
    private static final String TEST_PASSWORD = "testPassword";
    private UserDao userDaoImpl;

    @Before
    public void setup() {
        userDaoImpl = new UserDaoImpl(new Gson());
    }

    @Test
    public void testAfterSignUp_UserAbleToSignIn_ThenCanDelete() throws ExecutionException, InterruptedException {
        HttpResponse<JsonNode> signUpResponse = userDaoImpl.performSignUpRequest(TEST_USER_EMAIL, TEST_PASSWORD).get();
        assertEquals(signUpResponse.getStatus(), HTTP_OK);

        HttpResponse<JsonNode> signInResponse = userDaoImpl.performSignInRequest(TEST_USER_EMAIL, TEST_PASSWORD).get();
        assertEquals(signInResponse.getStatus(), HTTP_OK);

        String firebaseId = (String) signInResponse.getBody().getObject().get(AUTH_TOKEN_KEY);
        assertFalse(firebaseId.isEmpty());

        HttpResponse<JsonNode> userDeleteResponse = userDaoImpl.performUserDeleteRequest(firebaseId).get();
        assertEquals(userDeleteResponse.getStatus(), HTTP_OK);
    }

    @Test
    public void testSigningUpTwice_WillReturnHttpError_ThenCanDelete() throws ExecutionException, InterruptedException {
        HttpResponse<JsonNode> firstResponse = userDaoImpl.performSignUpRequest(TEST_USER_EMAIL, TEST_PASSWORD).get();
        assertEquals(firstResponse.getStatus(), HTTP_OK);

        String firebaseId = (String) firstResponse.getBody().getObject().get(AUTH_TOKEN_KEY);
        assertFalse(firebaseId.isEmpty());

        HttpResponse<JsonNode> secondResponse = userDaoImpl.performSignUpRequest(TEST_USER_EMAIL, TEST_PASSWORD).get();
        assertEquals(secondResponse.getStatus(), HTTP_ERROR);

        HttpResponse<JsonNode> userDeleteResponse = userDaoImpl.performUserDeleteRequest(firebaseId).get();
        assertEquals(userDeleteResponse.getStatus(), HTTP_OK);
    }
}