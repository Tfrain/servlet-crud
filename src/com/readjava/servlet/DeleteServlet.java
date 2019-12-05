package com.readjava.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.readjava.dao.NewStudentDao;
import com.readjava.dao.StudentDao;

import java.io.IOException;

@WebServlet("/delete")
@SuppressWarnings("serial")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        StudentDao studentDao = new StudentDao();
        studentDao.deleteStudent(studentId);

        NewStudentDao newstudentDao = new NewStudentDao();
        newstudentDao.deleteNewStudent(studentId);
        req.getRequestDispatcher("/list").forward(req, resp);
    }
}