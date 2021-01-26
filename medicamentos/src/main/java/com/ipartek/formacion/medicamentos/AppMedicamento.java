package com.ipartek.formacion.medicamentos;

import java.math.BigDecimal;
import java.util.Scanner;

import com.ipartek.formacion.medicamentos.accesodatos.Dao;
import com.ipartek.formacion.medicamentos.controladores.Configuracion;
import com.ipartek.formacion.medicamentos.modelos.Medicamento;

public class AppMedicamento {
	static final private String NOMBRE_POJO = "Medicamento";

	static final private int MIN_LENGTH = 3;
	static final private int MAX_LENGTH = 50;

	static final protected String OP_LISTAR = "1";
	static final protected String OP_CREAR = "2";
	static final protected String OP_ELIMINAR = "3";
	static final protected String OP_SALIR = "s";

	private static Dao<Medicamento> dao = Configuracion.daoMedicamentos;

	private static String opcion = "";

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("******************************");
		System.out.println("****  APP   MEDICAMENTO  *****");

		do {
			pintarMenu(NOMBRE_POJO);
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

	/**
	 * Obtiene todos los medicamentos ordenados Alfabeticamente
	 * 
	 * @return Collection<Medicamento>, si no existen libros Lista vacia pero no
	 *         nula
	 */

	private static void listar() {

		System.out.println("-------------------------------------");
		System.out.println("       LISTADO DE MEDICAMENTOS       ");
		System.out.println("-------------------------------------");

		for (Medicamento medicamento : dao.obtenerTodos()) {
			System.out.println(String.format("[%s] (%s) %-17s  ........... [%.2f€]", medicamento.getId(),
					medicamento.getReferencia(), medicamento.getNombre(), medicamento.getPrecio()));
		}

		System.out.println("----------------------------------------");
		System.out.println("Número TOTAL de medicamentos en la BBDD: " + dao.numeroRegistros());
		System.out.println("----------------------------------------");
		System.out.println("\n");

	}

	/**
	 * Eliminar un Medicamento por su identificador
	 * 
	 * @param id Long identificador
	 * @return
	 */
	private static void eliminar() {

		// Mostramos un listado con los Medicamentos
		listar();

		// variables
		boolean flag = true;
		boolean isError = true;
		Long id = 0L;
		Medicamento medicamentoEliminar = null;

		do {
			do {
				try {
					System.out.println("Introduce el ID del medicamento que quieres borrar:");
					id = Long.parseLong(sc.nextLine());

					// si la linea de arriba lanza excepcion, estas de abajo nunca se ejecutaran
					isError = false;
				} catch (Exception e) {
					System.out.println("**error, no es un numero valido. Escribe un numero");
				}
			} while (isError);

			medicamentoEliminar = dao.obtenerPorId(id); // recuperamos el libro a suprimir dentro del ArrayList

			if (medicamentoEliminar == null) {
				System.out.println("*** Lo sentimos pero no existe ese medicamento");
			} else {
				flag = false;
			}

		} while (flag);

		// Pedimos confirmacion sobre el borrado del medicamento
		// pedir confirmacion de referencia para eliminar
		flag = true;

		do {
			System.out.printf("Por favor escribe [%s] para eliminar o \"s\" para [S]alir\n",
					medicamentoEliminar.getReferencia());
			String referencia = sc.nextLine();

			if (OP_SALIR.equalsIgnoreCase(referencia)) {
				break; // salimos del bucle

			} else { // comprobar nombre del medicamento

				if (medicamentoEliminar.getReferencia().equalsIgnoreCase(referencia)) {
					try {
						dao.eliminar(id);
						flag = false;
						System.out.println("-------------------------------------");
						System.out.printf("Hemos dado de baja el medicamento: %s (%s) \n",
								medicamentoEliminar.getReferencia(), medicamentoEliminar.getNombre());
						System.out.println("-------------------------------------");

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("*** error: No coincide la referencia del medicamento **");
				}
			}
		} while (flag);

	}

	private static void crear() {

		// variables
		String referencia = "";
		String nombre = "";
		BigDecimal precio = new BigDecimal(0);

		boolean isError = true;

		// pedimos datos del medicamento por consola
		try {
			referencia = validarString("referencia");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			nombre = validarString("nombre");
		} catch (Exception e) {
			e.printStackTrace();
		}

		do {
			try {
				System.out.println("Introduzca precio del medicamento:  \n"); // Precio medicamento
				String precioString = sc.nextLine();

				if (precioString.matches("\\d+\\.\\d\\d")) {
					precio = new BigDecimal(precioString);
					if (precio == null || precio.compareTo(BigDecimal.ZERO) < 0) {
						System.out.println("** error: el precio tiene que ser mayor que 0");
					} else {
						isError = false;
					}
				} else {
					System.out.println("**error: no es un número valido. Debe tener 2 decimales");
				}
			} catch (NumberFormatException e) {
				System.out.println("**error: no es un número valido");

			} catch (Exception e) {
				System.out.println("**error: no es un número valido");
			}
		} while (isError);

		// Crear un medicamento y setear valores
		/*
		 * Medicamento medicamentoNuevo = new Medicamento();
		 * 
		 * medicamentoNuevo.setReferencia(referencia);
		 * medicamentoNuevo.setNombre(nombre); medicamentoNuevo.setPrecio(precio);
		 */
		// llamar al modelo para guardar en la BBDD

		try {
			Medicamento medicamentoNuevo = dao.crearYObtener(new Medicamento(null, referencia, nombre, precio));

			// dao.crear(medicamentoNuevo);
			System.out.println("-------------------------------------");
			System.out.println("Medicamento guardado correctamente");
			System.out.println(medicamentoNuevo);
			System.out.println("-------------------------------------");
			isError = false;
		} catch (Exception e) {
			System.out.println("**error: No se ha podido crear el medicamento");
		}

	}

	/**
	 * Se encraga de pintar las pociones del menu.
	 * 
	 * @param nombrePojo nombre del recurso que se gestiona en esta App
	 * 
	 */
	private static void pintarMenu(final String nombrePojo) {

		System.out.println("******************************");
		System.out.println(" " + OP_LISTAR + ".- Listar todos los " + nombrePojo);
		System.out.println(" " + OP_CREAR + ".- Crear un " + nombrePojo);
		System.out.println(" " + OP_ELIMINAR + ".- Dar de baja un " + nombrePojo);
		System.out.println(" ");
		System.out.println(" " + OP_SALIR + " - Salir");
		System.out.println("******************************");
	}

	/**
	 * Valida que los datos introducidos por pantalla son de tipo String
	 * 
	 * @param nombre de la propiedad de tipo String
	 * @return valor de tipo String cuya longitud sea > 2 y < 50 caracteres
	 */
	private static String validarString(String propiedad) throws Exception {

		boolean isError = true;
		String nombre = "";

		// Repetir hasta que no haya error
		do {
			System.out.println("Introduzca el " + propiedad + " del " + NOMBRE_POJO + " : \n");
			nombre = sc.nextLine();
			if (nombre.trim().length() < MIN_LENGTH || nombre.trim().length() > MAX_LENGTH
					|| !nombre.matches("\\p{Lu}[ \\p{L}\\d+]*")) {
				System.out
						.println("**error, el " + propiedad + " debe tener más de " + MIN_LENGTH + " letras y menos de "
								+ MAX_LENGTH + ". '" + nombre + "' tiene " + nombre.length() + " caracter(es) \n");
			} else {
				isError = false;
			}
		} while (isError);

		return nombre;
	}
}
