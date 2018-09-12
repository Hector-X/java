package com.cultivation.javaBasicExtended.wordProcessor;

import java.util.ArrayList;
import java.util.List;

class TextProcessor {
    public static final String ERROR_INVALID_CHARACTER = "ERROR: Invalid character detected!";
    public static final String ERROR_WIDTH_OUT_OF_RANGE = "Width out of range.";
    public static final int MIN_WIDTH = 10;
    public static final int MAX_WIDTH = 80;

    private final TextProcessorSettings settings;
    private final TextProcessImp textProcessImp;

    TextProcessor(int width) {
        this(width, null);
    }

    TextProcessor(int width, char[] whitespaces) {
        if (width < MIN_WIDTH || width > MAX_WIDTH) {
            throw new IllegalArgumentException(ERROR_WIDTH_OUT_OF_RANGE);
        }
        textProcessImp = new TextProcessImp();
        settings = new TextProcessorSettings(width, getWhitespaces(whitespaces));
    }

    private char[] getWhitespaces(char[] whitespaces) {
        return whitespaces == null ?
            new char[] {' '} :
            whitespaces;
    }

    String process(String text) {
        // TODO: Please implement the method to pass all the test
        // <--start
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException();
        }
        textProcessImp.isLetterBefore = settings.isValidLetter(text.charAt(0));
        List<Character> inputList = transformString2CharacterList(text);
        inputList.forEach(textProcessImp::processImp);
        textProcessImp.addLineNumberComment();
        return textProcessImp.getProcessResult();

        // --end-->
    }


    private List<Character> transformString2CharacterList(String text) {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < text.length(); ++i) {
            char charAtIndex = text.charAt(i);
            if (!settings.isValidLetter(charAtIndex) && !settings.isWhitespace(charAtIndex)) {
                throw new IllegalArgumentException(ERROR_INVALID_CHARACTER);
            }
            characters.add(charAtIndex);
        }
        return characters;
    }

    class TextProcessImp {
        static final String LINE_INDEX_END = ");";
        static final String LINE_INDEX_START = "(";
        static final String LINE_INDEX_SPILT = ",";

        private boolean isLetterBefore;

        private StringBuilder resultBuilder;

        private int indexOfLine = 1;

        private int indexOfWidth;

        private ArrayList<Integer> lineIndexOfOneSegment;

        public TextProcessImp() {
            this.lineIndexOfOneSegment = new ArrayList<>();
            resultBuilder = new StringBuilder();
        }

        private void processImp(Character character) {
            indexOfWidth++;
            checkSegmentSameOrAddLineNumbers(character);
            resultBuilder.append(character.toString());
            updateStatusInfo();
        }

        private void checkSegmentSameOrAddLineNumbers(Character character) {
            boolean isLetterNow = settings.isValidLetter(character);
            if (!isLetterNow == isLetterBefore) {
                addLineNumberComment();
            }
            isLetterBefore = isLetterNow;
        }

        private String getProcessResult() {
            String result = resultBuilder.toString();
            resultBuilder.delete(0, resultBuilder.length());
            return result;
        }

        private void updateStatusInfo() {
            lineIndexOfOneSegment.add(indexOfLine);
            if (indexOfWidth / settings.getWidth() > 0) {
                indexOfWidth %= settings.getWidth();
                indexOfLine++;
            }
        }

        private void addLineNumberComment() {

            resultBuilder.append(LINE_INDEX_START);
            lineIndexOfOneSegment.stream().distinct().forEach(integer -> resultBuilder.append(integer).append(LINE_INDEX_SPILT));
            resultBuilder.deleteCharAt(resultBuilder.length() - 1);
            resultBuilder.append(LINE_INDEX_END);
            lineIndexOfOneSegment.clear();
        }

    }


}

