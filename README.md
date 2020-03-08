If I understand correctly Netty has 3 types of threads : **Boss**, **Worker**, and **Application Executor**.

When an incoming connexion (a request) reaches Netty, the boss thread (managed by ServerSocketChannel) gives it to a worker thread (managed by NioServerSocketChannelFactory) that performs in a non-blocking mode.

Micronaut documentation says (https://docs.micronaut.io/latest/guide/index.html#serverIO) :

> Micronaut’s HTTP server supports writing data without blocking simply
> by returning a Publisher the emits objects that can be encoded to the
> HTTP response.

And also (https://docs.micronaut.io/latest/guide/index.html#reactiveServer):

> If your controller method returns a non-blocking type such as an
> RxJava Observable or a CompletableFuture then Micronaut will use the
> Event loop thread to subscribe to the result.
> 
> If however you return any other type then Micronaut will execute your
> @Controller method in a preconfigured I/O thread pool.

What strategy of coding should be looked after :

**Reactive coding ?**

So if we have some blocking I/O we should return an Observable or a CompletableFuture and use a subscribeOn( Schedulers.io(), or else) strategy :

    @Get( "/{id}" )
    @Produces( MediaType.APPLICATION_JSON )
    public Single<Book> get( long id ) {
      return Single
        .fromCallable( () -> ioService.get( id ) )
        .subscribeOn( Schedulers.io( ) );
    }

while configuring io threads if many : 

    micronaut:
      executors:
        io:
          type: fixed
          nThreads: 75


and without blocking I/O, we could go directly like :

      @Get( "/{id}" )
      @Produces( MediaType.APPLICATION_JSON )
      public Book get( long id ) throws Exception {
        return nonIOService.get( id );
      }


**All IO ?**

If we know everything will requires IO, configure :

    micronaut:
        server:
            thread-selection: IO

And then just go by returning direct type (public Book myEndpoint... ) ?

      @Get( "/{id}" )
      @Produces( MediaType.APPLICATION_JSON )
      public Book get( long id ) throws Exception {
        return service.get( id );
      }

**Using annotations ?**

Configure :

    micronaut:
        server:
            thread-selection: MANUAL

And then use @NonBlocking or @Async without Schedulers, but then we have to return a CompletionStage or its derivative... Any example ?

      @Get( "/{id}" )
      @Async( "IO" )
      @Produces( MediaType.APPLICATION_JSON )
      return CompletionStage
        ???
      }




Bonus question, do you have some best practices to hide/simplify the reactive code, and error management ?


Thanks,


