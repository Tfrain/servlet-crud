package com.readjava.util;

import com.readjava.bean.Student;
import com.readjava.dao.NewStudentDao;
import com.readjava.dao.StudentDao;
import java.util.List;

// ²éÑ¯Æ÷
public class Queryer {

    public List<Student> selectAllStudent() {
        StudentDao studentDao = new StudentDao();
        NewStudentDao newstudentDao = new NewStudentDao();
        List<Student> newStudentList = newstudentDao.selectNewStudent();
        List<Student> studentList = studentDao.selectStudent();
        studentList.addAll(newStudentList);
        return studentList;
    }



}
