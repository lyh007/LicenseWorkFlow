package com.lyh.licenseworkflow;

import org.junit.Test;

/**
 * @author kevin
 * @version Revision: 1.00 Date: 11-9-20上午10:56
 * @Email liuyuhui007@gmail.com
 */
public class JpdlDeployTest extends BaseTestCase {
    public JpdlDeployTest() {
        getRepositoryService().createDeployment().addResourceFromClasspath("LicenseWorkFlow.jpdl.xml");
              //  .addResourceFromClasspath("demo.png").deploy();
    }

    @Test
    public void testDeploy() {
        System.out.println("hello");
    }
}
