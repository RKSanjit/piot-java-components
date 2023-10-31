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
 * SensorData class represents data from a sensor device.
 * It extends the BaseIotData class and contains information about the sensor's value.
 */
public class SensorData extends BaseIotData implements Serializable
{
    // private variables
    private float value = ConfigConst.DEFAULT_VAL;

    // constructors
    public SensorData()
    {
        super();
    }

    public SensorData(int sensorType)
    {
        super();
    }

    // public methods
    public float getValue()
    {
        return this.value;
    }

    public void setValue(float val)
    {
        this.value = val;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append(',');
        sb.append(ConfigConst.VALUE_PROP).append('=').append(this.getValue());

        return sb.toString();
    }

    // protected methods
    @Override
    protected void handleUpdateData(BaseIotData data)
    {
        // Handle update if needed
    }
}

