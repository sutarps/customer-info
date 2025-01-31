# customer-info
Sample customer information bounded context microservice example.
This example demonstrates the API specification first development approach.
1. First define the API specification using Open API specs. See the customer-info.1.0.0.yaml file under resources folder.
2. Use the openapi-generator-maven-plugin to generate the boilder plate java code based on API specification. This speeds up development as the model and API controller class interface is auto generated.
3. Developer just needs to implement the business logic once the boiler plate code is auto generated.

