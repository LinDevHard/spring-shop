# Spring Shop
A test project that was created to learn Spring Zen and backend development. The result was Spring REST Api for a simple store.
###### Developed by LinDevHard


## Features
 - Authorization via JWT Token
 - Error Handling
 - Product list with pagination 
 - Product adding 
 - User profile
 - User roles
 - User Cart 
 - User Order
  
  ## Database structure
![structure][database_structure]





## Build
The application was created using Docker, use `docker-compose` to build and run

```bash
docker-compose up --build
```

## Docs
Documentation provided by Swagger and Markdown
### Swagger

```url
http://localhost:8080/swagger-ui.html
```

### Markdown
Open markdown [DOCS.md]


[DOCS.md]: https://github.com/LinDevHard/spring-shop/blob/master/docs/DOCS.md
[database_structure]: https://github.com/LinDevHard/spring-shop/blob/master/pic/db.png
