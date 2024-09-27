package xyz.block.example.impl;

import xyz.block.example.api.Calculator;

/**
 * Default implementation of the {@link Calculator}
 */
public class CalculatorImpl implements Calculator {

  public static Calculator INSTANCE = new CalculatorImpl();

  /**
   * No external instantiation
   */
  private CalculatorImpl(){ }

  @Override
  public int add(int a, int b) {
    int sum = a+b;
    return sum;
  }
}
