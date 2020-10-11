package de.moebus.hofanmeldung;

import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLConnector {

    private final String server = "10.10.1.29:1433";
    private final String username = "sa";
    private final String password = "sqladmin123!";
    private final String databasename = "TITAN";
    private final String instancename = "TITAN";
    private final String classes = "net.sourceforge.jtds.jdbc.Driver";
    private final String url = "jdbc:jtds:sqlserver://"+server+"/"+databasename+";instance="+instancename;

    private Connection connection = null;


    public SQLConnector() {
        try {
            this.connect();
            Log.i("SUCCESS", "Verbindung erfolgreich aufgebaut");
        } catch (SQLException e) {
            Log.i("SQLException", e.getMessage());
            Log.i("URL", url);
        } catch (ClassNotFoundException e) {
            Log.i("ClassNotFoundException", e.getMessage());
        }

    }


    private Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName(classes);
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    public ArrayList<Long> getArbeitsauftragsnummern() throws SQLException, ClassNotFoundException {

        ArrayList<Long> arbeitsauftraege = null;

        Statement statement = null;

        statement = connect().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT WORKORDERNO FROM _15053_mt2titan");
        while (resultSet.next()) {
            arbeitsauftraege.add(resultSet.getLong(0));
        }
        connect().close();
        return arbeitsauftraege;
    }

    public boolean arbeitsauftragsnummerMatchesPartienummer(String partienummer, String arbeitsauftragsnummer) throws SQLException, ClassNotFoundException {
        Statement statement = null;
        statement = connect().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT SUBCONTRACTNO FROM _15053_mt2titan WHERE WORKORDERNO = '"+arbeitsauftragsnummer+"'");
        resultSet.next();

        if (partienummer.equals(resultSet.getString(1))) {
            connect().close();
            return true;
        } else {
            connect().close();
            return false;
        }
    }

    public String getPartienummer(String arbeitsauftragsnummer) throws SQLException, ClassNotFoundException {
        String partienummer;
        Statement statement = null;
        statement = connect().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT SUBCONTRACTNO FROM _15053_mt2titan WHERE WORKORDERNO = '"+arbeitsauftragsnummer+"'");
        resultSet.next();

        partienummer = resultSet.getString(1);
        connect().close();
        return partienummer;

    }


}
