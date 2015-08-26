# Lexicon

A dictionary application in Java that makes searching words faster using compact tries. Lets assume we have the following list of words.

* coalescent
* selva
* throwster
* lactoscope
* potentiated
* pregrading
* push
* gorgoneia
* tungusic
* nonrepair
* pursuit
* curr
* subway
* redescend
* ever
* ctenidia
* readvocation
* freakiness
* commination
* rugger

Adding these list of words to the trie would result in a tree structure as shown below:
```
|
c--c
        |
        o--o
                |
                a--alescent.$
                m--mmination.$
        t--tenidia.$
        u--urr.$
e--ever.$
f--freakiness.$
g--gorgoneia.$
l--lactoscope.$
n--nonrepair.$
p--p
        |
        o--otentiated.$
        r--regrading.$
        u--u
                |
                r--rsuit.$
                s--sh.$
r--r
        |
        e--e
                |
                a--advocation.$
                d--descend.$
        u--ugger.$
s--s
        |
        e--elva.$
        u--ubway.$
t--t
        |
        h--hrowster.$
        u--ungusic.$
```

## Usage
### Adding words
```java
String word = "Some Word";
Trie trie = new Trie();
trie.addWord(word); 								// Adds the word to the trie.
```
### Searching for a prefix
```java
String prefix = "Some";								// Returns a set of strings in the
Set<String> words = trie.getWords(prefix);			// trie that starts with 'Some'
```

## About Me
My name is Janeve George. I am a software engineer by profession and a hardcore agile development enthusiast. Currently I am a Lead Engineer working in Bangalore, India. I am also the founder of [Agile Developer Notes](http://www.agiledevelopernotes.com) which was started in July 2011 - a site that helps in understanding and promoting Agile Software Development Methods and Tools.
## Website

http://www.janeve.me