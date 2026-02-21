import java.util.*;
/*
 * Problem: Word Ladder
 *
 * Approach:
 * - Treat each word as a node.
 * - Two words are connected if they differ by one character.
 * - Use BFS to find shortest transformation sequence.
 *
 * Time Complexity:
 *      O(N * L)
 * Space Complexity:
 *      O(N)
 */

public class WordLadder {
    class Pair{
        String first;
        int second;

        public Pair(String first,int second){
            this.first = first;
            this.second = second;
        }
    }
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {


            Queue<Pair> queue = new LinkedList<>();
            Set<String> set = new HashSet<>();
            queue.offer(new Pair(beginWord,1));
            for(int i=0;i<wordList.size();i++){
                set.add(wordList.get(i));
            }
            if(!set.contains(endWord)){
                return 0;
            }
            set.remove(beginWord);

            while(!queue.isEmpty()){
                Pair p = queue.poll();
                String word = p.first;
                int steps = p.second;


                if(word.equals(endWord)){
                    return steps;
                }
                for(int i=0;i<word.length();i++){
                    for(char ch = 'a';ch<= 'z';ch++){
                        char[] str = word.toCharArray();
                        str[i] = ch;
                        String newWord = new String(str);
                        if(set.contains(newWord)){
                            queue.offer(new Pair(newWord,steps + 1));
                            set.remove(newWord);
                        }
                    }
                }
            }
            return 0;
        }
    }

