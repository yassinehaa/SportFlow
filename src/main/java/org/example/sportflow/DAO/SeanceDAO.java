package org.example.sportflow.DAO;

import org.example.sportflow.DBC.ConnectionDb;

import java.sql.Connection;
import java.sql.SQLException;

public class SeanceDAO {
    private Connection con;
    public SeanceDAO() throws SQLException, ClassNotFoundException {
        con = ConnectionDb.getconnectiondb();
    }
}
