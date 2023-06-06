package api.steps;

import utils.PropertyReader;

import java.util.HashMap;
import java.util.Map;

public class LoginStep extends BaseStep{

    private Map<String, String> credentialsToLogin = new HashMap<String, String>();

    public Map <String, String> addValidCredentials(){
        credentialsToLogin.put("username", PropertyReader.getLogin());
        credentialsToLogin.put("password", PropertyReader.getPassword());

        return credentialsToLogin;
    }

    public Map <String, String> addEmptyCredentials(){
        credentialsToLogin.put("username", "");
        credentialsToLogin.put("password", "");

        return credentialsToLogin;
    }

    public Map <String, String> addInvalidLogin(){
        credentialsToLogin.put("username", "someInvalidLogin");
        credentialsToLogin.put("password", PropertyReader.getPassword());

        return credentialsToLogin;
    }

    public Map <String, String> addInvalidPassword(){
        credentialsToLogin.put("username", PropertyReader.getLogin());
        credentialsToLogin.put("password", "someInvalidPassword");

        return credentialsToLogin;
    }

    public Map <String, String> addInvalidCredentials(){
        credentialsToLogin.put("username", "someInvalidLogin");
        credentialsToLogin.put("password", "someInvalidPassword");

        return credentialsToLogin;
    }

}
