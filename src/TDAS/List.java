/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package TDAS;

/**
 *
 * @author USER1
 */
public interface List<E> extends Iterable<E> {
    
     boolean addFirst(E element);
   boolean addLast(E element);
   boolean removeFirst();
   boolean removeLast();
   E getFirst();
   E getLast();
   boolean insert(int index, E element);
   boolean contains(E element);
   E get(int index);
   int indexOf(E element);
   boolean isEmpty();
   E remove(int index);
   boolean remove(E element);
   E set(int index, E element);
   int size();
}
