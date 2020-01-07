package exchange;

public class ComLogin {
    private String login = null;
    private String password = null;
    private int id = -1;

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
     * 
     * @param id
     */
    public ComLogin(int id) {
        this.id = id;
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

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        String ts = "login: " + this.login + 
                "; password: " + this.password +
                "; id: " + this.id;
        return ts;
    }
}
