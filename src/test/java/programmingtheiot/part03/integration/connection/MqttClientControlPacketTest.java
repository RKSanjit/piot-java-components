/**
 * 
 * This class is part of the Programming the Internet of Things
 * project, and is available via the MIT License, which can be
 * found in the LICENSE file at the top level of this repository.
 * 
 * Copyright (c) 2020 by Andrew D. King
 */ 

package programmingtheiot.part03.integration.connection;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import programmingtheiot.common.ConfigConst;
import programmingtheiot.common.ConfigUtil;
import programmingtheiot.common.IDataMessageListener;
import programmingtheiot.common.ResourceNameEnum;
import programmingtheiot.data.*;
import programmingtheiot.gda.connection.*;

/**
 * Integration test class for MqttClientControlPacketTest.
 * This class tests the MQTT client control packet functionality,
 * including connecting, disconnecting, server pinging, and publishing/subscribing.
 */

public class MqttClientControlPacketTest
{
	// static logger
	
	private static final Logger _Logger =
		Logger.getLogger(MqttClientControlPacketTest.class.getName());
	
	
	 // Member variable for MQTT client
	
	private MqttClientConnector mqttClient = null;
	
	
	// test setup methods
	
    /**
     * Sets up resources for each test.
     * @throws java.lang.Exception
     */
	@Before
	public void setUp() throws Exception
	{
		this.mqttClient = new MqttClientConnector();
	}
	
	@After
	public void tearDown() throws Exception
	{
		// Optional cleanup after each test
	}
	

	
	// test methods
	
	
    /**
     * Test method for connecting and disconnecting the MQTT client.
     */
	@Test
	public void testConnectAndDisconnect()
	{
		_Logger.info("Testing connect and disconnect...");
		assertTrue(this.mqttClient.connectClient());
		_Logger.info("Connected to the MQTT broker.");
		try {
			Thread.sleep(1000);  // Small delay to ensure connection establishment
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertTrue(this.mqttClient.disconnectClient());
		_Logger.info("Disconnected from the MQTT broker.");
	}
	
	
    /**
     * Test method for validating server ping functionality.
     */
	@Test
	public void testServerPing()
	{
		_Logger.info("Testing server ping...");
		assertTrue(this.mqttClient.connectClient());
		_Logger.info("Connected to the MQTT broker.");
		// Assuming a keep-alive interval smaller than this sleep time
		try {
			Thread.sleep(2 * this.mqttClient.getKeepAlive());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// The actual PINGREQ and PINGRESP would have to be validated through logs
		_Logger.info("Ping should have occurred.");
		assertTrue(this.mqttClient.disconnectClient());
		_Logger.info("Disconnected from the MQTT broker after ping test.");
	}
	
	
    /**
     * Test method for validating publish and subscribe functionality with different QoS levels.
     */
	@Test
	public void testPubSub()
	{
		String topic = ResourceNameEnum.CDA_ACTUATOR_CMD_RESOURCE.getResourceName();
		String testMessageQoS1 = "Test message QoS 1";
		String testMessageQoS2 = "Test message QoS 2";
		
		_Logger.info("Testing publish and subscribe with QoS 1 and QoS 2...");
		assertTrue(this.mqttClient.connectClient());
		_Logger.info("Connected to the MQTT broker.");

		assertTrue(this.mqttClient.subscribeToTopic(ResourceNameEnum.CDA_ACTUATOR_CMD_RESOURCE, 1));
		_Logger.info("Subscribed to " + topic + " with QoS 1.");
		assertTrue(this.mqttClient.publishMessage(ResourceNameEnum.CDA_ACTUATOR_CMD_RESOURCE, testMessageQoS1, 1));
		_Logger.info("Published message with QoS 1: " + testMessageQoS1);
		assertTrue(this.mqttClient.unsubscribeFromTopic(ResourceNameEnum.CDA_ACTUATOR_CMD_RESOURCE));
		_Logger.info("Unsubscribed from " + topic + ".");

		assertTrue(this.mqttClient.subscribeToTopic(ResourceNameEnum.CDA_ACTUATOR_CMD_RESOURCE, 2));
		_Logger.info("Subscribed to " + topic + " with QoS 2.");
		assertTrue(this.mqttClient.publishMessage(ResourceNameEnum.CDA_ACTUATOR_CMD_RESOURCE, testMessageQoS2, 2));
		_Logger.info("Published message with QoS 2: " + testMessageQoS2);
		assertTrue(this.mqttClient.unsubscribeFromTopic(ResourceNameEnum.CDA_ACTUATOR_CMD_RESOURCE));
		_Logger.info("Unsubscribed from " + topic + ".");

		assertTrue(this.mqttClient.disconnectClient());
		_Logger.info("Disconnected from the MQTT broker after pub/sub test.");
	}
}
