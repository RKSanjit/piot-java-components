/**
 * This class is part of the Programming the Internet of Things project.
 * 
 * It is provided as a simple shell to guide the student and assist with
 * implementation for the Programming the Internet of Things exercises,
 * and designed to be modified by the student as needed.
 */ 

package programmingtheiot.data;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

import programmingtheiot.common.ConfigConst;
/**
 * The DataUtil class provides utility methods for converting data objects to JSON format and vice versa.
 * 
 * It is designed to facilitate data serialization and deserialization using the Google Gson library. This class can be used to convert ActuatorData, 
 * SensorData, SystemPerformanceData, and SystemStateData objects to JSON, as well as to convert JSON data back to these object types.
 */

public class DataUtil
{
    private static final Logger _Logger = Logger.getLogger(DataUtil.class.getName());

    private static final DataUtil _Instance = new DataUtil();

    /**
     * Returns the Singleton instance of this class.
     *
     * @return DataUtil
     */
    public static final DataUtil getInstance()
    {
        return _Instance;
    }

    /**
     * Default constructor (private).
     */
    private DataUtil()
    {
        super();
    }

    /**
     * Converts an ActuatorData object to a JSON string.
     *
     * @param actuatorData The ActuatorData object to be converted.
     * @return The JSON representation as a string, or null if the input is null.
     */
    public String actuatorDataToJson(ActuatorData actuatorData)
    {
        String jsonData = null;

        if (actuatorData != null) {
            Gson gson = new Gson();
            jsonData = gson.toJson(actuatorData);
        }

        return jsonData;
    }

    /**
     * Converts a SensorData object to a JSON string.
     *
     * @param sensorData The SensorData object to be converted.
     * @return The JSON representation as a string, or null if the input is null.
     */
    public String sensorDataToJson(SensorData sensorData)
    {
        String jsonData = null;

        if (sensorData != null) {
            Gson gson = new Gson();
            jsonData = gson.toJson(sensorData);
        }

        return jsonData;
    }

    /**
     * Converts a SystemPerformanceData object to a JSON string.
     *
     * @param sysPerfData The SystemPerformanceData object to be converted.
     * @return The JSON representation as a string, or null if the input is null.
     */
    public String systemPerformanceDataToJson(SystemPerformanceData sysPerfData)
    {
        String jsonData = null;

        if (sysPerfData != null) {
            Gson gson = new Gson();
            jsonData = gson.toJson(sysPerfData);
        }

        return jsonData;
    }

    /**
     * Converts a SystemStateData object to a JSON string.
     *
     * @param sysStateData The SystemStateData object to be converted.
     * @return The JSON representation as a string, or null if the input is null.
     */
    public String systemStateDataToJson(SystemStateData sysStateData)
    {
        String jsonData = null;

        if (sysStateData != null) {
            Gson gson = new Gson();
            jsonData = gson.toJson(sysStateData);
        }

        return jsonData;
    }

    /**
     * Converts a JSON string to an ActuatorData object.
     *
     * @param jsonData The JSON string to be converted to an ActuatorData object.
     * @return The ActuatorData object, or null if the input is null or empty.
     */
    public ActuatorData jsonToActuatorData(String jsonData)
    {
        ActuatorData data = null;

        if (jsonData != null && jsonData.trim().length() > 0) {
            Gson gson = new Gson();
            data = gson.fromJson(jsonData, ActuatorData.class);
        }

        return data;
    }

    /**
     * Converts a JSON string to a SensorData object.
     *
     * @param jsonData The JSON string to be converted to a SensorData object.
     * @return The SensorData object, or null if the input is null or empty.
     */
    public SensorData jsonToSensorData(String jsonData)
    {
        SensorData data = null;

        if (jsonData != null && jsonData.trim().length() > 0) {
            Gson gson = new Gson();
            data = gson.fromJson(jsonData, SensorData.class);
        }

        return data;
    }

    /**
     * Converts a JSON string to a SystemPerformanceData object.
     *
     * @param jsonData The JSON string to be converted to a SystemPerformanceData object.
     * @return The SystemPerformanceData object, or null if the input is null or empty.
     */
    public SystemPerformanceData jsonToSystemPerformanceData(String jsonData)
    {
        SystemPerformanceData data = null;

        if (jsonData != null && jsonData.trim().length() > 0) {
            Gson gson = new Gson();
            data = gson.fromJson(jsonData, SystemPerformanceData.class);
        }

        return data;
    }

    /**
     * Converts a JSON string to a SystemStateData object.
     *
     * @param jsonData The JSON string to be converted to a SystemStateData object.
     * @return The SystemStateData object, or null if the input is null or empty.
     */
    public SystemStateData jsonToSystemStateData(String jsonData)
    {
        SystemStateData data = null;

        if (jsonData != null && jsonData.trim().length() > 0) {
            Gson gson = new Gson();
            data = gson.fromJson(jsonData, SystemStateData.class);
        }

        return data;
    }
}