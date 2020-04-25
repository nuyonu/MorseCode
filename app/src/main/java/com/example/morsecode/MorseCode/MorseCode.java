package com.example.morsecode.MorseCode;

import java.util.HashMap;
import java.util.Map;

public class MorseCode
{
    public static Map<String, String> morseCode;
    public static Map<String, String> morseCodeReversed;
    static {
        morseCode = new HashMap<>();
        morseCode.put("a", ".-");
        morseCode.put("b", "-...");
        morseCode.put("c", "-.-");
        morseCode.put("d", "-..");
        morseCode.put("e", ".");
        morseCode.put("f", "..-.");
        morseCode.put("g", "--.");
        morseCode.put("h", "....");
        morseCode.put("i", "..");
        morseCode.put("j", ".---");
        morseCode.put("k", "-.");
        morseCode.put("l", ".-..");
        morseCode.put("m", "--");
        morseCode.put("n", "-.");
        morseCode.put("o", "---");
        morseCode.put("p", ".--.");
        morseCode.put("q", "--.-");
        morseCode.put("r", ".-.");
        morseCode.put("s", "...");
        morseCode.put("t", "-");
        morseCode.put("u", "..-");
        morseCode.put("v", "...-");
        morseCode.put("w", ".--");
        morseCode.put("x", "-..-");
        morseCode.put("y", "-.--");
        morseCode.put("z", "--..");
        morseCode.put("1", ".----");
        morseCode.put("2", "..---");
        morseCode.put("3", "...--");
        morseCode.put("4", "....-");
        morseCode.put("5", ".....");
        morseCode.put("6", "-....");
        morseCode.put("7", "--...");
        morseCode.put("8", "---..");
        morseCode.put("9", "----.");
        morseCode.put("0", "-----");
        morseCode.put(" ", ".......");
        morseCode.put(".", ".-.-.-");
        morseCode.put("?", "..--..");
        morseCode.put(",", "--..--");
        morseCodeReversed = invert(morseCode);
    }

    private static <V, K> Map<V, K> invert(Map<K, V> map) {

        Map<V, K> inv = new HashMap<>();

        for (Map.Entry<K, V> entry : map.entrySet())
            inv.put(entry.getValue(), entry.getKey());

        return inv;
    }

    public static String transformFromTextToMorseCode(String text) {
        StringBuilder result = new StringBuilder();

        for(int index = 0; index < text.length(); index++)
        {
            String currentCharacter = Character.toString(text.charAt(index));
            String morseForCurrentCharacter = morseCode.get(currentCharacter);
            if (morseForCurrentCharacter != null)
            {
                result.append(morseForCurrentCharacter).append(" ");
            }
            else {
                if(text.charAt(index) == ' ')
                    result.append(".......").append(" ");
            }
        }
        if(result.length() > 0)
            result.delete(result.length() - 1, result.length());
        return result.toString();
    }

    public static String tranformFromMorseCodeToText(String morse) {
        StringBuilder result = new StringBuilder();

        String[] list = morse.split(" ");
        for (String s : list) {
            try {
                String currentCharacter = morseCodeReversed.get(s);
                if(currentCharacter != null)
                    result.append(currentCharacter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result.toString();
    }
}