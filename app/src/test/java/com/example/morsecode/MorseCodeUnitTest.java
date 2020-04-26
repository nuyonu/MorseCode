package com.example.morsecode;

import com.example.morsecode.MorseCode.MorseCode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/***
 * https://onlinetexttools.com/convert-text-to-morse -> text to morse code.
 */
public class MorseCodeUnitTest {
    @Test
    public void transformFromTextToMorseCode_isCorrect() {
        final String text = "SOS. I need help";
        final String expectedMorseCode = "... --- ... .-.-.- ....... .. ....... -. . . -.. ....... .... . .-.. .--.";

        final String result = MorseCode.transformFromTextToMorseCode(text);

        assertEquals(expectedMorseCode, result);
    }

    /***
     * Character / is not in MorseCodeMap.
     * Function transformFromTextToMorseCode should ignore this character.
     */
    @Test
    public void transformFromTextToMorseCode_isCorrectWhenACharacterNotInMorseCodeMap() {
        final String text = "SOS./ I need help";
        final String expectedMorseCode = "... --- ... .-.-.- ....... .. ....... -. . . -.. ....... .... . .-.. .--.";

        final String result = MorseCode.transformFromTextToMorseCode(text);

        assertEquals(expectedMorseCode, result);
    }

    /***
     * Input is ok.
     * Function transformFromTextToMorseCode should return a lowercase string with initial message decoded.
     */
    @Test
    public void transformFromMorseCodeToText_isCorrect() {
        final String morseCode = "... --- ... .-.-.- ....... .. ....... -. . . -.. ....... .... . .-.. .--.";
        final String expectedText = "sos. i need help";

        final String result = MorseCode.transformFromMorseCodeToText(morseCode);

        assertEquals(expectedText, result);
    }

    /***
     * Phrase ..------- doesn't exist in ReversedMorseCodeMap.
     * Function transformFromMorseCodeToText should ignore this phrase.
     */
    @Test
    public void transformFromMorseCodeToText_isCorrectWhenAPhraseNotInReversedMorseCodeMap() {
        final String morseCode = "... --- ... .-.-.- ....... .. ....... -. . . -.. ....... .... . .-.. .--. ..-------";
        final String expectedText = "sos. i need help";

        final String result = MorseCode.transformFromMorseCodeToText(morseCode);

        assertEquals(expectedText, result);
    }

    /***
     * Phrase ..213121d- doesn't exist in ReversedMorseCodeMap.
     * Function transformFromMorseCodeToText should ignore this phrase.
     */
    @Test
    public void transformFromMorseCodeToText_isCorrectWhenAPhraseNotInReversedMorseCodeMap2() {
        final String morseCode = "... --- ... .-.-.- ....... .. ....... -. . . -.. ....... .... . .-.. .--. ..213121d-";
        final String expectedText = "sos. i need help";

        final String result = MorseCode.transformFromMorseCodeToText(morseCode);

        assertEquals(expectedText, result);
    }
}
