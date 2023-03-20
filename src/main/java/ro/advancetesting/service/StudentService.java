package ro.advancetesting.service;


import org.springframework.stereotype.Service;
import ro.advancetesting.entity.Student;
import ro.advancetesting.repositoy.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }

    public Student findStudentById(String id){
        return studentRepository.getStudentById(id);
    }

    public void deleteStudent(Student student){
        studentRepository.deleteStudent(student);
    }

    public void updateStudent(Student student){
        studentRepository.updateStudent(student);
    }

    public String saveStudent(Student student){
        return studentRepository.addStudent(student);
    }

}
