package semillero.pruebasUnitarias;

import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.log4j.BasicConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.enums.EstadoEnum;
import com.hbt.semillero.enums.TematicaEnum;

/**
 * Descripción: Clase que determina
 * Caso de Uso: SEMILLERO HBT 2021
 * @author Brayan Perez
 * @version 1.0
 */
public class CreacionComicTest {

	/**
	 * Constante que contiene el Log de la clase CreacionComicTest
	 */
	private final static Logger Log = Logger.getLogger(CreacionComicTest.class);
	
	private ArrayList<ComicDTO> listaComicsCreados; // Lista de comics creados para las pruebas 
	
	/**
	 * 
	 * Metodo encargado de inicializar los valores necesarios para realizar las pruebas 
	 * Caso de Uso: SEMILLERO HBT 2021
	 * @author Brayan Perez
	 *
	 */
	@BeforeTest
	public void inicializar() 
	{
		BasicConfigurator.configure(); //Inicializa el logger con una configuracion Basica
		
		Log.info("::::::::::::::INICIAN PRUEBAS UNITARIAS::::::::::::::::::");
		listaComicsCreados = crearComics(); //Se inicialia la ista de comics creados con los comics retornados por el metodo crearComics()
	} 
	
	/**
	 * 
	 * Metodo encargado de realizar la prueba que comprueba el metodo que retorna una lista con los comics que se encuentran activos 
	 * Caso de Uso: SEMILLERO HBT 2021
	 * @author Brayan Perez
	 *
	 */
	@Test
	public void comprobarListadoComicsActivos()
	{
		Log.info("Inicia ejecucion del metodo comprobarListadoComicsActivos()");
		
		//Se inicializa la lista de comics activos con la lista retornada por el metodo comicsActivos() que recibe la lista de comics creados 
		ArrayList<ComicDTO> listaComicsActivos = comicsActivos(listaComicsCreados);	

		//Ciclo donde se recorre la lista de comics activos y se muestra la informacion de cada comic y se comprueba que el comic si se encuentre activos 
		for (ComicDTO comicActivo : listaComicsActivos) {
			System.out.println("Nombre: "+comicActivo.getNombre());
			System.out.println("Editorial: "+comicActivo.getEditorial());
			System.out.println("Autores: "+comicActivo.getAutores());
			System.out.println("Estado: "+comicActivo.getEstadoEnum());
			System.out.println("-----------------------------------------");
			Assert.assertEquals(comicActivo.getEstadoEnum(), EstadoEnum.ACTIVO); // Se comprueba que el comic si se encuentra activo
		}
		
		Log.info("Finaliza ejecucion del metodo comprobarListadoComicsActivos()");
	}
	
	/**
	 * 
	 * Metodo encargado de realizar la prueba que comprueba el metodo que retorna una lista con los comics que se encuentran inactivos  
	 * Caso de Uso: SEMILLERO HBT 2021
	 * @author Brayan Perez
	 *
	 */
	@Test()
	public void comprobarListadoComicsInactivos()
	{
		Log.info("Inicia ejecucion del metodo comprobarListadoComicsInactivos()");
				
		//Bloque para conrolar la excepcion retornada por el metodo comicsInactivos()
		try {
			comicsInactivos(listaComicsCreados);
			Assert.fail("Exception expected."); //Garantiza que el metodo tiene que retornar una excepcion
		} catch (Exception e) {
			System.out.println("\n"+e.getMessage()+"\n"); //Muestra el mensaje en consola
		}
		
		Log.info("Finaliza ejecucion del metodo comprobarListadoComicsInactivos()");
	}
	
	/**
	 * 
	 * Metodo encargado de limpiar todo lo utilizado para la realización de las pruebas 
	 * Caso de Uso: SEMILLERO HBT 2021
	 * @author Brayan Perez
	 *
	 */
	@AfterTest
	public void finalizar() 
	{
		Log.info("::::::::::::::FINALIZAN PRUEBAS UNITARIAS::::::::::::::::::");
	}
	
	
	/**
	 * 
	 * Metodo encargado de crear 10 comics y retornar una lista de comcis creados
	 * Caso de Uso: SEMILLERO HBT 2021
	 * @author Brayan Perez
	 * 
	 * @return ArrayList<ComicDTO> - Lista con los comics creados
	 */
	private ArrayList<ComicDTO> crearComics() {
		
		//Lista de comics que se crean
		ArrayList<ComicDTO> listaComicsCreados = new ArrayList<ComicDTO>();	
		
		//Comics creados haciendo uso del constructor de la clase comicDTO
		ComicDTO comicCreado1 = new ComicDTO(1L,"Dragon Ball Super","Toei Animation",TematicaEnum.AVENTURAS,"Serie Manga",131,1200,"Akira Toriyama",true,LocalDate.now(),EstadoEnum.ACTIVO,20L);
		ComicDTO comicCreado2 = new ComicDTO(2L,"Batman","DC Comics",TematicaEnum.CIENCIA_FICCION,"Anime",144,1300,"Bill Finger",true,LocalDate.now(),EstadoEnum.ACTIVO,10L);
		ComicDTO comicCreado3 = new ComicDTO(3L,"Flash","DC Comics",TematicaEnum.AVENTURAS,"Manga Shonen",144,1400,"Dragon Garow Lee",true,LocalDate.now(),EstadoEnum.ACTIVO,89L);
		ComicDTO comicCreado4 = new ComicDTO(4L,"Superman","DC Comics",TematicaEnum.AVENTURAS,"Anime",144,1400,"Jerry Siegel",true,LocalDate.now(),EstadoEnum.ACTIVO,23L);
		ComicDTO comicCreado5 = new ComicDTO(5L,"Aquaman","DC Comics",TematicaEnum.AVENTURAS,"More Fun Comics",144,1700,"Mort Weisinger",true,LocalDate.now(),EstadoEnum.INACTIVO,0L);
		ComicDTO comicCreado6 = new ComicDTO(6L,"La mujer maravilla","DC Comics",TematicaEnum.FANTASTICO,"Manga Shonen",144,2100,"Dragon Garow Lee",true,LocalDate.now(),EstadoEnum.ACTIVO,20L);
		ComicDTO comicCreado7 = new ComicDTO(7L,"The Spectacular Spider_Man","Panini Comics",TematicaEnum.FANTASTICO,"MARVEL COMICS",208,6225,"Straczynski,Deodato Jr.,Barnes,Eaton",false,LocalDate.now(),EstadoEnum.INACTIVO,0L);
		ComicDTO comicCreado8 = new ComicDTO(8L,"Robocop","Marvel Comics",TematicaEnum.BELICO,"Manga Shonen",144,6000,"Alan Grante-Lee Sullivan",true,LocalDate.now(),EstadoEnum.ACTIVO,2L);
		ComicDTO comicCreado9= new ComicDTO(9L,"Captain America Corps","Panini Comics",TematicaEnum.FANTASTICO,"BIBLIOTECA MARVEL",128,5000,"Phillippe Briones, Roger Stern ",false,LocalDate.now(),EstadoEnum.ACTIVO,5L);
		ComicDTO comicCreado10 = new ComicDTO(10L,"X-MEN Unlimited","‎Marvel Comics",TematicaEnum.HORROR,"Anime",144,9000,"Stan Lee - ‎Jack Kirby",true,LocalDate.now(),EstadoEnum.INACTIVO,0L);
		
		//Se adiciona cada comic creado a la lista de comics creados 
		listaComicsCreados.add(comicCreado1);
		listaComicsCreados.add(comicCreado2);
		listaComicsCreados.add(comicCreado3);
		listaComicsCreados.add(comicCreado4);
		listaComicsCreados.add(comicCreado5);
		listaComicsCreados.add(comicCreado6);
		listaComicsCreados.add(comicCreado7);
		listaComicsCreados.add(comicCreado8);
		listaComicsCreados.add(comicCreado9);
		listaComicsCreados.add(comicCreado10);
		
		//retorna la lista de comics creados
		return listaComicsCreados;
	}
	
	/**
	 * 
	 * Metodo encargado de retornar una lista con los comics que se encuentran activos seleccionandolos de la lista de comics creados
	 * Caso de Uso: SEMILLERO HBT 2021
	 * @author Brayan Perez
	 * 
	 * @param listaComicsCreados - Lista con los comics que existen registrados
	 * @return ArrayList<ComicDTO> - Lista con los comics que estan Activos
	 */
	private ArrayList<ComicDTO> comicsActivos(ArrayList<ComicDTO> listaComicsCreados) {
		
		//Lista donde se guardan los comics que se encuentran activos
		ArrayList<ComicDTO> listaComicsActivos = new ArrayList<ComicDTO>();
		
		//Ciclo for para recorrer la lista de comics creados y guardar los comics activos en la lista de comics activos
		for (ComicDTO comicCreado : listaComicsCreados) {
			//condicion para saber si un comic se encuentra activo teniendo en cuenta el enum
			if(comicCreado.getEstadoEnum().equals(EstadoEnum.ACTIVO))
			{
				//se agrega el comic que se encuentra activo a la lista
				listaComicsActivos.add(comicCreado);
			}
		}
		//se retorna la lista con los comics activos
		return listaComicsActivos;
	}
	
	/**
	 * 
	 * Metodo encargado de retornar una lista con los comics que se encuentran inactivos seleccionandolos de la lista de comics creados 
	 * Caso de Uso: SEMILLERO HBT 2021
	 * @author Brayan Perez
	 * 
	 * @param listaComicsCreados - Lista con los comics que existen registrados
	 * @return ArrayList<ComicDTO> - Lista con los comics que estan Inactivos 
	 * @throws Exception 
	 */
	private ArrayList<ComicDTO> comicsInactivos(ArrayList<ComicDTO> listaComicsCreados) throws Exception {
		
		//Lista donde se guardan los comics que se encuentran inactivos
		ArrayList<ComicDTO> listaComicsInactivos = new ArrayList<ComicDTO>();
		//Lista donde se guardan los comics que se encuentran activos
		ArrayList<ComicDTO> listaComicsActivos = comicsActivos(listaComicsCreados);
		
		
		int tamanioListaTotal = listaComicsCreados.size(); //Variable para guardar el numero total de comics que se encuentran registrados
		int numeroTotalActivos = listaComicsActivos.size(); //Variable para guardar el numero total de comics que se encuentran activos
		int numeroTotalInactivos = 0;//Variable para guardar el numero total de comics que se encuentran inactivos
		
		String mensaje = ""; //Variable para guardar el mensaje que se va mostrar en la excepcion 
		
		String nombresComicsInactivos = ""; //Variable para guardar los nombres de los comics que se encuentran inactivos
		
		//Ciclo for para recorrer la lista de comics creados y guardar los comics activos en la lista de comics inactivos
		for (ComicDTO comicCreado : listaComicsCreados) {
			//condicion para saber si un comic se encuentra inactivo teniendo en cuenta el enum
			if(comicCreado.getEstadoEnum().equals(EstadoEnum.INACTIVO))
			{
				// Se concatenan los nombres de los comics inactivos 
				nombresComicsInactivos += comicCreado.getNombre()+" - ";
				//se agrega el comic que se encuentra inactivo a la lista 
				listaComicsInactivos.add(comicCreado);
			}
		}
		
		//Se guardan el numero total de comics inactivos en la variable
		numeroTotalInactivos = listaComicsInactivos.size();
		
		//Se crea el mensaje que se va mostrar en la excepcion en la variable mensaje
		mensaje += "Se ha detectado que de " + tamanioListaTotal + " comics, se encontraron que "+ numeroTotalActivos
				+ " se encuentran activos y " + numeroTotalInactivos + " inactivos. Los comics inactivos son: " + nombresComicsInactivos;
		
		//Se garantiza que si hay comics inactivos se cree la excepcion con el mensaje 
		if(!listaComicsInactivos.isEmpty()) 
		{
			throw new Exception(mensaje);
		}
		
		//se retorna la lista con los comics inactivos
		return listaComicsInactivos;
	}
}
