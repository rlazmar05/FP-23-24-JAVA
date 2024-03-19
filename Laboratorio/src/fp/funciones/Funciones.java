package fp.funciones;
import java.util.List;
import java.util.ArrayList;

public class Funciones {
	
	 public static boolean Primo(int numero) {
	        if (numero <= 1) {
	            return false;
	        }
	        for (int i = 2; i * i <= numero; i++) {
	            if (numero % i == 0) {
	                return false;
	            }
	        }
	        return true;
	    }
	 
	 
	 public static long Combinatorio(int n, int k) {
	        if (n < k) {
	            throw new IllegalArgumentException("n debe ser mayor o igual a k");
	        }
	        if (n < 0 || k < 0) {
	            throw new IllegalArgumentException("n y k deben ser positivos");
	        }
	        return Factorial(n) / (Factorial(k) * Factorial(n - k));
	    }

	   

	    public static long Factorial(int num) {
	    	if (num == 0 || num == 1) {
	            return 1;
	        } else {
	            return num * Factorial(num - 1);
	        }
	    }
	
	    
	    public static double S(int n, int k) {
	        if (n < k) {
	            throw new IllegalArgumentException("n debe ser mayor o igual que k");
	        }
	        if (n < 0 || k < 0) {
	            throw new IllegalArgumentException("n y k deben ser positivos");
	        }
	        double res = 0;
	        for (int i = 0; i <= k; i++) {
	            res += Math.pow(-1, i) * Combinatorio(k, i) * Math.pow(k - i, n);
	        }
	        return (1.0 / Factorial(k)) * res;
	    }
	    
	    
	    public static List<Integer>Diferencias(List<Integer> lista) {
	        List<Integer> diferencias = new ArrayList<>();
	        for (int i = 1; i < lista.size(); i++) {
	            int diferencia = lista.get(i) - lista.get(i - 1);
	            diferencias.add(diferencia);
	        }
	        return diferencias;
	    }
	    
	    
	    public static String encontrarCadenaMasLarga(List<String> lista) {
	        String cadenaMasLarga = "";
	        int longitudMaxima = Integer.MIN_VALUE; // Inicializamos con el valor mínimo posible

	        for (String cadena : lista) {
	            int longitudActual = cadena.length();
	            if (longitudActual > longitudMaxima) {
	                longitudMaxima = longitudActual;
	                cadenaMasLarga = cadena;
	            }
	        }

	        return cadenaMasLarga;
	    }
	    
	    
	    
}

