package ru.zubkoff.sber.stream;

import java.util.Arrays;
import java.util.Iterator;

class SourceStream<T> extends Stream<T> {

  private final Iterator<T> sourceDataIterator;

  public SourceStream(T[] sourceData) {
    sourceDataIterator = Arrays.asList(sourceData).iterator();
  }

  @Override
  public T next() {
    return sourceDataIterator.next();
  } 

  @Override
  public boolean hasNext() {
    return sourceDataIterator.hasNext();
  }

}