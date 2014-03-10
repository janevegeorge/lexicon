package me.janeve.lexicon;

import java.util.*;

public class Trie {

    private String charSequence;
    private Trie parent = null;
    private Map<Character, Trie> children;

    private boolean isWord;
    private boolean isLeaf = true;

    public void addWord(String word){
        addWord(word, true);
    }

    private void addWord(String word, boolean isWord) {
        if(word == null) {
            throw new NullPointerException("Cannot accept null value for a word.");
        }

        if(charSequence == null || word.equals(charSequence)) {
            charSequence = word;
            this.isWord = isWord;
        } else {
            addWordToAppropriateChildNode(word);
        }
    }

    private void addWordToAppropriateChildNode(String word) {

        if(charSequence.equals("")) {
            addWordToChild(word);
        } else {
            int mismatchIndex = 0;
            for(; foundMismatchIndex(word, mismatchIndex); mismatchIndex++){ }

            if(mismatchIndex < charSequence.length()) {
                String newChildWord = charSequence.substring(mismatchIndex);
                charSequence = charSequence.substring(0, mismatchIndex);

                Trie newWordTrie = new Trie(this);
                newWordTrie.setChildren(this.children);
                newWordTrie.addWord(newChildWord, isWord());
                this.children = new TreeMap<Character, Trie>();
                isLeaf = false;
                this.children.put(newChildWord.charAt(0), newWordTrie);

                isWord = false;
            }

            if(mismatchIndex < word.length()) {
                word = word.substring(mismatchIndex);
                addWordToChild(word);
            } else {
                isWord = true;
            }
        }


    }

    private Trie addWordToChild(String word) {
        Character firstChar = word.charAt(0);

        if(this.children == null) { this.children = new TreeMap<Character, Trie>(); isLeaf = false;}

        if( this.children.get(firstChar) == null ) {
            this.children.put(firstChar, new Trie(this));
            isLeaf = false;
        }

        Trie childNode = this.children.get(firstChar);
        childNode.addWord(word);
        return childNode;
    }

    private void setChildren(Map<Character, Trie> children) {
        if(children != null && !children.isEmpty()) {
            isLeaf = false;
        }
        this.children = children;
    }

    private boolean foundMismatchIndex(String word, int index) {
        return charSequence.length() > index && word.length()> index && charSequence.charAt(index) == word.charAt(index);
    }

    public boolean isWord() {
        return isWord;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public String getCharSequence() {
        return charSequence;
    }

    public Set<String> getWords(String prefix){
        Set<String> words = new TreeSet<String>();

        if(isWord) {
            words.add( getCompleteWord() );
        }

        if (prefix.length() > 1 && children != null && children.containsKey(prefix.charAt(0))) {
            words.addAll( children.get(prefix.charAt(0)).getWords(prefix.substring(1)) );
        }

        return words;
    }

    public Set<String> getWords(){
        Set<String> words = new TreeSet<String>();

        if(isWord) {
            words.add( getCompleteWord() );
        }

        if(!isLeaf) {
            for(Map.Entry<Character, Trie> child:children.entrySet()) {
                if(child.getValue() != null) {
                    words.addAll(child.getValue().getWords());
                }
            }
        }

        return words;
    }

    private String getCompleteWord() {
        if(parent != null) {
            return parent.getCompleteWord() + charSequence;
        } else {
            return charSequence;
        }
    }

    private Trie(Trie parent) {
        this.parent = parent;
    }

    public Trie(){
        this.charSequence = "";
    }

    public void printTreeView(int i){

//        printSpaces(i);
        System.out.print(charSequence);
        if(isWord()) { System.out.print("."); }
        if(isLeaf()) { System.out.print("$"); }
        System.out.println();
        if(children != null && children.size() > 0) {
            printSpaces(i);
            System.out.println("|");

            for(Map.Entry<Character, Trie> entry:children.entrySet()){
                printSpaces(i);
                System.out.print(entry.getKey());
                System.out.print("--");
                entry.getValue().printTreeView(i+1);
            }

        }


    }

    private void printSpaces(int i) {
        for(int j=0; j<=(i*8); j++) {
            System.out.print(" ");
        }
    }

}