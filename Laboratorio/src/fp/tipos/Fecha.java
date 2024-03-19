package fp.tipos;

import java.util.List;
import java.util.Objects;
import java.time.LocalDate;

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
        String[] diasSemana = {"lunes", "martes", "miércoles", "jueves", "viernes", "sábado", "domingo"};
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
                cantidadDias -= cantidadDias - diasRestantesMes;
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

    public int diferenciaEnDías(Fecha otraFecha) {
    	LocalDate fecha1 = LocalDate.of(año, mes, dia);
        LocalDate fecha2 = LocalDate.of(otraFecha.año, otraFecha.mes, otraFecha.dia);
        return Math.abs((int) fecha1.until(fecha2).getDays());
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
        String[] díasSemana = {"lunes", "martes", "miércoles", "jueves", "viernes", "sábado", "domingo"};
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
