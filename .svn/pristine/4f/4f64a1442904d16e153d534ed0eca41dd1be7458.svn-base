package samples;

import java.net.URL;
import org.apache.axis.client.Stub;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import com.manheim.webservices.Blob;
import com.manheim.blobmanager.exceptions.BlobException;
import com.manheim.webservices.BlobMetaData;
import com.manheim.webservices.BlobService;
import com.manheim.webservices.BlobServiceServiceLocator;
import com.manheim.webservices.MetaDataItem;
import com.manheim.webservices.Source;
import com.manheim.webservices.Store;


public class BlobServiceClientExample  {
	private static String serviceEndpoint = "http://localhost:8080/BlobServerWebService-1.1.0/services/BlobService";
	private static String SOURCE_FILE_LOCATION="C:/Projects/WebServices/BlobServer/BlobClientSample/src/samples/tostore/";
	private static String TARGET_FILE_LOCATION="C:/Projects/WebServices/BlobServer/BlobClientSample/src/samples/retrived/";
	private static BlobService service;


	/**
	 * Setup method for the tests
	 */
	public static void setup() {
		if(service == null) {
			System.out.println("\nTest Setup -- InitService invoked to set up service endpoint...");

			// retrieve a reference to the remote service
			try {
				URL serviceEndpointURL = new URL(serviceEndpoint);
				BlobServiceServiceLocator serviceLocator = new BlobServiceServiceLocator();
				service = serviceLocator.getBlobService(serviceEndpointURL);
				System.out.println("Service found serviceEndpoint= " + serviceEndpointURL);

				// setup basic auth user and pass on service
				Stub axisStub = (org.apache.axis.client.Stub) service;
				axisStub.setUsername("testdeal1");
				axisStub.setPassword("testdeal1");

			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Clean up method to release the resources after tests.
	 */
	public static void cleanup() {
		try {
			service = null;
			System.out.println("Test Tear Down.\n");
		} catch (Exception e) {
			//Assert.fail(e.getMessage());
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

	public static void testStore() {
		System.out.println("Testing store()...");
		setup();
		System.out.println("Current path="+System.getProperty("user.dir"));
		File file = new File(SOURCE_FILE_LOCATION+"OFF_ROAD_CR.pdf");
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
						"Original BlobId: " + blob.getId() + " name: " + blob.getName() + "." + blob.getType() + " at "
						+ blob.getLocation() + " version: " + blob.getVersion());

				//Assert.assertNotNull(blob);

				if (blob!=null) {
					Blob returnBlob = service.getById(blob.getId());
					System.out.println(
							"Returned BlobId: " + returnBlob.getId() + " name: " + returnBlob.getName() + "." + returnBlob.getType() + " at "
							+ returnBlob.getLocation() + " version: " + returnBlob.getVersion());

					//Assert.assertNotNull(returnBlob);
				}

			} catch(BlobException e) {
				e.printStackTrace();
				e.getMessage();
			} catch(IOException e) {
				e.printStackTrace();
			}
			finally{
				cleanup();
			}
		}
	}



	public static void testGetById() {
		System.out.println("Testing getById()...");
		setup();
		try {
			Blob returnBlob = service.getById("CEFEBAD0-9DEE-11DC-BAD0-EB4BB07254D7");
			System.out.println(
					"Returned BlobId: " + returnBlob.getId() + " name: " + returnBlob.getName() + "." + returnBlob.getType() + " at "
					+ returnBlob.getLocation() + " version: " + returnBlob.getVersion());

			//Assert.assertNotNull(returnBlob);
		}
		catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		finally{
			cleanup();
		}

	}

	public static void testGetIdFromName() {
		System.out.println("Testing getIdFromName()...");
		setup();
		try {
			String returnBlobId = service.getIdFromName("1692250AAA");
			System.out.println(
					"Returned BlobId: " + returnBlobId);

			//Assert.assertNotNull(returnBlobId);
		}
		catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		finally{
			cleanup();
		}

	}

	public static void testGetIdFromNameAndVersion() {
		System.out.println("Testing getIdFromNameAndVersion()...");
		setup();
		try {
			String returnBlobId = service.getIdFromNameAndVersion("1692250AAA",15);
			System.out.println(
					"Returned BlobId: " + returnBlobId);

			//Assert.assertNotNull(returnBlobId);
		}
		catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		finally{
			cleanup();
		}


	}

	public static void testGetBlobByMetadata() {
		System.out.println("Testing getBlobByMetadata()...");
		setup();
		try {

			BlobMetaData meta = new BlobMetaData();
			MetaDataItem auctionMeta = new MetaDataItem();
			auctionMeta.setName("auction");
			auctionMeta.setValue("AAA");
			MetaDataItem workOrderMeta = new MetaDataItem();
			workOrderMeta.setName("sblu");
			workOrderMeta.setValue("10458583");
			meta.setItems(new MetaDataItem[] {auctionMeta, workOrderMeta});

			Blob returnBlob = service.getBlobByMetadata(meta);
			System.out.println(
					"Returned BlobId: " + returnBlob.getId() + " name: " + returnBlob.getName() + "." + returnBlob.getType() + " at "
					+ returnBlob.getLocation() + " version: " + returnBlob.getVersion());

			//Assert.assertNotNull(returnBlob);
		}
		catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		finally{
			cleanup();
		}

	}


	public static void testGetBlobsByMetadata() {
		System.out.println("Testing getBlobsByMetadata()...");
		setup();
		try {

			BlobMetaData meta = new BlobMetaData();
			MetaDataItem auctionMeta = new MetaDataItem();
			auctionMeta.setName("auction");
			auctionMeta.setValue("AAA");
			MetaDataItem workOrderMeta = new MetaDataItem();
			workOrderMeta.setName("sblu");
			workOrderMeta.setValue("10458583");
			meta.setItems(new MetaDataItem[] {auctionMeta, workOrderMeta});

			Object[] returnBlobs = service.getBlobsByMetadata(meta);
			//Assert.assertNotNull(returnBlobs);
			for (int i=0;i<returnBlobs.length;i++) {
				Blob blob = (Blob)(returnBlobs[i]);
				System.out.println(
						"Returned BlobId: " + blob.getId() + " name: " + blob.getName() + "." + blob.getType() + " at "
						+ blob.getLocation() + " version: " + blob.getVersion());

			}
		}
		catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}			
		finally{
			cleanup();
		}
	}


	public static void testStress() {
		System.out.println("Testing Stress()...");
		System.out.println("Current path="+System.getProperty("user.dir"));
		File file = new File(SOURCE_FILE_LOCATION+"jeep3.jpg");
		System.out.println("File to store is: " + file.getAbsolutePath());
		setup();
		if((file != null) && (file.length() > 0)) {
			try {
				for (int i=0;i<10;i++) {
					System.out.println("******************Test case "+i+" ****************");
					
					Store storeData = new Store();
					storeData.setBlobType("jpg");
					storeData.setName("192250AAC006_"+i);
					storeData.setTransactionId("");
					storeData.setExpiryPolicy("30d");

					// Need to get the data from the file.
					Source source = new Source();
					source.setData(convertPdfToByteArray(file.getAbsolutePath()));
					storeData.setIn(source);

					// Set some meta data for this blob
					BlobMetaData meta = new BlobMetaData();

					MetaDataItem auctionMeta = new MetaDataItem();
					auctionMeta.setName("auctionID");
					auctionMeta.setValue("AAC_"+i);

					MetaDataItem workOrderMeta = new MetaDataItem();
					workOrderMeta.setName("DealerID");
					workOrderMeta.setValue("1045858356778_"+i);

					meta.setItems(new MetaDataItem[] {auctionMeta, workOrderMeta});
					storeData.setMeta(meta);
					// Make the call to store the document.
					long startTime = System.currentTimeMillis();
					Blob blob = service.store(storeData.getIn(),storeData.getName(),storeData.getMeta(),storeData.getBlobType(),storeData.getTransactionId(),storeData.getExpiryPolicy());
					long endTime = System.currentTimeMillis();
					System.out.println("Store blob took: " + ((endTime - startTime) / 1000) + " seconds.");

					System.out.println(
							"Original BlobId: " + blob.getId() + " name: " + blob.getName() + "." + blob.getType() + " at "
							+ blob.getLocation() + " version: " + blob.getVersion());



					if (blob!=null) {
						Blob returnBlob = service.getById(blob.getId());
						System.out.println(
								"Returned BlobId: " + returnBlob.getId() + " name: " + returnBlob.getName() + "." + returnBlob.getType() + " at "
								+ returnBlob.getLocation() + " version: " + returnBlob.getVersion());

	    				
					}	                
				}
			} catch(BlobException e) {
				e.printStackTrace();
				e.getMessage();
			} catch(IOException e) {
				e.printStackTrace();
			}
			finally{
				cleanup();
			}
		}
	}	
	
	

	public static void main(String[] args) {
		testStore();
		testGetById();
		testGetIdFromName();
		testGetIdFromNameAndVersion();
		testGetBlobByMetadata();
		testGetBlobsByMetadata();
		testStress();
	}

}
