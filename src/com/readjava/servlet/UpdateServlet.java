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

@WebServlet("/update")
@SuppressWarnings("serial")
//doGet仿佛是为了实现加载实现的，就是提前将数据库里面的东西提取出来，再转到jsp页面
public class UpdateServlet extends HttpServlet {
	//static int database = 0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    	int studentId = Integer.parseInt(req.getParameter("studentId"));
        
        // 先查看 student 的表里面有没有具体对应的数据
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.getStudentById(studentId);

        // 不设置一个构造函数默认属性，不能判断从数据库中取出的实体类对象是否为空，好像挺难办的
        //System.out.println(student.getStudentName().equals("name"));
        //通过判断第一个表的student是否为空来判断是否查询第二个表
        if(student.getStudentName().equals("name")) {
        	NewStudentDao newstudentDao = new NewStudentDao();
            student = newstudentDao.getNewStudentById(studentId);
            //database = 1;
        }
        
        req.setAttribute("student", student);
        req.getRequestDispatcher("update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        Student student = new Student();
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        String studentNumber = req.getParameter("studentNumber");
        String studentName = req.getParameter("studentName");
        String studentPassword = req.getParameter("studentPassword");
        int studentSex = Integer.parseInt(req.getParameter("studentSex"));
        int studentDatabase = Integer.parseInt(req.getParameter("studentDatabase"));

        student.setStudentId(studentId);
        student.setStudentNumber(studentNumber);
        student.setStudentName(studentName);
        student.setStudentPassword(studentPassword);
        student.setStudentSex(studentSex);
        student.setDatabase(studentDatabase);
        // 根据变量来确定更改哪一个数据库
        if(studentDatabase == 1) {
        	StudentDao studentDao = new StudentDao();
            studentDao.updateStudent(student);
        } else {
        	NewStudentDao newstudentDao = new NewStudentDao();
            newstudentDao.updateNewStudent(student);
        }
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }
}
