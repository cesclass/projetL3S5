package test;

import java.sql.*;

public class TestJDBC {

	static Connection myConn = null;
	static PreparedStatement myStmt = null;
	static ResultSet myRs = null;

	static String bdd = "jdbc:mysql://127.0.0.1:3306/nts";
	static String id = "root";
	static String mdp = "";

	public static void main(String[] args) throws SQLException {

		try {
			// Connexion
			myConn = DriverManager.getConnection(bdd, id, mdp);

			// Requette
			myStmt = myConn.prepareStatement("SELECT `first_name`,`last_name` FROM `users`");
			myRs = myStmt.executeQuery();

			// Affichage
			while(myRs.next()){
				System.out.println(
						myRs.getString("first_name") + " " + 
						myRs.getString("last_name")
				);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();
			}
		}

	}

}
