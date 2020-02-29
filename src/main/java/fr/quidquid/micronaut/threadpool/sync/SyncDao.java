package fr.quidquid.micronaut.threadpool.sync;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SyncDao {

  DataSource dataSource;

  @Inject
  public SyncDao( DataSource dataSource ) {
    this.dataSource = dataSource;
  }


  public String get( long id ) throws SQLException {

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
