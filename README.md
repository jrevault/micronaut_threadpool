Hi ,

If I understood correctly Netty has 3 types of threads : Boss, Worker, and Application Executor.

When an incoming connexion (a request) reaches Netty, the boss thread (managed by ServerSocketChannel) gives it to a worker thread (managed by NioServerSocketChannelFactory) that performs in a non-blocking mode.
