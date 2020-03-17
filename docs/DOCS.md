# Документация Spring Shop 

[Аутентификация](#Аутентификация)
- [Регистрация](#Регистрация)
- [Авторизация](#Авторизация)

## Аутентификация

### Регистрация

> **Url**: /api/v1/user/signup
>
> **Method**: POST
>
> **Auth**: False

```
    email - requered|string|email
    password - requered|string|min:8|max:32
    firstName - requered|string
    lastName - requered|string
    phone - requered|string|lenght:11
```
Подробное описание параметров:

1) email - Email пользователя
2) password - Пароль
3) firstName - Имя пользователя
4) lastName - Фамилия пользователя
5) phone - Номер телефона пользователя

### Пример

#### Запрос
##### Url
http://localhost:8080/api/v1/user/signup


##### Headers
Content-Type: application/json

#### Тело запроса:
``` json
{
	"email": "test@gmail.com",
	"password": "testtest",
	"firstName": "Lubomir",
	"lastName": "Kuznetsov",
	"phone": "77783465927"
}
```


##### **Status**: 200 <a id="reg200"></a>

> **Status**: 200

#### Тело ответа:

``` json
{

    "success": true,
    "status": "OK",
    "data": {
        "email": "test@gmail.com",
        "firstName": "Lubomir",
        "lastName": "Kuznetsov",
        "mobilePhone": "77783465927",
        "userRoles": [
            "USER"
        ],
        "fullName": "Lubomir Kuznetsov"
}
```


### Типы ответа
``` 
    success - required|bool
    status: required|string,
    data - requered|object
        userRoles - array|string
        fullName - requered|string
```

Подробное описание ответа:
1. data - объект с данными
    1. userRoles - массив ролей 
   

### Авторизация

> **Url**: /api/auth
>
> **Method**: POST
>
> **Auth**: False

```
    email - requered|string
    password - requered|string
```


Подробное описание параметров:

1) email - Email пользователя, зарегистированного в системе
2) password - Пароль

### Пример

#### Запрос
##### Url
http://localhost:8080/api/auth


##### Headers
Content-Type: application/json

#### Тело запроса:
``` json
{
    "email": "test@gmail.com",
    "password": "testtest"
}
```

#### Ответ
 - [Status: 200](#auth200)
 - [Status: 404](#auth404)
 - [Status: 400](#auth400)

##### **Status**: 200 <a id="auth200"></a>

> **Status**: 200

#### Тело ответа:

``` json
{
    "success": true,
    "status": "OK",
    "data": {
        "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsImV4cCI6MTU4NDI5Njg4OSwiaWF0IjoxNTg0Mjc4ODg5fQ.PPxTrmhG3W-WDbLoNTLQf4tOiBqH62kFoTVy9OpuSMWm-AJihXnbO6oxil7KZoFAtLiSJGBiuntR6S8v71LQ4w"
    }
}
```

### Типы ответа
``` 
    success - required|bool
    status: required|string,
    data - nullable|object
        accessToken - required|string
```

Подробное описание ответа:
1. data - объект с данными
    1. accessToken - Beerer токен
   
> **Status**: 404 <a id="auth404"></a>

#### Тело ответа:

``` json
{
    "success": false,
    "status": "NOT_FOUND",
    "message": "Requested user with email - test@gmail.com does not exist."
}
```

### Типы ответа
``` 
    success - required|bool
    status: required|string,
    message - required|string
```
   
> **Status**: 400 <a id="auth400"></a>

#### Тело ответа:

``` json

{
    "success": false,
    "status": "VALIDATION_EXCEPTION",
    "message": "[email: must be a well-formed email address, password: must not be blank]"
}
```