package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import javax.servlet.ServletContext;

public class ImageUtil {

    public static String saveImage(String imageSource, ServletContext context) throws IOException {
        // Nếu là link Google/Facebook, trả về link luôn, không lưu file
        if (imageSource.startsWith("http")) {
            return imageSource;
        }

        // Nếu là file upload từ máy tính, lưu vào thư mục /assets/images/
        String fileName = UUID.randomUUID().toString() + getFileExtension(imageSource);
        String folderPath = context.getRealPath("/assets/images/");
        
        // Kiểm tra và tạo thư mục nếu chưa có
        File folder = new File(folderPath);
        if (!folder.exists()) folder.mkdirs();

        // Đường dẫn file lưu
        String filePath = folderPath + File.separator + fileName;

        // Lưu file ảnh (Trường hợp ảnh upload từ máy tính)
        File sourceFile = new File(imageSource);
        if (sourceFile.exists()) {
            Files.copy(sourceFile.toPath(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        }

        // Nếu `imageSource` là URL (nhưng không phải link Google/Facebook)
        try (InputStream in = new URL(imageSource).openStream()) {
            Files.copy(in, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            System.err.println("❌ Lỗi tải file từ URL: " + e.getMessage());
            return null;
        }
    }

    // Lấy đuôi file từ đường dẫn (VD: "abc.png" -> ".png")
    private static String getFileExtension(String filePath) {
        int lastDotIndex = filePath.lastIndexOf(".");
        return (lastDotIndex != -1) ? filePath.substring(lastDotIndex) : "";
    }
    
}
