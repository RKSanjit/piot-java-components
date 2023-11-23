package programmingtheiot.gda.app;

import java.util.logging.Level;
import java.util.logging.Logger;

import programmingtheiot.common.ConfigConst;
import programmingtheiot.common.ConfigUtil;
import programmingtheiot.common.IActuatorDataListener;
import programmingtheiot.common.IDataMessageListener;
import programmingtheiot.common.ResourceNameEnum;

import programmingtheiot.data.ActuatorData;
import programmingtheiot.data.DataUtil;
import programmingtheiot.data.SensorData;
import programmingtheiot.data.SystemPerformanceData;
import programmingtheiot.data.SystemStateData;

import programmingtheiot.gda.connection.CloudClientConnector;
import programmingtheiot.gda.connection.CoapServerGateway;
import programmingtheiot.gda.connection.IPersistenceClient;
import programmingtheiot.gda.connection.IPubSubClient;
import programmingtheiot.gda.connection.IRequestResponseClient;
import programmingtheiot.gda.connection.MqttClientConnector;
import programmingtheiot.gda.connection.RedisPersistenceAdapter;
import programmingtheiot.gda.connection.SmtpClientConnector;
import programmingtheiot.gda.system.SystemPerformanceManager;

public class DeviceDataManager implements IDataMessageListener {
    private static final Logger _Logger = Logger.getLogger(DeviceDataManager.class.getName());

    private boolean enableMqttClient = true;
    private boolean enableCoapServer = false;
    private boolean enableCloudClient = false;
    private boolean enableSystemPerf = false;
    private boolean enablePersistenceClient = false;
    private boolean enableSmtpClient = false;

    private IActuatorDataListener actuatorDataListener = null;
    private IPubSubClient mqttClient = null;
    private IPubSubClient cloudClient = null;
    private IPersistenceClient persistenceClient = null;
    private IRequestResponseClient smtpClient = null;
    private CoapServerGateway coapServer = null;
    private SystemPerformanceManager sysPerfMgr = null;

    public DeviceDataManager() {
        super();
        ConfigUtil configUtil = ConfigUtil.getInstance();

        this.enableMqttClient = configUtil.getBoolean(ConfigConst.GATEWAY_DEVICE, ConfigConst.ENABLE_MQTT_CLIENT_KEY);
        this.enableCoapServer = configUtil.getBoolean(ConfigConst.GATEWAY_DEVICE, ConfigConst.ENABLE_COAP_SERVER_KEY);
        this.enableCloudClient = configUtil.getBoolean(ConfigConst.GATEWAY_DEVICE, ConfigConst.ENABLE_CLOUD_CLIENT_KEY);
        this.enablePersistenceClient = configUtil.getBoolean(ConfigConst.GATEWAY_DEVICE, ConfigConst.ENABLE_PERSISTENCE_CLIENT_KEY);
        initManager();
    }

    private void initManager() {
        ConfigUtil configUtil = ConfigUtil.getInstance();

        this.enableSystemPerf = configUtil.getBoolean(ConfigConst.GATEWAY_DEVICE, ConfigConst.ENABLE_SYSTEM_PERF_KEY);

        if (this.enableSystemPerf) {
            this.sysPerfMgr = new SystemPerformanceManager();
            this.sysPerfMgr.setDataMessageListener(this);
        }

        if (this.enableMqttClient) {
    		this.mqttClient = new MqttClientConnector();
    		
    		this.mqttClient.setDataMessageListener(this);
        }

        if (this.enableCoapServer) {
            // TODO: implement this in Lab Module 8
        }

        if (this.enableCloudClient) {
            // TODO: implement this in Lab Module 10
        }

        if (this.enablePersistenceClient) {
            // TODO: implement this as an optional exercise in Lab Module 5
        }
        
    	if (this.enableCoapServer) {
    		this.coapServer = new CoapServerGateway(this);
    	}
    }

    public DeviceDataManager(boolean enableMqttClient, boolean enableCoapClient,
                            boolean enableCloudClient, boolean enableSmtpClient, boolean enablePersistenceClient) {
        super();
        initConnections();
    }

    @Override
    public boolean handleActuatorCommandResponse(ResourceNameEnum resourceName, ActuatorData data) {
        if (data != null) {
            _Logger.info("Handling actuator response: " + data.getName());
            // this next call is optional for now
            // this.handleIncomingDataAnalysis(resourceName, data);

            if (data.hasError()) {
                _Logger.warning("Error flag set for ActuatorData instance.");
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean handleIncomingMessage(ResourceNameEnum resourceName, String msg) {
        if (msg != null) {
            _Logger.info("Handling incoming generic message: " + msg);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean handleSensorMessage(ResourceNameEnum resourceName, SensorData data) {
        if (data != null) {
            _Logger.info("Handling sensor message: " + data.getName());
            if (data.hasError()) {
                _Logger.warning("Error flag set for SensorData instance.");
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean handleSystemPerformanceMessage(ResourceNameEnum resourceName, SystemPerformanceData data) {
        if (data != null) {
            _Logger.info("Handling system performance message: " + data.getName());
            if (data.hasError()) {
                _Logger.warning("Error flag set for SystemPerformanceData instance.");
            }

            return true;
        } else {
            return false;
        }
    }

    public void setActuatorDataListener(String name, IActuatorDataListener listener)
    {
    	if (listener != null) {
    		// for now, just ignore 'name' - if you need more than one listener,
    		// you can use 'name' to create a map of listener instances
    		this.actuatorDataListener = listener;
    	}
    }

    public void startManager() {
        if (this.sysPerfMgr != null) {
            this.sysPerfMgr.startManager();
            
        }

    	if (this.mqttClient != null) {
    		if (this.mqttClient.connectClient()) {
    			_Logger.info("Successfully connected MQTT client to broker.");
    			
    			// add necessary subscriptions
    			
    			// TODO: read this from the configuration file
    			int qos = ConfigConst.DEFAULT_QOS;
    			
    			// TODO: check the return value for each and take appropriate action
    			this.mqttClient.subscribeToTopic(ResourceNameEnum.GDA_MGMT_STATUS_MSG_RESOURCE, qos);
    			this.mqttClient.subscribeToTopic(ResourceNameEnum.CDA_ACTUATOR_RESPONSE_RESOURCE, qos);
    			this.mqttClient.subscribeToTopic(ResourceNameEnum.CDA_SENSOR_MSG_RESOURCE, qos);
    			this.mqttClient.subscribeToTopic(ResourceNameEnum.CDA_SYSTEM_PERF_MSG_RESOURCE, qos);
    		} else {
    			_Logger.severe("Failed to connect MQTT client to broker.");
    			
    			// TODO: take appropriate action
    		}
    	}
    	
    	if (this.enableCoapServer && this.coapServer != null) {
    		if (this.coapServer.startServer()) {
    			_Logger.info("CoAP server started.");
    		} else {
    			_Logger.severe("Failed to start CoAP server. Check log file for details.");
    		}
    	}
    }

    public void stopManager() {
        if (this.sysPerfMgr != null) {
            this.sysPerfMgr.stopManager();
        }
        
    	if (this.mqttClient != null) {
    		// add necessary un-subscribes
    		
    		// TODO: check the return value for each and take appropriate action
    		this.mqttClient.unsubscribeFromTopic(ResourceNameEnum.GDA_MGMT_STATUS_MSG_RESOURCE);
    		this.mqttClient.unsubscribeFromTopic(ResourceNameEnum.CDA_ACTUATOR_RESPONSE_RESOURCE);
    		this.mqttClient.unsubscribeFromTopic(ResourceNameEnum.CDA_SENSOR_MSG_RESOURCE);
    		this.mqttClient.unsubscribeFromTopic(ResourceNameEnum.CDA_SYSTEM_PERF_MSG_RESOURCE);
    		
    		if (this.mqttClient.disconnectClient()) {
    			_Logger.info("Successfully disconnected MQTT client from broker.");
    		} else {
    			_Logger.severe("Failed to disconnect MQTT client from broker.");
    			
    			// TODO: take appropriate action
    		}
    	}
    	
    	if (this.enableCoapServer && this.coapServer != null) {
    		if (this.coapServer.stopServer()) {
    			_Logger.info("CoAP server stopped.");
    		} else {
    			_Logger.severe("Failed to stop CoAP server. Check log file for details.");
    		}
    	}
        
    }

    private void initConnections() {
    }

    private void handleIncomingDataAnalysis(ResourceNameEnum resourceName, ActuatorData data) {
        _Logger.fine("Handling incoming data analysis for actuator data.");
        // Implement data analysis logic here.
        // You may publish data back to the CDA using MQTT or CoAP in Part 03.
        // We'll revisit this in a later module.
        
        
    }

    private void handleIncomingDataAnalysis(ResourceNameEnum resourceName, SystemStateData data) {
        _Logger.fine("Handling incoming data analysis for system state data.");
        // Implement data analysis logic here.
        // This is a command that the GDA should interpret and handle internally.
        
        
    }

    private boolean handleUpstreamTransmission(ResourceNameEnum resourceName, String jsonData, int qos) {
        _Logger.fine("Handling upstream transmission to the cloud service.");
        // Implement logic to publish data to the cloud service.
        // We'll revisit this in a later module when discussing connectivity.
        return true; // Return success or failure.
    }
}
