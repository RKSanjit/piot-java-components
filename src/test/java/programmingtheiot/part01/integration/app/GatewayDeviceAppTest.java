/**
* 
* This class is part of the Programming the Internet of Things
* project, and is available via the MIT License, which can be
* found in the LICENSE file at the top level of this repository.
* 
* Copyright (c) 2020 by Andrew D. King
*/
 
package programmingtheiot.part01.integration.app;
 
import java.util.logging.Logger;
 
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
 
import programmingtheiot.gda.app.GatewayDeviceApp;
import programmingtheiot.gda.app.DeviceDataManager;
import programmingtheiot.gda.system.SystemPerformanceManager;
/**
* This test case class contains very basic integration tests for
* GatewayDeviceApp. It should not be considered complete,
* but serve as a starting point for the student implementing
* additional functionality within their Programming the IoT
* environment.
*
*/
public class GatewayDeviceAppTest
{
	// static
	private static final Logger _Logger =
		Logger.getLogger(GatewayDeviceAppTest.class.getName());

 
	// member var's
	private GatewayDeviceApp gda = null;
	private DeviceDataManager deviceDataManager = null;
	private SystemPerformanceManager systemPerformanceManager = null;
	// test setup methods
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		gda = new GatewayDeviceApp((String[]) null);
		//deviceDataManager = new DeviceDataManager();
		//systemPerformanceManager = new SystemPerformanceManager();
	}
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}
	// test methods
		/**
		 * Convenience test method for starting and stopping the GDA. This will invoke both
		 * {@link programmingtheiot.gda.app.GatewayDeviceApp#startApp()} and
		 * {@link programmingtheiot.gda.app.GatewayDeviceApp#stopApp(int)} in sequence.
		 * <p>
		 * Validation is via log output and the expectation that no exception will be
		 * thrown during execution.
		 */
		@Test
		public void testStartAndStopGatewayApp()
		{

			this.gda.startApp();
	       /*_Logger.info("Starting DeviceDataManager...");
	        deviceDataManager.startManager();
	        _Logger.info("Starting SystemPerformanceManager...");
	        systemPerformanceManager.startManager();*/
			try {
				Thread.sleep(3000000L);
			} catch (InterruptedException e) {
				// ignore
			}
 
	 
	        _Logger.info("Stopping DeviceDataManager...");
	        //deviceDataManager.stopManager();
	        //_Logger.info("Stopping SystemPerformanceManager...");
	        //systemPerformanceManager.stopManager();
			this.gda.stopApp(0);
 
	 
		}
	}