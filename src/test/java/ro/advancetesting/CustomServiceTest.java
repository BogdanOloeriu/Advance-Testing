package ro.advancetesting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import ro.advancetesting.service.CustomService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomServiceTest {


    @InjectMocks
    private CustomService customService;

    @Test
    void accessPrivateMethodTest(){

       //a doua e numele metodei private
        Assertions.assertEquals("private Message", ReflectionTestUtils.invokeMethod(customService, "privateMessage"));
    }

    @Test
    void accessPrivateField(){
        ReflectionTestUtils.setField(customService, "name", "myValue");
        Assertions.assertTrue(Objects.equals(ReflectionTestUtils.getField(customService, "name"), "myValue"));
    }


    @Test
    void createMock(){

        List mockList = mock(ArrayList.class); //aici mocuim lista, nu folosim lista in sine

        mockList.add("value");
        verify(mockList).add("value");

        Assertions.assertEquals(0, mockList.size());
    }

    @Test
    void createSpy(){
        List spyList = spy(ArrayList.class); //spylist incrementeaza lista

        spyList.add("value");
        verify(spyList).add("value");

        Assertions.assertEquals(1, spyList.size());
    }

}
