package fp.tipos;

import java.util.List;
import java.util.Objects;


public record Fecha(int año, int mes, int dia) {

    public static Fecha of(int año, int mes, int dia) {
        return new Fecha(año, mes, dia);
    }

    public static Fecha parse(String cadenaFecha) {
        String[] partes = cadenaFecha.split("-");
        if (partes.length != 3) {
            throw new IllegalArgumentException("Formato de fecha incorrecto. Debe ser 'yyyy-MM-dd'.");
        }
        int año = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int dia = Integer.parseInt(partes[2]);
        return new Fecha(año, mes, dia);
    }

    public String NombreMes() {
        List<String> meses = List.of("enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre");
        return meses.get(mes - 1);
    }

    public String DiaSemana() {
        int h;
        int añoActual = año;
        int mesActual = mes;

        if (mesActual < 3) {
            añoActual--;
            mesActual += 12;
        }
        int K = añoActual % 100;
        int J = añoActual / 100;
        h = (dia + 13 * (mesActual + 1) / 5 + K + K / 4 + J / 4 + 5 * J) % 7;
        String[] diasSemana = {"sabado", "domingo", "lunes", "martes", "miércoles", "jueves", "viernes"};
        return diasSemana[h];
    }

    public Fecha sumarDías(int cantidadDias) {
        int diaActual = dia;
        int mesActual = mes;
        int añoActual = año;

        while (cantidadDias > 0) {
            int diasMesActual = diasEnMes(añoActual, mesActual);
            int diasRestantesMes = diasMesActual - diaActual + 1;

            if (cantidadDias >= diasRestantesMes) {
                cantidadDias -= diasRestantesMes;
                diaActual = 1;
                mesActual++;

                if (mesActual > 12) {
                    mesActual = 1;
                    añoActual++;
                }
            } else {
                diaActual += cantidadDias;
                cantidadDias = 0;
            }
        }

        return new Fecha(añoActual, mesActual, diaActual);
    }

    public Fecha restarDías(int cantidadDias) {
        int diaActual = dia;
        int mesActual = mes;
        int añoActual = año;

        while (cantidadDias > 0) {
            if (diaActual > cantidadDias) {
                diaActual -= cantidadDias;
                cantidadDias = 0;
            } else {
                cantidadDias -= diaActual;
                mesActual--;

                if (mesActual < 1) {
                    mesActual = 12;
                    añoActual--;
                }

                diaActual = diasEnMes(añoActual, mesActual);
            }
        }

        return new Fecha(añoActual, mesActual, diaActual);
    }
    
    public static boolean fechaMayor(Fecha fecha1, Fecha fecha2) {
    	if (fecha1.año > fecha2.año) {
    		return true;
    	} else if (fecha1.año == fecha2.año) {
    		if (fecha1.mes > fecha2.mes) {
    			return true;
    		} else if (fecha1.mes == fecha2.mes) {
    			return fecha1.dia > fecha2.dia;
    		}
    	}
    	return false;
    }

    public int diferenciaEnDías(Fecha otraFecha) {
    	int d;
    	Fecha fechaMayor = new Fecha(año, mes, dia);
    	Fecha fechaMenor = new Fecha(año, mes, dia);
    	Fecha fecha = new Fecha(año, mes, dia);
    	if (fecha == otraFecha) {
    		d = 0;
    	}
    	if (Fecha.fechaMayor(fecha, otraFecha)) {
    		d = 1;
    		fechaMenor = otraFecha;
    		fechaMayor = fecha;
    	} else {
    		d = -1;
    		fechaMenor = fecha;
    		fechaMayor = otraFecha;
    	}
    	int diasDiferencia = 0;
    	while (Fecha.fechaMayor(fechaMayor, fechaMenor)) {
    		diasDiferencia = diasDiferencia +1;
    		fechaMenor = fechaMenor.sumarDías(1);
    	}
    	return Math.abs(d * diasDiferencia);
    	}
    

    private int diasEnMes(int año, int mes) {
        int[] díasPorMes = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (mes == 2 && esAñoBisiesto(año)) {
            return 29;
        }
        return díasPorMes[mes];
    }

    public boolean esAñoBisiesto(int año) {
        return (año % 4 == 0 && año % 100 != 0) || (año % 400 == 0);
    }
    
    public String congruenciaZeller(int año, int mes, int dia) {
        if (mes < 3) {
            año--;
            mes += 12;
        }
        int K = año % 100;
        int J = año / 100;
        int h = (dia + 13 * (mes + 1) / 5 + K + K / 4 + J / 4 + 5 * J) % 7;
        String[] díasSemana = {"sábado", "domingo","lunes", "martes", "miércoles", "jueves", "viernes"};
        return díasSemana[h];
    }

    
    @Override
    public boolean equals(Object obj) {
    	if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Fecha otraFecha = (Fecha) obj;
        return año == otraFecha.año && mes == otraFecha.mes && dia == otraFecha.dia;
    }
    
    @Override
    public int hashCode() {
    	return Objects.hash(año, mes, dia);
    }
    
    @Override
    public String toString() {
        return DiaSemana() + ", " + dia + " de " + NombreMes() + " de " + año;
    }
}
