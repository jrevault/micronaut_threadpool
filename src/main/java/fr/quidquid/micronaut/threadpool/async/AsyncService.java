package fr.quidquid.micronaut.threadpool.async;

import fr.quidquid.micronaut.threadpool.async.to.Book;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.sql.SQLException;

public class AsyncService {

  private static Logger log = LoggerFactory.getLogger( AsyncService.class );

  private AsyncDao dao;

  @Inject
  public AsyncService( AsyncDao dao ) {
    this.dao = dao;
  }

//  public Observable<Book> get( long id) {
//    return Observable.fromCallable(() -> {
//      if (log.isDebugEnabled()) {
//        log.debug("Getting book with id : " + id);
//      }
//      // trace logic here, potentially performing I/O
//      Book book = new Book( );
//      book.id = id;
//      book.title = dao.get( id );
//      return book;
//    }).subscribeOn(Schedulers.io());
//  }

//  public Book get( long id ) throws SQLException {
//    log.info( Thread.currentThread( ).getName( ) );
//    Book book = new Book( );
//    book.id = id;
//
//    book.title =  dao.get( id );
//    Single.fromCallable( () -> dao.get( id ) )
//        .subscribeOn( Schedulers.io( ) )
//        .subscribe( title -> {
//          log.info( Thread.currentThread( ).getName( ) );
//          book.title = title;
//          Thread.sleep( 200 );
//        });

//    return book;
//  }

  public Book get( long id ) throws SQLException {
    log.info( Thread.currentThread( ).getName( ) );
    Book book = new Book( );
    book.id = id;

    book.title =  dao.get( id );
    return book;
  }
}
