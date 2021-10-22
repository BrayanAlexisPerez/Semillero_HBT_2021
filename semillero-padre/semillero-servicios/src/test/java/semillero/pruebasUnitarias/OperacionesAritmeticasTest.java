//package semillero.pruebasUnitarias;
//
//import org.apache.log4j.BasicConfigurator;
//import org.apache.log4j.Logger;
//import org.testng.Assert;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
///**
// * 
// * Descripción: Clase que determina
// * Caso de Uso: SEMILLERO HBT 2021
// * @author Brayan Perez
// * @version 1.0
// */
//public class OperacionesAritmeticasTest {
//	
//	/**
//	 * Constante que contiene el Log de la clase OperacionesAritmeticasTest
//	 */
//	private final static Logger Log = Logger.getLogger(OperacionesAritmeticasTest.class);
//	
//	@BeforeTest
//	public void inicializar() 
//	{
//		BasicConfigurator.configure(); //Inicializa el logger con una configuracion Basica
//		Log.info("::::::::::::::INICIAN PRUEBAS UNITARIAS::::::::::::::::::");
//	}
//	
//	@Test
//	public void validarResultadoSumaExitoso()
//	{
//		Log.info("Inicia ejecucion del metodo validarResultadoSumaExitoso()");
//		
//		int numero1 = 300;
//		int numero2 = 150;
//		int resultado = 450;
//				
//		//Assert es una libreria para validar resultados, compara dos condiciones
//		//assertEquals es para ver sin dos condiciones son iguales
//		Assert.assertEquals(resultado, numero1 + numero2);
//		
//		Log.info("Finaliza ejecucion del metodo validarResultadoSumaExitoso()");
//	}
//	
//	@Test
//	public void validarResultadoSumaFallido()
//	{
//		Log.info("Inicia ejecucion del metodo validarResultadoSumaFallido()");
//		
//		int numero1 = 300;
//		int numero2 = 150;
//		int resultado = 455;
//		
//		try {
//			if(resultado != (numero1 + numero2))
//			{
//				Log.info("Se ha generado un error funcional, probando el test validarResultadoSumaFallido()");
//				throw new Exception("La suma ha fallado en el calculo");
//			}
//		} catch (Exception e) {
//			Assert.assertEquals(e.getMessage(), "La suma ha fallado en el calculo");
//		}
//		
//		Log.info("Finaliza ejecucion del metodo validarResultadoSumaFallido()");
//	}
//	
//	@AfterTest
//	public void finalizar() 
//	{
//		Log.info("::::::::::::::FINALIZAN PRUEBAS UNITARIAS::::::::::::::::::");
//	}
//	
//}
