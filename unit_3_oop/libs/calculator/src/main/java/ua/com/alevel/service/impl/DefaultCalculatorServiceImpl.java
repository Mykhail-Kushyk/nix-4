package ua.com.alevel.service.impl;

import ua.com.alevel.service.CalculatorService;

import java.math.BigInteger;

public class DefaultCalculatorServiceImpl implements CalculatorService {
    @Override
    public BigInteger sum(BigInteger firstComponent, BigInteger secondComponent) {
        return firstComponent.add(secondComponent);
    }

    @Override
    public BigInteger subtract(BigInteger reducing, BigInteger subtracted) {
        return reducing.subtract(subtracted);
    }

    @Override
    public BigInteger multiply(BigInteger firstComponent, BigInteger secondComponent) {
        return firstComponent.multiply(secondComponent);
    }

    @Override
    public BigInteger divide(BigInteger dividend, BigInteger divider) {
        return dividend.divide(divider);
    }
}