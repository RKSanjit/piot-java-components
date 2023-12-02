# Gateway Device Application (Connected Devices)

## Lab Module 08



### Description

This implementation integrates a CoAP server into the Gateway Device Application (GDA) using the Eclipse Californium library. It allows the Constrained Device Application (CDA) to send sensor data to the GDA and retrieve actuator commands using CoAP request/response messaging.

The CoAP server functionality is handled by the CoapServerGateway class, which provides an adapter to the Eclipse Californium library's CoapServer. The server manages CoAP resources and routes requests to specific resource handlers that process and respond to GET and PUT requests. Custom resource handlers were implemented to receive sensor data and system performance data from the CDA via PUT requests. Another handler allows the CDA to retrieve pending actuator commands via GET requests and supports observer notifications using CoAP. These CoAP resource handlers interface with the GDA's DeviceDataManager to process data and actuator messages. Overall, this enables CoAP-based integration between the CDA and GDA for sending telemetry and receiving actuator commands.


### Code Repository and Branch



URL: https://github.com/RKSanjit/piot-java-components/blob/labmodule08

### UML Design Diagram(s)

![mod8](https://github.com/RKSanjit/book-exercise-docs/assets/144634185/1b221a47-b5a1-42ab-9198-da791cbec4ee)

### Integration Tests Executed

- CoapServerGatewayTest

EOF.
