package com.readjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.readjava.bean.Student;
import com.readjava.util.DBConnection;
import com.readjava.xml.GetXML;

public class StudentDao {

    /**
     * 列出所有学生信息
     *
     * @return
     */

    List result = GetXML.get();
    String[] tm = (String[] ) result.get(0);
    Connection conn = DBConnection.getFirstConnection();
    String dataBase = GetXML.getDataBase(conn);

    public List<Student> selectStudent() {
        List<Student> studentList = new ArrayList<>();

        String sql = "select * from " + dataBase;
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                Student student = new Student();
                studentList.add(getStudentData(student,rst));
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    /**
     * 添加学生
     *
     * @param student
     * @return
     */
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO  " + dataBase + "("+tm[2]+","+tm[3]+","+tm[4]+","+tm[1]+","+tm[5]+") VALUES(?,?,?,?,?);";
        // Connection conn = DBConnection.getFirstConnection();
        return getConnect(conn,sql,student,1);
    }

    /**
     * 更新学生信息
     *
     * @param student
     * @return
     */
    public boolean updateStudent(Student student) {
        String sql = "UPDATE " + dataBase + " set " + tm[2]+ "=?,"+tm[3]+"=?,"+tm[4]+"=?,"+tm[1]+"=? WHERE "+tm[0]+"=?";
        // Connection conn = DBConnection.getFirstConnection();
        return getConnect(conn,sql,student,2);
    }

    /**
     * 根据学生的主键来删除学生
     *
     * @param studentId
     * @return
     */
    public boolean deleteStudent(int studentId) {
        String sql = "delete from " + dataBase + " where "+ tm[0] +" = ?";
        // Connection conn = DBConnection.getFirstConnection();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, studentId);
            int count = pst.executeUpdate();
            pst.close();
            return count > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 通过学生主键来查询学生
     *
     * @param studentId
     * @return
     */
    public Student getStudentById(int studentId) {
        // Connection conn = DBConnection.getFirstConnection();
        String sql = "select * from " + dataBase + " where "+ tm[0]+ "="+studentId;
        Student student = new Student();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                getStudentData(student,rst);
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public Student getStudentData(Student student,ResultSet rst) {
        try {
            student.setStudentId(rst.getInt(tm[0]));
            student.setStudentName(rst.getString(tm[2]));
            student.setStudentPassword(rst.getString(tm[3]));
            student.setStudentSex(rst.getInt(tm[4]));
            student.setStudentNumber(rst.getString(tm[1]));
            student.setDatabase(rst.getInt(tm[5]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    public boolean getConnect(Connection conn,String sql,Student student,int data){
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, student.getStudentName());
            pst.setString(2, student.getStudentPassword());
            pst.setInt(3, student.getStudentSex());
            pst.setString(4, student.getStudentNumber());
            if (data == 1) {
                pst.setInt(5, student.getDatabase());
            }
            else{
                pst.setInt(5, student.getStudentId());
            }
            int count = pst.executeUpdate();
            pst.close();
            return count > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
