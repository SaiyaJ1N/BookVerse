## Online Book SHOP

---
#### Let me introduce you to the "Online Book Shop" project
#### "Online Book Shop" is a web application created using Java and the Spring Security framework, designed for online book buying and selling. With this project, users can register and log into their personal accounts, ensuring data security. The main functions of the application include book search and browsing, adding them to the shopping cart, making purchases, and book catalog administration for administrators. Spring Security helps ensure user security and authentication, making your store reliable and user-friendly.

---

### Basic Overview:
 - [Installation](#installation)
 - [Requirements](#requirements)
 - [Entities](#entities)
 - [Controllers](#controllers)
 - [Technologies](#technologies-used)

---

### Installation:
1. Clone this repository:

   ``git clone https://github.com/irynamekh/bookstore.git``

2. Go to the root project directory and build the project with command:

   ``mvn clean install``

3. Build docker container:

   ``docker build -t your-image-name .``

4. Run docker container:

   ``docker compose up``

---

### Requirements
#### Ensure that you have installed:
- Java 17 (https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- Docker (https://www.docker.com/)
- IntelliJ IDEA (https://www.jetbrains.com/idea/)
- You may also need Postman and Git.

---

### Entities

- **User**: Represents registered users with authentication details and personal information.
- **Role**: Defines user roles such as admin or regular user.
- **Book**: Represents books available in the store.
- **Category**: Represents books categories.
- **ShoppingCart**: Represents a user's shopping cart.
- **CartItem**: Represents an item in a user's shopping cart.
- **Order**: Represents an order placed by a user.
- **OrderItem**: Represents an item in a user's order.

---

### Controllers
- AuthenticationController (/auth)<p>
    Endpoints for authentication controller:
1) POST /register - Register user and save to DB;
2) POST /login - Login user and return JWT token;
-  BookController (/books)<p>
   Endpoints for authentication controller:
1) GET - Get a list of all available books;
2) GET /{id} - Get book by id;
3) POST - Create a new book and insert into database;
4) DELETE /{id} - Delete book by id;
5) PUT /{id} - Update a book to the request values;
6) GET /search - Search books by params. All params : author, isbn, price, title;
- CategoryController (/categories)<p>
  Endpoints for category controller:
1) POST - Create a new category;
2) GET - Get a list of all available categories;
3) GET /{id} - Get a category by id;
4) PUT /{id} - Update a category;
5) DELETE /{id} - Delete a category by id;
6) GET /{id}/books - Get a list of all available books by category id;
- ShoppingCartController (/cart)<p>
  Endpoints for shopping controller:
1) GET - Get a shopping cart with all cart items;
2) POST - Add a new cart item into shopping cart;
3) PUT - /cart-items/{cartItemId} - Update a cart item in shopping cart;
4) DELETE - /cart-items/{cartItemId} - Delete cart item in shopping cart
- OrderController (/orders)<p>
  Endpoints for order controller:
1) GET - Get orders history;
2) POST - Create an order and clear shopping cart;
3) GET - /{orderId}/items - Get a list of all items from order;
4) GET - /{orderId}/items/{itemId} - Get an order item from order;
5) PATCH - /{id} - Change order status;

---

### Technologies Used
This application includes the following list of technologies:
- **Spring Data JPA:** Ensuring streamlined and effective data retrieval and persistence.
- **Spring Boot:**     The project is built using the Spring Boot templates and conventions.
- **Spring Web:** Spring Web is used for HTTP requests handling, managing sessions, and processing web-related tasks.
- **Spring Security:** Authentication and authorization are managing to ensure secure access to endpoints with Spring Security.
- **Docker:** Docker is used for containerization database and for testing.
- **MapStruct:** Mapstruct is used for object mapping between DTOs and entities.
- **Swagger UI:** Swagger UI is used to provide interactive API documentation, testing use APIs more easily.




