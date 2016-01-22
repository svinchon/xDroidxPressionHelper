package com.diy.xpression.helperserver;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.rmi.RemoteException;
import com.diy.xpression.quickdoc.QuickDocProxy;

public class QuickDocPlus {
	
	public String publishDocumentWithImages(
			String requestContext,
			String documentName,
			String customerData,
			String outputProfileName,
			byte[] resourcesZIP, 
			ImageMetadata[] imageMetadata
	) {
		String returnMessage = "zzz";
		try {
			// write zip to temp
			Utils.convertByte2File(resourcesZIP, "c:/temp/fromCaptiva.zip");
			
			// unzip
			ZipFile zipFile = new ZipFile("c:/temp/fromCaptiva.zip");
			zipFile.extractAll("c:/temp/fromCaptiva");
			
			// generate temp files names
			/*int imageCount = imageMetadata.length;
			String[] ImagesFullPath = new String[imageCount];
			for (int i=0; i< imageMetadata.length; i++ ) {
				ImagesFullPath[i] = "c:/temp/"+imageMetadata[i].name+"."+imageMetadata[i].format;
			}*/
			
			// write images to temp files
			/*for (int i=0; i< imageMetadata.length; i++ ) {
				//writeToFile(new ByteArrayInputStream(images[i]), ImagesFullPath[i]);
			}
			*/
			
			// add images location and names to data
			System.out.println(customerData);
			/*XPath2Xml x2x = new XPath2Xml();
			x2x.initDocumentFromString(customerData);
			for (int i=0; i< imageMetadata.length; i++ ) {
				x2x.addElement("/Documents/Document/Image", imageMetadata[i].name+"."+imageMetadata[i].format);
			}		
			customerData = x2x.getResultingDocument();*/
			//System.out.println("new: "+customerData);
			
			// call quick doc
			QuickDocProxy qd = new QuickDocProxy();
			//Utils.log(qd.getEndpoint());
			returnMessage = qd.publishDocument(
					requestContext,
					documentName,
					customerData,
					outputProfileName
			);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return status
		return returnMessage;
	}
	
}
