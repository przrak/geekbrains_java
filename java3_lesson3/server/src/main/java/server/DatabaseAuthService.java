package server;

import java.sql.*;

public class DatabaseAuthService implements AuthService {

    private Connection connection;
    private Statement stmt;
    private PreparedStatement ps;

    public DatabaseAuthService() throws SQLException, ClassNotFoundException {
        connect();
        prepareStatementForInsertUser();
        clearTable();
        fillTable();
        disconnect();
    }

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        try {
            connect();
            prepareStatementForSelectUserByLoginAndPassword();
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("nickname");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return null;
    }

    private String getNicknameByLogin(String login) {
        try {
            connect();
            prepareStatementForSelectUserByLogin();
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("nickname");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return null;
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        try {
            this.connect();
            this.prepareStatementForInsertUser();
            this.insertUser(login, password, nickname);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return true;
    }

    @Override
    public String changeNickname(String login, String newNickname) {
        try {
            this.connect();
            this.prepareStatementForUpdateUser();
            this.updateUserNickname(login, newNickname);
            return this.getNicknameByLogin(login);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return null;
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
        ps.setString(1, login);
        ps.setString(2, password);
        ps.setString(3, nickname);
        ps.executeUpdate();
    }

    private void updateUserNickname(final String login, final String nickname) throws SQLException {
        ps.setString(1, nickname);
        ps.setString(2, login);
        ps.executeUpdate();
    }

    private void prepareStatementForInsertUser() throws SQLException {
        ps = connection.prepareStatement(
                "INSERT INTO user_table (login, password, nickname) VALUES ( ? , ? , ?);");
    }

    private void prepareStatementForSelectUserByLoginAndPassword() throws SQLException {
        ps = connection.prepareStatement(
                "SELECT login, password, nickname FROM user_table WHERE login = ? and password = ?;");
    }

    private void prepareStatementForSelectUserByLogin() throws SQLException {
        ps = connection.prepareStatement(
                "SELECT login, nickname FROM user_table WHERE login = ?;");
    }

    private void prepareStatementForUpdateUser() throws SQLException {
        ps = connection.prepareStatement(
                "UPDATE user_table SET nickname = ? WHERE login = ?;"
        );
    }

    private void clearTable() throws SQLException {
        stmt.executeUpdate("DELETE FROM user_table;");
    }
}
