/**
 * evaluacionComic.java
 */
package com.hbt.semillero.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Descripción: Clase que contiene una lista de comics que superan o igualan el numero 
 * 				de longitud ingresado y otra lista con los que no lo superan.  
 * Caso de Uso: SEMILLERO HBT 2021
 * @author Brayan Perez
 * @version 1.0
 */
public class ConsultarComicTamanioNombreDTO extends ResultadoDTO{
	
	/**
	 * SerialVersionUID es el id único que identifica una clase 
	 * cuando lo serializamos. mediante este id podemos identificar el objeto convertido 
	 * en un array de bytes. 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributo que contiene la lista de nombres de los comics que no superan el tamanio ingresado 
	 */
	private List<String> listaComicsNoSuperanTamanio;
	
	/**
	 * Atributo que contiene la lista de nombres de los comics que superan el tamanio ingresado 
	 */
	private List<String> listaComicsSuperanTamanio;

	/**
	 * Constructor vacío de la clase.
	 */
	public ConsultarComicTamanioNombreDTO() {
		this.listaComicsNoSuperanTamanio = new ArrayList<String>();
		this.listaComicsSuperanTamanio = new ArrayList<String>();
	}

	/**
	 * Metodo encargado de retornar el valor del atributo listaComicsNoSuperanTamanio
	 * @return El listaComicsNoSuperanTamanio asociado a la clase
	 */
	public List<String> getListaComicsNoSuperanTamanio() {
		return listaComicsNoSuperanTamanio;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo listaComicsNoSuperanTamanio
	 * @param listaComicsNoSuperanTamanio El nuevo listaComicsNoSuperanTamanio a modificar.
	 */
	public void setListaComicsNoSuperanTamanio(List<String> listaComicsNoSuperanTamanio) {
		this.listaComicsNoSuperanTamanio = listaComicsNoSuperanTamanio;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo listaComicsSuperanTamanio
	 * @return El listaComicsSuperanTamanio asociado a la clase
	 */
	public List<String> getListaComicsSuperanTamanio() {
		return listaComicsSuperanTamanio;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo listaComicsSuperanTamanio
	 * @param listaComicsSuperanTamanio El nuevo listaComicsSuperanTamanio a modificar.
	 */
	public void setListaComicsSuperanTamanio(List<String> listaComicsSuperanTamanio) {
		this.listaComicsSuperanTamanio = listaComicsSuperanTamanio;
	}
}
