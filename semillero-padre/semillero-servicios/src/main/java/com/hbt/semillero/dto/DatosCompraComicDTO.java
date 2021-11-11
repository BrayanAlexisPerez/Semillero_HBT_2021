/**
 * DatosActualizar.java
 */
package com.hbt.semillero.dto;

/**
 * Descripción: Clase que determina los datos necesarios para la compra de un comic
 * Caso de Uso: SEMILLERO HBT 2021
 * @author Brayan Perez
 * @version 1.0
 */
public class DatosCompraComicDTO{
	
	//Atributo que almacena el id del comic
	private Long idComic;
	
	//Atributo que almanena la cantidad de comics a comprar
	private Long cantidadComic;

	/**
	 * Constructor vacio de la clase.
	 */
	public DatosCompraComicDTO() {
	}
	/**
	 * Metodo encargado de retornar el valor del atributo idComic
	 * @return El idComic asociado a la clase
	 */
	public Long getIdComic() {
		return idComic;
	}
	/**
	 * Metodo encargado de modificar el valor del atributo idComic
	 * @param idComic El nuevo idComic a modificar.
	 */
	public void setIdComic(Long idComic) {
		this.idComic = idComic;
	}
	/**
	 * Metodo encargado de retornar el valor del atributo cantidadComic
	 * @return El cantidadComic asociado a la clase
	 */
	public Long getCantidadComic() {
		return cantidadComic;
	}
	/**
	 * Metodo encargado de modificar el valor del atributo cantidadComic
	 * @param cantidadComic El nuevo cantidadComic a modificar.
	 */
	public void setCantidadComic(Long cantidadComic) {
		this.cantidadComic = cantidadComic;
	}
	
	
	

}
