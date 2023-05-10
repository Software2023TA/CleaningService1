package com.example.demo1;

import java.io.IOException;

public class MyException extends Exception {
    public MyException (IOException errorMessage) {
        super(errorMessage);
    }
    public MyException (String errorMessage) {
        super(errorMessage);
    }
}
