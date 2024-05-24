package TipoBanco;

import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.ejemplos_b1_tipos.Persona;
import us.lsi.tools.Preconditions;

public class ExamenEntrega3 {

	public static List<Empleado> empleadosEntreDiaMes(Banco banco, String ini, String fin) {
		Preconditions.checkNotNull(ini, "El parámetro 'ini' no puede ser nulo");
		Preconditions.checkNotNull(fin, "El parámetro 'fin' no puede ser nulo");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
		MonthDay inicio;
		MonthDay finDiaMes;

		try {
			inicio = MonthDay.parse(ini, formatter);
			finDiaMes = MonthDay.parse(fin, formatter);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Formato de fecha inválido. El formato debe ser DD/MM.");
		}

		List<Empleado> empleados = banco.empleados().todos().stream().filter(empleado -> {
			LocalDateTime fechaNacimiento = empleado.persona().fechaDeNacimiento();
			MonthDay fechaNacDiaMes = MonthDay.of(fechaNacimiento.getMonth(), fechaNacimiento.getDayOfMonth());

			if (inicio.isBefore(finDiaMes) || inicio.equals(finDiaMes)) {

				return !fechaNacDiaMes.isBefore(inicio) && !fechaNacDiaMes.isAfter(finDiaMes);
			} else {

				return !fechaNacDiaMes.isBefore(inicio) || !fechaNacDiaMes.isAfter(finDiaMes);
			}
		}).collect(Collectors.toList());

		return empleados;

	}

	public static Map<Character, List<Empleado>> empleadosConLetraDni(Banco banco, Set<Character> letras) {
		Preconditions.checkNotNull(banco, "El parámetro 'banco' no puede ser nulo");
		Preconditions.checkNotNull(letras, "El parámetro 'letras' no puede ser nulo");

		boolean allAlphabetic = letras.stream().allMatch(Character::isLetter);
		Preconditions.checkArgument(allAlphabetic, "El conjunto de letras solo debe contener caracteres alfabéticos");

		Map<Character, List<Empleado>> empleadosPorLetra = banco.empleados().todos().stream().filter(empleado -> {
			String dni = empleado.persona().dni();
			return dni != null && !dni.isEmpty() && letras.contains(dni.charAt(dni.length() - 1));
		}).collect(Collectors
				.groupingBy(empleado -> empleado.persona().dni().charAt(empleado.persona().dni().length() - 1)));

		return empleadosPorLetra;

	}

	public static Optional<Double> clientesEdadMedia(Banco banco, int m) {
		var clientesMayores = banco.personas().todos().stream().filter(c -> c.edad() > m).mapToInt(Persona::edad)
				.boxed().collect(Collectors.toList());

		if (clientesMayores.isEmpty()) {
			return Optional.empty();
		}

		double edadMedia = clientesMayores.stream().mapToInt(Integer::intValue).average().getAsDouble();
		return Optional.of(edadMedia);

	}

	public static Set<String> clientesConMenosPrestamos(Banco banco, int n) {
		Preconditions.checkNotNull(banco, "El parámetro 'banco' no puede ser nulo");
		Preconditions.checkArgument(n > 0, "El parámetro 'n' debe ser mayor que cero");

		Map<Persona, Long> prestamosPorCliente = banco.personas().todos().stream().collect(
				Collectors.toMap(persona -> persona, persona -> (long) banco.prestamosDeCliente(persona.dni()).size()));

		Comparator<Map.Entry<Persona, Long>> sortByPrestamos = Comparator.comparing(Map.Entry::getValue);

		Set<String> clientesOrdenados = prestamosPorCliente.entrySet().stream().sorted(sortByPrestamos).limit(n)
				.map(entry -> entry.getKey().dni()).collect(Collectors.toCollection(LinkedHashSet::new));

		return clientesOrdenados;

	}

	public static void main(String[] args) {
		Banco banco = Banco.of();

		System.out.println("Empleados nacidos entre 01/06 y 31/12:");
		List<Empleado> empleados = empleadosEntreDiaMes(banco, "01/06", "31/12");
		empleados.forEach(empleado -> System.out.println(empleado.persona().nombre()));

		System.out.println("__________");

		System.out.println("\nEmpleados con DNI que termina en 'A', 'B' o 'Z' :");
		Set<Character> letrasDNI = new HashSet<>();
		letrasDNI.add('A');
		letrasDNI.add('B');
		letrasDNI.add('Z');
		Map<Character, List<Empleado>> empleadosPorLetra = empleadosConLetraDni(banco, letrasDNI);
		empleadosPorLetra.forEach((letra, listaEmpleados) -> {
			System.out.println("Letra " + letra + ":");
			listaEmpleados.forEach(empleado -> System.out.println(empleado.persona().nombre()));
		});
		
		System.out.println("___________");
		
		System.out.println("\nEdad media de clientes mayores de 110:");
	    Optional<Double> edadMedia = clientesEdadMedia(banco, 110);
	    if (edadMedia.isPresent()) {
	        System.out.println("Edad media: " + edadMedia.get());
	    } else {
	        System.out.println("No hay clientes mayores de 110.");
	    }
	    
	    System.out.println("___________");
	    
	    
	    System.out.println("\nClientes con menos de 8 préstamos:");
	    Set<String> clientes = clientesConMenosPrestamos(banco, 8);
	    clientes.forEach(System.out::println);
	}

}
