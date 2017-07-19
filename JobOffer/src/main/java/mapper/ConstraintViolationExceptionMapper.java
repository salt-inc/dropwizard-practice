package mapper;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException>{
	
	/** 改行コード */
	private static final String BR = System.getProperty("line.separator");

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		
		final Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
		
		// 返却するメッセージを設定
		String responseMessage = violations.stream()
			.map(message -> message.getMessage() + BR)
			.collect(Collectors.joining());
		
		return Response.status(Status.PRECONDITION_FAILED).type(MediaType.TEXT_PLAIN_TYPE)
				.entity(responseMessage).build();
	}

}
