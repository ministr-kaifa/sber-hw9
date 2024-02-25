import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import ru.zubkoff.sber.stream.Stream;

class StreamTest {

  @Test
  void givenIntArray_whenTransformToString_thenResultSameAsJavaStream() {
    //given
    Integer[] intArray = {1,2,3,4};

    //when
    var result = Stream.of(intArray)
      .transform(Object::toString)
      .toList().toArray();

    //then
    assertArrayEquals(
      java.util.stream.Stream.of(intArray)
        .map(Object::toString)
        .toList().toArray(), 
        result
    );
  }

  @Test
  void givenIntArray_whenToMap_thenResultSameAsJavaStream() {
    //given
    Integer[] intArray = {1,2,1,4};

    //when
    var result = Stream.of(intArray)
      .toMap(Function.identity(), Function.identity(), Integer::sum);
      
    //then
    assertEquals(
      java.util.stream.Stream.of(intArray)
        .collect(Collectors.toMap(Function.identity(), Function.identity(), Integer::sum)), 
      result
    );
  }
  
}