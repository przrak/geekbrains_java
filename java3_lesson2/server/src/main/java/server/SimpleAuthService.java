package server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleAuthService implements AuthService {

    private Connection connection;
    private Statement stmt;
    private PreparedStatement psInsert;
    private PreparedStatement psSelect;

    private class UserData {
        String login;
        String password;
        String nickname;

        public UserData(String login, String password, String nickname) {
            this.login = login;
            this.password = password;
            this.nickname = nickname;
        }
    }

    private List<UserData> users;

    public SimpleAuthService() throws SQLException, ClassNotFoundException {
        connect();
        prepareStatementForInsertUser();
        prepareStatementForSelectUser();
        clearTable();
        fillTable();
        disconnect();
    }

    private void fillTable() throws SQLException {
        connection.setAutoCommit(false);
        this.insertUser("qwe", "qwe", "qwe");
        this.insertUser("asd", "asd", "asd");
        this.insertUser("zxc", "zxc", "zxc");
        for (int i = 1; i < 10; i++) {
            this.insertUser("user" + i, "pass" + i, "nick" + i);
        }
        connection.commit();
    }

    private void insertUser(final String login, final String password, final String nickname) throws SQLException {
        psInsert.setString(1, login);
        psInsert.setString(2, password);
        psInsert.setString(3, nickname);
        psInsert.executeUpdate();
    }

    private void prepareStatementForInsertUser() throws SQLException {
        psInsert = connection.prepareStatement(
                "INSERT INTO user_table (login, password, nickname) VALUES ( ? , ? , ?);");
    }

    private void prepareStatementForSelectUser() throws SQLException {
        psSelect = connection.prepareStatement(
                "SELECT login, password, nickname FROM user_table WHERE login = ? and password = ?;");
    }

    private void clearTable() throws SQLException {
        stmt.executeUpdate("DELETE FROM user_table;");
    }

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) throws SQLException {
        psSelect.setString(1, login);
        psSelect.setString(2, password);
        ResultSet rs = psSelect.executeQuery();
        for (UserData user : users) {
            if(user.login.equals(login) && user.password.equals(password)){
                return user.nickname;
            }
        }
        while (rs.next()) {
            System.out.println();
//            return rs.getString()
        }
        return null;
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        for (UserData user : users) {
            if(user.login.equals(login) || user.nickname.equals(nickname)){
                return false;
            }
        }
        users.add(new UserData(login, password, nickname));
        return true;
    }

    private void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:java3_lesson2/server/main.db");
        stmt = connection.createStatement();
    }

    private void disconnect() {
        try {
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
