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
import programmingtheiot.common.IActuatorDataListener;
import programmingtheiot.common.IDataMessageListener;
import programmingtheiot.common.ResourceNameEnum;
import programmingtheiot.data.ActuatorData;
import programmingtheiot.data.DataUtil;
 
/**
* 
* This class is a CoAP resource handler for retrieving actuator command data.
*/
public class GetActuatorCommandResourceHandler extends CoapResource implements IActuatorDataListener
{
	// constructors
	public GetActuatorCommandResourceHandler(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		super.setObservable(true);
	}
 
	// static
	private static final Logger _Logger =
		Logger.getLogger(GenericCoapResourceHandler.class.getName());
	// params
	private ActuatorData actuatorData = null;
	/**
	 * Constructor.
	 * 
	 * @param resourceName The name of the resource.
	 */
	/**
	 * Called when actuator data is updated.
	 * 
	 * @param data The updated actuator data.
	 * @return True if the data is successfully updated, false otherwise.
	 */
	public boolean onActuatorDataUpdate(ActuatorData data)
	{
		if (data != null && this.actuatorData != null) {
			this.actuatorData.updateData(data);
			// Notify all connected clients
			super.changed();
			_Logger.fine("Actuator data updated for URI: " + super.getURI() + ": Data value = " + this.actuatorData.getValue());
			return true;
		}
		return false;
	}
	// public methods
	@Override
	public void handleDELETE(CoapExchange context)
	{
		// TODO: Implement logic for handling DELETE requests
	}
	@Override
	public void handleGET(CoapExchange context)
	{
		// TODO: Validate 'context'
	    // Accept the request
	    context.accept();
	    // Convert the locally stored ActuatorData to JSON using DataUtil
	    String jsonData = DataUtil.getInstance().actuatorDataToJson(this.actuatorData);
 
	    // Generate a response message, set the content type, and set the response code
	    context.respond(ResponseCode.CONTENT, jsonData);
	}
	@Override
	public void handlePOST(CoapExchange context)
	{
		// TODO: Implement logic for handling POST requests
	}
	@Override
	public void handlePUT(CoapExchange context)
	{
		// TODO: Implement logic for handling PUT requests
	}
	/**
	 * Sets the data message listener for this resource handler.
	 * 
	 * @param listener The data message listener to set.
	 */
	public void setDataMessageListener(IDataMessageListener listener)
	{
		// TODO: Implement logic for setting the data message listener
	}
}