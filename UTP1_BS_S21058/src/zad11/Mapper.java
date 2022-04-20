/**
 *
 *  @author Bozhko Sviatoslav S21058
 *
 */

package zad11;


public interface Mapper<T,V> { // Uwaga: interfejs musi być sparametrtyzowany
    <V>Integer map(T value);
}  
