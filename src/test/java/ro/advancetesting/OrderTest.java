package ro.advancetesting;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)   //ca sa ne customizam ordinea
public class OrderTest {

    @Test
    @Order(1) // punem ordinea pe care o vrem
    void firstOne(){
        System.out.println("firstOne");
    }
    @Test
    @Order(2)
    void secondOne(){
        System.out.println("secondOne");
    }
    @Test
    @Order(3)
    void thirdOne(){
        System.out.println("thirdOne");
    }
}
