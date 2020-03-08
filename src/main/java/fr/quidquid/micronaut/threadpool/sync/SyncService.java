package fr.quidquid.micronaut.threadpool.sync;

import fr.quidquid.micronaut.threadpool.sync.to.Book;
import io.micronaut.core.annotation.NonBlocking;
import io.micronaut.scheduling.annotation.Async;
import io.micronaut.scheduling.executor.ThreadSelection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.sql.SQLException;

public class SyncService {

  private static Logger log = LoggerFactory.getLogger( SyncService.class );

  private SyncDao dao;

  @Inject
  public SyncService( SyncDao dao ) {
    this.dao = dao;
  }

  public Book get( long id) throws Exception {
    log.info( Thread.currentThread( ).getName( ) );
    try {
      Book book = new Book();
      book.id = id;
      book.title = dao.get(id);
      return book;
    }
    catch ( SQLException e ) {
      e.printStackTrace();
      throw new Exception( "Could not find book with id " + id, e );
    }
  }
}
