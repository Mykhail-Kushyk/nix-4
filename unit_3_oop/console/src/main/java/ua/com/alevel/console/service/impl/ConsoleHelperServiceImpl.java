package ua.com.alevel.console.service.impl;

import ua.com.alevel.console.service.ConsoleHelperService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class ConsoleHelperServiceImpl implements ConsoleHelperService {

    public BigInteger readNumberAsString() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BigInteger number = new BigInteger("0");
        try {
            number = new BigInteger(reader.readLine().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return number;
    }

    public String readString() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String string = "";
        try {
            string = reader.readLine().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }

    public void output(Object outputString) {
        System.out.print(outputString);
    }
}