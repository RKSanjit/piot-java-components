/**
* This class is part of the Programming the Internet of Things project.
* 
* It is provided as a simple shell to guide the student and assist with
* implementation for the Programming the Internet of Things exercises,
* and designed to be modified by the student as needed.
*/
package programmingtheiot.gda.connection;
 
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
 
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.network.Endpoint;
import org.eclipse.californium.core.network.interceptors.MessageTracer;
import org.eclipse.californium.core.server.resources.Resource;
 
import programmingtheiot.common.ConfigConst;
import programmingtheiot.common.IDataMessageListener;
import programmingtheiot.common.ResourceNameEnum;
import programmingtheiot.data.SystemPerformanceData;
import programmingtheiot.gda.connection.handlers.GenericCoapResourceHandler;
import programmingtheiot.gda.connection.handlers.GetActuatorCommandResourceHandler;
import programmingtheiot.gda.connection.handlers.UpdateSystemPerformanceResourceHandler;
import programmingtheiot.gda.connection.handlers.UpdateTelemetryResourceHandler;
 
/**
* This class represents a CoAP server gateway for handling IoT device communication.
*/
public class CoapServerGateway
{
    // static
    private static final Logger _Logger =
        Logger.getLogger(CoapServerGateway.class.getName());
    // params
    private CoapServer coapServer = null;
    private IDataMessageListener dataMsgListener = null;
 
    // constructors
    /**
     * Default constructor.
     */
    public CoapServerGateway() {
        super();
        initServer();
    }
 
    /**
     * Constructor with IDataMessageListener parameter.
     * 
     * @param dataMsgListener The IDataMessageListener to set.
     */
    public CoapServerGateway(IDataMessageListener dataMsgListener)
    {
        super();
        this.dataMsgListener = dataMsgListener;
        initServer();
    }
 
    // public methods
 
    /**
     * Add a CoAP resource to the server.
     * 
     * @param resourceType The type of the resource.
     * @param endName      The end name of the resource.
     * @param resource     The resource to add.
     */
    public void addResource(ResourceNameEnum resourceType, String endName, Resource resource)
    {
        if (resourceType != null && resource != null) {
            createResourceChain(resourceType, resource);
        }
    }
 
    /**
     * Check if the server has a resource with the given name.
     * 
     * @param name The name of the resource.
     * @return True if the server has the resource, false otherwise.
     */
    public boolean hasResource(String name)
    {
        // TODO: Implement logic for checking if the server has a resource with the given name
        return false;
    }
 
    /**
     * Set the data message listener for this CoAP server gateway.
     * 
     * @param listener The IDataMessageListener to set.
     */
    public void setDataMessageListener(IDataMessageListener listener)
    {
        // Handle callbacks into the DeviceDataManager class
        if (listener != null) {
            this.dataMsgListener = listener;
        }
    }
 
    /**
     * Start the CoAP server.
     * 
     * @return True if the server starts successfully, false otherwise.
     */
    public boolean startServer()
    {
        try {
            if (this.coapServer != null) {
                this.coapServer.start();
                for (Endpoint ep : this.coapServer.getEndpoints()) {
                    ep.addInterceptor(new MessageTracer());
                }
                return true;
            } else {
                _Logger.warning("CoAP server START failed. Not yet initialized.");
            }
        } catch (Exception e) {
            _Logger.log(Level.SEVERE, "Failed to start CoAP server.", e);
        }
        return false;
    }
 
    /**
     * Stop the CoAP server.
     * 
     * @return True if the server stops successfully, false otherwise.
     */
    public boolean stopServer()
    {
        try {
            if (this.coapServer != null) {
                _Logger.info("Stopping server");
                this.coapServer.stop();
                for (Endpoint ep : this.coapServer.getEndpoints()) {
                    ep.addInterceptor(new MessageTracer());
                }
                return true;
            } else {
                _Logger.warning("CoAP server STOP failed. Not yet initialized.");
            }
        } catch (Exception e) {
            _Logger.log(Level.SEVERE, "Failed to stop CoAP server.", e);
        }
        return false;
    }
 
    // private methods
 
    /**
     * Create a resource chain and add it to the CoAP server.
     * 
     * @param resourceType The type of the resource.
     * @param resource     The resource to add.
     */
    private void createResourceChain(ResourceNameEnum resourceType, Resource resource)
    {
        _Logger.info("Adding server resource handler chain: " + resourceType.getResourceName());
        List<String> resourceNames = resourceType.getResourceNameChain();
        Queue<String> queue = new ArrayBlockingQueue<>(resourceNames.size());
 
        queue.addAll(resourceNames);
        // check if we have a parent resource
        Resource parentResource = this.coapServer.getRoot();
        // if no parent resource, add it
        if (parentResource == null) {
            parentResource = new CoapResource(queue.poll());
            this.coapServer.add(parentResource);
        }
        // check the next resource
        while (!queue.isEmpty()) {
            // get the next resource name
            String resourceName = queue.poll();
            Resource nextResource = parentResource.getChild(resourceName);
            // Reach to the end of the branch
            if (nextResource == null) {
                if (queue.isEmpty()) {
                    nextResource = resource;
                    nextResource.setName(resourceName);
                } else {
                    nextResource = new CoapResource(resourceName);
                }
                parentResource.add(nextResource);
            }
            parentResource = nextResource;
        }
    }
 
    /**
     * Initialize the CoAP server with default resources.
     */
    private void initServer()
    {
        this.coapServer = new CoapServer();
        initDefaultResources();
    }
 
    /**
     * Initialize default resources for the CoAP server.
     */
    private void initDefaultResources()
    {
    	// initialize pre-defined resources
    	GetActuatorCommandResourceHandler getActuatorCmdResourceHandler =
    		new GetActuatorCommandResourceHandler(
    			ResourceNameEnum.CDA_ACTUATOR_CMD_RESOURCE.getResourceType());
    	
    	if (this.dataMsgListener != null) {
    		this.dataMsgListener.setActuatorDataListener(null, getActuatorCmdResourceHandler);
    	}
    	
    	addResource(ResourceNameEnum.CDA_ACTUATOR_CMD_RESOURCE, null, getActuatorCmdResourceHandler);
    	
    	UpdateTelemetryResourceHandler updateTelemetryResourceHandler =
    		new UpdateTelemetryResourceHandler(
    			ResourceNameEnum.CDA_SENSOR_MSG_RESOURCE.getResourceType());
    	
    	updateTelemetryResourceHandler.setDataMessageListener(this.dataMsgListener);
    	
    	addResource(
    		ResourceNameEnum.CDA_SENSOR_MSG_RESOURCE, null,	updateTelemetryResourceHandler);
    	
    	UpdateSystemPerformanceResourceHandler updateSystemPerformanceResourceHandler =
    		new UpdateSystemPerformanceResourceHandler(
    			ResourceNameEnum.CDA_SYSTEM_PERF_MSG_RESOURCE.getResourceType());
    	
    	updateSystemPerformanceResourceHandler.setDataMessageListener(this.dataMsgListener);
    	
    	addResource(
    		ResourceNameEnum.CDA_SYSTEM_PERF_MSG_RESOURCE, null, updateSystemPerformanceResourceHandler);
    }
}