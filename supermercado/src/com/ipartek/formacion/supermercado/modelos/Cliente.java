package com.ipartek.formacion.supermercado.modelos;

import java.time.LocalDate;

public class Cliente {
	private Long id;
	private String nombre, apellidos, cif;
	private LocalDate fechaNacimiento;

	private String errorId, errorNombre, errorApellidos, errorCif, errorFechaNacimiento;

	public Cliente(String id, String nombre, String apellidos, String cif, String fechaNacimiento) {
		setId(id);
		setNombre(nombre);
		setApellidos(apellidos);
		setCif(cif);
		setFechaNacimiento(fechaNacimiento);
	}

	public Cliente(Long id, String nombre, String apellidos, String cif, LocalDate fechaNacimiento) {
		setId(id);
		setNombre(nombre);
		setApellidos(apellidos);
		setCif(cif);
		setFechaNacimiento(fechaNacimiento);
	}

	public Cliente() {

	}

	public Long getId() {
		return id;
	}

	public void setId(String id) {
//		if(id != null) {
//			setId(Long.parseLong(id));
//		} else {
//			setId((Long)null);
//		}

		try {
			setId(id != null && id.trim().length() > 0 ? Long.parseLong(id) : null);
		} catch (NumberFormatException e) {
			setErrorId("El id debe ser numérico");
		}
	}

	public void setId(Long id) {
		if (id != null && id < 1L) {
			setErrorId("El id debe ser mayor que 0");
		} else {
			setErrorId(null);
		}

		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null || !nombre.matches("\\p{Lu}\\p{Ll}{2}[ \\p{L}]*")) {// nombre.trim().length() < 3) {
			setErrorNombre("El nombre es obligatorio y debe tener al menos 3 caracteres y comenzar en mayúscula");
		} else {
			setErrorNombre(null);
		}
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		apellidos = apellidos == null || apellidos.trim().length() == 0 ? null : apellidos;

		if (apellidos != null && !apellidos.matches("\\p{Lu}\\p{Ll}{2}[ \\p{L}]*")) {
			setErrorApellidos(
					"Los apellidos no son obligatorios, pero deben tener al menos 3 letras y comenzar en mayúscula");
		} else {
			setErrorApellidos(null);
		}

		this.apellidos = apellidos;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		// B12345678 X1234567A 12345678Z
		if (cif == null || !cif.matches("[ABCDEFGHJPQRSUVNW]\\d{8}|[XYZ]\\d{7}[A-Z]|\\d{8}[A-Z]")) {
			setErrorCif("El CIF debe tener uno de los siguientes formatos: B12345678 X1234567A 12345678Z");
		} else {
			setErrorCif(null);
		}
		this.cif = cif;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		try {
			// Si fuera obligatorio:
			// if(fechaNacimiento == null || fechaNacimiento.trim().length() == 0) {
			// setErrorFechaNacimiento("La fecha es obligatoria"); return; }
			// setFechaNacimiento(LocalDate.parse(fechaNacimiento));
			setFechaNacimiento(
					fechaNacimiento != null && fechaNacimiento.trim().length() > 0 ? LocalDate.parse(fechaNacimiento)
							: null);
		} catch (Exception e) {
			setErrorFechaNacimiento("La fecha de nacimiento tiene que tener un formato por ejemplo: 2020-01-31");
		}
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		if (fechaNacimiento != null && fechaNacimiento.isAfter(LocalDate.now().minusYears(18))) {
			setErrorFechaNacimiento("Debes ser mayor de edad y no haber nacido en el futuro");
		} else {
			setErrorFechaNacimiento(null);
		}

		this.fechaNacimiento = fechaNacimiento;
	}

	public boolean isCorrecto() {
		return errorId == null && errorNombre == null && errorApellidos == null && errorCif == null
				&& errorFechaNacimiento == null;
	}

	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}

	public String getErrorNombre() {
		return errorNombre;
	}

	public void setErrorNombre(String errorNombre) {
		this.errorNombre = errorNombre;
	}

	public String getErrorApellidos() {
		return errorApellidos;
	}

	public void setErrorApellidos(String errorApellidos) {
		this.errorApellidos = errorApellidos;
	}

	public String getErrorCif() {
		return errorCif;
	}

	public void setErrorCif(String errorCif) {
		this.errorCif = errorCif;
	}

	public String getErrorFechaNacimiento() {
		return errorFechaNacimiento;
	}

	public void setErrorFechaNacimiento(String errorFechaNacimiento) {
		this.errorFechaNacimiento = errorFechaNacimiento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((cif == null) ? 0 : cif.hashCode());
		result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (cif == null) {
			if (other.cif != null)
				return false;
		} else if (!cif.equals(other.cif))
			return false;
		if (fechaNacimiento == null) {
			if (other.fechaNacimiento != null)
				return false;
		} else if (!fechaNacimiento.equals(other.fechaNacimiento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", cif=" + cif
				+ ", fechaNacimiento=" + fechaNacimiento + ", errorId=" + errorId + ", errorNombre=" + errorNombre
				+ ", errorApellidos=" + errorApellidos + ", errorCif=" + errorCif + ", errorFechaNacimiento="
				+ errorFechaNacimiento + ", isCorrecto()=" + isCorrecto() + "]";
	}

}