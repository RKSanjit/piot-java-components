package programmingtheiot.part03.integration.app;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import programmingtheiot.common.ConfigConst;
import programmingtheiot.common.ResourceNameEnum;
import programmingtheiot.data.SensorData;
import programmingtheiot.gda.app.DeviceDataManager;
import programmingtheiot.common.ConfigUtil;

/**
 * This test case class provides simple integration tests for DeviceDataManager
 * focusing on actuation commands based on humidity sensor data.
 */
public class DeviceDataManagerSimpleCdaActuationTest {
    // static vars
    private static final Logger _Logger = Logger.getLogger(DeviceDataManagerSimpleCdaActuationTest.class.getName());

    // member vars
    private DeviceDataManager devDataMgr = null;

    // test setup methods

    @Before
    public void setUp() throws Exception {
        this.devDataMgr = new DeviceDataManager();
    }

    @After
    public void tearDown() throws Exception {
        this.devDataMgr = null;
    }

    // test methods

    @Test
    public void testSendActuationEventsToCda() {
        ConfigUtil cfgUtil = ConfigUtil.getInstance();

        float nominalVal = cfgUtil.getFloat(ConfigConst.GATEWAY_DEVICE, "nominalHumiditySetting");
        float lowVal = cfgUtil.getFloat(ConfigConst.GATEWAY_DEVICE, "triggerHumidifierFloor");
        float highVal = cfgUtil.getFloat(ConfigConst.GATEWAY_DEVICE, "triggerHumidifierCeiling");
        int delay = cfgUtil.getInteger(ConfigConst.GATEWAY_DEVICE, "humidityMaxTimePastThreshold");

        // Test Sequence No. 1
        generateAndProcessHumiditySensorDataSequence(devDataMgr, nominalVal, lowVal, highVal, delay);

        // TODO: Add more test sequences if desired.
    }

    private void generateAndProcessHumiditySensorDataSequence(DeviceDataManager ddm, float nominalVal, float lowVal, float highVal, int delay) {
        SensorData sd = new SensorData();
        sd.setName("My Test Humidity Sensor");
        sd.setLocationID("constraineddevice001");
        sd.setTypeID(ConfigConst.HUMIDITY_SENSOR_TYPE);
        
        // Nominal value tests
        sd.setValue(nominalVal);
        ddm.handleSensorMessage(ResourceNameEnum.CDA_SENSOR_MSG_RESOURCE, sd);
        waitForSeconds(2);
        
        sd.setValue(nominalVal);
        ddm.handleSensorMessage(ResourceNameEnum.CDA_SENSOR_MSG_RESOURCE, sd);
        waitForSeconds(2);
        
        // Low value tests
        sd.setValue(lowVal - 2);
        ddm.handleSensorMessage(ResourceNameEnum.CDA_SENSOR_MSG_RESOURCE, sd);
        waitForSeconds(delay + 1);
        
        sd.setValue(lowVal - 1);
        ddm.handleSensorMessage(ResourceNameEnum.CDA_SENSOR_MSG_RESOURCE, sd);
        waitForSeconds(delay + 1);
        
        // High value tests
        sd.setValue(lowVal + 1);
        ddm.handleSensorMessage(ResourceNameEnum.CDA_SENSOR_MSG_RESOURCE, sd);
        waitForSeconds(delay + 1);
        
        sd.setValue(nominalVal);
        ddm.handleSensorMessage(ResourceNameEnum.CDA_SENSOR_MSG_RESOURCE, sd);
        waitForSeconds(delay + 1);
    }

    private void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            // ignore
        }
    }
}
