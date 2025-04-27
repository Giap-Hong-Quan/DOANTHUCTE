package controller;

import java.util.HashMap;
import java.util.Map;

import com.cloudinary.Cloudinary;

public class CloudinaryConfig {
    private static Cloudinary cloudinary;

    public static Cloudinary getInstance() {
        if (cloudinary == null) {
            Map<String, String> config = new HashMap<>();
            config.put("cloud_name", "dglzu3rsm");
            config.put("api_key", "955273494859918");
            config.put("api_secret", "YhkS-LxaxQ4zYYnIj14sRtThFUc");
            cloudinary = new Cloudinary(config);
        }
        return cloudinary;
    }

    // Phương thức kiểm tra kết nối
    public static void testConnection() {
        try {
            // Kiểm tra kết nối với Cloudinary
            Cloudinary cloudinary = getInstance();
            Map<String, Object> result = cloudinary.api().ping(null);
            System.out.println("Kết nối Cloudinary thành công: " + result);
        } catch (Exception e) {
            System.err.println("Lỗi khi kết nối tới Cloudinary: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Gọi hàm testConnection để kiểm tra kết nối
        testConnection();
    }
}
