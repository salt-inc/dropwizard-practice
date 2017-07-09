package mapper;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException>{
	
	/** 改行コード */
	private static final String BR = System.getProperty("line.separator");

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		
		final Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
		
		String responseMessage = "";
		
		Iterator<ConstraintViolation<?>> iterator = violations.iterator();
		
		while (iterator.hasNext()) {
			responseMessage += iterator.next().getMessage() + BR;
		}
		
		return Response.status(412).type(MediaType.TEXT_PLAIN_TYPE)
				.entity(responseMessage).build();
	}

}
