/**
 *
 *  @author Bozhko Sviatoslav S21058
 *
 */

package zad1;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Finder {
    List<String> list;
    int nif=0;
    int nwar=0;

    public Finder(String fname) throws IOException {
        Charset codeplik = Charset.forName("cp1250");
        list = new ArrayList<>();
        Files.walk(Paths.get(fname)).filter(Files::isRegularFile).forEach(
                l -> {
                    try {
                        Files.lines(l, codeplik).forEach(list::add);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
    findIfCountAndWorldCount();
    }
    public void findIfCountAndWorldCount() {
        boolean commentLineSlashFlag = false;
        boolean commentCodeSlashFlag = false;
        int counter = 0;

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).length(); j++) {
                if (j + 1 < list.get(i).length()) {
                    if (list.get(i).charAt(j) == '/'&&list.get(i).charAt(j + 1)=='/'&&counter%2==0&&!commentLineSlashFlag)
                        commentCodeSlashFlag = true;
                    if (list.get(i).charAt(j) == '/' && list.get(i).charAt(j + 1) == '*' && counter % 2 == 0 && !commentCodeSlashFlag)
                        commentLineSlashFlag = true;
                    if (list.get(i).charAt(j) == '*' && list.get(i).charAt(j + 1) == '/' && counter % 2 == 0 && !commentCodeSlashFlag)
                        commentLineSlashFlag = false;
                    if (list.get(i).charAt(j) == '"' && !commentCodeSlashFlag && !commentLineSlashFlag) {
                        counter++;
                        if (j != 0) {
                            if (list.get(i).charAt(j - 1) == '\\') {
                                counter--;
                            }
                        }
                    }
                }
                if (j+3<list.get(i).length()) {
                    if (list.get(i).charAt(j) == 'i' &&
                            list.get(i).charAt(j + 1) == 'f' &&
                            ((list.get(i).charAt(j + 2) == ' ' &&
                                    list.get(i).charAt(j + 3) == '(') || list.get(i).charAt(j + 2) == '(') &&
                            !commentLineSlashFlag && !commentCodeSlashFlag && counter % 2 == 0) {
                        nif++;
                        if (j != 0) {
                            if ((list.get(i).charAt(j - 1) > 47 && list.get(i).charAt(j - 1) < 58) ||
                                    (list.get(i).charAt(j - 1) > 64 && list.get(i).charAt(j - 1) < 91) ||
                                    (list.get(i).charAt(j - 1) > 96 && list.get(i).charAt(j - 1) < 123) ||
                                    list.get(i).charAt(j - 1) == '_') {
                                nif--;
                            }
                        }
                    }
                }

            }
            commentCodeSlashFlag = false;
        }
    }

    public int getIfCount() {
      return nif;
    }

    public int getStringCount(String wariant) {
    return nwar;
    }
}

