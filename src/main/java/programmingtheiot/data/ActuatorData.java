/**
 * This class is part of the Programming the Internet of Things project.
 * 
 * It is provided as a simple shell to guide the student and assist with
 * implementation for the Programming the Internet of Things exercises,
 * and designed to be modified by the student as needed.
 */ 

package programmingtheiot.data;

import java.io.Serializable;

import programmingtheiot.common.ConfigConst;

/**
 * ActuatorData class represents data from an actuator device.
 * It extends the BaseIotData class and contains information about
 * the command, value, state, and response flag.
 */
public class ActuatorData extends BaseIotData implements Serializable
{
    // private variables
    private int command = ConfigConst.DEFAULT_COMMAND;
    private float value = ConfigConst.DEFAULT_VAL;
    private boolean isResponse = false;
    private String stateData = "";

    // constructors
    public ActuatorData()
    {
        super();
    }

    // public methods
    public int getCommand()
    {
        return this.command;
    }

    public float getValue()
    {
        return this.value;
    }

    public boolean isResponseFlagEnabled()
    {
        return this.isResponse;
    }

    public void setAsResponse()
    {
        this.isResponse = true;
    }

    public void setCommand(int command)
    {
        this.command = command;
    }

    public void setValue(float val)
    {
        this.value = val;
    }

    public String getStateData()
    {
        return this.stateData;
    }

    public void setStateData(String stateData)
    {
        this.stateData = stateData;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append(',');
        sb.append(ConfigConst.COMMAND_PROP).append('=').append(this.getCommand()).append(',');
        sb.append(ConfigConst.IS_RESPONSE_PROP).append('=').append(this.isResponseFlagEnabled()).append(',');
        sb.append(ConfigConst.VALUE_PROP).append('=').append(this.getValue()).append(',');
        sb.append(ConfigConst.STATE_DATA_PROP).append('=').append(this.getStateData());

        return sb.toString();
    }

    // protected methods
    @Override
    protected void handleUpdateData(BaseIotData data)
    {
        // Handle update if needed
    }
}
