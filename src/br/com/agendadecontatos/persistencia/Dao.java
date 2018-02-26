package br.com.agendadecontatos.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Dao {

    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    
    public void abrirDB()throws Exception{
        
        Class.forName("com.mysql.jdbc.Driver");
        String url = "Jdbc:mysql://localhost:3306/agenda_contatos";
        String user = "root";
        String password = "root";
        
        conn = DriverManager.getConnection(url, user, password);
    }
    
    public void fecharDB()throws Exception{
        
        conn.close();
    }
}
