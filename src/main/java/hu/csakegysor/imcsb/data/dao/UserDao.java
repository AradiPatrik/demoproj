package hu.csakegysor.imcsb.data.dao;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import java.util.concurrent.Future;

public interface UserDao {
    Future<HttpResponse<JsonNode>> performSignUpRequest(String userEmail, String password);
    Future<HttpResponse<JsonNode>> performSignInRequest(String userEmail, String password);
    Future<HttpResponse<JsonNode>> performUserDeleteRequest(String firebaseId);
}
