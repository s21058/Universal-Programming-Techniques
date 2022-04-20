/**
 *
 *  @author Bozhko Sviatoslav S21058
 *
 */

package zad11;


import java.util.ArrayList;
import java.util.List;

public class ListCreator <T> { // Uwaga: klasa musi być sparametrtyzowana
    List<T> list;
    List<T> newlist;
    ListCreator(List<T>list){
        this.list=list;
    }
    public static <T> ListCreator<T>collectFrom(List<T>src){
        return new ListCreator<>(src);
    }
    public ListCreator<T>when(Selector<T>selector){
        newlist=new ArrayList<>();
        for (T value:list){
            if(selector.select(value)){
                newlist.add(value);
            }
        }
        list=newlist;
        return this;
    }
    public List<T>mapEvery(Mapper mapper){
newlist=new ArrayList<>();
        for(T value:list){
            newlist.add((T) mapper.map(value));
        }
        return newlist;
    }
}  
