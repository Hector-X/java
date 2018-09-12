package com.cultivation.javaBasicExtended.wordProcessor;

import java.util.ArrayList;
import java.util.List;

class TextProcessor {
    public static final String ERROR_INVALID_CHARACTER = "ERROR: Invalid character detected!";
    public static final String ERROR_WIDTH_OUT_OF_RANGE = "Width out of range.";
    public static final int MIN_WIDTH = 10;
    public static final int MAX_WIDTH = 80;
    private final TextProcessorSettings settings;
    private TextProcessInfo textProcessInfo;

    TextProcessor(int width) {
        this(width, null);
    }

    TextProcessor(int width, char[] whitespaces) {
        if (width < MIN_WIDTH || width > MAX_WIDTH) {
            throw new IllegalArgumentException(ERROR_WIDTH_OUT_OF_RANGE);
        }
        textProcessInfo = new TextProcessInfo();
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
        textProcessInfo.isLetterBefore = settings.isValidLetter(text.charAt(0));
        List<Character> inputList = transformString2CharacterList(text);
        inputList.forEach(character -> {
            textProcessInfo.processImp(character);
        });
        textProcessInfo.addLineNumberComment();
        return textProcessInfo.getProcessResult();

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

    class TextProcessInfo {
        static final String LINE_INDEX_END = ");";
        static final String LINE_INDEX_START = "(";
        static final String LINE_INDEX_SPILT = ",";
        private boolean isLetterBefore;
        private StringBuilder resultBuilder;

        private int indexOfLine;

        private int indexOfWidth;

        private ArrayList<Integer> lineIndexOfOneSegment;

        public TextProcessInfo() {
            this.isLetterBefore = false;
            this.indexOfLine = 1;
            this.indexOfWidth = 0;
            this.lineIndexOfOneSegment = new ArrayList<>();
            resultBuilder = new StringBuilder();
        }

        private void processImp(Character character) {
            indexOfWidth++;
            boolean isLetterNow = settings.isValidLetter(character);
            if (!isLetterNow == isLetterBefore) {
                addLineNumberComment();
            }
            resultBuilder.append(character.toString());
            updateInfo(isLetterNow);
        }

        private String getProcessResult() {
            String result = resultBuilder.toString();
            resultBuilder.delete(0, resultBuilder.length());
            return result;
        }

        private void updateInfo(boolean isLetterNow) {
            isLetterBefore = isLetterNow;

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

