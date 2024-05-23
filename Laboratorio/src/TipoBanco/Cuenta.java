package TipoBanco;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cuenta {
	public String iban;
	public String dni;
	public LocalDate fechacreacion;
	public Double saldo;
	
	
	public void ingresar (Double c) {
		saldo += c;
	}
	
	public void retirar (Double c) {
		saldo -=c;
	}
	
	private Cuenta(String iban, String dni, LocalDate fechacreacion, Double saldo) {
		this.iban = iban;
		this.dni = dni;
		this.fechacreacion = fechacreacion;
		this.saldo = saldo;
	}
	
	public static Cuenta of(String iban, String dni, LocalDate fechacreacion, Double saldo) {
		return new Cuenta(iban, dni, fechacreacion, saldo);
	}
	
	public static Cuenta parse(String text) {
		DateTimeFormatter fm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String[] partes = text.split(",");
		LocalDate fechacreacion = LocalDateTime.parse(partes[2],fm).toLocalDate();
		Double saldo = Double.parseDouble(partes[3]);
		return Cuenta.of(partes[0], partes[1], fechacreacion, saldo);
	}
	
	public String getDni() {
		return dni;
	}
	
	public String getIban() {
		return iban;
	}
		
	@Override
	public String toString() {
		return iban + "," + saldo;
	}
	
	@Override
	public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cuenta cuenta = (Cuenta) obj;
        return iban.equals(cuenta.iban) &&
                dni.equals(cuenta.dni) &&
                fechacreacion.equals(cuenta.fechacreacion) &&
                saldo.equals(cuenta.saldo);
    }
	
	public static void main(String[] args) {
		
	}

}
