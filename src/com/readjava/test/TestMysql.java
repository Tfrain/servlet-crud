package com.readjava.test;

import com.readjava.util.DBConnection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

//jdbc�������ݿ�,��ȡ�������ֶ���������
public class TestMysql {
    public static void main(String[] args) throws Exception {
        Connection conn = DBConnection.getFirstConnection();
        String dataBase = null;
        // ��ȡ���б���
        DatabaseMetaData dbm = conn.getMetaData();
        ResultSet rs = dbm.getTables(null, null, "%student", null);
        while(rs.next()){
            System.out.println(rs.getString(3)); //��ӡ����
            dataBase = rs.getString(3);
        }

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from " + dataBase);
        // ��ȡ����
        ResultSetMetaData metaData = resultSet.getMetaData();
        for (int i = 0; i < metaData.getColumnCount(); i++) {
            // resultSet�����±��1��ʼ
            String columnName = metaData.getColumnName(i + 1);
            System.out.print(columnName + "\t");
        }
        System.out.println();
        statement.close();
        conn.close();
    }


}
