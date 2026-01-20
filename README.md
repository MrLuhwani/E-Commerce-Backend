# E-Commerce Backend System (Java CLI)
A simple command-line E-Commerce system built in Java.
This project is still in it's early phase, so the major focus is on applying core OOP principles such as abstraction and inheritance.
---

## Features
User Management:
- Customers create accounts
- Change account password
- Ability to add products to cart
- Login authentication (username & password)

product Management:
- products have different categories
- Variants of different products
- products can be stored in cart by users for later

Admin Services:
- Register new admins
- Admin login
- Change admin password
- Addition and removal of products, variants, and product categories
- edit product stock
- Change in price of products

---

## Future Features
- Persistence in App logic using Postgresql
- Usage of JDBC for proper encryptioning
- Password Encryption
- Integration of Spring boot
---

## Project Structure
ECommerceBackend/  
├── e-commerce-project/  
├── src/  
│   ├── main/  
│   │    └── java/  
│   │        └── dev/  
│   │            └── luhwani/  
│   │                └── app/  
│   │                    ├── applicationContext/  
│   │                    │   └── AdminAppContext.java  
│   │                    │   └── UserAppContext.java  
│   │                    ├── models/  
│   │                    │   ├─ cartModel/  
│   │                    │   │    └── Cart.java  
│   │                    │   │    └── CartItem.java
│   │                    │   ├─ productModels/  
│   │                    │   │    └── Category.java  
│   │                    │   │    └── Product.java  
│   │                    │   │    └── Variant.java  
│   │                    │   └─ userModels/  
│   │                    │        └── Admin.java  
│   │                    │        └── Customer.java  
│   │                    │        └── Person.java  
│   │                    │        └── Staff.java  
│   │                    │        └── UserModel.java  
│   │                    ├── repositories/  
│   │                    │   └── AdminRepo.java  
│   │                    │   └── CartRepo.java  
│   │                    │   └── CustomerRepo.java  
│   │                    │   └── ProductRepo.java  
│   │                    ├── services/  
│   │                    │   ├─ adminServices/  
│   │                    │   │    └── AdminProductService.java  
│   │                    │   │    └── AdminService.java  
│   │                    │   ├─ userServices/  
│   │                    │   │    └── CartService.java  
│   │                    │   │    └── CustomerService.java  
│   │                    │   │    └── ProductService.java  
│   │                    │   └─ Utils.java
│   │                    ├── AdminApp.java  
│   │                    └── UserApp.java  
│   ├── target/  
│   └── pom.xml  
├── .gitignore  
└── README.md  
---

## License
This project is for learning purposes at the moment, so it is therefore open for modification and experimentation