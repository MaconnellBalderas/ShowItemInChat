package games.crusader.itemchat.utils;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class StringUtilTest {

    StringUtil stringUtil = new StringUtil();

    @Test
    public void split_nonMatchingDelimiter_returnsOriginalString(){
        String delimiter = "[item]";
        String text = "Hello there";
        assertEquals(Arrays.asList(text), stringUtil.split(delimiter, text));
    }

    @Test
    public void split_matchingDelimiter_returnsTwoStrings(){
        String delimiter = "[item]";
        String text = "Hello there [item]";
        List<String> expected = Arrays.asList("Hello there ", "[item]");
        assertEquals(expected, stringUtil.split(delimiter, text));
    }

    @Test
    public void split_matchingDelimiter_ReturnsThreeString(){
        String delimiter = "[item]";
        String text = "Hello there [item] BigMac";
        List<String> expected = Arrays.asList("Hello there ", "[item]", " BigMac");
        assertEquals(expected, stringUtil.split(delimiter, text));
    }

    @Test
    public void split_matchingDelimiter_ReturnsTwoInstances(){
        String delimiter = "[item]";
        String text = "one[item]two[item]three";
        List<String> expected = Arrays.asList("one","[item]","two","[item]","three");
        assertEquals(expected, stringUtil.split(delimiter, text));
    }

}