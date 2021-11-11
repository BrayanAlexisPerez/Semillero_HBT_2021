package com.hbt.semillero.dto;

import java.util.ArrayList;
import java.util.List;


public class ConsultarComicEstadoDTO extends ResultadoDTO{
	
	/**
	 * SerialVersionUID es el id único que identifica una clase 
	 * cuando lo serializamos. mediante este id podemos identificar el objeto convertido 
	 * en un array de bytes. 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Atriburo para almacenar los comics activos
	 */
	private List<ComicDTO> listaComicsActivos;
	
	/**
	 * Atriburo para almacenar los comics activos
	 */
	private List<ComicDTO> listaComicsInactivos;
	
	
	public ConsultarComicEstadoDTO() {
		this.listaComicsActivos = new ArrayList<ComicDTO>();
		this.listaComicsInactivos = new ArrayList<ComicDTO>();
	}
	
	/**
	 * Metodo encargado de retornar el valor del atributo listaComicsActivos
	 * @return El listaComicsActivos asociado a la clase
	 */
	public List<ComicDTO> getListaComicsActivos(){
		return listaComicsActivos;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo listaComicsActivos
	 * @param listaComicsActivos El nuevo listaComicsActivos a modificar.
	 */
	public void setListaComicsActivos(List<ComicDTO> listaComicsActivos) {
		this.listaComicsActivos = listaComicsActivos;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo listaComicsInactivos
	 * @return El listaComicsInactivos asociado a la clase
	 */
	public List<ComicDTO> getListaComicsInactivos() {
		return listaComicsInactivos;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo listaComicsInactivos
	 * @param listaComicsInactivos El nuevo listaComicsInactivos a modificar.
	 */
	public void setListaComicsInactivos(List<ComicDTO> listaComicsInactivos) {
		this.listaComicsInactivos = listaComicsInactivos;
	}
	
	
	
}
