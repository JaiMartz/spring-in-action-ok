package tacos.gc;

import java.util.stream.IntStream;

public class Principal2 {

	public static void main(String args[]) {

		int resultado = IntStream.of(5, 2, 7, 9).reduce(0, (x, y) -> x + y);
		System.out.println(resultado);
	}
}

/* reduceEquivale a 
int result = identity;
for (int element : this stream)
    result = accumulator.applyAsInt(result, element)
return result;
*/