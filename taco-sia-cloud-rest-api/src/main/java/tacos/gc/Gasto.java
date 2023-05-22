package tacos.gc;


import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class Gasto {
	final String desc;
	final int importe;
	
}
