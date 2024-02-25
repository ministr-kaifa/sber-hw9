package ru.zubkoff.sber.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class Stream<T> implements Iterator<T> {

  public static <T> Stream<T> of(T[] objects) {
    return new SourceStream<>(objects);
  }

  public Stream<T> filter(Predicate<? super T> filter) {
    return new FilterStream<>(this, filter);
  }

  public <O> Stream<O> transform(Function<? super T, O> transform) {
    return new TransformStream<>(this, transform);
  }

  public List<T> toList() {
    List<T> result = new ArrayList<>();
    while(hasNext()) {
      result.add(next());
    }
    return result;
  }

  public <K, V> Map<K, V> toMap(Function<? super T, K> keyMapper,
                                Function<? super T, V> valueMapper, 
                                BinaryOperator<V> collisionHandler) {
    var result = new HashMap<K, V>();
    while (hasNext()) {
      var next = next();
      var key = keyMapper.apply(next);
      var val = valueMapper.apply(next);
      if(!result.containsKey(keyMapper.apply(next))) {
        result.put(key, val);
      } else {
        result.put(key, collisionHandler.apply(result.get(key), val));
      }
    }
    return result;
  }

}