package me.janeve.lexicon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Random;
import java.util.Set;

@RunWith(JUnit4.class)
public class TestTrie {

    Trie trie;

    @Before
    public void initialize() {
        trie = new Trie();
    }

    @Test(expected=NullPointerException.class)
    public void addingNullThrowsException() {
        trie.addWord(null);
    }

    @Test
    public void newTrieShouldBeValid() {
        assertNotNull(trie);
        assertTrue("Empty trie node will always be leaf node.", trie.isLeaf());
        assertFalse("Empty trie node cannot be a word.", trie.isWord());
        assertEquals("Empty trie should have a null character sequence.", "", trie.getCharSequence());

    }

    @Test
    public void addingOneWordShouldSucceed() {
        String expectedWord = generateAWord("winner");
        trie.addWord(expectedWord);

        assertTrue(expectedWord + " should be found in trie.", trieContainsWord(expectedWord));
    }

    @Test
    public void addingTwoWordShouldSucceed() {
        String expectedWord1 = generateAWord("winner");
        String expectedWord2 = generateAWord("winner");

        trie.addWord(expectedWord1);
        trie.addWord(expectedWord2);

        assertTrue(expectedWord1 + " should be found in trie.", trieContainsWord(expectedWord1));
        assertTrue(expectedWord2 + " should be found in trie.", trieContainsWord(expectedWord2));
    }

    @Test
    public void testingUnmatchedPrefix() {
        String expectedWord1 = generateAWord("winner");
        String expectedWord2 = generateAWord("winner");

        trie.addWord(expectedWord1);

        assertTrue(expectedWord1 + " should be found in trie.", trieContainsWord(expectedWord1));
        assertFalse(expectedWord2 + " should not be found in trie.", trieContainsWord(expectedWord2));
    }

    @Test
    public void searchingByPrefixShouldReturnTrue() {
        final String prefix = "winner";
        String expectedWord = generateAWord(prefix);

        trie.addWord(expectedWord);

        assertTrue(expectedWord + " should be found in trie.", trieContainsWord(prefix, expectedWord));
    }

    @Test
    public void searchingByDifferentPrefixShouldReturnFalse() {
        final String prefix = "winner";
        final String anotherPrefix = "looser";
        String expectedWord = generateAWord(prefix);

        trie.addWord(expectedWord);

        assertFalse(expectedWord + " should not be found in trie.", trieContainsWord(anotherPrefix, expectedWord));
    }

    @Test
    public void searchingAPrefixShouldReturnMultipleOutput() {
        generateADictinaryOfWords();
        final String prefix = "Ja";
        final int expectedOutput = 2;

        Set<String> words = trie.getWords(prefix);

        assertNotNull("Trie should always return a result but not null.", words);
        assertEquals(expectedOutput + " words should have been returned", expectedOutput, words.size());
    }

    @Test
    public void searchingAPrefixShouldReturnSingleOutput() {
        generateADictinaryOfWords();
        final String prefix = "Ap";
        final int expectedOutput = 1;

        Set<String> words = trie.getWords(prefix);

        assertNotNull("Trie should always return a result but not null.", words);
        assertEquals(expectedOutput + " words should have been returned", expectedOutput, words.size());
    }

    @Test
    public void searchingAPrefixShouldReturnNoOutput() {
        generateADictinaryOfWords();
        final String prefix = "BA";
        final int expectedOutput = 0;

        Set<String> words = trie.getWords(prefix);

        assertNotNull("Trie should always return a result but not null.", words);
        assertEquals(expectedOutput + " words should have been returned", expectedOutput, words.size());
    }

    @Test
    public void searchingAPrefixWithParentAndChildren() {
        generateADictinaryOfWords();
        final String prefix = "March";
        final int expectedOutput = 2;

        Set<String> words = trie.getWords(prefix);

        assertNotNull("Trie should always return a result but not null.", words);
        assertEquals(expectedOutput + " words should have been returned", expectedOutput, words.size());
    }

    private void generateADictinaryOfWords() {
        final String[] words = {"Janeve", "George", "January", "February", "March", "April", "May", "Marching"};

        for (String word : words) {
            trie.addWord(word);
        }
    }

    private String generateAWord(String prefix) {
        return prefix + new Random().nextInt();
    }

    private boolean trieContainsWord(String prefix, String expectedWord) {
        Set<String> words = trie.getWords(prefix);
        return words != null && words.contains(expectedWord);
    }

    private boolean trieContainsWord(String prefix) {
        Set<String> words = trie.getWords();
        return words != null && words.contains(prefix);
    }

}