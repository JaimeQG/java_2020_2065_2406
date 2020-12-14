package com.ipartek.formacion.libreria.modelos;

import java.math.BigDecimal;

public class Libro {

	static final protected String AUTOR_DEFECTO = "Anónimo";
	static final protected String PATH_IMAGEN = "imgs/libros/";
	static final protected String FILE_IMAGEN = "imagen_default.jpg";
	static final protected String URL_IMAGEN = PATH_IMAGEN + FILE_IMAGEN;

	private Long id;
	private String nombre;
	private BigDecimal precio;
	private Integer descuento;
	private String autor;
	private String urlImagen;

	private boolean correcto = true;

	private String errorId;
	private String errorNombre;
	private String errorUrlImagen;
	private String errorPrecio;
	private String errorDescuento;
	private String errorAutor;

	public Libro(String id, String nombre, String urlImagen, String precio, String descuento, String autor) {

		setId(id);
		setNombre(nombre);
		setUrlImagen(urlImagen);
		setPrecio(precio);
		setDescuento(descuento);
		setAutor(autor);
	}

	private void setId(String id) {
		try {
			setId(id.trim().length() != 0 ? Long.parseLong(id) : null);
		} catch (NumberFormatException e) {
			setErrorId("El id debe ser numérico");
		}
	}

	private void setPrecio(String precio) {
		try {
			if ((precio.matches("\\d+\\.\\d\\d"))) {
				setPrecio(new BigDecimal(precio));
			} else {
				setErrorPrecio("El precio no tiene un formato correcto");
			}
		} catch (Exception e) {
			setErrorPrecio("El precio no tiene un formato correcto");
		}
	}

	private void setDescuento(String descuento) {
		try {
			setDescuento(Integer.parseInt(descuento));
		} catch (NumberFormatException e) {
			setErrorDescuento("El descuento debe ser un número entero");
		}
	}

	public Libro(Long id, String nombre, BigDecimal precio, Integer descuento, String autor, String urlImagen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.descuento = descuento;
		this.autor = autor;
		this.urlImagen = urlImagen; // img/libros/imagen.jpg
	}

	public Libro() {
		super();
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
		if (nombre == null || nombre.trim().length() < 2 || !nombre.matches("^[A-Za-z0-9 -]+$")) {
			setErrorNombre("Debe introducir un nombre con solo letras y mayúscula la primera. Mínimo 3 caracteres");
		}
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		// precio < 10 -----> precio.compareTo(new BigDecimal("10")) < 0
		// precio >= 100 ---> precio.compareTo(new BigDecimal("100")) >= 0
		// precio == 5 -----> precio.compareTo(new BigDecimal("5")) == 0
		if (precio == null || precio.compareTo(BigDecimal.ZERO) < 0) {
			setErrorPrecio("Debe rellenarse y ser mayor que 0");
		}

		this.precio = precio;
	}

	public Integer getDescuento() {
		return descuento;
	}

	public void setDescuento(Integer descuento) {
		if (descuento != null && (descuento < 0 || descuento > 100)) {
			setErrorDescuento("El descuento debe estar comprendido entre 0 y 100");
		}
		this.descuento = descuento;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {

		this.autor = autor.trim().length() != 0 ? autor : AUTOR_DEFECTO;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {

		this.urlImagen = urlImagen.trim().length() != 0 ? urlImagen : URL_IMAGEN;
	}

	public boolean isCorrecto() {
		return correcto;
	}

	public void setCorrecto(boolean correcto) {
		this.correcto = correcto;
	}

	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		correcto = false;
		this.errorId = errorId;
	}

	public String getErrorNombre() {
		return errorNombre;
	}

	public void setErrorNombre(String errorNombre) {
		correcto = false;
		this.errorNombre = errorNombre;
	}

	public String getErrorUrlImagen() {
		return errorUrlImagen;
	}

	public void setErrorUrlImagen(String errorUrlImagen) {
		correcto = false;
		this.errorUrlImagen = errorUrlImagen;
	}

	public String getErrorPrecio() {
		return errorPrecio;
	}

	public void setErrorPrecio(String errorPrecio) {
		correcto = false;
		this.errorPrecio = errorPrecio;
	}

	public String getErrorDescuento() {
		return errorDescuento;
	}

	public void setErrorDescuento(String errorDescuento) {
		correcto = false;
		this.errorDescuento = errorDescuento;
	}

	public String getErrorAutor() {
		return errorAutor;
	}

	public void setErrorAutor(String errorAutor) {
		correcto = false;
		this.errorAutor = errorAutor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((descuento == null) ? 0 : descuento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
		result = prime * result + ((urlImagen == null) ? 0 : urlImagen.hashCode());
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
		Libro other = (Libro) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (descuento == null) {
			if (other.descuento != null)
				return false;
		} else if (!descuento.equals(other.descuento))
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
		if (precio == null) {
			if (other.precio != null)
				return false;
		} else if (!precio.equals(other.precio))
			return false;
		if (urlImagen == null) {
			if (other.urlImagen != null)
				return false;
		} else if (!urlImagen.equals(other.urlImagen))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", descuento=" + descuento + ", autor="
				+ autor + ", urlImagen=" + urlImagen + "]";
	}

}
