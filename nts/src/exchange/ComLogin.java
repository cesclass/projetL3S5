package exchange;

public class ComLogin {
    private String login = null;
    private String password = null;

    /**
     * Identity constructor
     * @param login
     * @param password
     */
    public ComLogin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
}
