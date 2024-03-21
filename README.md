------------------------------------------- Project Overview ----------------------------------------------

ShopSphere represents the next generation of e-commerce platforms, designed to provide an intuitive, secure, and comprehensive online shopping experience. Built using cutting-edge technologies, ShopSphere caters to both end-users and administrators, offering a wide range of functionalities from product browsing to complex order management systems. This document outlines the technical foundation, features, and endpoint details of ShopSphere, highlighting its microservices architecture and Angular-based frontend design.

------------------------------------------- Technology Stack ----------------------------------------------

• Frontend: Angular, Bootstrap
• Backend: Spring Boot (Java), Spring Data JPA, Spring Security with JWT, Spring Batch
• Database: MySQL

------------------------------------------- Microservices Architecture ----------------------------------------------

• User Service: Handles authentication, registration, and user management. Integrates with Spring Security for secure authentication.
• Product Service: Manages product listings, including categories and bulk uploads, leveraging Spring Data JPA for database operations.
• Cart Service: Facilitates shopping cart operations, including adding, removing, and updating itemswithin a user's cart.
• Wishlist Service: Allows users to manage their wishlists, adding or removing products as desired.
• Order Service: Processes orders, maintaining records of user purchases and order history.
• Discount Service: Manages discount codes and coupon applications for orders, enhancing the shopping experience.

------------------------------------------- Microservices Endpoints ----------------------------------------------
-------Public endpoints-------
auth/register -> user registration
auth/login -> user login with email and password. User will be routed to user page and ADMIN will be routed to admin page
auth/logout -> user logout with token
products -> will display all the products available to purchase for users
products/{productID} -> retrieves detailed information about a specific product.
products/categories/{categoryName} -> display all the products of that perticular category name

-------Secure endpoints of Admin----------

admin/users -> gets the list of all users
admin/users/{userId} -> can update specific user based on their userId
admin/users/{userId} -> can delete specific user based on their userId
admin/products -> adds a new product to the catalog
admin/products/{productId} -> updates information for an existing product.
admin/products/{productId} -> removes a product from the catalog.
admin/discount/addDiscount -> adds a discount code and price
admin/discount/{discountCode} -> retreives the price of the discountCode to be reduced
admin/deleteDiscount/{discountId} -> removes a discount from the table.

-------Secure endpoints of User----------

cart/{userId}/add -> adds a product to the cart
cart/{userId} -> display all the products in the cart of that user.
cart/{userId}/update -> updates the quantity of a product in the cart
cart/{userId}/remove/{productId} -> removes a specific product from the cart of that user
orders/{username} -> gets the orders details of that user
orders/{userId} -> placed an order and saves the order details.

------------------------------------------- Clone to your local system ---------------------------
Backend: git clone https://github.com/tnangsel/ShopSphere-Capstone.git -b master
Frontend: git clone https://github.com/tnangsel/ShopSphere.git -b master

-------------------- Angular CLI commands to run the server-------------------------------
npm install
ng serve
