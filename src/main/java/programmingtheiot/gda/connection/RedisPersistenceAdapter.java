package programmingtheiot.gda.connection;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import programmingtheiot.common.ConfigConst;
import programmingtheiot.common.ConfigUtil;
import programmingtheiot.data.ActuatorData;
import programmingtheiot.data.SensorData;
import programmingtheiot.data.SystemPerformanceData;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Shell representation of class for student implementation.
 */
public class RedisPersistenceAdapter implements IPersistenceClient {
    // static
    private static final Logger _Logger = Logger.getLogger(RedisPersistenceAdapter.class.getName());

    // private var's
    private String host;
    private int port;
    private Jedis redisClient;

    // constructors
    public RedisPersistenceAdapter() {
        super();
        initConfig();
    }

    // private methods
    private void initConfig() {
        ConfigUtil configUtil = ConfigUtil.getInstance();
        this.host = configUtil.getProperty(ConfigConst.DATA_GATEWAY_SERVICE, ConfigConst.HOST_KEY);
        this.port = Integer.parseInt(configUtil.getProperty(ConfigConst.DATA_GATEWAY_SERVICE, ConfigConst.PORT_KEY));
        this.redisClient = new Jedis(this.host, this.port);
    }

    // public methods
    @Override
    public boolean connectClient() {
        if (this.redisClient.isConnected()) {
            _Logger.warning("Redis client already connected.");
            return true;
        }
        try {
            this.redisClient.connect();
            _Logger.info("Connected to Redis server.");
            return true;
        } catch (JedisConnectionException e) {
            _Logger.log(Level.SEVERE, "Failed to connect to Redis server: ", e);
            return false;
        }
    }

    @Override
    public boolean disconnectClient() {
        if (!this.redisClient.isConnected()) {
            _Logger.warning("Redis client already disconnected.");
            return true;
        }
        try {
            this.redisClient.disconnect();
            _Logger.info("Disconnected from Redis server.");
            return true;
        } catch (JedisConnectionException e) {
            _Logger.log(Level.SEVERE, "Failed to disconnect from Redis server: ", e);
            return false;
        }
    }

    @Override
    public ActuatorData[] getActuatorData(String topic, Date startDate, Date endDate) {
        // Implement retrieval logic here
        return null;
    }

    @Override
    public SensorData[] getSensorData(String topic, Date startDate, Date endDate) {
        // Implement retrieval logic here
        return null;
    }

    @Override
    public void registerDataStorageListener(Class cType, IPersistenceListener listener, String... topics) {
        // Implement listener registration logic here
    }

    @Override
    public boolean storeData(String topic, int qos, ActuatorData... data) {
        // Implement data storage logic here
        return false;
    }

    @Override
    public boolean storeData(String topic, int qos, SensorData... data) {
        // Implement data storage logic here
        return false;
    }

    @Override
    public boolean storeData(String topic, int qos, SystemPerformanceData... data) {
        // Implement data storage logic here
        return false;
    }
}
