package fp.tipos.test;
import fp.tipos.Fecha;


public class TestFecha {
	public static void testMetodosFecha() {
		Fecha fecha1 = Fecha.parse("2005-7-23");
		Fecha fecha2 = Fecha.parse("2024-3-18");
		System.out.println(fecha1.equals(fecha2));
		System.out.println(fecha1.diferenciaEnDías(fecha2));
		System.out.println(fecha1);
		System.out.println(fecha1.sumarDías(53));
		System.out.println(fecha1.restarDías(11));
		System.out.println(fecha1.esAñoBisiesto(2007));
		System.out.println(Fecha.of(2007, 11, 23));
		System.out.println(Fecha.parse("2023-07-22"));
	}
	
	
	public static void main(String[] args) {
		testMetodosFecha();
		
	}

}
