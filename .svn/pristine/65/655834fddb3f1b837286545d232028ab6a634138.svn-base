import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.axis.client.Stub;

import com.manheim.webservices.Blob;
import com.manheim.webservices.BlobException;
import com.manheim.webservices.BlobMetaData;
import com.manheim.webservices.BlobService;
import com.manheim.webservices.BlobServiceServiceLocator;
import com.manheim.webservices.MetaDataItem;
import com.manheim.webservices.Source;
import com.manheim.webservices.Store;

/**
 * Simple client example and tester for the BlobServer Web Services (intermediate).
 *
 * This example was created using Apache Axis to auto generate the client stub
 * classes from an existing published WSDL.  (Clients can be generated for various
 * programming languages via various tools - using the WSDL.)
 *
 * This example also uses attachments via the Axis client stub.
 *
 * Manheim uses the "attachment matching id" idiom for attachment data.
 * This means that each attachment can be in any format (MIME, DIME, etc)
 * but it must have a content-Id and the XML data must have a corresponding
 * element that also notes the id (and the server looks for attachment data
 * to match each id on the incoming request).
 *
 * This class demonstrates how to use the Axis client stub.
 *
 */
public class TestServiceClient {
    //private static String serviceEndpoint = "http://http://appstg1.imanheim.com:9284/BlobServerWebService/services/BlobService";
    //private static String serviceEndpoint = "http://localhost:9080/BlobServerWebService-1.0.1/services/BlobService";

    //private static String serviceEndpoint = "http://webservices.imanheim.com/BlobServerWebService/services/BlobService";
    private static String serviceEndpoint = "http://webservices.manheim.com/BlobServerWebService/services/BlobService";
    ///private static String serviceEndpoint = "http://tx-app05.imanheim.com:9384/BlobServerWebService/services/BlobService";
    ///private static String serviceEndpoint = "http://tx-app06.imanheim.com:9384/BlobServerWebService/services/BlobService";
    ///private static String serviceEndpoint = "http://10.103.171.162:8081/ListingWebServices/service/BlobService";
    ///private static String serviceEndpoint = "http://10.103.171.159:4040/ListingWebServices/service/BlobService";
    private static BlobService service;

    /**
     * Main test driver.
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("---------------------------------------------------------------");
        System.out.println(
            "\nTestServiceClient will initialize the service endpoint\n,"
            + "and then use a hard coded \"BlobSeverWebService\" object (obtained from\n"
            + "TestConstants) to perform the STORE, GET and then DELETE\n" + "operations of the service.\n\n");
        System.out.println("---------------------------------------------------------------");

        // INIT
        TestServiceClient.initService();

        // STORE
        TestServiceClient.testStoreBlobTest();

        // GET
        //TestServiceClient.testRetrieveBlobByMetaDataTest();

        // DELAY BEFORE DELETE       
        try {
            long delay = TestConstants.TEST_DELAY_TIME;
            System.out.println("wait " + (delay / 1000) + " seconds for test delay buffer");
            Thread.sleep(delay);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize the service by using the client stub service locator, with the service URL,
     * to create a client stub for the service (also set the HTTP basic auth user and pass).
     *
     */
    public static void initService() {
        if(TestServiceClient.service == null) {
            System.out.println("\ninitService invoked - setting up service endpoint");

            // retrieve a reference to the remote service
            try {
                URL serviceEndpointURL = new URL(serviceEndpoint);
                BlobServiceServiceLocator serviceLocator = new BlobServiceServiceLocator();
                service = serviceLocator.getBlobService(serviceEndpointURL);
                System.out.println("service found serviceEndpoint= " + serviceEndpointURL);
                
                // setup basic auth user and pass on service
                Stub axisStub = (org.apache.axis.client.Stub) service;
                axisStub.setUsername("testdeal1");
                axisStub.setPassword("testdeal1");

            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void testStoreBlobTest() {
        System.out.println("Testing storing Blob");

        File file = new File("C:/Projects/BlobManager/test/test_pdfs/OFF_ROAD_CR.pdf");
        System.out.println("File to store is: " + file.getAbsolutePath());

        if((file != null) && (file.length() > 0)) {
            try {
                //doBasicAuth(serviceLocator);

                Store storeData = new Store();
                storeData.setBlobType("pdf");
                storeData.setName("1692250AAA");
                storeData.setTransactionId("");
                storeData.setExpiryPolicy("30d");

                // Need to get the data from the file.
                Source source = new Source();
                source.setData(convertPdfToByteArray(file.getAbsolutePath()));
                //source.setUrl("file://C:/TEMP/1692250.pdf");
                storeData.setIn(source);

                // Set some meta data for this blob
                BlobMetaData meta = new BlobMetaData();

                /*MetaDataItem vinMeta = new BlobServiceServiceStub.MetaDataItem();
                vinMeta.setName("vin");
                vinMeta.setValue("1nxbr12e3y123456");*/

                /*                MetaDataItem dealerMeta = new BlobServiceServiceStub.MetaDataItem();
                                dealerMeta.setName("dealer");
                                dealerMeta.setValue("1234567");*/
                MetaDataItem auctionMeta = new MetaDataItem();
                auctionMeta.setName("auction");
                auctionMeta.setValue("AAA");

                MetaDataItem workOrderMeta = new MetaDataItem();
                workOrderMeta.setName("sblu");
                workOrderMeta.setValue("10458583");

                meta.setItems(new MetaDataItem[] {auctionMeta, workOrderMeta});
                storeData.setMeta(meta);
                // Make the call to store the document.
                long startTime = System.currentTimeMillis();
                Blob blob = service.store(storeData.getIn(),storeData.getName(),storeData.getMeta(),storeData.getBlobType(),storeData.getTransactionId(),storeData.getExpiryPolicy());
                long endTime = System.currentTimeMillis();
                System.out.println("Store blob took: " + ((endTime - startTime) / 1000) + " seconds.");

                //Blob blob = storeResponse.getStoreReturn();
                System.out.println(
                    "BlobId: " + blob.getId() + " name: " + blob.getName() + "." + blob.getType() + " at "
                    + blob.getLocation() + "version: " + blob.getVersion());
            } catch(BlobException e) {
                e.printStackTrace();
                e.getMessage();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static byte[] convertPdfToByteArray(String fileName) {    
       byte[] result = null;
       BufferedInputStream in = null;
       try {
           in = new BufferedInputStream 
             (new FileInputStream(fileName));
          result = new byte[in.available()];
           in.read(result);
       } catch (Exception ignore) {
        } finally {
          try {
              in.close();
          } catch (Exception ignore) {}
        }      
       return result;
    }
   /* private static void doBasicAuth(BlobServiceServiceLocator service) {

        HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
        auth.setUsername("testdeal1");
        auth.setPassword("testdeal1");
        // set if realm or domain is know
        stub._getServiceClient().getOptions()
            .setProperty(org.apache.axis2.transport.http.HTTPConstants.AUTHENTICATE, auth);

        //AxisProperties.setProperty(AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
    }*/

    /*public static void testRetrieveBlobByMetaDataTest() {
        try {
            System.out.println("\n\ntesting retrieve by meta data");

            BlobServiceService service = new BlobServiceService();
            EndpointReference endpoint = stub._getServiceClient().getOptions().getTo();
            System.out.println("endpoint is: " + endpoint.getAddress());
            doBasicAuth(stub);

            BlobServiceServiceStub.GetBlobByMetadata getBlobsByMetadata = new BlobServiceServiceStub.GetBlobByMetadata();

            // Set some meta data for this blob
            BlobServiceServiceStub.BlobMetaData meta = new BlobServiceServiceStub.BlobMetaData();

            /* MetaDataItem vinMeta = new BlobServiceServiceStub.MetaDataItem();
             vinMeta.setName("vin");
             vinMeta.setValue("1nxbr12e3y123456");*/

            /*MetaDataItem dealerMeta = new BlobServiceServiceStub.MetaDataItem();
            dealerMeta.setName("dealer");
            dealerMeta.setValue("1234567");
            MetaDataItem auctionMeta = new BlobServiceServiceStub.MetaDataItem();
            auctionMeta.setName("auction");
            auctionMeta.setValue("AAA");

            MetaDataItem workOrderMeta = new BlobServiceServiceStub.MetaDataItem();
            workOrderMeta.setName("sblu");
            workOrderMeta.setValue("10458583");

            BlobServiceServiceStub.ArrayOfMetaDataItem items = new BlobServiceServiceStub.ArrayOfMetaDataItem();
            //items.addItem(vinMeta);
            //items.addItem(dealerMeta);
            items.addItem(auctionMeta);
            items.addItem(workOrderMeta);
            meta.setItems(items);
            getBlobsByMetadata.setMeta(meta);

            BlobServiceServiceStub.GetBlobByMetadataResponse response = stub.getBlobByMetadata(getBlobsByMetadata);

            if(response != null) {
                Blob blob = response.getGetBlobByMetadataReturn();
                System.out.println(
                    "BlobId: " + blob.getId() + " name: " + blob.getName() + "." + blob.getType() + " at "
                    + blob.getLocation() + "version: " + blob.getVersion());
            } else {
                System.out.println("No response returned for that meta data.");
            }
        } catch(AxisFault e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(BlobExceptionException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Finished getByMetaData");
        }
    }*/
}
