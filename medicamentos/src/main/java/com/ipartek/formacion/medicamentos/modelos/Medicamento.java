package com.ipartek.formacion.medicamentos.modelos;

import java.io.Serializable;
import java.math.BigDecimal;

public class Medicamento implements Serializable {

	private static final long serialVersionUID = 3948732104082031294L;

	private Long id;
	private String nombre;
	private String referencia;
	private BigDecimal precio;

	public Medicamento(Long id, String referencia, String nombre, BigDecimal precio) {

		setId(id);
		setReferencia(referencia);
		setNombre(nombre);
		setPrecio(precio);
	}

	public Medicamento() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	@Override
	public String toString() {
		return "Medicamento [id=" + id + ", referencia=" + referencia + ", nombre=" + nombre + ", precio=" + precio
				+ "]";
	}

}
