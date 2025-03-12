package org.example.sportflow.DAO;

import org.example.sportflow.DBC.ConnectionDb;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDAO {
    private Connection con;
    public UserDAO() throws SQLException, ClassNotFoundException {
        con = ConnectionDb.getconnectiondb();
    }



}
