package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ConsultarComicTamanioNombreDTO;
import com.hbt.semillero.dto.DatosActualizarCantidadDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO;
import com.hbt.semillero.dto.ConsultarComicEstadoDTO;
import com.hbt.semillero.interfaces.IGestionarComicLocal;

@Path("/gestionarComic")
public class GestionarComicRest {
	
	@EJB
	private IGestionarComicLocal gestionarComicLocal;

	//***************************************************************************************
	// 			SERVICIOS DE TIPO POST
	
	@POST
	@Path("/crearComic")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComicDTO crearComic(ComicDTO comicDTO) {
		ComicDTO comicDTOResult = new ComicDTO();
		try {
			comicDTOResult = this.gestionarComicLocal.crearComic(comicDTO);
		}  catch (Exception e) {
			comicDTOResult.setExitoso(false);
			comicDTOResult.setMensajeEjecucion("Se ha presentado un error tecnico, causa: " + e.getMessage());
		}
		return comicDTOResult;
	} 
	
	@POST
	@Path("/eliminarComic")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultadoDTO eliminarComic(Long idComic){
		return this.gestionarComicLocal.eliminarComic(idComic);
	}

 	@POST
 	@Path("/actualizarColorComic")
 	@Produces(MediaType.APPLICATION_JSON)
 	@Consumes(MediaType.APPLICATION_JSON)
 	public ResultadoDTO actualizarColorComic(Long idComic) {
 		return this.gestionarComicLocal.actualizarColorComic(idComic);
 	}
 	
 	@POST
 	@Path("/actualizarCantidadComic")
 	@Produces(MediaType.APPLICATION_JSON)
 	@Consumes(MediaType.APPLICATION_JSON)
	public ResultadoDTO actualizarCantidadComic(DatosActualizarCantidadDTO datosActualizarCantidad) {
		return this.gestionarComicLocal.actualizarCantidadComic(datosActualizarCantidad);
	}
 	
 	
	//***************************************************************************************
	// 			SERVICIOS DE TIPO GET
 	
 	@GET
 	@Path("/consultarComics")
 	@Produces(MediaType.APPLICATION_JSON)
 	public List<ComicDTO> consultarComics(){
 		return this.gestionarComicLocal.consultarComics();
 	}
	
	@GET
	@Path("/consultarNombrePrecioComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ConsultaNombrePrecioComicDTO consultarNombrePrecioComic(@QueryParam("idComic") Long idComic) {
		return this.gestionarComicLocal.consultarNombrePrecioComic(idComic);
	}
	
	@GET
	@Path("/consultarComicTamanioNombre")
	@Produces(MediaType.APPLICATION_JSON)
	public ConsultarComicTamanioNombreDTO consultarComicTamanioNombre(@QueryParam("lengthComic") Short lengthComic) {
		return this.gestionarComicLocal.consultarComicTamanioNombre(lengthComic);
	}
	
	@GET
	@Path("/consultarEstadoComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ConsultarComicEstadoDTO consultarComicEstado() {
		return this.gestionarComicLocal.consultarComicEstado();
	}
	
	
 	@GET
	@Path("/existeComicConResult")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultadoDTO existeComicConResult(@QueryParam("idComic") Long idComic) {
 		return this.gestionarComicLocal.existeComicConResult(idComic);
	}
}
