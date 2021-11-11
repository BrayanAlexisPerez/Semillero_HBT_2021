package com.hbt.semillero.interfaces;

import javax.ejb.Local;

import com.hbt.semillero.dto.DatosCompraComicDTO;
import com.hbt.semillero.dto.ResultadoDTO;

@Local
public interface IGestionarCompraComic {
	
	//Metodo para gestionar la compra de un comic
	public ResultadoDTO comprarComic(DatosCompraComicDTO datosCompraComic);
	
}
