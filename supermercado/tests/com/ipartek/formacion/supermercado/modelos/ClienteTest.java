package com.ipartek.formacion.supermercado.modelos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClienteTest {

	private Cliente cliente;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		cliente = new Cliente();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testConstructorClienteString() {
		cliente = new Cliente("-5", "alskdfjlks", "kalsjdflds", "alskdjfl", "alkdjsafls");

		assertFalse(cliente.isCorrecto());

		cliente = new Cliente("1", "Pepe", "Pérez", "12345678Z", "");

		assertTrue(cliente.isCorrecto());

		cliente = new Cliente("-5", "alskdfjlks", "kalsjdflds", "alskdjfl", "alkdjsafls");

		cliente.setId("5");

		assertFalse(cliente.isCorrecto());

		cliente = new Cliente("1", "Pepe", "Pérez", "12345678Z", "");

		assertTrue(cliente.isCorrecto());
	}

	@Test
	void testSetIdString() {
		String id = null;
		cliente.setId(id);

		assertNull(cliente.getId());
		assertNull(cliente.getErrorId());

		id = "   ";
		cliente.setId(id);

		assertNull(cliente.getId());
		assertNull(cliente.getErrorId());

		id = "alsdjkhfalsjdhf";
		cliente.setId(id);

		assertNotNull(cliente.getErrorId());

		id = "-5";
		cliente.setId(id);

		assertNotNull(cliente.getErrorId());

		id = "5";
		cliente.setId(id);

		assertEquals(5L, cliente.getId());
		assertNull(cliente.getErrorId());
	}

	@Test
	void testSetIdLong() {
		Long id = null;
		cliente.setId(id);

		assertNull(cliente.getId());
		assertNull(cliente.getErrorId());

		id = -5L;
		cliente.setId(id);

		assertNotNull(cliente.getErrorId());

		id = 7L;
		cliente.setId(id);

		assertEquals(id, cliente.getId());
		assertNull(cliente.getErrorId());
	}

	@Test
	void testSetNombre() {
		String nombre = null;

		cliente.setNombre(nombre);

		assertNotNull(cliente.getErrorNombre());

		nombre = "   ";

		cliente.setNombre(nombre);

		assertNotNull(cliente.getErrorNombre());

		nombre = "A";

		cliente.setNombre(nombre);

		assertNotNull(cliente.getErrorNombre());

		nombre = "añlsdkjflakds";

		cliente.setNombre(nombre);

		assertNotNull(cliente.getErrorNombre());

		nombre = "François Javier";

		cliente.setNombre(nombre);

		assertEquals(nombre, cliente.getNombre());
		assertNull(cliente.getErrorNombre());
	}

	@Test
	void testSetApellidos() {
		String apellidos = null;

		cliente.setApellidos(apellidos);

		assertNull(cliente.getApellidos());
		assertNull(cliente.getErrorApellidos());

		apellidos = "   ";

		cliente.setApellidos(apellidos);

		assertNull(cliente.getApellidos());
		assertNull(cliente.getErrorApellidos());

		apellidos = "A";

		cliente.setApellidos(apellidos);

		assertNotNull(cliente.getErrorApellidos());

		apellidos = "alsdkfj";

		cliente.setApellidos(apellidos);

		assertNotNull(cliente.getErrorApellidos());

		apellidos = "Lete García";

		cliente.setApellidos(apellidos);

		assertEquals(apellidos, cliente.getApellidos());
		assertNull(cliente.getErrorApellidos());

	}

	@Test
	void testSetCif() {
		String cif = null;

		cliente.setCif(cif);

		assertNotNull(cliente.getErrorCif());

		cif = "   ";

		cliente.setCif(cif);

		assertNotNull(cliente.getErrorCif());

		cif = "B12345678";

		cliente.setCif(cif);

		assertEquals(cif, cliente.getCif());
		assertNull(cliente.getErrorCif());

		cif = "X1234567Z";

		cliente.setCif(cif);

		assertEquals(cif, cliente.getCif());
		assertNull(cliente.getErrorCif());

		cif = "12345678Z";

		cliente.setCif(cif);

		assertEquals(cif, cliente.getCif());
		assertNull(cliente.getErrorCif());

		cif = "B1234567B";

		cliente.setCif(cif);

		assertNotNull(cliente.getErrorCif());

		cif = "123456789";

		cliente.setCif(cif);

		assertNotNull(cliente.getErrorCif());
	}

	@Test
	void testSetFechaNacimientoString() {
		String fechaNacimiento = null;

		cliente.setFechaNacimiento(fechaNacimiento);

		assertNull(cliente.getFechaNacimiento());
		assertNull(cliente.getErrorFechaNacimiento());

		fechaNacimiento = "   ";

		cliente.setFechaNacimiento(fechaNacimiento);

		assertNull(cliente.getFechaNacimiento());
		assertNull(cliente.getErrorFechaNacimiento());

		fechaNacimiento = LocalDate.now().toString();

		cliente.setFechaNacimiento(fechaNacimiento);

		assertNotNull(cliente.getErrorFechaNacimiento());

		fechaNacimiento = "lakjsdhflakjsd";

		cliente.setFechaNacimiento(fechaNacimiento);

		assertNotNull(cliente.getErrorFechaNacimiento());

		fechaNacimiento = "1990-11-12";

		cliente.setFechaNacimiento(fechaNacimiento);

		assertNotNull(cliente.getFechaNacimiento());
		assertEquals(1990, cliente.getFechaNacimiento().getYear());
		assertEquals(Month.NOVEMBER, cliente.getFechaNacimiento().getMonth());
		assertEquals(12, cliente.getFechaNacimiento().getDayOfMonth());
		assertNull(cliente.getErrorFechaNacimiento());
	}

	@Test
	void testSetFechaNacimientoLocalDate() {
		LocalDate fechaNacimiento = null;

		cliente.setFechaNacimiento(fechaNacimiento);

		assertNull(cliente.getFechaNacimiento());
		assertNull(cliente.getErrorFechaNacimiento());

		fechaNacimiento = LocalDate.now().minusYears(18);

		cliente.setFechaNacimiento(fechaNacimiento);

		assertNull(cliente.getErrorFechaNacimiento());

		fechaNacimiento = fechaNacimiento.plusDays(1);

		cliente.setFechaNacimiento(fechaNacimiento);

		assertNotNull(cliente.getErrorFechaNacimiento());
	}

}