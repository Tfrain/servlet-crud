package com.readjava.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection
{
    static String Driver="com.mysql.jdbc.Driver";
    static String First_Url="jdbc:mysql://localhost:3306/diai";//xml为数据源
    static String Name="root";
    static String Pwd="654321";

    static String Second_Url="jdbc:mysql://localhost:3306/diai1";//xml为数据源


    public static Connection getFirstConnection()
    {
        Connection conn=null;
        try
        {
            Class.forName(Driver);
            conn= DriverManager.getConnection(First_Url,Name,Pwd);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection getSecondConnection()
    {
        Connection conn=null;
        try
        {
            Class.forName(Driver);
            conn= DriverManager.getConnection(Second_Url,Name,Pwd);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return conn;
    }

    public ResultSet sqlFirstUser() throws SQLException{
        //getFirstConnection();
        String sql="select * from student";
        ResultSet rs=null;
        PreparedStatement ps=null;
        Connection conn=null;
        conn=DriverManager.getConnection(First_Url,Name,Pwd);
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery(sql);
        return rs;
    }

    public ResultSet sqlSecondUser() throws SQLException{
        //getSecondConnection();
        String sql="select * from newstudent";
        ResultSet rs=null;
        PreparedStatement ps=null;
        Connection conn=null;
        conn=DriverManager.getConnection(Second_Url,Name,Pwd);
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery(sql);
        return rs;
    }
}