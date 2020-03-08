package fr.quidquid.micronaut.threadpool.async;

import fr.quidquid.micronaut.threadpool.async.to.Book;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.scheduling.annotation.Async;
import io.micronaut.scheduling.executor.ThreadSelection;
import io.reactivex.*;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

@Controller( "/async/books" )
public class AsyncEndpoint {

  private static Logger log = LoggerFactory.getLogger( AsyncEndpoint.class );

  private AsyncService service;

  @Inject
  public AsyncEndpoint( AsyncService service ) {
    this.service = service;
  }

  @Get( "/{id}" )
  @Produces( MediaType.APPLICATION_JSON )
  @Async( "IO" )
  public CompletableFuture<Book> get( long id ) {
    log.info( Thread.currentThread( ).getName( ) );

    return CompletableFuture.supplyAsync( () -> {
      try {
        return service.get( id );
      }
      catch ( SQLException e ) {
        throw new RuntimeException( e );
      }
    } );

  }

//  @Async( "IO" )
//  @Get( "/{id}" )
//  @Produces( MediaType.APPLICATION_JSON )
//  public Single<Book> get( long id ) {
//    log.info( Thread.currentThread( ).getName( ) );
//
//    return Single
//        .fromCallable( () -> service.get( id ) )
//        .subscribeOn( Schedulers.io( ) );
//
//    }
}
