package programmingtheiot.part02.integration.connection;

import static org.junit.Assert.*;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import programmingtheiot.data.ActuatorData;
import programmingtheiot.gda.connection.RedisPersistenceAdapter;

public class RedisClientAdapterTest {
    private static final Logger _Logger = Logger.getLogger(RedisClientAdapterTest.class.getName());
    private RedisPersistenceAdapter rpa;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        // Perform any class-level setup needed
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        // Perform any class-level cleanup needed
    }

    @Before
    public void setUp() throws Exception {
        rpa = new RedisPersistenceAdapter();
    }

    @After
    public void tearDown() throws Exception {
        rpa.disconnectClient();
    }

    @Test
    public void testConnectClient() {
        assertTrue("Expected successful connection to Redis", rpa.connectClient());
    }

    @Test
    public void testDisconnectClient() {
        rpa.connectClient(); // Ensure connection before disconnection
        assertTrue("Expected successful disconnection from Redis", rpa.disconnectClient());
    }

    @Test
    public void testGetActuatorData() {
        // Assuming actuator data setup in Redis
        //ActuatorData[] data = rpa.getActuatorData("testTopic", new Date(), new Date());
        //assertNotNull("Expected non-null actuator data", data);
       //Additional checks as necessary
    }

    @Test
    public void testGetSensorData() {
        // Assuming sensor data setup in Redis
        // SensorData[] data = rpa.getSensorData("testTopic", new Date(), new Date());
        // assertNotNull("Expected non-null sensor data", data);
        // Additional checks as necessary
    }

    @Test
    public void testStoreDataStringIntActuatorDataArray() {
        // ActuatorData[] testData = { new ActuatorData() }; // Populate with test data
        // assertTrue("Expected successful storage of actuator data", rpa.storeData("testTopic", 1, testData));
    }

    @Test
    public void testStoreDataStringIntSensorDataArray() {
        // SensorData[] testData = { new SensorData() }; // Populate with test data
        // assertTrue("Expected successful storage of sensor data", rpa.storeData("testTopic", 1, testData));
    }

    @Test
    public void testStoreDataStringIntSystemPerformanceDataArray() {
        // SystemPerformanceData[] testData = { new SystemPerformanceData() }; // Populate with test data
        // assertTrue("Expected successful storage of system performance data", rpa.storeData("testTopic", 1, testData));
    }
}
