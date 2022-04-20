/**
 *
 *  @author Bozhko Sviatoslav S21058
 *
 */

package zad1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgLang<T,S> {
    Map<String, List<String>> progsMap;
    Map<T, S> lang_map;

    public ProgLang(String s) {
        lang_map = new LinkedHashMap<T, S>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(s));
            String help_str;
            while ((help_str = bufferedReader.readLine()) != null) {
                String[] string_tab = help_str.split("\\t");
                List<String> strings = new ArrayList<>();
                for (int i = 1; i < string_tab.length; i++) {
                    strings.add(string_tab[i]);
                }
                strings = strings.stream().distinct().collect(Collectors.toList()); // Removes duplicates
                lang_map.put((T) string_tab[0], (S) strings);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<T, S> getLangsMap() {
        return lang_map;
    }

    public Map<T, S> getProgsMap() {
        progsMap = new LinkedHashMap<String, List<String>>();
        for (T value : lang_map.keySet()) {
            for (S val : (List<S>) lang_map.get(value)) {
                if (progsMap.containsKey(val)) {
                    progsMap.get(val).add((String) value);
                } else {
                    List<String> langs = new ArrayList<>();
                    langs.add((String) value);
                    progsMap.put((String) val,  langs);

                }
            }
        }
        return (Map<T, S>) progsMap;
    }

    public Map<T, S> getLangsMapSortedByNumOfProgs() {
        return lang_map.entrySet().stream().sorted(((l, l1) -> {
            List<String> list = (List<String>) l.getValue();
            List<String> list1 = (List<String>) l1.getValue();
            Integer a = list.size();
            Integer b = list1.size();
            return b.compareTo(a);
        })).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e, e1) -> e, LinkedHashMap::new));

    }

    public Map<T, S> getProgsMapSortedByNumOfLangs() {
        Map<T, S> map = (LinkedHashMap<T, S>) this.progsMap.entrySet().stream().sorted(((l, l1) -> {
            Integer a = l.getValue().size();
            Integer b = l1.getValue().size();
            if (a == b) {
                return l.getKey().compareTo(l1.getKey());
            }
            return b.compareTo(a);
        })).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e, e1) -> e, LinkedHashMap::new));

        return map;
    }

    public Map<T, S> getProgsMapForNumOfLangsGreaterThan(int i) {
        Map<T, S> map = (LinkedHashMap<T, S>) this.progsMap.entrySet().stream().filter(l -> l.getValue().size() > i)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e, e1) -> e, LinkedHashMap::new));

        return map;
    }

    public  <T, S> Map<T, List<S>> sorted(Map<T, List<S>> map, Comparator<Map.Entry<T, List<S>>> lambdaExpr) {
        LinkedHashMap<T, List<S>> returnMap = new LinkedHashMap<>();
        map.entrySet().stream().sorted(lambdaExpr).forEach(entry -> returnMap.put(entry.getKey(), entry.getValue()));
        return returnMap;
    }

    public  <T, S> LinkedHashMap<T, List<S>> filtered(Map<T, List<S>> map, Predicate<Map.Entry<T, List<S>>> lambdaExpr) {
        LinkedHashMap<T, List<S>> returnMap = new LinkedHashMap<>();

        returnMap.entrySet().stream().filter(lambdaExpr).forEach(entry -> returnMap.put(entry.getKey(), entry.getValue()));

        return returnMap;
    }
}
