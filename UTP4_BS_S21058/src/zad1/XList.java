package zad1;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList<T> extends ArrayList<T>  {

    public XList(T... integers) {
        super(Arrays.asList(integers));
    }

    public XList(Collection<T> collections) {
        super(collections);
    }

    public static <T> XList<T> of(T... args) {
        return new XList<>(args);
    }

    public static <T> XList<T> of(Collection<T> collection) {
        return new XList<>(collection);
    }

    public static <T> XList<T> charsOf(String ala_ma_kota) {
        List list = new ArrayList();
        for (int i = 0; i < ala_ma_kota.length(); i++) {
            list.add(ala_ma_kota.charAt(i));
        }
        return new XList<T>(list);
    }

    public static <T> XList<T> tokensOf(String... ala_ma_kota) {
        List list = null;
        if (ala_ma_kota.length == 1) {
            list = new ArrayList<>(Arrays.asList(ala_ma_kota[0].split("\\s")));

        } else {
            list = new ArrayList<>(Arrays.asList(ala_ma_kota[0].split(ala_ma_kota[ala_ma_kota.length - 1])));

        }
        return new XList<T>(list);
    }

    public XList<T> union(T... list2) {
        List<T> list = this;
        List<T> copyList = new ArrayList<>();
        copyList.addAll(list);
        copyList.addAll(Arrays.asList(list2));
        return new XList<>(copyList);
    }

    public XList<T> union(Collection<T> collection) {
        List<T> list = this;
        list.addAll(collection);
        return new XList<T>(list);
    }

    public XList<T> diff(Collection<T> collection) {
        List<T> list = new ArrayList();
        list.addAll(this);
        list.removeAll(collection);
        return new XList<>(list);
    }

    public XList<T> unique() {
        return new XList<T>((this.stream().distinct().collect(Collectors.toList())));
    }
//
    public XList<XList<String>> combine() {
//public void combine() {
        XList<XList<String>> xList = new XList<>();
        xList.addAll((Collection<? extends XList<String>>) this);
        XList<XList<String>> returnAllList = new XList<>();

        int count = xList.size();
        int combination = 1;

        for (int i = 0; i < xList.size(); i++) {
            XList x = XList.of(xList.get(i));
            combination *= x.size();
        }
        for (int i = 0; i < combination; i++) {
            returnAllList.add(new XList<String>());
        }

        for (int i = 0; i < count; i++) {
            int iterator = 0;
            for (int j = 0; j < combination; j++) {
                XList x = XList.of(xList.get(i));
                if (iterator < x.size() - 1) {
                    returnAllList.get(j).add(String.valueOf(x.get(iterator)));
                    iterator++;
                } else {
                    returnAllList.get(j).add(String.valueOf(x.get(iterator)));
                    iterator = 0;
                }
            }
            Collections.sort(returnAllList, new Comparator<List<String>>() {
                @Override
                public int compare(List<String> a, List<String> b) {
                    return a.get(returnAllList.get(0).size() - 1).compareTo(b.get(returnAllList.get(0).size() - 1));
                }
            });
        }

        return returnAllList;
    }

    public String join(String separator) {
        return this.stream()
                .map( Object::toString )
                .collect( Collectors.joining(separator));
    }

    public String join() {
        return join("");
    }

    public <T> XList<String> collect (Function<XList<T>, String> function) {
        XList list = new XList();

        for (XList<T> t : ((XList<XList<T>>) this)) {
            list.add(function.apply(t));
        }

        return new XList<String>(list);

    }
    public void forEachWithIndex(BiConsumer<T, Integer> consumer) {
        for (int i = 0; i <this.size() ; i++) {
            consumer.accept(this.get(i),i);
        }
    }

}
