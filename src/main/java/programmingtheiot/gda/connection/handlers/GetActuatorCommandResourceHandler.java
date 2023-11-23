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
* Shell representation of class for student implementation.
*
*/
public class GetActuatorCommandResourceHandler extends CoapResource implements IActuatorDataListener
{
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
	public boolean onActuatorDataUpdate(ActuatorData data)
	{
		if (data != null && this.actuatorData != null) {
			this.actuatorData.updateData(data);
			// notify all connected clients
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
	}
	@Override
	public void handleGET(CoapExchange context)
	{
		// TODO: validate 'context'
	    // accept the request
	    context.accept();
	    // TODO: convert the locally stored ActuatorData to JSON using DataUtil
	    String jsonData = DataUtil.getInstance().actuatorDataToJson(this.actuatorData);
 
	    // TODO: generate a response message, set the content type, and set the response code
 
	    // send an appropriate response
	    context.respond(ResponseCode.CONTENT, jsonData);
	}
	@Override
	public void handlePOST(CoapExchange context)
	{
	}
	@Override
	public void handlePUT(CoapExchange context)
	{
	}
	public void setDataMessageListener(IDataMessageListener listener)
	{
	}
}