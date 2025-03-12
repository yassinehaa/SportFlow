package org.example.sportflow.DAO;

import org.example.sportflow.DBC.ConnectionDb;

import java.sql.Connection;
import java.sql.SQLException;

public class MembreDAO {
    private Connection con;
    public MembreDAO() throws SQLException, ClassNotFoundException {
        con = ConnectionDb.getconnectiondb();
    }
}
