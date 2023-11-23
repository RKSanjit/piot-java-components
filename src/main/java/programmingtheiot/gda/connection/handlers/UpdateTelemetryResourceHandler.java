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
import programmingtheiot.data.SensorData;
 
 
/**
* Shell representation of class for student implementation.
*
*/
public class UpdateTelemetryResourceHandler extends CoapResource
{
	// constructors
	/**
	 * Constructor.
	 * 
	 * @param resourceName The name of the resource.
	 */
	public UpdateTelemetryResourceHandler(String resourceName) {
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
	}
	@Override
	public void handleGET(CoapExchange context)
	{
	}
	@Override
	public void handlePOST(CoapExchange context)
	{
	}
	@Override
	public void handlePUT(CoapExchange context)
	{
		ResponseCode code = ResponseCode.NOT_ACCEPTABLE;
		context.accept();
		if(this.dataMsgListener != null) {
			try {
				String jsonData = new String(context.getRequestPayload());
				SensorData sysPerfData = DataUtil.getInstance().jsonToSensorData(jsonData);
				this.dataMsgListener.handleSensorMessage(ResourceNameEnum.CDA_SYSTEM_PERF_MSG_RESOURCE, sysPerfData);
				code = ResponseCode.CHANGED;
			}catch(Exception e) {
				_Logger.warning(
					"Failed to handle PUT request. Message: " + e.getMessage());
					code = ResponseCode.BAD_REQUEST;
				}
			}else {
				_Logger.info("No callback listener for request. Ignoring PUT.");
				code = ResponseCode.CONTINUE;
		}
		String msg = "Update system perf data request handled: " + super.getName();
		context.respond(code, msg);
	}
	public void setDataMessageListener(IDataMessageListener listener)
	{
		if (listener != null) {
			this.dataMsgListener = listener;
		}
	}
}