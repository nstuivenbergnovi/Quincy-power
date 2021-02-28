AdminController - admin
Get
-	https://localhost:8443/admin
AuthenticatedController - admin
Get
-	https://localhost:8443/authenticated
CustomerController – admin
Post – admin & user
-	https://localhost:8443/customers

{
    "firstName": "pieter",
    "lastName": "post",
    "country": "Leverworst",
    "phoneNumber": "123456789",
    "email": "Anoniempje@hotmail.com"
}

Get
-	https://localhost:8443/customers
-	https://localhost:8443/customers/a/{customerId}
-	https://localhost:8443/customers/a/{firstName}
Put
-	https://localhost:8443/customers/update
Delete
-	https://localhost:8443/customers/delete/{customerId}



InvoiceController – user
Post
-	https://localhost:8443/invoice/{customerId}
-	{
-	"description": "description",
-	"information": "information"
-	}
-	https://localhost:8443/invoice/a/{productId}/{invoiceId}
-	{
-	    "quantity" : "2",
-	    "invoice_id" : "1",
-	    "product_id" : "2"
-	}
Get
-	https://localhost:8443/invoice/a/{invoiceId}
ProductController – user & admin
Post - user
-	https://localhost:8443/products
-	{
-	    "name": "Roze koek",
-	    "price": "1.99"
-	}
Get - user
-	https://localhost:8443/products
-	https://localhost:8443/products/a/{id}
-	https://localhost:8443/products/name
Put – admin
-	https://localhost:8443/products/update
Delete - admin
-	https://localhost:8443/products/{id}






UserController – user & admin
Get
-	https://localhost:8443/users
-	https://localhost:8443/users/{username} - admin
-	https://localhost:8443/{username}/authorities - admin
Post
-	https://localhost:8443/users
-	{
-	        "username": "klant",
-	        "password": "password",
-	        "authorities": [
-	                {
-	                    "username": "klant",
-	                    "authority": "ROLE_USER"
-	                }
-	            ]
-	}
-	https://localhost:8443/{usersname}/authorities - admin
-	{
-	    "username": "klant",
-	    "authority": "ROLE_ADMIN"
-	}
Put
-	https://localhost:8443/{usersname} – admin
Delete
-	https://localhost:8443/{usersname} - admin
-	https://localhost:8443/{usersname}/authorities/{authority} - admin
