package com.hbt.semillero.ejb;


import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import com.hbt.semillero.dto.DatosCompraComicDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.enums.EstadoEnum;
import com.hbt.semillero.interfaces.IGestionarCompraComic;

/**
 * 
 * Descripción: Clase que gestiona los servicios relacionados a la compra de un comic 
 * 				con un EJB de tipo Session sin estado 
 * 				la transacción la va a manejar el contenedor es decir no va a ser manual
 * Caso de Uso: SEMILLERO HBT 2021
 * @author Brayan Perez
 * @version 1.0
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarCompraComicBean implements IGestionarCompraComic {

	/**
	 * Instancia de la clase EntityManager, que permite administrar objetos de datos, 
	 * de una fuente de datos específica.
	 */
	@PersistenceContext
	public EntityManager em;

	/**
	 * 
	 * Metodo encargado de de realizar todas las transacciones necesarias al comprar un comic 
	 * la cantidad ingresada
	 * @param datosCompraComic
	 * @return resultadoDTO
	 */	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) 
	public ResultadoDTO comprarComic(DatosCompraComicDTO datosCompraComic) {
		ResultadoDTO result = new ResultadoDTO();
		
		result = actualizarDatosComic(datosCompraComic);
		
		return result;
	}
	
	
		
	//*********************************************************************************************
	// 			METODOS PRIVADOS AUXILIARES 
	
	/**
	 * 
	 * Metodo encargado de Actualizar todos la fecha, la cantidad y el estado de un comic dependiendo de 
	 * la cantidad ingresada
	 * @param datosCompraComic
	 * @return resultadoDTO
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private ResultadoDTO actualizarDatosComic(DatosCompraComicDTO datosCompraComic) {
		ResultadoDTO result = new ResultadoDTO();
		
		try {
			String consultaComic = " SELECT cm FROM Comic cm WHERE cm.id = :idComic";
			Comic infoComic = new Comic();
			Long nuevaCantidad = 0L;
			
			Query queyConsultaComic = em.createQuery(consultaComic);
			queyConsultaComic.setParameter("idComic", datosCompraComic.getIdComic()); // Se pasa el parametro que recibe la consulta
			infoComic = (Comic) queyConsultaComic.getSingleResult();
			
			if(infoComic.getCantidad()>datosCompraComic.getCantidadComic())
			{
				actualizarFechaComic(datosCompraComic.getIdComic());
				nuevaCantidad = obtenerNuevaCantidad(datosCompraComic);
				datosCompraComic.setCantidadComic(nuevaCantidad);
				actualizarCantidadComic(datosCompraComic);
				result.setExitoso(true);
				result.setMensajeEjecucion("La compra del comic "+infoComic.getNombre()+" fue exitosa");
			}
			else {
				if(infoComic.getCantidad().equals(datosCompraComic.getCantidadComic()))
				{
					actualizarEstadoComic(datosCompraComic.getIdComic()).getMensajeEjecucion();
					
					actualizarFechaComic(datosCompraComic.getIdComic());
					nuevaCantidad = obtenerNuevaCantidad(datosCompraComic);
					datosCompraComic.setCantidadComic(nuevaCantidad);
					actualizarCantidadComic(datosCompraComic);
					result.setExitoso(true);
					result.setMensajeEjecucion("La compra del comic "+infoComic.getNombre()+" fue exitosa");
				}
				else {
					result.setMensajeEjecucion("La cantidad existente del comic es "+infoComic.getCantidad()+", y supera la ingresada");
				}
			}
			
		} catch (Exception e) {
			result.setExitoso(false);
			result.setMensajeEjecucion("Se ha producido un error tecnico");
		}
		
		return result;
	}
	
	/**
	 * 
	 * Metodo encargado de Actualizar la cantidad de unidades de un comic 
	 * @param datosCompraComic
	 * @return resultadoDTO
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	private ResultadoDTO actualizarCantidadComic(DatosCompraComicDTO datosCompraComic) {
		ResultadoDTO result = new ResultadoDTO();
		
		try {
			String actualizarCantidadComic = " UPDATE Comic c SET c.cantidad = :cantidadComic WHERE c.id = :idComic";
			Query queryActualizarCantidad = em.createQuery(actualizarCantidadComic);
			queryActualizarCantidad.setParameter("cantidadComic", datosCompraComic.getCantidadComic());
			queryActualizarCantidad.setParameter("idComic", datosCompraComic.getIdComic());
			queryActualizarCantidad.executeUpdate();
			result.setExitoso(true);
			result.setMensajeEjecucion("Cantidad de comic actualizada");
		
		} catch (Exception e) {
			result.setExitoso(false);
			result.setMensajeEjecucion("Se ha producido un error tecnico");
		}
		
		return result;
	}
	
	/**
	 * 
	 * Metodo encargado de obtener la nueva cantidad de un comic haciendo la resta de las unidades existentes del comic menos 
	 * la cantidad ingresada
	 * @param datosCompraComic
	 * @return resultadoDTO
	 */	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	private Long obtenerNuevaCantidad(DatosCompraComicDTO datosCompraComic) 
	{
		Long nuevaCantidad = 0L;
		
		try {
			String consultaComic = " SELECT cm FROM Comic cm WHERE cm.id = :idComic";
			Comic infoComic = new Comic();
			
			Query queyConsultaComic = em.createQuery(consultaComic);
			queyConsultaComic.setParameter("idComic", datosCompraComic.getIdComic()); // Se pasa el parametro que recibe la consulta
			infoComic = (Comic) queyConsultaComic.getSingleResult();
			
			nuevaCantidad = infoComic.getCantidad() - datosCompraComic.getCantidadComic();
			
		} catch (Exception e) {
			nuevaCantidad = null;
		}
		
		return nuevaCantidad;
	}
	/**
	 * 
	 * Metodo encargado de Actualizar la fecha de un comic con la fecha actual  
	 * @param datosCompraComic
	 * @return resultadoDTO
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	private ResultadoDTO actualizarFechaComic(Long idComic) {
		
		ResultadoDTO result = new ResultadoDTO();
		
		String fecha = new SimpleDateFormat("dd/MM/yy").format(Calendar.getInstance().getTime());
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy"); 
		LocalDate fechaCompra = LocalDate.parse(fecha, formato); 
		
		
		try {
			String actualizarEstadoComic = " UPDATE Comic c SET c.fechaVenta = :fechaCompra WHERE c.id = :idComic";
			Query queryActualizarEstado = em.createQuery(actualizarEstadoComic);
			queryActualizarEstado.setParameter("fechaCompra", fechaCompra);
			queryActualizarEstado.setParameter("idComic", idComic);
			queryActualizarEstado.executeUpdate();
			result.setMensajeEjecucion("Fecha de comic actualizada");
		} catch (Exception e) {
			result.setMensajeEjecucion(e+"Se ha producido un error tecnico");
		}
		return result;
	}
	
	/**
	 * 
	 * Metodo encargado de Actualizar a el estado Inactivo a un comic si su cantidad es 0 
	 * @param datosCompraComic
	 * @return resultadoDTO
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	private ResultadoDTO actualizarEstadoComic(Long idComic) {
		ResultadoDTO result = new ResultadoDTO();
		try {
			String actualizarEstadoComic = " UPDATE Comic c SET c.estadoEnum = :nuevoEstado WHERE c.id = :idComic";
			Query queryActualizarEstado = em.createQuery(actualizarEstadoComic);
			queryActualizarEstado.setParameter("nuevoEstado", EstadoEnum.INACTIVO);
			queryActualizarEstado.setParameter("idComic", idComic);
			queryActualizarEstado.executeUpdate();
		} catch (Exception e) {
			result.setMensajeEjecucion(e+"error tecnico");
		}
		return result;
	}
}
