# API endpoints

Base URL: `http://localhost:8080`

## Customers

| Method | Path                            | Description                 |
|--------|---------------------------------|-----------------------------|
| POST   | `/customers?name=&phoneNumber=` | Create a customer           |
| GET    | `/customers`                    | List customer + vehicles    |
| GET    | `/customers/customer-id?name=`  | Get a customer's ID by name |

## Vehicles

| Method | Path                                                       | Description                 |
|--------|------------------------------------------------------------|-----------------------------|
| POST   | `/vehicles?regNumber=&brand=&model=&prodYear=`             | Create a vehicle            |
| POST   | `/vehicles/add-vehicle-to-customer?customerId=&vehicleId=` | Connect vehicle to customer |
| GET    | `/vehicles`                                                | List all vehicles           |
| GET    | `/vehicles/vehicle-by-brand?brand=`                        | List vehicles of a brand    |
| GET    | `/vehicles/vehicle-brand-info`                             | List brand and reg number   |
| GET       | `/vehicles/vehicle-by-customer/{customerID}`              | List all vehicles by customer-ID                            |
| PUT    | `/vehicles/{id}/brand?brand=`                              | Update a vehicle's brand    |


## Example requests

Start the app, then run these in a separate terminal.
The examples uses data from `data.sql`.

Create a new customer:
```
curl -X POST "http://localhost:8080/customers?name=Jan+Karlsson&phoneNumber=0701234567"
```

Get a customer's ID by name:
````
curl http://localhost:8080/customers/customer-id?name=Jan+Karlsson
````

List all customer and their vehicles:
````
curl http://localhost:8080/customers
````

Create a vehicle:
````
curl -X POST "http://localhost:8080/vehicles?regNumber=MBI024&brand=Toyota&model=Corolla&prodYear=1999"
````

List all vehicles:
````
curl http://localhost:8080/vehicles
````

Get all vehicles of a brand:
````
curl "http://localhost:8080/vehicles/vehicle-by-brand?brand=Volvo"
````

Connect vehicle to customer:
````
curl -X POST "http://localhost:8080/vehicles/add-vehicle-to-customer?customerId=4&vehicleId=5"
````

Update a vehicle's brand:
````
curl -X PUT "http://localhost:8080/vehicles/1/brand?brand=Saab"
````

List brand and registration numer for all vehicles:
````
curl http://localhost:8080/vehicles/vehicle-brand-info
````

Get a customer's vehicles by customer-ID:
````
curl http://localhost:8080/vehicles/vehicle-by-customer/1
````

