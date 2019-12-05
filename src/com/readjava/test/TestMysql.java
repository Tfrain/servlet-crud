package com.readjava.test;

import com.readjava.util.DBConnection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

//jdbc链接数据库,获取表名，字段名和数据
public class TestMysql {
    public static void main(String[] args) throws Exception {
        Connection conn = DBConnection.getFirstConnection();
        String dataBase = null;
        // 获取所有表名
        DatabaseMetaData dbm = conn.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "%student", null);
        while(rs.next()){
            System.out.println(rs.getString(3)); //打印表名
            dataBase = rs.getString(3);
        }

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from " + dataBase);
        // 获取列名
        ResultSetMetaData metaData = resultSet.getMetaData();
        for (int i = 0; i < metaData.getColumnCount(); i++) {
            // resultSet数据下标从1开始
            String columnName = metaData.getColumnName(i + 1);
            System.out.print(columnName + "\t");
        }
        System.out.println();
        statement.close();
        conn.close();
    }


}
