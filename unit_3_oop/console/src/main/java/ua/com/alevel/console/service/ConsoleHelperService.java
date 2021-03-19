package ua.com.alevel.console.service;

import java.math.BigInteger;

public interface ConsoleHelperService {

    BigInteger readNumberAsString();

    String readString();

    void output(Object outputString);
}