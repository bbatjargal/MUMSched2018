package mum.swe.mumsched.service;

public interface SecurityServices {
    String findLoggedInUsername();
    void autologin(String username, String password);
}
