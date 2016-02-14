package machineCoding.rankOfRepeatedWords;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by rajesh.kalloor on 05/11/15.
 */
public class WordRank {

    LinkedHashMap<Integer,ArrayList<String>> rank;

    public void process(){
        try{
            rank=new LinkedHashMap<Integer,ArrayList<String>>();
            FileInputStream f=new FileInputStream("src/main/java/machineCoding/rankOfRepeatedWords/TestFile.txt");
            BufferedReader br=new BufferedReader(new InputStreamReader(f));
            String line;
            char[] word;
            int startIndex,endIndex,extra,flag;
            HashMap<String,Integer> words=new HashMap<String,Integer>();
            int nextLine=0;
            while((line=br.readLine())!=null){
                word=line.toCharArray();
                startIndex=0;
                endIndex=0;
                flag=0;
                int value=0;
                extra=0;
                System.out.println(word.toString());
                for(char ch:word){
                    if((int)ch==32||ch==':'||ch==';'||ch==','||ch=='.'){
                        extra++;
                        flag=1;
                        nextLine=1;
                    }
                    else{
                        endIndex++;
                        continue;
                    }
                    if(flag==1){
                        String wordFormed="";
                        for(int i=startIndex;i<endIndex;i++){
                            wordFormed=wordFormed+word[i];
                        }
                        if(words.containsKey(wordFormed)){
                            value=words.get(wordFormed);
                            value++;
                            words.put(wordFormed, value);
                        }
                        else{
                            words.put(wordFormed, 1);
                        }

                        startIndex=endIndex+extra;
                        endIndex=startIndex;
                        extra=0;

                    }
                }
                if(nextLine==0){
                    String wordFormed="";
                    for(int i=startIndex;i<endIndex;i++){
                        wordFormed=wordFormed+word[i];
                    }
                    if(words.containsKey(wordFormed)){
                        value=words.get(wordFormed);
                        value++;
                        words.put(wordFormed, value);
                    }
                    else{
                        words.put(wordFormed, 1);
                    }
                }
            }

            int len=words.size();
            int max;
            int previousMax=0;
            String maxKey;
            int rankValue=1;
            ArrayList<String> rankWords=new ArrayList<String>();
            for(int i=0;i<len;i++){
                max=0;
                maxKey=null;
                for(String key:words.keySet()){
                    if(max<=words.get(key)){
                        max=words.get(key);
                        maxKey=key;
                    }
                }
                if(previousMax==0){
                    previousMax=max;
                }
                if(previousMax==max){
                    rankWords.add(maxKey);
                    rank.put(rankValue, rankWords);
                }
                else{
                    rankValue++;
                    rankWords=new ArrayList<String>();
                    rankWords.clear();
                    rankWords.add(maxKey);
                    ArrayList<String> rankInsert=rankWords;
                    rank.put(rankValue,rankWords);
                }
                previousMax=max;
                words.remove(maxKey);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    public void getRankedWord(int k){
        if(!rank.containsKey(k)) {
            System.out.println("Invalid input");
        }
        else{
            ArrayList<String> list=rank.get(k);
            for(String x:list)
                System.out.println(x);
        }
    }
    public void topKWords(int k){
        if(k>rank.size())
            System.out.println("Invalid Input");
        else{
            int count=0;
            int flag=0;
            for(int x:rank.keySet()){
                ArrayList<String> val=rank.get(x);
                for(String v:val){
                    System.out.println(v);
                }
                count++;
                if(count==k)
                    break;
            }
        }
    }

    public static void main(String[] args){
        WordRank r=new WordRank();
        r.process();
        System.out.println("Printing Rank2 words:");
        r.getRankedWord(1);
        System.out.println("Printing top2 words");
        r.topKWords(1);
    }
}


