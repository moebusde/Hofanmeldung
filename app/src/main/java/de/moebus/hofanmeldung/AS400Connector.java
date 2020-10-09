package de.moebus.hofanmeldung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AS400Connector {

    private final String server = "10.251.80.190:446";
    private final String username = "titanusr";
    private final String password = "uty0604p";
    private final String databasename = "ALPHATST01";
    private final String classes = "net.sourceforge.jtds.jdbc.Driver";
    private final String url = "jdbc:jtds:sqlserver://"+server+"/"+databasename;

    private Connection connection = null;

    public AS400Connector() throws SQLException , ClassNotFoundException {
        this.connect();
    }

    private Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName(classes);
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

}
