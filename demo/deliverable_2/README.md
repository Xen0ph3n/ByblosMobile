# Draft of Byblos' Mobile App

- [View this Plantuml online](https://www.plantuml.com/plantuml/uml/ZPDVRXCn4CRVVGfBF7GZI7i0U6X2GLGgL0hb01FlxCQA_opZSKHKuEmuhhtPa22HbynyC_FjRxpUsy107AqHGXa8GTv5Q_VoMKWvbmFf7J2c-5MAFQDYR5FsMmxRNbhaZM-RMH9Uoo6kZLOf-INbpPLym1vTMypUtich6o7k7IDre51-2qZoETigGc1SAEMZuwRyF-q7dEMoyRrspHZxo8T7LDP5QxKxCVgAjDCAomIXzRlH_BAMnO3yoKSo-nLm7KxQHq-sAPRNdA3UI1K3UujqxpgVI0h81piOlqUyk5GjLbk3Yayv0_8o1kqmX0D6KTVU6mHtE7cLAcLaXTyZXf7SUTRT_dsXQev8LRg8L8lZ-a1gioHmQjFy6xE_S2ApwquRKuVYskhSK_486AjlGiil_jtF-VpbrKtoUaWJgK8A8Qxa_Bz_atjs82dxTve2Vm6BEJCmILfWVEoMcdYJSsXRmX1oZ1QqIU2m3aoWMAxGCPY9yQ3LDWvFkfWHSYGtfaOhT04F5pgvvB6ysz6ctcK5TEQTj3BvqiCKrVQiAmReSK48OzzdlzEkdxJaZ_p1As3jNLRGjIUvYtQDzDYbKQHm8Ytz3mp9MzGdboLEaS-gVy4Ubv_W2N6RGA8ruWy0)


## Users:
|                                 | Possible Methods / Variables        | Descriptions                                                                        |
|---------------------------------|-------------------------------------|-------------------------------------------------------------------------------------|
| User                            | createAccount(Account.type)         | Create an account (not for Admin)                                                   |
|                                 | login()                             | Can log in                                                                          |
|                                 | logout()                            | Can log off (Optional)                                                              |
|---------------------------------|-------------------------------------|-------------------------------------------------------------------------------------|
| Administrator can:              |                                     | (User)                                                                              |
|                                 | createService()                     | Create services (>= 3)                                                              |
|                                 | removeAccount()                     | Delete employee (branches) accounts                                                 |
|                                 | removeAccount()                     | Delete customers accounts                                                           |
|                                 |                                     |                                                                                     |
| Set up:                         | setHourlyRate()                     | the hourly rate (price)                                                             |
|                                 | setForm(Service.type, customerInfo) | ? customer info (when they request a service):                                      |
|---------------------------------|-------------------------------------|-------------------------------------------------------------------------------------|
| The Byblos branch employee can: |                                     | (User)                                                                              |
|                                 | createAccount(Employee)             | Create an account (aka branch account)                                              |
|                                 | selectService()                     | Select services provided (from the set of all available services)                   |
|                                 | setBusinessHour()                   | Enter the working hours of the branch                                               |
|                                 | boolean processServiceRequest()     | View submitted `Service Requests`                                                   |
|                                 |                                     | - Approve request                                                                   |
|                                 |                                     | - Reject request                                                                    |
|                                 | notifyCustomer()                    | Notify customer when the request was approved                                       |
|---------------------------------|-------------------------------------|-------------------------------------------------------------------------------------|
| The customer can:               |                                     | (User)                                                                              |
|                                 | createAccount(Customer)             | Create an account                                                                   |
|                                 | searchBranch()                      | input: `Service.address`, `Service.type`; output: available Services, working hours |
|                                 | selectService()                     | Select a service they would like to purchase                                        |
|                                 | getForm(Service.type).fill()        | Fill in the required information                                                    |
|                                 | submitForm()                        | Submit the `Service Requests`                                                       |
|                                 | rateBranch()                        | Rate their experience with the Byblos branch                                        |
|---------------------------------|-------------------------------------|-------------------------------------------------------------------------------------|

## Customer info Forms (for Admin):

|                   | Possible Methods / Variables | Descriptions                                  |
|-------------------|------------------------------|-----------------------------------------------|
| General Form      | firstName                    | first name                                    |
|                   | lastName                     | last name                                     |
|                   | dateOfBirth                  | date of birth                                 |
|                   | address                      | address                                       |
|                   | email                        | email                                         |
|-------------------|------------------------------|-----------------------------------------------|
| Rental Form       |                              | (General Form)                                |
|                   | pickupTime                   | pickup date and time                          |
|                   | returnTime                   | return date and time                          |
|                   | licenseType                  | license type (G1 G2 G)                        |
|-------------------|------------------------------|-----------------------------------------------|
|-------------------|------------------------------|-----------------------------------------------|
| Car rental        |                              | (Rental Form)                                 |
|                   | carType                      | preferred car type (compact intermediate SUV) |
|-------------------|------------------------------|-----------------------------------------------|
| Truck rental      |                              | (Rental Form)                                 |
|                   | usageArea                    | the area where the truck will be used         |
|-------------------|------------------------------|-----------------------------------------------|
| Moving assistance |                              | (General Form)                                |
|                   | startLocation                | moving start location                         |
|                   | endLocation                  | moving end location                           |
|                   | numberOfMovers               | number of movers required                     |
|                   | numberOfBoxes                | expected number of boxes                      |
|-------------------|------------------------------|-----------------------------------------------|

## Final Check:

| The application should:                                       |
|---------------------------------------------------------------|
| Byblos branch can display a summary of all `Service Requests` |
| Show the rating of each branch                                |
| Show admin account with all registered users                  |
| Allow admin to delete user accounts                           |
