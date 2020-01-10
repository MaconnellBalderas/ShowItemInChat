package games.crusader.itemchat.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

    public static List<String> split(String delimiter, String text) {
        List<String> result = new ArrayList<>();
        StringBuilder vanillaText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            if (i + delimiter.length() - 1 < text.length()) {
                int start = i;
                int end = i + delimiter.length();
                String subString = text.substring(start, end);
                if (subString.equals(delimiter)) {
                    result.add(vanillaText.toString());
                    result.add(delimiter);
                    vanillaText = new StringBuilder();
                    i += delimiter.length();
                }
            }

            if (i < text.length()) {
                vanillaText.append(text.charAt(i));
            }

        }

        if (vanillaText.length() > 0) {
            result.add(vanillaText.toString());
        }
        return result;
    }
}
