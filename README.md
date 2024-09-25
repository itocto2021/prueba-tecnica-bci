## Diagrama de solucion
![Diagrama de solucion](src/main/resources/static/bci-diagram.jpg)


# API de Gestión de Usuarios

Este proyecto proporciona una API REST para gestionar usuarios en el sistema. Permite registrar, obtener, actualizar y eliminar usuarios.

## DESCARGAR LAS COLECCIONES DE POSTMAN
- [BCI.postman_collection.json](src/main/resources/postman/BCI.postman_collection.json)
- [BCI.postman_environment.json](src/main/resources/postman/BCI.postman_environment.json)

## Endpoints

### 1. Registrar un Nuevo Usuario

- **URL**: `/usuario`
- **Método**: `POST`
- **Headers**:
    - `Accept:application/json`
    - `Authorization:Bearer {{token_bci}}`.
- **Request Body**:
  ```json
  {
    "name": "Nombre del Usuario",
    "email": "usuario@example.com",
    "password": "contraseña",
    "phones": [
      {
        "number": "123456789",
        "citycode": "01",
        "contrycode": "52"
      }
    ]
  }
### 2. Actualizar Usuario

- **URL**: `/usuario`
- **Método**: `PUT`
- **Headers**:
    - `Accept:application/json`
    - `Authorization:Bearer {{token_bci}}`.
- **Request Body**:
  ```json
  {
    "id": "d84f96f6-9db6-4f51-a4ee-624a4a510e0b",
    "name": "Julia",
    "email": "toctojulian@gmail.org",
    "password": "Jul123Toc456#",
    "phones": [
        {
            "number": "956433700",
            "citycode": "1",
            "contrycode": "51"
        }
    ]     
  }
### 3. Consultar Usuarios

- **URL**: `/usuario`
- **Método**: `GET`
- **Headers**:
    - `Accept:application/json`
    - `Authorization:Bearer {{token_bci}}`.
- **Request Body**:
  ```json
  [
    {
        "id": "18f3b277-c8ee-46fa-98e7-556c5bec0991",
        "name": "JuIlderan Rodriguez",
        "email": "cruzi@rodriguez.org",
        "password": "Ild123Toc456#",
        "phones": [
            {
                "number": "956433712",
                "citycode": "1",
                "contrycode": "57"
            }
        ]
    },
    {
        "id": "aa572b71-bc8a-4988-b0d1-9c3c6b02db38",
        "name": "prueba2 Rodriguez",
        "email": "prueba2@rodriguez.org",
        "password": "Ild123Toc456#",
        "phones": [
            {
                "number": "956433712",
                "citycode": "1",
                "contrycode": "57"
            }
        ]
    },
    {
        "id": "1e724b11-c1b7-4263-b120-3b2bc08503d9",
        "name": "prueba3 Rodriguez",
        "email": "prueb3@rodriguez.org",
        "password": "Ild123Toc456#",
        "phones": [
            {
                "number": "956433712",
                "citycode": "1",
                "contrycode": "57"
            }
        ]
    }
  ]
### 4. Consultar Usuarios Por Id

- **URL**: `/usuario/18f3b277-c8ee-46fa-98e7-556c5bec0991`
- **Método**: `GET`
- **Headers**:
    - `Accept:application/json`
    - `Authorization:Bearer {{token_bci}}`.
- **Request Body**:
  ```json
  {
    "id": "18f3b277-c8ee-46fa-98e7-556c5bec0991",
    "name": "JuIlderan Rodriguez",
    "email": "cruzi@rodriguez.org",
    "password": "Ild123Toc456#",
    "phones": [
        {
            "number": "956433712",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
  }
### 5. Eliminar Usuario

- **URL**: `/usuario/18f3b277-c8ee-46fa-98e7-556c5bec0991`
- **Método**: `DELETE`
- **Headers**:
    - `Accept:application/json`
    - `Authorization:Bearer {{token_bci}}`.

## Pasos para Probar la API

descargar la collection de postman e importar


## 1. Autenticación

Para consumir la API de creación de usuario, primero necesitas autenticarte.

- **Método**: `POST`
- **URI**: `http://localhost:8080/authenticate`

#### Cuerpo (Body)

```json
{
    "username": "T50735",
    "password": "Ild234Toc486#"
}
```
#### Response (200)
```
{
    "token": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJUNTA3MzUiLCJpYXQiOjE3MjY2MTUwOTYsImV4cCI6MTcyNjYxODY5Nn0.NgpI2ZmOp1bjTQj3xQgPSUe7vSJjmOv2BWgTMFI21xhCrV1f5nWPk1-OkxxAsqK1",
    "tokenType": "Bearer",
    "expiresIn": 3600
}
```

## 2. Consumir la API Crear Usuario
Con el token que generamos en el paso 1 consumimos en el API de crear Usuario

- **Método**: `POST`
- **URI**: `http://localhost:8080/usuario`

#### 2.1 Agregamos los Headers
```
Accept:application/json
Authorization:Bearer {pegar aqui el token}
```
![Header](src/main/resources/static/create_user.png)

#### 2.2 Agreamos el siguiente Body
```
{
    "name":"Juan Rodriguez",
    "email":"juan@rodriguez.org",
    "password":"Ild123Toc456#",
    "phones":[
        {
        "number":"1234567",
        "citycode":"1",
        "contrycode":"57"
        }
    ]
}
```

#### 2.3 Response
```
{
    "id": "a17a097d-a1eb-42e7-ac1c-1f8929008ef2",
    "created": "2024-09-17T18:24:07.475731500",
    "modified": "2024-09-17T18:24:07.475731500",
    "lastLogin": "2024-09-17T18:24:07.476731200",
    "token": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJUNTA3MzUiLCJpYXQiOjE3MjY
    2MTUwOTYsImV4cCI6MTcyNjYxODY5Nn0.NgpI2ZmOp1bjTQj3xQgPSUe7vSJjmOv2BWgTMFI21xhCrV1f5nWPk1-OkxxAsqK1",
    "isActive": "true"
}
```
![Header](src/main/resources/static/resp_create_user.png)

## 3. Validaciones
#### 3.2 Validacion de Emamil
- Se valida mediante un regex que sea un email valido

#### 3.2 Validacion de Password
  - Debe contener al menos una letra minúscula (a-z).
  - Debe contener al menos una letra mayúscula (A-Z).
  - Debe contener al menos un dígito (0-9).
  - Debe contener al menos un carácter especial de los permitidos (!@#$%^&*(),.?":{}|<>~[]£=).
  - Debe tener al menos 8 caracteres de longitud.

