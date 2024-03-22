package fp.funciones.test;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import fp.funciones.Funciones;

public class TestFunciones {
	
	public static void main (String[] args) {
		
		Scanner scanner = new Scanner(System.in);
	
		//TEST NUMERO PRIMO
		        		        
		System.out.print("Ingresa un número para verificar si es primo: ");
		int numero = scanner.nextInt();
		        
		System.out.println(numero + " es primo: " + Funciones.Primo(numero));
		        
		        		    
		
		//TEST COMBINATORIO
		       		
		System.out.print("Ingresa el valor de n: ");
		int n = scanner.nextInt();
		
		System.out.print("Ingresa el valor de k: ");
		int k = scanner.nextInt();
		
		try {
		    long resultado = Funciones.Combinatorio(n, k);
		    System.out.println("El coeficiente binomial C(" + n + ", " + k + ") es: " + resultado);
		} catch (IllegalArgumentException e) {
		    System.out.println("Error: " + e.getMessage());
		        }
		
		
		//TEST S
		        		
		 System.out.print("Ingresa el valor de n: ");
		 int n1 = scanner.nextInt();
		
		 System.out.print("Ingresa el valor de k: ");
		 int k1 = scanner.nextInt();
		
		 try {
		      double resultado = Funciones.S(n1, k1);
		      System.out.println("El resultado de S(" + n1 + ", " + k1 + ") es: " + resultado);
		 } catch (IllegalArgumentException e) {
		      System.out.println("Error: " + e.getMessage());
		        }
	    
		
		//Test Diferencias
		        
		 // Creamos una lista de enteros para después trabajar sobre ella
		 List<Integer> DiferenciasLista = new ArrayList<>();
		 DiferenciasLista.add(25);
		 DiferenciasLista.add(40);
		 DiferenciasLista.add(78);
		 DiferenciasLista.add(93);
		 DiferenciasLista.add(107);
		
		 // Calculamos las diferencias entre los elementos adyacentes
		 List<Integer> diferencias = Funciones.Diferencias(DiferenciasLista);
		
		 // Imprimimos las diferencias que se han obtenido
		 System.out.println("Diferencias entre los elementos adyacentes:");
		 for (int diferencia : diferencias) {
		   System.out.println(diferencia);
		   }
		    
		
		 //Test encontrarCadenaMasLarga
		 
		 // Creamos una lista de cadenas
		 List<String> miLista = new ArrayList<>();
		 miLista.add("Esternocleidomastoideo");
		 miLista.add("Programación");
		 miLista.add("Farmaceútico");
		 miLista.add("Murciélago");
		 miLista.add("Electroencefalografista");
		        
	
		 // Encontramos la cadena más larga de la lista anterior
		 String cadenaMasLarga = Funciones.encontrarCadenaMasLarga(miLista);
	
		 // Imprimimos la cadena más larga
		 System.out.println("La cadena más larga es: " + cadenaMasLarga);
		        
		 		 
		        
		 //INICIO DEFENSA
		 
		 //APARTADO A
		 
		 System.out.print("Ingrese el valor de n: ");
         int n2 = scanner.nextInt();

         System.out.print("Ingrese el valor de k: ");
         int k2 = scanner.nextInt();

         System.out.print("Ingrese el valor de i: ");
         int i = scanner.nextInt();

         long resultado = Funciones.FuncionP2(n2, k2, i);
         System.out.println("El resultado es: " + resultado);
		 
		 //APARTADO B
		 
		 System.out.print("Ingrese el valor de n: ");
         int n3 = scanner.nextInt();

         System.out.print("Ingrese el valor de k: ");
         int k3 = scanner.nextInt();

         long coeficienteBinomial = Funciones.FuncionC2(n3, k3);
         System.out.println("El coeficiente binomial es: " + coeficienteBinomial);
		 
         
         //APARTADO C
         
         System.out.print("Ingrese el valor de n: ");
         int n4 = scanner.nextInt();

         System.out.print("Ingrese el valor de k: ");
         int k4 = scanner.nextInt();

         long res = Funciones.FuncionS2(n4, k4);
         System.out.println("El resultado es: " + res);
         
         
         scanner.close();
	}
}
		   




