/**
* This class is part of the Programming the Internet of Things project.
* 
* It is provided as a simple shell to guide the student and assist with
* implementation for the Programming the Internet of Things exercises,
* and designed to be modified by the student as needed.
*/ 
package programmingtheiot.gda.connection.handlers;
 
import java.util.logging.Logger;
 
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;
 
import programmingtheiot.common.ConfigConst;
import programmingtheiot.common.ConfigUtil;
import programmingtheiot.common.IDataMessageListener;
import programmingtheiot.common.ResourceNameEnum;
import programmingtheiot.data.DataUtil;
import programmingtheiot.data.SystemPerformanceData;
 
/**
*
* This class is a CoAP resource handler for updating system performance data.
*/
public class UpdateSystemPerformanceResourceHandler extends CoapResource
{
	// constructors
	/**
	 * Constructor.
	 * 
	 * @param resourceName The name of the resource.
	 */
	public UpdateSystemPerformanceResourceHandler(String resourceName) {
		super(resourceName);
		// TODO Auto-generated constructor stub
	}
 
	// static
	private IDataMessageListener dataMsgListener = null;
	private static final Logger _Logger =
		Logger.getLogger(GenericCoapResourceHandler.class.getName());
	// params
 
	
	// public methods
	@Override
	public void handleDELETE(CoapExchange context)
	{
		// TODO: Implement logic for handling DELETE requests
	}
	@Override
	public void handleGET(CoapExchange context)
	{
		// TODO: Implement logic for handling GET requests
	}
	@Override
	public void handlePOST(CoapExchange context)
	{
		// TODO: Implement logic for handling POST requests
	}
	@Override
	public void handlePUT(CoapExchange context)
	{
		ResponseCode code = ResponseCode.NOT_ACCEPTABLE;
		context.accept();
		if(this.dataMsgListener != null) {
			try {
				// Convert received JSON payload to SystemPerformanceData object
				String jsonData = new String(context.getRequestPayload());
				SystemPerformanceData sysPerfData = DataUtil.getInstance().jsonToSystemPerformanceData(jsonData);
				// Notify the data message listener with the system performance data
				this.dataMsgListener.handleSystemPerformanceMessage(ResourceNameEnum.CDA_SYSTEM_PERF_MSG_RESOURCE, sysPerfData);
				code = ResponseCode.CHANGED;
			} catch(Exception e) {
				// Log a warning if handling PUT request fails
				_Logger.warning("Failed to handle PUT request. Message: " + e.getMessage());
				code = ResponseCode.BAD_REQUEST;
			}
		} else {
			// Log an info message if no callback listener is registered
			_Logger.info("No callback listener for request. Ignoring PUT.");
			code = ResponseCode.CONTINUE;
		}
		// Respond to the client with the result of the request handling
		String msg = "Update system perf data request handled: " + super.getName();
		context.respond(code, msg);
	}
	/**
	 * Sets the data message listener for this resource handler.
	 * 
	 * @param listener The data message listener to set.
	 */
	public void setDataMessageListener(IDataMessageListener listener)
	{
		if (listener != null) {
			this.dataMsgListener = listener;
		}
	}
}