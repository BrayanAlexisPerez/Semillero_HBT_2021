package com.hbt.semillero.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ConsultarComicTamanioNombreDTO;
import com.hbt.semillero.dto.DatosActualizarCantidadDTO;
import com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO;
import com.hbt.semillero.dto.ConsultarComicEstadoDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.enums.EstadoEnum;
import com.hbt.semillero.interfaces.IGestionarComicLocal;

/**
 * 
 * Descripción: Clase que gestiona los servicios relacionados a un comic 
 * 				con un EJB de tipo Session sin estado 
 * 				la transacción la va a manejar el contenedor es decir no va a ser manual
 * Caso de Uso: SEMILLERO HBT 2021
 * @author Brayan Perez
 * @version 1.0
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarComicBean implements IGestionarComicLocal {

	/**
	 * Instancia de la clase EntityManager, que permite administrar objetos de datos, 
	 * de una fuente de datos específica.
	 */
	@PersistenceContext
	public EntityManager em;

	/**
	 * 
	 * Metodo encargado de de realizar la consulta a la BD saber si en la los nombres de los comics superan o no una longitud
	 * 
	 * @param lengthComic
	 * @return EvaluacionComicDTO 
	 * @throws Exception 
	 */
	
	@Override
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) 
	public ConsultarComicTamanioNombreDTO consultarComicTamanioNombre (Short lengthComic)
	{		
		ConsultarComicTamanioNombreDTO consultarComicTamanioNombreDTOResult =  new ConsultarComicTamanioNombreDTO();
		try {
			String consultarComics = " SELECT cm.nombre FROM Comic cm ";
			Query queryConsultarComics = em.createQuery(consultarComics);
			List<String> listaComics = queryConsultarComics.getResultList();
			
			
			for (String nombreComic : listaComics) {
				if(nombreComic.length() <= lengthComic)
				{
					consultarComicTamanioNombreDTOResult.getListaComicsNoSuperanTamanio().add(nombreComic);
				}
				else {
					consultarComicTamanioNombreDTOResult.getListaComicsSuperanTamanio().add(nombreComic);
				}
				consultarComicTamanioNombreDTOResult.setExitoso(true);
				consultarComicTamanioNombreDTOResult.setMensajeEjecucion("Comics procesados exitosamente");
			}
		} catch (Exception e) {
			consultarComicTamanioNombreDTOResult.setExitoso(false);
			consultarComicTamanioNombreDTOResult.setMensajeEjecucion("Se ha presentado un error técnico");
		}
		
		return consultarComicTamanioNombreDTOResult;
	}
	
	
	/**
	 * Método encargado de realizar la consulta a la BD para obtener el nombre y el precio de un comic
	 * Esta operación se ejecuta sin una transacción
	 * 
	 * @return ConsultaNombrePrecioComicDTO : Con la información del comic que necesitamos
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) 
	public ConsultaNombrePrecioComicDTO consultarNombrePrecioComic(Long idComic) {
		
		// Variable donde se almacena la consulta SQL 
		String consultaNombrePrecioComic = "SELECT new com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO(c.nombre, c.precio)  "
						+ " FROM Comic c WHERE c.id = :idComic";
		// Objeto de la clase consultaNombrePrecioDTO donde se va a almacenar la información del comic que reorne la consulta
		ConsultaNombrePrecioComicDTO consultaNombrePrecioDTO = new ConsultaNombrePrecioComicDTO();
		
		//Bloque que controla la excepción que pueda retornar el método createQuery()
		try {
			Query queryConsultaNombrePrecio = em.createQuery(consultaNombrePrecioComic); //variable donde se almacena la query para la consulta   
			queryConsultaNombrePrecio.setParameter("idComic", idComic); // Se pasa el parametro que recibe la consulta
			// Se almacena el objeto que retorna la ejecución de la consulta en un objeto DTO
			consultaNombrePrecioDTO = (ConsultaNombrePrecioComicDTO) queryConsultaNombrePrecio.getSingleResult();  
			consultaNombrePrecioDTO.setExitoso(true); // Se modifica la variebale exitoso a true 
			consultaNombrePrecioDTO.setMensajeEjecucion("Se ejecuto exitosamente la consulta");	//Se modifica el mensaje de ejecución
		} catch (Exception e) {
			consultaNombrePrecioDTO.setExitoso(false); // Se modifica la variebale exitoso a false
			consultaNombrePrecioDTO.setMensajeEjecucion("Se ha presentado un error tecnico al consultar el comic");//Se modifica el mensaje de ejecución
		}

		return consultaNombrePrecioDTO; //retorna el objeto DTO con la información del comic 
	}

	/**
	 * Método encargado de realizar la inserción de un comic a la BD
	 * Esta operación puede o no ejecutarse en un contexto transaccional.
	 * 
	 * @return ComicDTO : Con la información del comic que se creo
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ComicDTO crearComic(ComicDTO comicDTO) throws Exception {
		//Con este condicional se valida que se envíe el campo obligatorio del nombre
		if(comicDTO.getNombre() == null) {
			throw new Exception("El campo nombre es requerido"); //Retorna una excepción
		}
		
		ComicDTO comicDTOResult = null; // Variable donde almacenamos la información del comic
		Comic comic = this.convertirComicDTOToComic(comicDTO); // Convertimos el objeto comicDTO a la entidad Comic
		em.persist(comic); //Insertamos el comic a la BD
		comicDTOResult = this.convertirComicToComicDTO(comic);// Convertimos la entidad Comic a objeto comicDTO
		comicDTOResult.setExitoso(true); // // Se modifica la variebale exitoso a true
		comicDTOResult.setMensajeEjecucion("El comic ha sido creado exitosamente"); //Se modifica el mensaje de ejecución
		
		return comicDTOResult; //retorna el objeto DTO con la información del comic que se creo
	}
	
	/**
	 * Método encargado de realizar la actualización del atributo color de un comic en la BD 
	 * Esta operación puede o no ejecutarse en un contexto transaccional.
	 * 
	 * @return ComicDTO : Con la información del comic que se creo
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ResultadoDTO actualizarColorComic(Long idComic) {
		
		ResultadoDTO result = new ResultadoDTO();
		
		try {
			if(existeComic(idComic))
			{
				String actualizarColorComic = " UPDATE Comic c SET c.color = :newColor WHERE c.id = :idComic";
				Query queryActualizarColor = em.createQuery(actualizarColorComic);
				queryActualizarColor.setParameter("newColor", true);
				queryActualizarColor.setParameter("idComic", idComic);
				queryActualizarColor.executeUpdate();
				result.setExitoso(true);
				result.setMensajeEjecucion("Comic actualizado");
			}
			else {
				result.setExitoso(false);
				result.setMensajeEjecucion("El comic no esxiste en la BD");
			}
		} catch (Exception e) {
			result.setExitoso(false);
			result.setMensajeEjecucion("Se ha producido un error tecnico");
		}
		
		return result;
	}
	
	/**
	 * Método encargado de realizar la creación a la BD para obtener el nombre y el precio de un comic
	 * Esta operación puede o no ejecutarse en un contexto transaccional.
	 * 
	 * @return ComicDTO : Con la información del comic que se creo
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ResultadoDTO eliminarComic(Long idComic) {
		
		ResultadoDTO result = new ResultadoDTO();
		try {
			if(existeComic(idComic))
			{
				String eliminarComic = " DELETE FROM Comic c WHERE c.id = :idComic";
				Query queryEliminar = em.createQuery(eliminarComic);
				queryEliminar.setParameter("idComic", idComic);
				queryEliminar.executeUpdate();
				result.setExitoso(true);
				result.setMensajeEjecucion("Comic Eliminado");
			}
			else {
				result.setExitoso(false);
				result.setMensajeEjecucion("El comic no existe en la BD");
			}
		} catch (Exception e) {
			result.setExitoso(false);
			result.setMensajeEjecucion("Se ha presentado un error tecnico");
		}
		
		return result;
	}

	/**
	 * Método encargado de realizar la creación a la BD para obtener el nombre y el precio de un comic
	 * Esta operación puede o no ejecutarse en un contexto transaccional.
	 * 
	 * @return ComicDTO : Con la información del comic que se creo
	 */
	@Override
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ComicDTO> consultarComics() {
		List<ComicDTO> listaComicsDTOs = new ArrayList<ComicDTO>();
		List<Comic> listaComic = null;
		
		try {
			String findAllComic = " SELECT cm FROM Comic cm ";
			Query queryFindAllComic = em.createQuery(findAllComic);
			listaComic = queryFindAllComic.getResultList();
			
			for (Comic comic : listaComic) {
				ComicDTO comicConvertido = convertirComicToComicDTO(comic);
				
				comicConvertido.setExitoso(true);
				comicConvertido.setMensajeEjecucion("Comic consultado");
				listaComicsDTOs.add(comicConvertido);
			}
			
		} catch (Exception e) {
			listaComicsDTOs.get(0).setExitoso(false);
			listaComicsDTOs.get(0).setMensajeEjecucion("Se ha presentado un error tecnico en retornar la lista de comics");
		}
		
		return listaComicsDTOs;
	}
	
	/**
	 * Método encargado de realizar la consulta a la BD para saber si existe o no un comic
	 * Esta operación se ejecuta sin una transacción
	 * 
	 * @return boolean : Existe o no un comic en la BD
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) 
	public ResultadoDTO existeComicConResult(Long idComic) {
		
		//Variable para saber si existe el comic
		ResultadoDTO result = new ResultadoDTO();
		
		// Obtencion de un registro de la tabla comic haciendo uso del metodo find de la clase EntityManager
		try {
			Comic comic =  em.find(Comic.class, idComic);
			
			//validación para saber si existe o no el comic
			if(comic != null)
			{
				result.setExitoso(true);
				result.setMensajeEjecucion("El comic existe");
			}
			else {
				result.setExitoso(false);
				result.setMensajeEjecucion("El comic no existe");
			}
		} catch (Exception e) {
			result.setExitoso(false);
			result.setMensajeEjecucion("Ha ocurrido un error tecnico");
		}
		//Retorna true o false
		return result;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultarComicEstadoDTO consultarComicEstado() {
		ConsultarComicEstadoDTO consultarComicEstadoDTOResult = new ConsultarComicEstadoDTO();
		List<Comic> listaComics = new ArrayList<Comic>();
		
		try {
			String consultaComics = " SELECT c FROM Comic c ";
			Query queyConsultaComics = em.createQuery(consultaComics);
			listaComics = queyConsultaComics.getResultList();
			
			for (Comic comic : listaComics) 
			{
				ComicDTO comicDTO = convertirComicToComicDTO(comic);
				if(comicDTO.getEstadoEnum().equals(EstadoEnum.ACTIVO)) 
				{
					consultarComicEstadoDTOResult.getListaComicsActivos().add(comicDTO);
				}
				else {
					consultarComicEstadoDTOResult.getListaComicsInactivos().add(comicDTO);
				}
			}
			consultarComicEstadoDTOResult.setExitoso(true);
			consultarComicEstadoDTOResult.setMensajeEjecucion("El servicio se ejecuto correctamente");
		} catch (Exception e) {
			consultarComicEstadoDTOResult.setExitoso(false);
			consultarComicEstadoDTOResult.setMensajeEjecucion("Ha ocurrido un error tecnico");
		}
		
		return consultarComicEstadoDTOResult;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ResultadoDTO actualizarCantidadComic(DatosActualizarCantidadDTO datosActualizarCantidad) {
		ResultadoDTO result = new ResultadoDTO();
		
		try {
			if(existeComic(datosActualizarCantidad.getIdComic()))
			{
				String actualizarCantidadComic = " UPDATE Comic c SET c.cantidad = :cantidadComic WHERE c.id = :idComic";
				Query queryActualizarCantidad = em.createQuery(actualizarCantidadComic);
				queryActualizarCantidad.setParameter("cantidadComic", datosActualizarCantidad.getCantidadComic());
				queryActualizarCantidad.setParameter("idComic", datosActualizarCantidad.getIdComic());
				queryActualizarCantidad.executeUpdate();
				result.setExitoso(true);
				result.setMensajeEjecucion("Cantidad de comic actualizada");
			}
			else {
				result.setExitoso(false);
				result.setMensajeEjecucion("El comic no esxiste en la BD");
			}
		} catch (Exception e) {
			result.setExitoso(false);
			result.setMensajeEjecucion("Se ha producido un error tecnico");
		}
		
		return result;
	}
	
	
	//*********************************************************************************************
	// 			METODOS PRIVADOS AUXILIARES 
	
	
	/**
	 * 
	 * Metodo encargado de verificar la existencia de un comic en la BD 
	 * 
	 * @param idComic
	 * @return boolean
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private boolean existeComic(Long idComic) {
		boolean result = false;
		
		Comic comic = null;
		
		try {
			String existeComic = " SELECT c FROM Comic c WHERE c.id = :idComic ";
			Query queryExisteComic = em.createQuery(existeComic);
			queryExisteComic.setParameter("idComic", idComic);
			comic = (Comic) queryExisteComic.getSingleResult();
			if(comic != null)
			{
				result = true;
			}
		} catch (Exception e) {
			return false;
		}
		return result;
	}
	
	
	/**
	 * 
	 * Metodo encargado de transformar un comic a un comicDTO
	 * 
	 * @param comic
	 * @return ComicDTO 
	 */
	private ComicDTO convertirComicToComicDTO(Comic comic) {
		ComicDTO comicDTO = new ComicDTO();
		comicDTO.setId(comic.getId());
		comicDTO.setNombre(comic.getNombre());
		comicDTO.setEditorial(comic.getEditorial());
		comicDTO.setTematicaEnum(comic.getTematicaEnum());
		comicDTO.setColeccion(comic.getColeccion());
		comicDTO.setNumeroPaginas(comic.getNumeroPaginas());
		comicDTO.setPrecio(comic.getPrecio());
		comicDTO.setAutores(comic.getAutores());
		comicDTO.setColor(comic.getColor());
		comicDTO.setFechaVenta(comic.getFechaVenta());
		comicDTO.setEstadoEnum(comic.getEstadoEnum());
		comicDTO.setCantidad(comic.getCantidad());
		return comicDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un comicDTO a un comic
	 * 
	 * @param ComicDTO
	 * @return Comic
	 */
	private Comic convertirComicDTOToComic(ComicDTO comicDTO) {
		Comic comic = new Comic();
		comic.setId(comicDTO.getId());
		comic.setNombre(comicDTO.getNombre());
		comic.setEditorial(comicDTO.getEditorial());
		comic.setTematicaEnum(comicDTO.getTematicaEnum());
		comic.setColeccion(comicDTO.getColeccion());
		comic.setNumeroPaginas(comicDTO.getNumeroPaginas());
		comic.setPrecio(comicDTO.getPrecio());
		comic.setAutores(comicDTO.getAutores());
		comic.setColor(comicDTO.getColor());
		comic.setFechaVenta(comicDTO.getFechaVenta());
		comic.setEstadoEnum(comicDTO.getEstadoEnum());
		comic.setCantidad(comicDTO.getCantidad());
		return comic;
	}
}
