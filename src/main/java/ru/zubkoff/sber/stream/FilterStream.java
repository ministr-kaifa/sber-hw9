package ru.zubkoff.sber.stream;

import java.util.NoSuchElementException;
import java.util.function.Predicate;

class FilterStream<T> extends Stream<T> {

  private final Stream<T> source;
  private final Predicate<? super T> filter;
  private boolean nextIsFresh;
  private T next;

  public FilterStream(Stream<T> source, Predicate<? super T> filter) {
    this.filter = filter;
    this.source = source;
    this.nextIsFresh = false;
  }

  private boolean tryUpdateLocalNext() {
    do {
      if(!source.hasNext()) {
        return false;
      }
      next = source.next();
    } while (!filter.test(next));
    nextIsFresh = true;
    return true;
  }

  @Override
  public boolean hasNext() {
    return nextIsFresh || tryUpdateLocalNext();
  }

  @Override
  public T next() {
    if(hasNext()) {
      nextIsFresh = false;
      return next;
    } else {
      throw new NoSuchElementException();
    }
  }

}