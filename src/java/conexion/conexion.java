/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
public class conexion {
 private Statement St;
    private String driver;
    private String user;
    private String pwd;
    private String cadena;
    private Connection con;

    String getDriver() {
        return this.driver;
    }

    String getUser() {
        return this.user;
    }

    String getPwd() {
        return this.pwd;
    }

    String getCadena() {
        return this.cadena;
    }

    public Connection getConexion() {
        return this.con;
    }

    public conexion() {

        this.driver = "org.postgresql.Driver";
        this.user = "postgres";
        this.pwd = "postgres2020";
        this.cadena = "jdbc:postgresql://172.17.42.227:5432/DIMENSIONAL_AMIE";
        this.con = this.crearConexion();

    }
    
    public int loguear (String us, String pass){
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        int tipo= 0;
        String sql ="select id_tipo_person from persona where correo='" + us + "' and clave='"+ pass + "'";
        try {
            Class.forName(this.driver);
            conn = DriverManager.getConnection(
                   this.cadena, 
                   this.user, 
                   this.pwd);
            pst= conn.prepareStatement(sql);
            rs= pst.executeQuery();
            while (rs.next()){
                tipo = rs.getInt(1); 
                System.out.println(tipo);
            }
            conn.close();
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return tipo;
    }
    
    
    
    

    Connection crearConexion() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {

        }

        try {
            Class.forName(getDriver()).newInstance();
            Connection con = DriverManager.getConnection(getCadena(), getUser(), getPwd());
            return con;
        } catch (Exception ee) {
            System.out.println("Error: " + ee.getMessage());
            return null;
        }
    }

    public String Ejecutar(String sql) {
        
        String error = "";
        try {
            St = getConexion().createStatement();
            St.execute(sql);
            error = "Datos insertados";
            St.close();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        return (error);
    }

    public ResultSet Consulta(String sql) {
        String error = "";
        ResultSet reg = null;

        try {
            St = getConexion().createStatement();
            reg = St.executeQuery(sql);

        } catch (Exception ee) {
            error = ee.getMessage();
        }
        return (reg);
    }

    public void cerrarConexion() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
