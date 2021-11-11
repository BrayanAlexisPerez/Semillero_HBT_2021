package com.hbt.semillero.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ConsultarComicTamanioNombreDTO;
import com.hbt.semillero.dto.DatosActualizarCantidadDTO;
import com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.dto.ConsultarComicEstadoDTO;

@Local
public interface IGestionarComicLocal {
	
	//Metodo para seber que comics tienen nombres que superan o no la longitud ingresada.  
	public ConsultarComicTamanioNombreDTO consultarComicTamanioNombre (Short lengthComic);
	
	//Metodo para consultar el nonmbre y el precio de un comic
	public ConsultaNombrePrecioComicDTO consultarNombrePrecioComic(Long idComic);

	//Metodo para crear un comic
	public ComicDTO crearComic(ComicDTO comicDTO) throws Exception;
	
	//Metodo para actualizar el color de un comic a true
	public ResultadoDTO actualizarColorComic(Long idComic);
	
	//Metodo para eliminar un comic
	public ResultadoDTO eliminarComic(Long idComic);
	
	//Metodo para consultar una lista de comics
	public List<ComicDTO> consultarComics();
	
	//Metodo para saber si existe o no un comic
	public ResultadoDTO existeComicConResult(Long idComic);
	
	//Metodo para saber los comics activos e inactivos
	public ConsultarComicEstadoDTO consultarComicEstado();
	
	//Metodo para actualizar la cantidad de un comic
	public ResultadoDTO actualizarCantidadComic(DatosActualizarCantidadDTO datosActualizarCantidad);
	
}
