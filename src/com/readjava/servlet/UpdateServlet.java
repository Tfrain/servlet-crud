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
//doGet�·���Ϊ��ʵ�ּ���ʵ�ֵģ�������ǰ�����ݿ�����Ķ�����ȡ��������ת��jspҳ��
public class UpdateServlet extends HttpServlet {
	//static int database = 0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    	int studentId = Integer.parseInt(req.getParameter("studentId"));
        
        // �Ȳ鿴 student �ı�������û�о����Ӧ������
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.getStudentById(studentId);

        // ������һ�����캯��Ĭ�����ԣ������жϴ����ݿ���ȡ����ʵ��������Ƿ�Ϊ�գ�����ͦ�Ѱ��
        //System.out.println(student.getStudentName().equals("name"));
        //ͨ���жϵ�һ�����student�Ƿ�Ϊ�����ж��Ƿ��ѯ�ڶ�����
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
        // ���ݱ�����ȷ��������һ�����ݿ�
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
