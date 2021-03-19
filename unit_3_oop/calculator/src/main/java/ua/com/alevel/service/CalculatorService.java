package ua.com.alevel.service;

import java.math.BigInteger;

public interface CalculatorService {

    BigInteger sum(BigInteger firstComponent, BigInteger secondComponent);

    BigInteger subtract(BigInteger reducing, BigInteger subtracted);

    BigInteger multiply(BigInteger firstComponent, BigInteger secondComponent);

    BigInteger divide(BigInteger dividend, BigInteger divider);
}
