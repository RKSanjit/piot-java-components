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
 * SystemPerformanceData class represents system performance data.
 * It extends the BaseIotData class and contains information about
 * CPU utilization, disk utilization, and memory utilization.
 */
public class SystemPerformanceData extends BaseIotData implements Serializable
{
    // private variables
    private float cpuUtil = ConfigConst.DEFAULT_VAL;
    private float diskUtil = ConfigConst.DEFAULT_VAL;
    private float memUtil = ConfigConst.DEFAULT_VAL;

    // constructors
    public SystemPerformanceData()
    {
        super();
        super.setName(ConfigConst.SYS_PERF_DATA);
    }

    // public methods
    public float getCpuUtilization()
    {
        return this.cpuUtil;
    }

    public float getDiskUtilization()
    {
        return this.diskUtil;
    }

    public float getMemoryUtilization()
    {
        return this.memUtil;
    }

    public void setCpuUtilization(float val)
    {
        this.cpuUtil = val;
    }

    public void setDiskUtilization(float val)
    {
        this.diskUtil = val;
    }

    public void setMemoryUtilization(float val)
    {
        this.memUtil = val;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append(',');
        sb.append(ConfigConst.CPU_UTIL_PROP).append('=').append(this.getCpuUtilization()).append(',');
        sb.append(ConfigConst.DISK_UTIL_PROP).append('=').append(this.getDiskUtilization()).append(',');
        sb.append(ConfigConst.MEM_UTIL_PROP).append('=').append(this.getMemoryUtilization());

        return sb.toString();
    }

    // protected methods
    @Override
    protected void handleUpdateData(BaseIotData data)
    {
        // Handle update if needed
    }
}
