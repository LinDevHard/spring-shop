# Spring Shop
Тестовый проект, который был создан с целью познать дзен Spring и бэкенед разработки. Результатом стал Spring REST Api для простого магазина.
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
Приложение было создано с использованием Docker, для сборки и запуска используте `docker-compose`
```bash
docker-compose up --build
```

## Docs
Документация представленна в Swagger и Markdown
### Swagger

```url
http://localhost:8080/swagger-ui.html
```

### Markdown
Open markdown [DOCS.md]


[DOCS.md]: https://gitlab.com/LinDevHard/demo-spring-shop/blob/master/docs/DOCS.md
[database_structure]: https://gitlab.com/LinDevHard/demo-spring-shop/raw/master/pic/db.png
