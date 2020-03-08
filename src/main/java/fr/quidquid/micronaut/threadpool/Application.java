package fr.quidquid.micronaut.threadpool;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.hateoas.Link;
import io.micronaut.runtime.Micronaut;

public class Application {

  public static void main( String[] args ) {
    Micronaut.run( Application.class );
  }

  @Error(global = true)
  public HttpResponse<JsonError> error( HttpRequest request, Exception exception) {
    JsonError error = new JsonError("Error occured: " + exception.getMessage())
        .link( Link.SELF, Link.of(request.getUri()));

    return HttpResponse.<JsonError>serverError().body(error);
  }

}