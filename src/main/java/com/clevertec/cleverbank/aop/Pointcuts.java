package com.clevertec.cleverbank.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Этот класс определяет точки входа для AOP в приложении CleverBank.
 */
public class Pointcuts {
    /**
     * Выражение точки входа для сервисных методов.
     * Оно охватывает все методы в классах в пакете "com.clevertec.cleverbank.services".
     */
    @Pointcut("execution(* com.clevertec.cleverbank.services.*.*(..))")
    public void serviceMethods() {}
}
