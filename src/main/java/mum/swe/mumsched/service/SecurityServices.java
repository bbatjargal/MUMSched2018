package mum.swe.mumsched.service;

/**
 * @author Batjargal Bayarsaikhan (Alex)
 * Jan 23, 2018
 */
public interface SecurityServices {
    String findLoggedInUsername();
    void autologin(String username, String password);
}
