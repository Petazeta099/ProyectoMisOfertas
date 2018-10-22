/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.*;

/**
 *
 * @author Raul
 */
public class Database {
        
    private static String db="bd_misofertas";
    private static String user="root";
    private static String pass="";
    private static String host="localhost:3306";
    private static String server="jdbc:mysql://"+host+"/"+db;
    Connection cn = null;
    
    public Database(){
        try{
//        Class.forName("com.mysql.jdbc.Driver");
//        cn = DriverManager.getConnection(server,user,pass);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            cn = DriverManager.getConnection("jdbc:oracle:thin:@misofertas.cdmhdbuxofjj.us-east-2.rds.amazonaws.com:1521:XE","misofertas","misofertas");
        if (cn!=null){
            System.out.println("Conexión a base de datos "+db+" OK\n");
        }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public Connection getConnection(){
        return cn;
    }

    public void desconectar(){
        cn = null;
    }
    
    public static Connection getConexion(){
        Connection cn=null;
        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            cn=DriverManager.getConnection(server,user,pass);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            cn = DriverManager.getConnection("jdbc:oracle:thin:@misofertas.cdmhdbuxofjj.us-east-2.rds.amazonaws.com:1521:XE","misofertas","misofertas");
            System.out.println("Conexión");
        }
        catch(Exception e){
            System.out.println(String.valueOf(e));
        }
        return cn;        
    }
    
    public static ResultSet crearConsulta(String Consulta){
        Connection cn=getConexion();
        Statement st;
        ResultSet lista=null;
        try{
            st=cn.createStatement();
            lista=st.executeQuery(Consulta);
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        return lista;
    }
    
}