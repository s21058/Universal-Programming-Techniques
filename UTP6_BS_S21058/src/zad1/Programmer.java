/**
 *
 *  @author Bozhko Sviatoslav S21058
 *
 */

package zad1;

import java.util.HashSet;
import java.util.Set;

public class Programmer {
    String names;
     Set languages = new HashSet<String>();

    Programmer(String names,Set languages) {
        this.languages = languages;
        this.names = names;
    }

    public Set getLanguages() {
        return languages;
    }

    public String getNames() {
        return names;
    }

    @Override
    public String toString() {
        return String.valueOf(languages);
    }

    public void setLanguages(Set languages) {
        this.languages = languages;
    }

    public void setNames(String names) {
        this.names = names;
    }
}
