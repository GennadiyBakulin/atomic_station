package com.javaacademy.nuclearstation.reactordepartment.exception;

/**
 * Класс пользовательское исключение - ReactorWorkException.
 */
public class ReactorWorkException extends Exception {

  /**
   * Конструктор класса, вызывает внутри себя конструктор супер класса.
   *
   * @param message - сообщение об ошибке.
   */
  public ReactorWorkException(String message) {
    super(message);
  }
}
