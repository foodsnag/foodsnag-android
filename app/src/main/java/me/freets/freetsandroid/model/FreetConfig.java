package me.freets.freetsandroid.model;

/**
 * Created by skroh on 2/21/15.
 */
public class FreetConfig {
    private final String user;
    private final String password;
    private final String college;

    public FreetConfig(String user, String password, String college) {
        this.user = user;
        this.password = password;
        this.college = college;
    }
}
