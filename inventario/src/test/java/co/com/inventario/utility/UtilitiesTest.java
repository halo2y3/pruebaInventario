package co.com.inventario.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import javax.validation.Validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import co.com.inventario.exception.ExceptionManager;
import co.com.inventario.exception.ExceptionManager.NullEntityExcepcion;
import co.com.inventario.modelo.Usuarios;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UtilitiesTest {

	@Autowired
	private Validator validator;

	@Test
	public void toStringObjecTest(){
		Object objeto=new Object();
		assertEquals(objeto.toString(), Utilities.toStringObjec(objeto));
	}

	@Test
	public void isNumericTest(){
		assertTrue(Utilities.isNumeric("1"));
		assertTrue(!Utilities.isNumeric("1a"));
	}
	
	@Test
	public void isDecimalTest(){
		assertTrue(Utilities.isDecimal("1"));
		assertTrue(!Utilities.isDecimal("1..ya"));
	}
	
	@Test
	public void checkWordAndCheckWithlengthTest(){
		assertTrue(Utilities.checkWordAndCheckWithlength("a", 1));
		assertTrue(!Utilities.checkWordAndCheckWithlength("ab", 1));
	}
	
	@Test
	public void isOnlyLettersTest(){
		assertTrue(Utilities.isOnlyLetters("a"));
		assertTrue(!Utilities.isOnlyLetters("ab@"));
	}
	
	@Test
	public void validationsListTest(){
		assertTrue(!Utilities.validationsList(null));
		ArrayList<String> lista=new ArrayList<>();
		lista.add("2");
		assertTrue(Utilities.validationsList(lista));
		lista.remove(0);
		assertTrue(!Utilities.validationsList(lista));
	}

	@Test
	public void validationObjetoTest(){
		Utilities.validationObjeto("1", "Algo");

		Assertions.assertThrows(NullEntityExcepcion.class, () -> {
			Utilities.validationObjeto(null, "Error Vacio");
		});
	}
	
	@Test
	public void validateTest(){
		Assertions.assertThrows(ExceptionManager.class, () -> {
			Utilities.validate(new Usuarios(), validator);
		});
	}

}
