/**
 *
 *  @author Bozhko Sviatoslav S21058
 *
 */

package zad2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
  public static void main(String[] args) throws IOException {
    URL url = new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt");
    BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()));
    Stream<String> stream = bf.lines();
    Map<String, List<String>> map = stream.collect(Collectors.groupingBy(Main::sorted));
    int maxMapValue = map.values().stream().map(List::size).max(Integer::compare).get();
  map.values().stream().filter(l->l.size()==maxMapValue).forEach(l-> System.out.println(l.stream().collect(Collectors.joining(" "))));
  }

  public static String sorted(String word) {
    char[] chars = word.toCharArray();
    Arrays.sort(chars);
    return new String(chars);
  }
}
