package com.hbt.semillero.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hbt.semillero.dto.DatosCompraComicDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.interfaces.IGestionarCompraComic;

@Path("/gestionarCompraComic")
public class GestionarCompraComicRest {
	
	@EJB
	private IGestionarCompraComic gestionarCompraComic;

	//***************************************************************************************
	// 			SERVICIOS DE TIPO POST
	
	/**
	 * 
	 * Servicio POST encargado degestionar la compra de un comic 
	 * 
	 * @param datosCompraComic
	 * @return ResultadoDTO
	 */
	
	@POST
	@Path("/comprarComic")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultadoDTO comprarComic(DatosCompraComicDTO datosCompraComic) {
		return this.gestionarCompraComic.comprarComic(datosCompraComic);
	} 
}