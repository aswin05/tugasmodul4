package com.users.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Barti
 */
public class koneksi {
    public static void main(String[] args){
        koneksi obj_koneksi = new koneksi();
        System.out.println(obj_koneksi.get_connection());}
    
public Connection get_connection(){    
    Connection connection = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tugasmodul4","kelompok24","kelompok24");
    } catch (Exception e) {
        System.out.println(e);}
    return connection;
}
}