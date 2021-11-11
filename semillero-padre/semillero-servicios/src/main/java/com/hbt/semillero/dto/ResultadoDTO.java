package com.hbt.semillero.dto;

import java.io.Serializable;

/**
 * 
 * Descripción: Clase que determina los valores que resultan de la ejecución de un servicio.
 * Caso de Uso: SEMILLERO HBT 2021
 * @author Brayan Perez
 * @version 2.0
 */
public class ResultadoDTO implements Serializable {

	/**
	 * SerialVersionUID es el id único que identifica una clase 
	 * cuando lo serializamos. mediante este id podemos identificar el objeto convertido 
	 * en un array de bytes.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Propiedad que indica el resultado de la ejecución de un servicio.
	 */
	private boolean exitoso = false;
	
	/**
	 * Propiedad que almacena el mensaje de ejecución donde se describe el resultado de la ejecución de un servicio.
	 */
	private String mensajeEjecucion;

	
	
	/**
	 * Constructor vacío de la clase.
	 */
	public ResultadoDTO() {
		super();
	}

	/**
	 * Constructor de la clase parametrizado con el exito y el mensaje de la ejecución.
	 * 
	 * @param exitoso
	 * @param mensajeEjecucion
	 */
	public ResultadoDTO(boolean exitoso, String mensajeEjecucion) {
		super();
		this.exitoso = exitoso;
		this.mensajeEjecucion = mensajeEjecucion;
	}
	
	/**
	 * 
	 * Metodo encargado de de retornar si la ejecución fue exitosa o no 
	 * 
	 * @return el valor de exitoso(true o false) 
	 */
	public boolean isExitoso() {
		return exitoso;
	}

	/**
	 * 
	 * Metodo encargado de modificar el valor de la propiedad exitoso
	 * 
	 * @param exitoso
	 */
	public void setExitoso(boolean exitoso) {
		this.exitoso = exitoso;
	}

	/**
	 * 
	 * Método que obtiene el valor de la propiedad mensajeEjecucion
	 * 
	 * @return mensajeEjecucion
	 */
	public String getMensajeEjecucion() {
		return mensajeEjecucion;
	}

	/**
	 * 
	 * Método que asigna el valor de la propiedad mensajeEjecucion
	 * 
	 * @param mensajeEjecucion
	 */
	public void setMensajeEjecucion(String mensajeEjecucion) {
		this.mensajeEjecucion = mensajeEjecucion;
	}

}
