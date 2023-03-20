package ro.advancetesting.repositoy;

import org.springframework.stereotype.Repository;
import ro.advancetesting.entity.Student;
import ro.advancetesting.exception.StudentNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class StudentRepository {

    private final List<Student> studentList = new ArrayList<>();

    public List<Student> findAll(){
        return studentList;
    }

    public String addStudent(Student student){
        studentList.add(student);
        return student.getId();
    }

    public void deleteStudent(Student student){
        studentList.remove(student);
    }

    public Student getStudentById(String id){
        return studentList
                .stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException("Student not found for Id: " + id));
    }

    public void updateStudent(Student student){

        Student foundStudent = getStudentById(student.getId());
        deleteStudent(student);
        studentList.add(student);
    }
}
