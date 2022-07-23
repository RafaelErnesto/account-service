package quarkus;




import io.quarkus.vertx.http.runtime.devmode.Json.JsonObjectBuilder;
import io.quarkus.vertx.http.runtime.devmode.Json;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException exception) {
        JsonObjectBuilder responseBuilder = Json.object()
                .put("exceptionType", exception.getClass().getName())
                .put("code", 404);

        if (exception.getMessage() != null) {
            responseBuilder.put("error", exception.getMessage());
        }

        return Response.status(404)
                .entity(responseBuilder.build())
                .build();
    }
}
