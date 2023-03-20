package ro.advancetesting;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class CalculatorTest {

    private  Calculator calculator;
   @BeforeEach
   void setUp(){
       calculator = new Calculator();
   }

   @BeforeAll
   static void init(){
       System.out.println("First log statement");
   }

   @AfterAll
   static void finish(){
       System.out.println("Last log statement");
   }

    @Test
    @DisplayName("Test the multiply method")
    //@RepeatedTest(5)
    void multiplyTest(){
        //given
        int a = 10;
        int b = 10;
        int expectedResult = 100;
        //when
        var actualResult = calculator.multiply(a, b);
        //then
        Assertions.assertEquals(actualResult, expectedResult);
    }

    @Test
    void testDivide(){

        Assertions.assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    }

    @ParameterizedTest
    @MethodSource("provideNumbers")
    void isOddTest(int input, boolean expected){ // ia inputul si ce rezultate se asteapta sa fie

       var result = calculator.isOdd(input);

       Assertions.assertEquals(result, expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"ana", "are", "mere"})
    void checkNotNull(String word){

       Assertions.assertNotNull(word);
    }

    // testele dinamice nu tin cint de beforeeach, beforeall si restull
    @TestFactory
    Stream<DynamicTest> checkDynamicTest(){
       int[][] data = date();
       return Arrays.stream(data).map(element -> {
           int arg1 = element[0];
           int arg2 = element[1];
           int result = element[2];

            return dynamicTest(arg1 + " * " + arg2 + " = " + result, () -> {
                Assertions.assertEquals(result, calculator.multiply(arg1,arg2));
            });
        });
    }

    @ParameterizedTest
    @MethodSource("date")
    void testParams(int[] input){

       Assertions.assertEquals(input[2], calculator.multiply(input[0], input[1]));
    }

    private static Stream<Arguments> provideNumbers(){
        return Stream.of(
                Arguments.of(5, true),
                Arguments.of(3, true),
                Arguments.of(2, false));
    }

    private static int[][] date(){ // primul argument, al doilea argument si al treilea e rezultatul pe care l astept
       return new int[][]{{1, 2, 2}, {3, 4, 12}, {1, 10, 10}};
    }
}
