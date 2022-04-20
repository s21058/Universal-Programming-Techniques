package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator<T> {
    List<T> list;
    List<T>newlist;
    ListCreator(List<T>list){
        this.list=list;
    }
    public static <T>ListCreator<T>collectFrom(List<T> scr){
        return  new ListCreator<>(scr);
    }
    public ListCreator<T>when(Predicate<T>predicate){
        newlist=new ArrayList<>();
        for(T value:list){
            if(predicate.test(value)){
                newlist.add(value);
            }
        }
        list=newlist;
        return this;
    }
    public <S>List<T>mapEvery(Function<T,S>function){
        newlist=new ArrayList<>();
        for(T value:list){
            newlist.add((T) function.apply(value));
        }
        return newlist;
    }

}
