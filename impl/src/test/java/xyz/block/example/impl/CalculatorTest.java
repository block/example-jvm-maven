package xyz.block.example.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import xyz.block.example.api.Calculator;

public class CalculatorTest {

  private static final Calculator calculator = CalculatorImpl.INSTANCE;

  @Test
  public void addPositiveInts() {
    int a = 40;
    int b = 2;
    assertAdd(a, b);
  }

  @Test
  public void addNegativeInt() {
    int a = 50;
    int b = -8;
    assertAdd(a, b);
  }

  void assertAdd(int a, int b){
    int expectedValue = a + b;
    int actualValue = calculator.add(a, b);
    System.out.println("Adding " + a + "+" + b + " expected: " + expectedValue + "; got: " + actualValue);
    Assertions.assertEquals(expectedValue,actualValue);
  }
}
