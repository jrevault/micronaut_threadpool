package fr.quidquid.micronaut.threadpool.sync.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;

@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonPropertyOrder( {
    "id" ,
    "title"
} )
public class Book {

  @JsonProperty( "id" )
  public long id;
  @JsonProperty( "title" )
  public String title;

}