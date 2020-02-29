package fr.quidquid.micronaut.threadpool.sync;

import fr.quidquid.micronaut.threadpool.to.Book;
import io.micronaut.http.*;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.hateoas.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Controller( "/books" )
public class SyncEndpoint {

  private static Logger log = LoggerFactory.getLogger( SyncEndpoint.class );

  private SyncService service;

  @Inject
  public SyncEndpoint( SyncService service ) {
    this.service = service;
  }

  @Get( value = "/{id}", produces = MediaType.APPLICATION_JSON )
  public MutableHttpResponse<Book> get( long id) throws Exception {
      return HttpResponse.ok( service.get( id ) );
  }


  @Post( value = "/", produces = MediaType.APPLICATION_JSON )
  public MutableHttpResponse<Object> create( @Body String title) throws Exception {
    service.create( title );
    return HttpResponse.ok();

  }

  @Error(global = true)
  public HttpResponse<JsonError> error( HttpRequest request, Exception exception) {
    JsonError error = new JsonError("Error occured: " + exception.getMessage())
        .link( Link.SELF, Link.of(request.getUri()));

    return HttpResponse.<JsonError>serverError().body(error);
  }
}
