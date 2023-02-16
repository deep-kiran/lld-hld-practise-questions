package autocompletesystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 class TrieNode{
    HashMap<Character, TrieNode> children;
    HashMap<String,Integer> sentenceFrequency;
    int hotDegree;
}
public class AutocompleteSystem {
    TrieNode root;
    TrieNode currentNode;
    StringBuilder inputBuilder;
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        inputBuilder = new StringBuilder();
        currentNode = root;
        
        for (int i = 0; i < sentences.length; i++) {
            insert(sentences[i], times[i]);
        }
    }
    
    public List<String> input(char c) {
        List<String> result = new ArrayList<>();
        if (c == '#') {
            insert(inputBuilder.toString(), 1);
            inputBuilder = new StringBuilder();
            currentNode = root;
        } else {
            inputBuilder.append(c);
            if (!currentNode.children.containsKey(c)) {
                currentNode.children.put(c, new TrieNode());
            }
            currentNode = currentNode.children.get(c);
            result = getHotSentences(currentNode.sentenceFrequency);
        }
        return result;
    }
    
    private void insert(String sentence, int frequency) {
        TrieNode node = root;
        for (char c : sentence.toCharArray()) {
            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
            node.sentenceFrequency.put(sentence, node.sentenceFrequency.getOrDefault(sentence, 0) + frequency);
        }
        node.hotDegree = node.sentenceFrequency.get(sentence);
    }
    
    private List<String> getHotSentences(Map<String, Integer> sentenceFrequency) {
        List<String> result = new ArrayList<>(sentenceFrequency.keySet());
        result.sort((a, b) -> {
            int frequencyComparison = sentenceFrequency.get(b) - sentenceFrequency.get(a);
            if (frequencyComparison == 0) {
                return a.compareTo(b);
            }
            return frequencyComparison;
        });
        return result.subList(0, Math.min(3, result.size()));
    }
}
