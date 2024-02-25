package ru.zubkoff.sber.stream;

import java.util.function.Function;

class TransformStream<I, O> extends Stream<O> {

  private final Stream<I> source;
  private final Function<? super I, O> transform;

  public TransformStream(Stream<I> source, Function<? super I, O> transform) {
    this.source = source;
    this.transform = transform;
  }

  @Override
  public boolean hasNext() {
    return source.hasNext();
  }

  @Override
  public O next() {
    return transform.apply(source.next());
  }

}