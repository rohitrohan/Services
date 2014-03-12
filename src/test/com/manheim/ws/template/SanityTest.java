// $Header: /export/repo/cvsroot/WebServices/BlobServer/src/test/com/manheim/ws/template/SanityTest.java,v 1.1 2006/03/27 17:18:02 rcooper Exp $
package com.manheim.ws.template;

import junit.framework.TestCase;


/**
 * This is a simple JUnit test case to ensure that the environment is okay.
 *
 * @author <a href="trajano@yahoo.com">Archimedes Trajano</a>
 * @version $Id: SanityTest.java,v 1.1 2006/03/27 17:18:02 rcooper Exp $
 */
public class SanityTest extends TestCase {
    public SanityTest() {
        super();
    }

    public void testSanity() {
        assertEquals("Sanity Test", "test", "test");
    }
}
