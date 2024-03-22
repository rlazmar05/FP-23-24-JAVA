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
	    
	    
	    //INICIO DEFENSA 1
	    
	    //APARTADO A
	    
	    public static long FuncionP2 (int n, int k, int i) {
	    	if (n < k) {
	            throw new IllegalArgumentException("n debe ser mayor o igual a k");
	        }
	    	if (i > k+1) {
	    		throw new IllegalArgumentException("i debe ser menor que el valor de k+1");
	    	}
	    	long resultado = 1;
	        for (int valor = i; valor < k + 1; valor++) {
	            resultado *= (valor - 2) * (n - valor);
	        }

	        return resultado;
	    	
	    }
	    
	    //APARTADO B
	    
	    public static long FuncionC2 (int n, int k) {
	    	if (n < k) {
	            throw new IllegalArgumentException("n debe ser mayor a k");
	        }
			if (n < 0 || k < 0) {
				throw new IllegalArgumentException("n y k deben ser positivos");
			}
			long coeficienteBinomial = factorial(n) / (factorial(k + 1) * factorial(n - (k + 1)));

			return coeficienteBinomial;	
	    }
	    
	    private static long factorial(int num) {
	        long resultado = 1;
	        for (int i = 1; i <= num; i++) {
	            resultado *= i;
	        }
	        return resultado;
	    }
	    
	    //APARTADO C
	    
	    public static long FuncionS2(int n, int k) {
	        if (n < k) {
	            throw new IllegalArgumentException("El valor de 'n' debe ser mayor o igual que 'k'.");
	        }

	        long res = (factorial(k) / factorial(k + 2)) * sumatoria(n, k);
	        
	        return res;
	    }
	    
	    private static long sumatoria(int n, int k) {
	        long sumatoria = 0;
	        for (int i = 0; i < k + 1; i++) {
	            sumatoria += Math.pow(-1, i) * coeficienteBinomial(k, i) * Math.pow(k - i, n);
	        }
	        return sumatoria;
	    }
	    
	    private static long coeficienteBinomial(int n, int k) {
	        return factorial(n) / (factorial(k) * factorial(n - k));
	    }
	     
}

