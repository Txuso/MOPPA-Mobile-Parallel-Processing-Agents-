package exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import com.sun.jersey.api.client.ClientResponse.Status;

public class MoppaExceptionHandler implements ExceptionMapper<MoppaException>{

	public Response toResponse(MoppaException exception) {
		return Response.status(Status.BAD_REQUEST).entity(exception.getMessage()).build();
		
	}

}
