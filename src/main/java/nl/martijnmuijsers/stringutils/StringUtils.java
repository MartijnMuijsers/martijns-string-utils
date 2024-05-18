/*
 * © Martijn Muijsers <martijnmuijsers@live.nl> 2018-2024.
 * Licensed under AGPLv3.
 */
package nl.martijnmuijsers.stringutils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

    public String remove(String str, char c) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char strCharacter : str.toCharArray()) {
            if (strCharacter != c) {
                stringBuilder.append(strCharacter);
            }
        }
        return stringBuilder.toString();
    }

    public int reverseIndexOf(String str, char c, int from) {
        for (int i = from; i >= 0; i--) {
            if (str.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }

    public int reverseIndexOf(String str, char c) {
        return reverseIndexOf(str, c, str.length() - 1);
    }

    public String replaceCharacter(String text, int index, char character) {
        char[] charArray = text.toCharArray();
        charArray[index] = character;
        return new String(charArray);
    }

    public String concatenate(List<String> strings) {
        return concatenate(strings, "");
    }

    public String concatenate(List<String> strings, String delimiter) {
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (String string : strings) {
            if (first) {
                first = false;
            } else {
                builder.append(delimiter);
            }
            builder = builder.append(string);
        }
        return builder.toString();
    }

    public String safeSubstring(String text, int start, int end) {
        int usedStart = start;
        if (usedStart < 0) {
            usedStart = 0;
        }
        int usedEnd = end;
        if (usedEnd > text.length()) {
            usedEnd = text.length();
        }
        if (usedEnd <= usedStart) {
            return "";
        }
        return text.substring(usedStart, usedEnd);
    }

    public String safeSubstring(String text, int start) {
        return safeSubstring(text, start, text.length());
    }

    public List<String> removeEmptyParts(List<String> list) {
        return list.stream().filter(text -> !text.isEmpty()).collect(Collectors.toList());
    }

    public List<String> splitIntoListSkippingEmptyParts(String text, String delimiter, String opener, String closer) {
        return removeEmptyParts(splitIntoListKeepingEmptyParts(text, delimiter, opener, closer));
    }

    public List<String> splitIntoListKeepingEmptyParts(String text, String delimiter, String opener, String closer) {
        List<String> list = new ArrayList<>();
        int index = 0;
        while (index <= text.length()) {
            int nextIndex = index;
            int depth = 0;
            while (true) {
                if (nextIndex == text.length()) {
                    break;
                }
                if (depth == 0 && text.startsWith(delimiter, nextIndex)) {
                    break;
                }
                if (text.startsWith(opener, nextIndex)) {
                    depth++;
                    nextIndex += opener.length();
                } else if (depth > 0 && text.startsWith(closer, nextIndex)) {
                    depth--;
                    nextIndex += closer.length();
                } else {
                    nextIndex++;
                }
            }
            list.add(safeSubstring(text, index, nextIndex));
            index = nextIndex + delimiter.length();
        }
        return list;
    }

    public List<String> splitIntoListSkippingEmptyParts(String text, String delimiter) {
        return removeEmptyParts(splitIntoListKeepingEmptyParts(text, delimiter));
    }

    public List<String> splitIntoListKeepingEmptyParts(String text, String delimiter) {
        List<String> list = new ArrayList<>();
        int index = 0;
        while (index <= text.length()) {
            int nextIndex = text.indexOf(delimiter, index);
            if (nextIndex == -1) {
                nextIndex = text.length();
            }
            list.add(safeSubstring(text, index, nextIndex));
            index = nextIndex + delimiter.length();
        }
        return list;
    }
}
