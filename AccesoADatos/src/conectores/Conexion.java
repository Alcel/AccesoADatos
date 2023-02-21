/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

/**
 *
 * @author isido
 */
public class Conexion {
      Connection con;
    public Conexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            /*
            Connection conn = DriverManager.getConnection( 
            "jdbc:mysql://localhost:3306/projects?user=user1&password=123");
            */
           /* con = DriverManager.getConnection( 
            "jdbc:mysql://localhost:3306/nba?user=root&password=04000122");*/
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto","root","root");
            System.out.println("conexion establecida");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error:" +e);
        }
    }
    public static void main(String[] args) {
        Conexion cn=new Conexion();
        
        Statement st;
        ResultSet rs;
        try {
            st=(Statement) cn.con.createStatement();
            rs=st.executeQuery("jdbc:sqlite:src/conectores/consultorio.SQLite.sql");
            while (rs.next()) {                
                System.out.println(rs.getString("CodigoSaga")+" " +
                        rs.getString("NombreSaga")+" "+rs.getInt("Cantidad")+" " +rs.getString("Creador"));
            }
            cn.con.close();
        } catch (Exception e) {System.out.print(e);
        }
      
    }
}
