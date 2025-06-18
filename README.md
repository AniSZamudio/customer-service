# 🛠️ Backend API – customer-service

Este proyecto representa el backend RESTful para gestionar clientes y sus direcciones, desarrollado en Java con Spring Boot. Proporciona una API que es consumida por el frontend Angular (`customer-app`).

---

## 🚀 Características principales

- API RESTful documentada con Swagger
- Acceso a base de datos en memoria H2
- Manejo de clientes y sus direcciones
- Integración con frontend Angular
- Permite operaciones de crear, consultar y eliminar datos

---

## 🧰 Tecnologías utilizadas

- Java 11
- Spring Boot
- Spring Web
- Spring Data JPA
- Base de datos H2 (en memoria)
- Swagger (OpenAPI 3)
- Maven

---

## 📑 Endpoints principales

| Método | Endpoint                                      | Descripción                         |
|--------|-----------------------------------------------|-------------------------------------|
| POST   | `/customer/create`                            | Crear nuevo cliente                 |
| POST   | `/customer/getAllCustomersByCreateDate`       | Buscar clientes por fecha           |
| GET    | `/customer/findCustomerById/{id}`             | Buscar cliente por ID               |
| POST   | `/customerAddress/addAddress`                 | Agregar dirección a cliente         |
| DELETE | `/customerAddress/deleteAddress`              | Eliminar dirección                  |
| GET    | `/customerAddress/getAddressesByCustomerId/{id}` | Obtener direcciones de cliente    |

---

## 📄 Documentación de API (Swagger)

Puedes explorar y probar los endpoints directamente desde la interfaz Swagger:

🔗 [http://localhost:8081/swagger-ui/index.html#/](http://localhost:8081/swagger-ui/index.html#/)

---

## 💾 Base de datos en memoria H2

La aplicación utiliza H2, una base de datos en memoria, ideal para pruebas rápidas y desarrollo.

🔗 Consola H2: [http://localhost:8081/h2-console/](http://localhost:8081/h2-console/)

### Configuración común:
- JDBC URL: `jdbc:h2:mem:transborderTec`
- Usuario: `admin`
- Contraseña: `admin`

---

## ▶️ Cómo ejecutar el proyecto

1. Clona el repositorio o abre el proyecto en tu IDE favorito (IntelliJ, Eclipse).
2. Asegúrate de tener Java 11+ instalado.
3. Ejecuta la aplicación desde `CustomerServiceApplication.java` o con:

```bash
./mvnw spring-boot:run
```

4. Accede a:
   - Swagger UI: [http://localhost:8081/swagger-ui/index.html#/](http://localhost:8081/swagger-ui/index.html#/)
   - H2 Console: [http://localhost:8081/h2-console/](http://localhost:8081/h2-console/)

---

## 🧪 Cómo probar

- Usa Swagger para probar los endpoints directamente.
- Usa Postman y la colección `transborderTec.postman_collection.json` incluida con el proyecto.
- O prueba directamente desde el frontend en Angular.

---

## 📌 Notas

- La base de datos se reinicia cada vez que se reinicia la aplicación.
- Ideal para desarrollo, pruebas o prototipos rápidos.
---
