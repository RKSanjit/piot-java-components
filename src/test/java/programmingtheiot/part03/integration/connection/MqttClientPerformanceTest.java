
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

import programmingtheiot.common.ConfigUtil;

import programmingtheiot.common.ResourceNameEnum;

import programmingtheiot.data.DataUtil;

import programmingtheiot.data.SensorData;

import programmingtheiot.gda.connection.MqttClientConnector;

/**

* Integration test class for MqttClientPerformanceTest.

* Tests the performance of the MqttClientConnector, particularly focusing

* on its efficiency in connecting, disconnecting, and publishing messages

* with different QoS levels. This is a basic performance test suite that

* can be expanded for more comprehensive IoT environment testing.

*/

public class MqttClientPerformanceTest

{

	// static

	private static final Logger _Logger =

		Logger.getLogger(MqttClientPerformanceTest.class.getName());

	public static final int MAX_TEST_RUNS = 10000;

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

		ConfigUtil.getInstance();

		this.mqttClient = new MqttClientConnector();

	}

    /**

     * Cleans up resources after each test.

     * @throws java.lang.Exception

     */

	@After

	public void tearDown() throws Exception

	{

	}

	// test methods

    /**

     * Test the connect and disconnect speed of the MQTT client.

     */

	@Test

	public void testConnectAndDisconnect()

	{

		long startMillis = System.currentTimeMillis();

		assertTrue(this.mqttClient.connectClient());

		assertTrue(this.mqttClient.disconnectClient());

		long endMillis = System.currentTimeMillis();

		long elapsedMillis = endMillis - startMillis;

		_Logger.info("Connect and Disconnect [1]: " + elapsedMillis + " ms");

	}

    /**

     * Test the publish speed with QoS 0.

     */

	//@Test

	public void testPublishQoS0()

	{

		execTestPublish(MAX_TEST_RUNS, 0);

	}

    /**

     * Test the publish speed with QoS 1.

     */

	//@Test

	public void testPublishQoS1()

	{

		execTestPublish(MAX_TEST_RUNS, 1);

	}

	 /**

     * Test the publish speed with QoS 2.

     */

	@Test

	public void testPublishQoS2()

	{

		execTestPublish(MAX_TEST_RUNS, 2);

	}

    // Private helper methods

    /**

     * Executes the publish test for a given number of runs and QoS level.

     * @param maxTestRuns The number of messages to publish.

     * @param qos The QoS level to use for the messages.

     */

	private void execTestPublish(int maxTestRuns, int qos)

	{

		/*

		assertTrue(this.mqttClient.connectClient());

		SensorData sensorData = new SensorData();

		String payload = DataUtil.getInstance().sensorDataToJson(sensorData);

		int payloadLen = payload.length();

		long startMillis = System.currentTimeMillis();

		for (int sequenceNo = 1; sequenceNo <= maxTestRuns; sequenceNo++) {

			this.mqttClient.publishMessage(ResourceNameEnum.CDA_MGMT_STATUS_CMD_RESOURCE, payload, qos);

		}

		long endMillis = System.currentTimeMillis();

		long elapsedMillis = endMillis - startMillis;

		assertTrue(this.mqttClient.disconnectClient());

		String msg =

			String.format(

				"\\n\\tTesting Publish: QoS = %s | msgs = %s | payload size = %s | start = %s | end = %s | elapsed = %s",

				qos, maxTestRuns, payloadLen,

				(float) startMillis / 1000, (float) endMillis / 1000, (float) elapsedMillis / 1000);

		_Logger.info(msg);

		*/

		assertTrue(this.mqttClient.connectClient());

		SensorData sensorData = new SensorData();

		String payload = DataUtil.getInstance().sensorDataToJson(sensorData);

		long startMillis = System.currentTimeMillis();

		for (int sequenceNo = 0; sequenceNo < maxTestRuns; sequenceNo++) {

			this.mqttClient.publishMessage(ResourceNameEnum.CDA_MGMT_STATUS_CMD_RESOURCE, payload, qos);

		}

		long endMillis = System.currentTimeMillis();

		long elapsedMillis = endMillis - startMillis;

		assertTrue(this.mqttClient.disconnectClient());

		_Logger.info("Publish message - QoS " + qos + " [" + maxTestRuns + "]: " + elapsedMillis + " ms");

	}

}