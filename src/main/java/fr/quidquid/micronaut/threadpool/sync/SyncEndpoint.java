package fr.quidquid.micronaut.threadpool.sync;

import fr.quidquid.micronaut.threadpool.sync.to.Book;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Controller( "/sync/books" )
public class SyncEndpoint {

  private static Logger log = LoggerFactory.getLogger( SyncEndpoint.class );

  private SyncService service;

  @Inject
  public SyncEndpoint( SyncService service ) {
    this.service = service;
  }

  @Get( "/{id}" )
  @Produces( MediaType.APPLICATION_JSON )
  public Book get( long id ) throws Exception {
    log.info( Thread.currentThread( ).getName( ) );
    return service.get( id );
  }

}
