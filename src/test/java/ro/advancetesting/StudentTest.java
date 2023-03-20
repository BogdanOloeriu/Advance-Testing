package ro.advancetesting;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.advancetesting.entity.Student;
import ro.advancetesting.exception.StudentNotFoundException;
import ro.advancetesting.repositoy.StudentRepository;
import ro.advancetesting.service.StudentService;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //ca sa folosimt Mockito
public class StudentTest {


   @Mock
    private StudentRepository studentRepository;
   @InjectMocks// se folseste cand injectam anumite dependita
    private StudentService studentService;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    private Student mockStudent(){
        return new Student(
                "10",
                "Ana",
                25 ,
                "ana@gmail.com");
    }
    @Test
    void getStudentByIdTest() {

        when(studentRepository.getStudentById("10")).thenReturn(mockStudent());

        Student resultStudent = studentService.findStudentById("10");

        Assertions.assertEquals(mockStudent(), resultStudent);
    }

    @Test
    void shouldReturnAnExceptionWhenStudentWasNotFound(){

        when(studentRepository.getStudentById("10")).thenThrow(StudentNotFoundException.class);

        Assertions.assertThrows(StudentNotFoundException.class, () -> studentRepository.getStudentById("10"));
    }

    @Test
    void addStudentTest(){
        Student testStudent = mockStudent();

        when(studentRepository.addStudent(testStudent)).thenReturn(testStudent.getId());

        Assertions.assertEquals(testStudent.getId(), studentRepository.addStudent(testStudent));
    }

    @Test
    void deleteStudentTest(){
        Student testStudent = mockStudent();
        studentService.deleteStudent(testStudent);
        verify(studentRepository, times(1)).deleteStudent(testStudent);
    }
}
