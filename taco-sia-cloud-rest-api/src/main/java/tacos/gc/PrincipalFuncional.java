package tacos.gc;

import java.util.ArrayList;

public class PrincipalFuncional {

	public static void main(String[] args) {

		ArrayList<Gasto> lista = new ArrayList<Gasto>();

		lista.add(new Gasto("A", 80));
		lista.add(new Gasto("B", 50));
		lista.add(new Gasto("C", 70));
		lista.add(new Gasto("D", 95));

		double resultado = lista.stream().mapToDouble(gasto -> gasto.getImporte() * 1.21).filter(gasto -> gasto < 100)
				.sum();

		System.out.println(resultado);

	}

}