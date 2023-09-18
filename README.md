# Gateway Device Application (Connected Devices)

## Lab Module 02



### Description
Implements core application and system performance monitoring classes for a gateway device application, using inheritance, configuration handling, and logging. The main app class orchestrates starting and stopping the system performance manager. The util task classes retrieve CPU and memory utilization metrics.
The implementation uses standard Java features like packages, inheritance, abstract classes, method overriding, logging, and configuration handling. Test cases are included to validate the functionality and interactions between components.


#### What does your implementation do? 

- Creates a new Java package called app in the programmingtheiot\gda source folder
- Imports java.util.logging logging framework
- Creates a GatewayDeviceApp class with:
	- A constructor that accepts a String array argument
	- startApp() and stopApp() methods that log info messages
	- initConfig() and parseArgs() helper methods
	- A main() method to run the app
- Creates a SystemPerformanceManager class with:
	- startManager() and stopManager() methods
	- Reads poll rate config value using ConfigUtil
- Creates SystemCpuUtilTask and SystemMemUtilTask classes that extend BaseSystemUtilTask
- SystemCpuUtilTask retrieves CPU utilization
- SystemMemUtilTask retrieves memory utilization
- Includes unit and integration tests

#### How does your implementation work?

The code creates a GatewayDeviceApp that starts and stops a SystemPerformanceManager. The SystemPerformanceManager uses SystemCpuUtilTask and SystemMemUtilTask to retrieve CPU and memory utilization metrics.

The GatewayDeviceApp handles command line arguments, logging, and application lifecycle events. The SystemPerformanceManager controls starting and stopping monitoring tasks. The utilization task classes inherit common functionality from a BaseSystemUtilTask abstract class and override the getTelemetryValue() method to retrieve their specific system utilization metric.

Configuration handling, logging, and unit+integration testing are used throughout for production-quality code. This architecture allows the GatewayDeviceApp to orchestrate and manage the system performance monitoring tasks in a decoupled, extensible way.

### Code Repository and Branch

URL: https://github.com/RKSanjit/piot-java-components/tree/labmodule02

### UML Design Diagram(s)




### Unit Tests Executed

- ConfigUtilTest
- SystemCpuUtilTaskTest
- testGetTelemetryValue()

### Integration Tests Executed

- GatewayDeviceAppTest.
- SystemPerformanceManagerTest
