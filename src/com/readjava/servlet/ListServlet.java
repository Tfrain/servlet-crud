package com.readjava.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.readjava.bean.Student;
import com.readjava.dao.NewStudentDao;
import com.readjava.dao.StudentDao;
import com.readjava.util.Queryer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/list")
@SuppressWarnings("serial")
public class ListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Queryer queryer = new Queryer();
        List<Student> newStudentList = queryer.selectAllStudent();
        // StudentDao studentDao = new StudentDao();
        // NewStudentDao newstudentDao = new NewStudentDao();
        // List<Student> newStudentList = newstudentDao.selectNewStudent();
        // List<Student> studentList = studentDao.selectStudent();

        req.setAttribute("studentList", newStudentList);
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }
}
