package fr.quidquid.micronaut.threadpool.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AsyncDao {

  private static Logger log = LoggerFactory.getLogger( AsyncDao.class );

  DataSource dataSource;

  @Inject
  public AsyncDao( DataSource dataSource ) {
    this.dataSource = dataSource;
  }


  public String get( long id ) throws SQLException {
    log.info( Thread.currentThread( ).getName( ) );

    String sql = "select title from books where id=" + id;

    try ( Connection con = dataSource.getConnection( ) ;
          PreparedStatement ps = con.prepareStatement( sql ) ; ) {

      try ( ResultSet rs = ps.executeQuery( ) ; ) {
        rs.next( );
        return rs.getString( "title" );
      }
    }
  }

}
