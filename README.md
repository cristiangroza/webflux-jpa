# About

This is a simple project that implements a simple CRUD API based on Spring Webflux with Spring Data JPA as data access layer. 

Because of the non-blocking nature of a Spring Webflux application and the blocking nature of Spring Data JPA we need to make sure that we don't block the reactive thread pool.

By design you SHOULD NOT use Spring Webflux with Spring Data JPA but with Spring Data R2DBC. There are use cases though, where R2DBC is not mature enough to cover enterprise requirements. 