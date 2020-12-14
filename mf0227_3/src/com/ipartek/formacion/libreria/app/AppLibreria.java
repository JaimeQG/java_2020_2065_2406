package com.ipartek.formacion.libreria.app;

import java.math.BigDecimal;
import java.util.Scanner;

import com.ipartek.formacion.libreria.accesodatos.LibroDaoTreeMap;
import com.ipartek.formacion.libreria.modelos.Libro;

/**
 * Clase principal para ejecutar el Main y usar nuestra App. <br>
 * Se encarga de pedir datos por consola al usuario y llamara al MODELO( dao )
 * para gestionar los Libros.
 * 
 * @author Jaime Quintana
 *
 */
public class AppLibreria {

	static final protected String OP_LISTAR = "1";
	static final protected String OP_CREAR = "2";
	static final protected String OP_ELIMINAR = "3";
	static final protected String OP_SALIR = "s";

	private static LibroDaoTreeMap libreria = LibroDaoTreeMap.getInstancia();
	private static String opcion = "";

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("************************************");
		System.out.println("*********  APP   LIBRERIA  *********");

		do {
			pintarMenu("Libro");
			System.out.println("Selecciona una opcion:");
			opcion = sc.nextLine();

			switch (opcion) {
			case OP_LISTAR:
				listar();
				break;

			case OP_CREAR:
				crear();
				break;

			case OP_ELIMINAR:
				eliminar();
				break;

			case OP_SALIR:
				System.out.println("Adios, vuelve pronto.....");
				break;

			default:
				System.out.println("* Opcion no permitida");
				break;
			}

		} while (!OP_SALIR.equalsIgnoreCase(opcion));

	}

	private static void listar() {

		System.out.println("-------------------------------------");
		System.out.println("          LISTADO DE LIBROS   ");
		System.out.println("-------------------------------------");

		for (Libro libro : libreria.obtenerTodos()) {
			System.out.println(String.format("[%s] %-17s (%s) ........... [%.2f€ %s] (%s)", libro.getId(),
					libro.getNombre(), libro.getAutor(), libro.getPrecio(),
					(libro.getDescuento() > 0 ? "- Descuento: " + libro.getDescuento() + "%" : ""),
					libro.getUrlImagen()));
		}

		System.out.println("-------------------------------------");
		System.out.println("TOTAL numero de libros: " + libreria.numeroRegistros());
		System.out.println("-------------------------------------");
		System.out.println("\n");

	}

	private static void eliminar() {

		// Mostramos un listado con los libros
		listar();

		// variables
		boolean flag = true;
		boolean isError = true;
		Long id = 0L;
		Libro libroEliminar = null;

		do {
			do {
				try {
					System.out.println("Introduce el ID del libro que quieres borrar:");
					id = Long.parseLong(sc.nextLine());

					// si la linea de arriba lanza excepcion, estas de abajo nunca se ejecutaran
					isError = false;
				} catch (Exception e) {
					System.out.println("**error, no es un numero valido. Escribe un numero");
				}
			} while (isError);

			libroEliminar = libreria.obtenerPorId(id); // recuperamos el libro a suprimir dentro del ArrayList

			if (libroEliminar == null) {
				System.out.println("*** Lo sentimos pero no existe ese libro");
			} else {
				flag = false;
			}

		} while (flag);

		// Pedimos confirmacion sobre el borrado del libro del TreeMap
		// pedir confirmacion de nombre para eliminar
		flag = true;

		do {
			System.out.printf("Por favor escribe [%s] para eliminar o \"s\" para [S]alir\n", libroEliminar.getNombre());
			String nombre = sc.nextLine();

			if (OP_SALIR.equalsIgnoreCase(nombre)) {
				break; // salimos del bucle

			} else { // comprobar nombre

				if (libroEliminar.getNombre().equalsIgnoreCase(nombre)) {

					try {
						libreria.eliminar(id);
						flag = false;
						System.out.println("-------------------------------------");
						System.out.printf("Hemos dado de baja el libro: %s (%s) \n", libroEliminar.getNombre(),
								libroEliminar.getAutor());
						System.out.println("-------------------------------------");

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					System.out.println("**No coincide el nombre**");
				}
			}

		} while (flag);

	}

	private static void crear() {
		// variables
		Integer descuento = 0;
		String nombre = "";
		BigDecimal precio = new BigDecimal(0);

		boolean isError = true;

		// pedimos datos del libro por consola
		try {
			nombre = validarString("nombre"); // Nombre y descripción libro
		} catch (Exception e) {
			e.printStackTrace();
		}

		do { // controlamos que no se meta un caracter o dato erroneo
			try {
				System.out.println("Introduzca precio del libro:  \n");
				precio = new BigDecimal(sc.nextLine());
				if (precio == null || precio.compareTo(BigDecimal.ZERO) < 0) {
					System.out.println("** error: el precio tiene que ser mayor que 0");
				} else {
					isError = false;
				}
			} catch (NumberFormatException e) {
				System.out.println("**error: no es un numero valido");

			} catch (Exception e) {
				System.out.println("**error: no es un numero valido");
			}
		} while (isError);

		try {
			descuento = validarInt("descuento"); // descuento
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Introduzca autor del libro: "); // autor libro
		String autor = sc.nextLine();

		System.out.println("Introduzca url de la imagen del libro: "); // urlImagen libro
		String urlImagen = sc.nextLine();

		// Crear un libro y setear valores
		Libro libroNuevo = new Libro();

		libroNuevo.setNombre(nombre);
		libroNuevo.setPrecio(precio);
		libroNuevo.setDescuento(descuento);
		libroNuevo.setAutor(autor);
		libroNuevo.setUrlImagen(urlImagen);

		// llamar al modelo para guardar en TreeMap
		do {
			try {
				libreria.crear(libroNuevo);
				System.out.println("-------------------------------------");
				System.out.println("Libro guardado");
				System.out.println(libroNuevo);
				System.out.println("-------------------------------------");
				isError = false;
			} catch (Exception e) {
				System.out.println("**error: No se ha podido crear el libro");
			}
		} while (isError);
	}

	/**
	 * Saca por pantalla el menú de opciones
	 * 
	 * @param
	 * @return
	 */
	private static void pintarMenu(final String nombrePojo) {

		System.out.println("************************************");
		System.out.println(" " + OP_LISTAR + ".- Listar todos los " + nombrePojo);
		System.out.println(" " + OP_CREAR + ".- Crear un " + nombrePojo);
		System.out.println(" " + OP_ELIMINAR + ".- Dar de baja un " + nombrePojo);
		System.out.println(" ");
		System.out.println(" " + OP_SALIR + " - Salir");
		System.out.println("************************************");
	}

	/**
	 * Valida que los datos introducidos por pantalla son de tipo int
	 * 
	 * @param nombre de la propiedad de tipo entero de la Clase Libro
	 * @return valor de tipo entero
	 */
	private static int validarInt(String propiedad) throws Exception {

		boolean isError = true;
		int intEntero = 0;

		// Repetir hasta que no haya error
		do {
			System.out.println("Introduzca el " + propiedad + " del libro: ");
			try {
				intEntero = Integer.parseInt(sc.nextLine());

				// si la linea de arriba lanza excepcion, estas de abajo nunca se ejecutaran
				if (intEntero < 0 || intEntero > 100) {
					System.out.println("**error, el " + propiedad + " debe estar entre 0 y 100");
				} else {
					isError = false;
				}
			} catch (Exception e) {
				// si quereis ver la traza de la Excepcion, usar e.printStackTrace()
				System.out.println("**error, no es un numero valido");
			}

		} while (isError);

		return intEntero;
	}

	/**
	 * Valida que los datos introducidos por pantalla son de tipo String
	 * 
	 * @param nombre de la propiedad de tipo String
	 * @return valor de tipo String cuya longitud sea > 2 y < 150 caracteres
	 */
	private static String validarString(String propiedad) throws Exception {

		boolean isError = true;
		String nombre = "";

		// Repetir hasta que no haya error
		do {
			System.out.println("Introduzca el " + propiedad + " del libro: ");
			nombre = sc.nextLine();
			if (nombre.trim().length() < 2 || nombre.trim().length() > 150) {
				System.out.println("**error, el " + propiedad + " debe tener más de 2 letras y menos de 150. " + nombre
						+ " tiene " + nombre.length() + " caracteres \n");
			} else {
				isError = false;
			}
		} while (isError);

		return nombre;
	}
}
