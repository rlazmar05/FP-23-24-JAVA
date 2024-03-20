package fp.tipos.test;
import fp.tipos.Fecha;


public class TestFecha {
	public static void testMetodosFecha() {
		Fecha fecha1 = Fecha.parse("2024-3-20");
		Fecha fecha2 = Fecha.parse("2005-11-4");
		//System.out.println(fecha1.equals(fecha2));
		System.out.println(fecha1.diferenciaEnDías(fecha2));
		//System.out.println(fecha2);
		//System.out.println(fecha1.sumarDías(500));
		//System.out.println(fecha1.restarDías(11));
		//System.out.println(fecha1.esAñoBisiesto(2024));
		//System.out.println(Fecha.of(2005, 11, 04));
		//System.out.println(Fecha.parse("2023-07-22"));
		//System.out.println(fecha1.DiaSemana());
		//System.out.println(fecha1.congruenciaZeller(2005, 11, 04));
	}
	
	
	public static void main(String[] args) {
		testMetodosFecha();
		
	}

}
