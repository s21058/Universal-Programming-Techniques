/**
 *
 *  @author Bozhko Sviatoslav S21058
 *
 */

package zad2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Anagrams {
    private List<String> list_of_words;
    private List<List<String>> list;

    public Anagrams(String allWords) {
        list_of_words = new ArrayList<>();
        BufferedReader br;
        String tmpLine;
        try {
            br = new BufferedReader(new FileReader(new File(allWords)));
            while ((tmpLine = br.readLine()) != null) {
                String[] words = tmpLine.split(" ");
                for (int i = 0; i < words.length; i++) {
                    list_of_words.add(words[i]);
                }
            }
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
    }

    public List<List<String>> getSortedByAnQty() {
        list = new ArrayList<>();
        List<String> anotherList = new ArrayList<>();
        for (int i = 0; i < list_of_words.size(); i++) {
            if (!anotherList.contains(list_of_words.get(i))) {
                List<String> tepm = new ArrayList<>();
                for (int j = 0; j < list_of_words.size(); j++) {
                    char[] temp = list_of_words.get(i).replaceAll("[\\s]", "").toCharArray();
                    char[] temp1 = list_of_words.get(j).replaceAll("[\\s]","").toCharArray();
                    Arrays.sort(temp);
                    Arrays.sort(temp1);
                    if (Arrays.equals(temp, temp1)) {
                        anotherList.add(list_of_words.get(j));
                        tepm.add(list_of_words.get(j));
                    }
                }
                list.add(tepm);
            }
        }
        list.sort((o1, o2) -> o2.toString().length() - o1.toString().length());
        return list;

    }

    public String getAnagramsFor(String next) {
        for (int i = 0; i <list.size() ; i++) {
            List nn= new ArrayList(list.get(i));
            for (int j = 0; j <nn.size() ; j++) {
                if(nn.get(j).equals(next)){
                    nn.remove(j);
                    return next+": "+nn;
                }
            }
        }
        return "";
    }
}
