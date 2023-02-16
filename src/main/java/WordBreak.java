import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TrieNode{
    boolean eow;
    TrieNode children[];
    public TrieNode(){
        this.eow=false;
        this.children =new TrieNode[26];
    }
}
class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
         TrieNode t=new  TrieNode();
        for(String word: wordDict){
            insertString(word.toCharArray(),t);
        }
        return helper(s.toCharArray(),0,t);
    }
    public boolean helper(char[] arr, int start,  TrieNode t){
        Queue< TrieNode> q =new LinkedList<>();
        q.add(t);
        boolean visited[] =new boolean[arr.length];
        while(!q.isEmpty() && start <arr.length){
            int size =q.size();
            for(int i=0;i<size;i++){
                 TrieNode temp =q.poll();
                if(temp.children[arr[start]-'a']!=null){
                    q.add(temp.children[arr[start]-'a']);
                    if(temp.children[arr[start]-'a'].eow==true && !visited[start]){
                        q.add(t);
                        visited[start]=true;
                    }
                }
            }
            if(q.size()!=0)start++;
        }
        if(start==arr.length && visited[start-1]==true)return true;
        return false;
    }

    public void insertString(char arr[],  TrieNode t){
        for(char c: arr ){
            if(t.children[c-'a']==null){
                t.children[c-'a']=new  TrieNode();
            }
            t=t.children[c-'a'];
        }
        t.eow =true;
    }
}