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
---

## Planned Features
User Management:
- Persistence in App logic

Admin Tools
- Addition and removal of products
- Analytics of most purchased product
- Change in price of products
---

## Project Structure
ECommerceBackend/  
├── .vscode/  
│   └── settings.json  
├── lib/  
├── src/  
│   └── dev/  
│       └── luhwani/  
│           └── eCommerceSystem/  
│               ├── cartModel/  
│               │   └── Cart.java  
│               │   └── CartItem.java  
│               │  
|               ├── productModels/  
|               |   ├── categories/  
│               │   │   └── Automotives.java  
│               │   │   └── Computerss.java  
│               │   │   └── Sports.java  
│               │   ├── Category.java  
│               │   ├── Product.java  
│               │   ├── Variant.java  
│               │  
│               ├── services/  
│               │   └── AdminProductServices.java  
│               │   └── AdminServices.java  
│               │   └── CartServices.java  
│               │   └── productServices.java  
│               │   └── UserServices.java  
│               │  
│               ├── userModels/  
│               │   └── Admin.java  
│               │   └── Customer.java  
│               │   └── Person.java  
│               │   └── Role.java  
│               │   └── UserModel.java  
│               │  
│               ├── AdminApp.java  
│               ├── UserApp.java  


The workspace contains two folders by default, where:
- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies
Meanwhile, the compiled output files will be generated in the `bin` folder by default.

---

## License
This project is for learning purposes at the moment, so it is therefore open for modification and experimentation