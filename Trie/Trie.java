package Lab.Trie;

class TrieNode {
    private TrieNode[] children;
    private boolean isEndOfWord;
    private int wordEnding;

    public TrieNode() {
        children = new TrieNode[26];
        isEndOfWord = false;
        wordEnding = 0;
    }

    public boolean hasChild(char ch) {
        return children[ch - 'a'] != null;
    }

    public void addChild(char ch) {
        children[ch - 'a'] = new TrieNode();
    }

    public TrieNode getChild(char ch) {
        return children[ch - 'a'];
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    public void incrementWordEnding() {
        wordEnding++;
    }

    public void decrementWordEnding() {
        wordEnding--;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void removeChild(char ch) {
        children[ch - 'a'] = null;
    }

    public int getChildrenCount() {
        int count = 0;
        for (int i = 0; i < children.length; i++) {
            if (children[i] != null) {
                count++;
            }
        }
        return count;
    }

    public int getWordEnding() {
        return wordEnding;
    }
}

public class Trie {
    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!current.hasChild(ch)) {
                current.addChild(ch);
            }
            current = current.getChild(ch);
        }
        current.setEndOfWord(true);
        current.incrementWordEnding();
    }

    public boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!current.hasChild(ch)) {
                return false;
            }
            current = current.getChild(ch);
        }
        return current.isEndOfWord();
    }

    // Lazy Deletion
    private void delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord()) {
                return;
            }
            current.setEndOfWord(false);
            current.decrementWordEnding();
            return;
        }
        char ch = word.charAt(index);
        if (!current.hasChild(ch)) {
            return;
        }
        TrieNode child = current.getChild(ch);
        delete(child, word, index + 1);
        if (!child.isEndOfWord() && child.getChildrenCount() == 0) {
            current.removeChild(ch);
        }
    }

    public void delete(String word) {
        delete(root, word, 0);
    }

    private int countWords(TrieNode current) {
        int count = 0;
        if (current.isEndOfWord()) {
            count += current.getWordEnding();
        }
        for(int i=0; i<26; i++) {
            if(current.hasChild((char)('a'+i))) {
                TrieNode child = current.getChild((char)('a'+i));
                count += countWords(child);
            }
        }
        return count;
    }

    public int countWords() {
        return countWords(root);
    }

    public int countPrefix(String prefix) {
        int count = 0;
        TrieNode current = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (!current.hasChild(ch)) {
                return 0;
            }
            current = current.getChild(ch);
        }
        count = countWords(current);
        return count;  
    }
}
