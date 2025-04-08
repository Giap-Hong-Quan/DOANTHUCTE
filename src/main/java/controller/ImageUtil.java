package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

public class ImageUtil {

    // Hàm lưu ảnh
    public static String saveImage(Part imagePart, ServletContext context) throws IOException {
        if (imagePart == null || imagePart.getSize() == 0) {
            return null; // Không có file upload
        }

        // Lấy tên file gốc
        String submittedFileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
        
        // Lấy đuôi file (vd: .jpg, .png, .gif)
        String fileExtension = getFileExtension(submittedFileName);
        
        // Tạo tên file mới với UUID và tên file gốc
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + "_" + submittedFileName;  // Kết hợp UUID với tên file gốc

        // Đường dẫn thư mục lưu ảnh
        String folderPath = context.getRealPath("/assets/images/");
        File folder = new File(folderPath);
        if (!folder.exists()) folder.mkdirs();

        // Đường dẫn đầy đủ của file
        String filePath = folderPath + File.separator + fileName;

        // Ghi nội dung file từ Part vào đường dẫn thực
        try (InputStream input = imagePart.getInputStream()) {
            Files.copy(input, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        }

        // Trả về tên file đã lưu để lưu vào DB (bao gồm UUID + tên file gốc)
        return fileName;
    }

    // Hàm lấy đuôi file (vd: .jpg, .png, .gif)
    private static String getFileExtension(String filePath) {
        int lastDotIndex = filePath.lastIndexOf(".");
        return (lastDotIndex != -1) ? filePath.substring(lastDotIndex) : "";
    }
}
