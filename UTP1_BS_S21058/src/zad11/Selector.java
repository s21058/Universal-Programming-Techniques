/**
 *
 *  @author Bozhko Sviatoslav S21058
 *
 */

package zad11;


public interface Selector<T> { // Uwaga: interfejs musi być sparametrtyzowany
    <T>  boolean select(T value);
}  
