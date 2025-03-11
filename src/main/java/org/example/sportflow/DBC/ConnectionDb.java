package org.example.sportflow.DBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDb{

public static Connection getconnectiondb() throws SQLException, ClassNotFoundException {



    Class.forName("com.mysql.cj.jdbc.Driver");

    Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/SportFlow","root","");

    Statement stm=connection.createStatement();

    String Membretable = "CREATE TABLE IF NOT EXISTS member(" +
            "id INT PRIMARY KEY AUTO_INCREMENT," +
            " name VARCHAR(255)," +
            " datdenaissance DATE(90)," +
            "sport VARCHAR(55))";
    stm.execute(Membretable);

    String Entraineurtable = "CREATE TABLE IF NOT EXISTS entraineur("+
            "id INT PRIMARY KEY AUTO_INCREMENT," +
            "nom VARCHAR (20)"+
            "specialite VARCHAR(60))";
    stm.execute(Entraineurtable);

    String Seancetable="CREATE TABLE IF NOT EXISTS seance("+
            "id INT PRIMARY KEY AUTO_INCREMENT,"+
            "idMember INT (10)"+
            "idEntraneur INT (10)"+
            "dateetheure DATETIME (90)"+
            "FOREIGN KEY (idMember) REFERENCES member(id) "+
            "FOREIGN KEY (idEntraneur) REFERENCES entraineur(id) )";
    stm.execute(Seancetable);
    return connection;
    }
}