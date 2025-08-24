package conexao;
// arquivo de conexao com banco de dados JDBC
//
//   SÓ FOI IMPLEMENTADO O MÉTODO getConexao.
//

import java.sql.*;

public class Conexao {

    //java.sql = Connection, Statement, ResultSet
    //DriverManager
    public static Connection criarConexao() {
        String url = "jdbc:mysql://localhost:3306/maridoaluguel?useSSL=false";
        String user = "root";
        String password = "";
        try {
            Connection conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (SQLException e) {
            System.out.println("Erro na conexão com banco de dados");
        }
        return null;
    }

    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection connection, Statement stmt) {
        close(connection);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection connection, Statement stmt, ResultSet rs) {
        close(connection, stmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

/*
 public static JdbcRowSet getRowSetConnection() {
        String url = "jdbc:mysql://localhost:3306/cfi028?useSSL=false";
        String user = "root";
        String password = "root";
        try {
            JdbcRowSet jdbcRowSet = RowSetProvider.newFactory().createJdbcRowSet();
            jdbcRowSet.setUrl(url);
            jdbcRowSet.setUsername(user);
            jdbcRowSet.setPassword(password);
            return jdbcRowSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static CachedRowSet getRowSetConnectionCached() {
        String url = "jdbc:mysql://localhost:3306/cfi028?useSSL=false&relaxAutoCommit=true";
        String user = "root";
        String password = "root";
        try {
            CachedRowSet cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
            cachedRowSet.setUrl(url);
            cachedRowSet.setUsername(user);
            cachedRowSet.setPassword(password);
            return cachedRowSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void close(RowSet jrs) {
        try {
            if (jrs != null)
                jrs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 */
