package fr.quidquid.micronaut.threadpool.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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