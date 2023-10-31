# Gateway Device Application (Connected Devices)

## Lab Module 05

### Description

My implementation creates the DeviceDataManager class which will serve as the central hub for managing data flow 
between the CDA, GDA, and cloud. The DeviceDataManager initializes the SystemPerformanceManager and sets itself 
as the listener to receive messages.

The DeviceDataManager implements the IDataMessageListener interface, providing skeleton implementations of the 
four callback methods: handleActuatorCommandResponse, handleIncomingMessage, handleSensorMessage, and handleSystemPerformanceMessage. 
These will be triggered when data is received from the various endpoints. The manager also contains startManager and stopManager methods 
to control the module lifecycle.

### Code Repository and Branch

URL: https://github.com/RKSanjit/piot-java-components/tree/labmodule05

### UML Design Diagram(s)

![mod5 GDA](https://github.com/RKSanjit/piot-java-components/assets/144634185/2d79c55c-82fd-4ecf-a1c5-95ee84ff259b)


### Unit Tests Executed

- ActuatorDataTest
- SensorDataTest
- SystemPerformanceDataTest
- SystemStateDataTest
- DataUtilTest

### Integration Tests Executed

- SystemPerformanceManagerTest
- DataIntegrationTest
- DeviceDataManagerNoCommsTest
- GatewayDeviceAppTest

EOF.
