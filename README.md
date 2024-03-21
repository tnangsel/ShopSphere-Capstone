------------------------------------------- Project Overview ----------------------------------------------</br>

ShopSphere represents the next generation of e-commerce platforms, designed to provide an
intuitive, secure, and comprehensive online shopping experience. Built using cutting-edge
technologies, ShopSphere caters to both end-users and administrators, offering a wide range of
functionalities from product browsing to complex order management systems. This document outlines
the technical foundation, features, and endpoint details of ShopSphere, highlighting its microservices
architecture and Angular-based frontend design.

------------------------------------------- Technology Stack ----------------------------------------------</br>

• Frontend: Angular, Bootstrap  </br>
• Backend: Spring Boot (Java), Spring Data JPA, Spring Security with JWT, Spring Batch   </br>
• Database: MySQL    </br>

------------------------------------------- Microservices Architecture ----------------------------------------------</br>

• User Service: Handles authentication, registration, and user management. Integrates with Spring Security for secure authentication. </br>
• Product Service: Manages product listings, including categories and bulk uploads, leveraging Spring Data JPA for database operations. </br>
• Cart Service: Facilitates shopping cart operations, including adding, removing, and updating itemswithin a user's cart. </br>
• Wishlist Service: Allows users to manage their wishlists, adding or removing products as desired. </br>
• Order Service: Processes orders, maintaining records of user purchases and order history. </br>
• Discount Service: Manages discount codes and coupon applications for orders, enhancing the shopping experience. </br>

------------------------------------------- Microservices Endpoints ----------------------------------------------</br>
-------Public endpoints-------</br>
auth/register -> user registration  </br>
auth/login -> user login with email and password. User will be routed to user page and ADMIN will be routed to admin page </br>
auth/logout -> user logout with token </br>
products -> will display all the products available to purchase for users </br>
products/{productID} -> retrieves detailed information about a specific product. </br>
products/categories/{categoryName} -> display all the products of that perticular category name </br>

-------Secure endpoints of Admin----------</br>

admin/users -> gets the list of all users </br>
admin/users/{userId} -> can update specific user based on their userId </br>
admin/users/{userId} -> can delete specific user based on their userId </br>
admin/products -> adds a new product to the catalog </br>
admin/products/{productId} -> updates information for an existing product. </br>
admin/products/{productId} -> removes a product from the catalog. </br>
admin/discount/addDiscount -> adds a discount code and price </br>
admin/discount/{discountCode} -> retreives the price of the discountCode to be reduced  </br>
admin/deleteDiscount/{discountId} -> removes a discount from the table. </br>

-------Secure endpoints of User----------</br>

cart/{userId}/add -> adds a product to the cart </br>
cart/{userId} -> display all the products in the cart of that user. </br>
cart/{userId}/update -> updates the quantity of a product in the cart </br>
cart/{userId}/remove/{productId} -> removes a specific product from the cart of that user </br>
orders/{username} -> gets the orders details of that user </br>
orders/{userId} -> placed an order and saves the order details. </br>

------------------------------------------- Clone to your local system ---------------------------</br>
Backend:  git clone https://github.com/tnangsel/ShopSphere-Capstone.git -b master   </br>
Frontend:  git clone https://github.com/tnangsel/ShopSphere.git -b master

-------------------- Angular CLI commands to run the server-------------------------------</br>
npm install     </br>
ng serve
