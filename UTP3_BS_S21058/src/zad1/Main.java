/**
 *
 *  @author Bozhko Sviatoslav S21058
 *
 */

package zad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*<--
 *  niezbędne importy
 */
public class Main {
  public static void main(String[] args) {
    Function<String,List<String>>flines=e->{
      try {
        return Files.readAllLines(Paths.get(e));
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
      return null;
    };
    Function<List<String>,String>join=e->{
      StringBuilder stringBuilder = new StringBuilder();
      Iterator iterator = e.iterator();
      while (iterator.hasNext()){
        String str= (String) iterator.next();
        stringBuilder.append(str);
      }
      return stringBuilder.toString();
    };
    Function<String,List<Integer>>collectInts=e->{
      Pattern pattern = Pattern.compile("-?\\d+");
      Scanner scanner = new Scanner(e);
      List<Integer>integerList=new ArrayList<>();
      while (scanner.hasNext()){
        String str=(scanner.next());
        Matcher matcher =pattern.matcher(str);
        while (matcher.find()) {
          integerList.add(Integer.parseInt(matcher.group()));
        }
      }
      return integerList;
    };
    Function<List<Integer>,Integer>sum=e->{
      int sum_of_all_elements=0;
      for (int i = 0; i <e.size() ; i++) {
        sum_of_all_elements=sum_of_all_elements+e.get(i);
      }
      return sum_of_all_elements;
    };
    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */

    String fname = System.getProperty("user.home") + "/LamComFile.txt";
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
