package com.cultivation.javaBasicExtended.reverseString;

class StringReverser {
    @SuppressWarnings({"WeakerAccess", "unused"})
    public static String[] reverse(String input) {
        // TODO: please implement the method to pass all the tests.
        // <--start
        if (input == null) {
            throw new IllegalArgumentException();
        }

        String inputAfterProcess = input.trim();
        if (inputAfterProcess.isEmpty()) return new String[0];

        String[] reverseResult = inputAfterProcess.split(" ");

        if (reverseResult.length < 2) return reverseResult;

        for (int index = 0; index < reverseResult.length / 2; index++) {
            String temp = reverseResult[index];
            reverseResult[index] = reverseResult[reverseResult.length - 1 - index];
            reverseResult[reverseResult.length - 1 - index] = temp;
        }
        return reverseResult;
        // --end-->
    }
}
