package com.cultivation.javaBasic.showYourIntelligence;

public class StackFrameHelper {
    public static String getCurrentMethodName() {
        // TODO: please modify the following code to pass the test
        // <--start
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        return  stackTrace[1].getClassName() + "." + stackTrace[1].getMethodName();
        // --end-->
    }
}
