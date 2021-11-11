package com.hbt.semillero.dto;

import java.math.BigDecimal;

/**
 * 
 * Descripción: Clase que determina los atributos para realizar la consulta del nombre y 
 *              el precio de un comic
 * Caso de Uso: SEMILLERO HBT 2021
 * @author Brayan Perez
 * @version 2.0
 */

public class ConsultaNombrePrecioComicDTO extends ResultadoDTO {

	/**
	 * SerialVersionUID es el id único que identifica una clase 
	 * cuando lo serializamos. mediante este id podemos identificar el objeto convertido 
	 * en un array de bytes.
	 */
	private static final long serialVersionUID = 1L; 
	
	private String nombre; //Atributo que determina el nombre de un comic
	private BigDecimal precio; // Atributo que determina el precio de un comic
	
	/**
	 * 
	 * Constructor vacìo de la clase.
	 */
	public ConsultaNombrePrecioComicDTO() {
	}
	
	/**
	 * 
	 * Constructor de la clase parametrizado con el nombre y el precio de un comic.
	 * @param nombre
	 * @param precio
	 */
	public ConsultaNombrePrecioComicDTO(String nombre, BigDecimal precio) {
		this.nombre = nombre;
		this.precio = precio;
	}
	
	/**
	 * 
	 * Metodo encargado de obtener el nombre de un comic 
	 * Caso de Uso: SEMILLERO HBT 2021
	 * 
	 * @return nombre - el nombre de un comic
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * 
	 * Metodo encargado de asignar el nombre de un comic 
	 * Caso de Uso: SEMILLERO HBT 2021
	 * 
	 * @param nombre - Nuevo ombre del comic
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * 
	 * Metodo encargado de obtener el precio de un comic 
	 * Caso de Uso: SEMILLERO HBT 2021
	 * 
	 * @return precio - Precio de un comic
	 */
	public BigDecimal getPrecio() {
		return precio;
	}
	
	/**
	 * 
	 * Metodo encargado de asignar el precio de un comic
	 * Caso de Uso: SEMILLERO HBT 2021
	 * 
	 * @param precio - Nuevo precio del comic
	 */
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
}
