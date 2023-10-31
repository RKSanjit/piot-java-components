/**
 * This class is part of the Programming the Internet of Things project.
 * 
 * It is provided as a simple shell to guide the student and assist with
 * implementation for the Programming the Internet of Things exercises,
 * and designed to be modified by the student as needed.
 */ 

package programmingtheiot.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import programmingtheiot.common.ConfigConst;

/**
 * SystemStateData class represents system state data.
 * It extends the BaseIotData class and contains information about
 * the command, system performance data list, and sensor data list.
 */
public class SystemStateData extends BaseIotData implements Serializable
{
    // private variables
    private int command = ConfigConst.DEFAULT_COMMAND;
    private List<SystemPerformanceData> sysPerfDataList = new ArrayList<>();
    private List<SensorData> sensorDataList = new ArrayList<>();

    // constructors
    public SystemStateData()
    {
        super();
        super.setName(ConfigConst.SYS_STATE_DATA);
    }

    // public methods
    public boolean addSensorData(SensorData data)
    {
        if (data != null)
        {
            sensorDataList.add(data);
            return true;
        }
        return false;
    }

    public boolean addSystemPerformanceData(SystemPerformanceData data)
    {
        if (data != null)
        {
            sysPerfDataList.add(data);
            return true;
        }
        return false;
    }

    public int getCommand()
    {
        return this.command;
    }

    public List<SensorData> getSensorDataList()
    {
        return this.sensorDataList;
    }

    public List<SystemPerformanceData> getSystemPerformanceDataList()
    {
        return this.sysPerfDataList;
    }

    public void setCommand(int actionCmd)
    {
        this.command = actionCmd;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append(',');
        sb.append(ConfigConst.COMMAND_PROP).append('=').append(this.getCommand()).append(',');
        sb.append(ConfigConst.SENSOR_DATA_LIST_PROP).append('=').append(this.getSensorDataList()).append(',');
        sb.append(ConfigConst.SYSTEM_PERF_DATA_LIST_PROP).append('=').append(this.getSystemPerformanceDataList());

        return sb.toString();
    }

    // protected methods
    @Override
    protected void handleUpdateData(BaseIotData data)
    {
        //

	}
	
}
