package controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class ImageUtil {
	  public static String saveImage(Part imagePart, ServletContext context) throws IOException {
	        if (imagePart == null || imagePart.getSize() == 0) {
	            return null;
	        }

	        // Kiểm tra Content-Type
	        String contentType = imagePart.getContentType();
	        if (contentType == null || !contentType.startsWith("image/")) {
	            throw new IOException("Chỉ chấp nhận tệp ảnh");
	        }

	        try (InputStream inputStream = imagePart.getInputStream();
	             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
	            
	            // Chuyển InputStream thành byte array
	            byte[] buffer = new byte[4096];
	            int bytesRead;
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, bytesRead);
	            }
	            byte[] imageBytes = outputStream.toByteArray();

	            // Upload lên Cloudinary
	            Cloudinary cloudinary = controller.CloudinaryConfig.getInstance();
	            Map<String, Object> uploadResult = cloudinary.uploader().upload(
	                imageBytes,
	                ObjectUtils.asMap(
	                    "public_id", "uploads/" + UUID.randomUUID().toString(),
	                    "resource_type", "auto"
	                )
	            );

	            return (String) uploadResult.get("secure_url");
	        } catch (Exception e) {
	            throw new IOException("Không thể upload ảnh lên Cloudinary: " + e.getMessage(), e);
	        }
	    }
}
