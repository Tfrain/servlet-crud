package com.readjava.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.readjava.bean.Student;
import com.readjava.dao.NewStudentDao;
import com.readjava.dao.StudentDao;

import java.io.IOException;

@WebServlet("/add")
@SuppressWarnings("serial")
// doGet来接收一般消息
public class AddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Student student = new Student();
        String studentNumber = req.getParameter("studentNumber");
        String studentName = req.getParameter("studentName");
        String studentPassword = req.getParameter("studentPassword");
        int studentSex = Integer.parseInt(req.getParameter("studentSex"));
        int studentDatabase = Integer.parseInt(req.getParameter("studentDatabase"));

        student.setStudentNumber(studentNumber);
        student.setStudentName(studentName);
        student.setStudentPassword(studentPassword);
        student.setStudentSex(studentSex);

        student.setDatabase(studentDatabase);

        if(studentDatabase == 1) {
        	StudentDao studentDao = new StudentDao();
        	studentDao.addStudent(student);
        } else {
        	NewStudentDao newstudentDao = new NewStudentDao();
        	newstudentDao.addNewStudent(student);
        }
        
        
        req.getRequestDispatcher("/list").forward(req, resp);
    }
}
