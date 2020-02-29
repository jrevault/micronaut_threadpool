package fr.quidquid.micronaut.threadpool.sync;

import fr.quidquid.micronaut.threadpool.to.Book;

import javax.inject.Inject;
import java.sql.SQLException;

public class SyncService {

  private SyncDao dao;

  @Inject
  public SyncService( SyncDao dao ) {
    this.dao = dao;
  }

  public void create(String title) throws Exception {
    try {
      dao.create(title);
    }
    catch ( SQLException e ) {
      e.printStackTrace();
      throw new Exception( "Could not insert book with title " + title, e );
    }
  }

  public Book get( long id) throws Exception {
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
