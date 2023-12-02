/**
* This class is part of the Programming the Internet of Things project.
* 
* It is provided as a simple shell to guide the student and assist with
* implementation for the Programming the Internet of Things exercises,
* and designed to be modified by the student as needed.
<<<<<<< HEAD
*/
 
=======
*/ 
>>>>>>> labmodule08
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
 
<<<<<<< HEAD
 
/**
* Shell representation of class for student implementation.
*
*/
public class GetActuatorCommandResourceHandler extends CoapResource implements IActuatorDataListener
{
=======
/**
* 
* This class is a CoAP resource handler for retrieving actuator command data.
*/
public class GetActuatorCommandResourceHandler extends CoapResource implements IActuatorDataListener
{
	// constructors
>>>>>>> labmodule08
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
<<<<<<< HEAD
=======
	/**
	 * Called when actuator data is updated.
	 * 
	 * @param data The updated actuator data.
	 * @return True if the data is successfully updated, false otherwise.
	 */
>>>>>>> labmodule08
	public boolean onActuatorDataUpdate(ActuatorData data)
	{
		if (data != null && this.actuatorData != null) {
			this.actuatorData.updateData(data);
<<<<<<< HEAD
			// notify all connected clients
=======
			// Notify all connected clients
>>>>>>> labmodule08
			super.changed();
			_Logger.fine("Actuator data updated for URI: " + super.getURI() + ": Data value = " + this.actuatorData.getValue());
			return true;
		}
		return false;
	}
<<<<<<< HEAD

=======
>>>>>>> labmodule08
	// public methods
	@Override
	public void handleDELETE(CoapExchange context)
	{
<<<<<<< HEAD
=======
		// TODO: Implement logic for handling DELETE requests
>>>>>>> labmodule08
	}
	@Override
	public void handleGET(CoapExchange context)
	{
<<<<<<< HEAD
		// TODO: validate 'context'
	    // accept the request
	    context.accept();
	    // TODO: convert the locally stored ActuatorData to JSON using DataUtil
	    String jsonData = DataUtil.getInstance().actuatorDataToJson(this.actuatorData);
 
	    // TODO: generate a response message, set the content type, and set the response code
 
	    // send an appropriate response
=======
		// TODO: Validate 'context'
	    // Accept the request
	    context.accept();
	    // Convert the locally stored ActuatorData to JSON using DataUtil
	    String jsonData = DataUtil.getInstance().actuatorDataToJson(this.actuatorData);
 
	    // Generate a response message, set the content type, and set the response code
>>>>>>> labmodule08
	    context.respond(ResponseCode.CONTENT, jsonData);
	}
	@Override
	public void handlePOST(CoapExchange context)
	{
<<<<<<< HEAD
=======
		// TODO: Implement logic for handling POST requests
>>>>>>> labmodule08
	}
	@Override
	public void handlePUT(CoapExchange context)
	{
<<<<<<< HEAD
	}
	public void setDataMessageListener(IDataMessageListener listener)
	{
=======
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
>>>>>>> labmodule08
	}
}