package zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {
    T value;
    Maybe(T value){
        this.value=value;
    }
    Maybe() {

    }
    public static <T>Maybe<T>of(T value){
        return new Maybe<>(value);
    }
    public void ifPresent(Consumer<T> consumer){
        if(!isPresent()){

        }else{
            consumer.accept(value);
        }
    }
    public <R>Maybe<R>map(Function<T,R>function){
        if(!isPresent()){
            return new Maybe<>();
        }else{
            R x=function.apply(this.value);
            return new Maybe<>(x);
        }
    }
    public T get(){
        if(!isPresent()){
            throw new NoSuchElementException(" maybe is empty");
        }else{
            return value;
        }
    }
    public T orElse(T defVal) {
        if (!isPresent()) {
            return defVal;
        } else {
            return value;
        }
    }
    public <T>Maybe<T>filter(Predicate<T>predicate){
        if(isPresent()){
            if(predicate.test((T) value)){
                return (Maybe<T>) this;
            }
        }
        return (Maybe<T>) this;
    }

    @Override
    public String toString() {
        if(!isPresent()){
            return "Maybe is empty";
        }else {
            return  "Maybe has value "+value;
        }
    }
    public boolean isPresent(){
        if(value==null){
            return false;
        }else {
            return true;
        }
    }
}
