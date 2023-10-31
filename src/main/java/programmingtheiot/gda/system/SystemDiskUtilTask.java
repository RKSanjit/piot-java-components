/**
 * This class is part of the Programming the Internet of Things project.
 * 
 * It is provided as a simple shell to guide the student and assist with
 * implementation for the Programming the Internet of Things exercises,
 * and designed to be modified by the student as needed.
 */ 

package programmingtheiot.gda.system;

import java.io.File;
import java.util.logging.Logger;

import programmingtheiot.common.ConfigConst;

/**
 * Shell representation of class for student implementation.
 * 
 */
public class SystemDiskUtilTask extends BaseSystemUtilTask
{
    // Add a logger
    private static final Logger logger = Logger.getLogger(SystemDiskUtilTask.class.getName());

    // Define the path to monitor
    private final String diskPath;

    // constructors

    /**
     * Default constructor.
     * 
     */
    public SystemDiskUtilTask()
    {
        super(ConfigConst.NOT_SET, ConfigConst.DEFAULT_TYPE_ID);
        // Set the default disk path to monitor (you can change this)
        this.diskPath = "/";
    }

    /**
     * Constructor with custom disk path.
     * 
     * @param diskPath The path to monitor for disk utilization.
     */
    public SystemDiskUtilTask(String diskPath)
    {
        super(ConfigConst.NOT_SET, ConfigConst.DEFAULT_TYPE_ID);
        this.diskPath = diskPath;
    }

    // public methods

    @Override
    public float getTelemetryValue()
    {
        // Calculate disk utilization for the specified path
        File diskPartition = new File(diskPath);
        long totalSpace = diskPartition.getTotalSpace();
        long freeSpace = diskPartition.getFreeSpace();
        float diskUtilization = ((float) (totalSpace - freeSpace) / totalSpace) * 100;

        logger.fine("Disk utilization for " + diskPath + ": " + diskUtilization + "%");

        return diskUtilization;
    }
}
