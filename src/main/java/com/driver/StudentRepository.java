package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository{
    private final HashMap<String, Student> studentHashMap;

    private final HashMap<String, Teacher> teacherHashMap;

    private final HashMap<String, List<String>> studentTeacherPair;

    public StudentRepository() {
        this.studentHashMap = new HashMap<>();
        this.teacherHashMap = new HashMap<>();
        this.studentTeacherPair = new HashMap<>();
    }

    public void addStudent(Student student){
        studentHashMap.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher){
        teacherHashMap.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String studentName, String teacherName){
        if(studentHashMap.containsKey(studentName) && teacherHashMap.containsKey(teacherName)){
            List<String>teacherStudents = new ArrayList<>();

            if(studentTeacherPair.containsKey(teacherName)){
                teacherStudents = studentTeacherPair.get(teacherName);
            }
            teacherStudents.add(studentName);
            studentTeacherPair.put(teacherName, teacherStudents);
        }
    }

    public Student getStudentByName(String studentName){
        return studentHashMap.get(studentName);
    }

    public Teacher getTeacherByName(String teacherName){
        return teacherHashMap.get(teacherName);
    }

    public List<String> getStudentsByTeacher(String teacherName){
        List<String>teacherStudents = new ArrayList<>();
        if(studentTeacherPair.containsKey(teacherName)){
            teacherStudents = studentTeacherPair.get(teacherName);
        }
        return teacherStudents;
    }

    public List<String> getAllStudents(){
        return new ArrayList<>(studentHashMap.keySet());
    }

    public void deleteTeacherByName(String teacherName){
        List<String>teacherStudents;
        if(studentTeacherPair.containsKey(teacherName)){
            teacherStudents = studentTeacherPair.get(teacherName);

            for(String studentName : teacherStudents){
                studentHashMap.remove(studentName);
            }

            studentTeacherPair.remove(teacherName);
            teacherHashMap.remove(teacherName);
        }
    }

    public void deleteAllTeachers(){
        List<String>teacherStudents = new ArrayList<>();
        for(String teacher: studentTeacherPair.keySet()){
            teacherStudents.addAll(studentTeacherPair.get(teacher));
        }

        for(String student : teacherStudents)
            studentHashMap.remove(student);
    }
}
