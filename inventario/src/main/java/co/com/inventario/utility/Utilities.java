package co.com.inventario.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import co.com.inventario.exception.ExceptionManager;
import co.com.inventario.modelo.Usuarios;
import lombok.extern.slf4j.Slf4j;


/**
 * @author Edwin Gonzalez
 * 
 */
@Slf4j
public class Utilities {
	
	private Utilities() {
	}

	public static void validate(Object object, Validator validator) {
		Set<ConstraintViolation<Object>> constraintViolations=validator.validate(object);
		if (!constraintViolations.isEmpty()) {
			StringBuilder strMessage = new StringBuilder();
			for (ConstraintViolation<?> constraintViolation : constraintViolations) {
				strMessage.append(constraintViolation.getPropertyPath().toString());
				strMessage.append(" - "+constraintViolation.getMessage()+". \n");
			}
			throw new ExceptionManager(strMessage.toString());
		}
	}

	public static String toStringObjec(Object objeto) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.enable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		try {
			return mapper.writeValueAsString(objeto);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
			return objeto.getClass().getName() + "@" + Integer.toHexString(objeto.hashCode());
		}
	}

	public static boolean isNumeric(String word) {
		boolean ret = false;
		Pattern pat = Pattern.compile("[^0-9',.\\s]");
		Matcher mat = pat.matcher(word);
		if (!mat.find()) {
			ret = true;
		}
		return ret;
	}

	/**
	 * 
	 * @param word
	 * @return Expresion regular "(\\d){1,10}\\.(\\d){1,10}" (\\d)digito
	 *         {1,10}de 1 a 10 caracteres \\. punto
	 * 
	 */
	public static boolean isDecimal(String word) {
		boolean ret = false;
		Pattern pat = Pattern.compile("(\\d){1,8}\\.(\\d){0,2}");
		Matcher mat = pat.matcher(word);
		if (!mat.find()) {
			ret = true;
		}
		return ret;
	}

	public static boolean checkWordAndCheckWithlength(String word, Integer length) {
		boolean ret = false;
		if (word.length() <= length) {
			ret = true;
		}
		return ret;
	}

	public static boolean isOnlyLetters(String word) {
		boolean ret = false;
		Pattern pat = Pattern.compile("[^A-Za-z0-9',.\\s]");
		Matcher mat = pat.matcher(word);
		if (!mat.find()) {
			ret = true;
		}
		return ret;
	}

	public static boolean validationsList(List<?> list) {
		return (list != null && !list.isEmpty());
	}

	public static void validationObjeto(Object objeto, String mensaje) {
		if (objeto == null) {
			throw new ExceptionManager().new NullEntityExcepcion(mensaje);
		}
	}
	
	public static Usuarios crearUsuario(Integer idUsuario) {
		if(idUsuario==null) {
			return null;
		}else {
			Usuarios usuario=new Usuarios();
			usuario.setIdusuario(idUsuario);
			return usuario;
		}
	}
	
	public static boolean validarFechaMenor(Date fecha) {
		if(fecha==null) {
			return false;
		}
		
		Date fechaActual=new Date();
	    return fecha.before(fechaActual)|| fecha.equals(fechaActual);
	}

	public static Date parsearFecha(String fechaTexto) {
		if(fechaTexto==null) {
			return null;
		}
		
		SimpleDateFormat formatD=new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formatD.parse(fechaTexto);
		} catch (ParseException e) {
			throw new ExceptionManager("Fecha no valida");
		}
	}
	
}