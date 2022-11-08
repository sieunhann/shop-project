package com.example.project.service.product;

import com.example.project.entity.product.ImageEntity;
import com.example.project.entity.product.ProductEntity;
import com.example.project.exception.BadRequestException;
import com.example.project.exception.NotFoundException;
import com.example.project.repository.product.ImageRepository;
import com.example.project.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// folder uploads
// folder theo userId -> chứa file

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProductRepository productRepository;

    private Path rootPath = Paths.get("uploads");

    // Lưu ảnh vào DB
    public ImageEntity saveToDB(ImageEntity image){
        return imageRepository.save(image);
    }

    public ImageService(){
        createFolder(rootPath.toString());
    }
    // Tao folder
    private void createFolder(String path){
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
    }

    // Upload file
    public String uploadFile(MultipartFile file) {

        // Tao folder tuong ung voi userId
//        Path userPath = rootPath.resolve(String.valueOf(id));
//        createFolder(userPath.toString());

        // Validate file
        validateFile(file);

        // Tao fileId
        String fileId = String.valueOf(System.currentTimeMillis());
        File serverFile = new File(rootPath + "/" + fileId);
        // todo
        //file.transferTo(serverFile);
        try {
            // Sử dụng Buffer để lưu dữ liệu từ file
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

            stream.write(file.getBytes());
            stream.close();

            return "/api/v1/product/images/" + fileId;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi upload file");
        }
    }

    public void validateFile(MultipartFile file){
        // Kiem tra ten
        String fileName = file.getOriginalFilename();
        if(fileName == null || file.equals("")){
            throw new BadRequestException("File's name can not blank");
        }

        // Kiem tra extension
        // avatar.png -> png
        // avatar.jpg -> jpg
        String fileExtension = getExtensionFile(fileName);
        if(!checkFileExtension(fileExtension)){
            throw new BadRequestException("File khong hop le");
        }

        // Kiem tra size
        // Upload <= 3MB
        double size = file.getSize()/Math.pow(1024,2);
        if(size > 3){
            throw new BadRequestException("Kich thuoc file khong vuot qua 3MB");
        }
    }

    // Lay duoi file
    public String getExtensionFile(String fileName){
        int lastIndex = fileName.lastIndexOf(".");
        if(lastIndex == -1){
            return "";
        }
        return fileName.substring(lastIndex + 1);
    }

    // Kiem tra duoi file
    public boolean checkFileExtension(String fileExtension){
        List<String> extensions = new ArrayList<>(List.of("png", "jpeg", "jpg"));
        return extensions.contains(fileExtension);
    }

    // Xem file
    public byte[] readFile(String fileId) {
//        // Lấy ra đường dẫn file tương ứng với product_id
//        Path userPath = rootPath.resolve(String.valueOf(id));
//
//        // Kiểm tra xem đường dẫn file có tồn tại hay không
//        if(!Files.exists(userPath)) {
//            throw new NotFoundException("Không thể đọc file " + fileId);
//        }

        try {
            // Lấy ra đường dẫn file tương ứng với file_id
            Path file = rootPath.resolve(fileId);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()) {
                InputStream stream = resource.getInputStream();
                byte[] bytes = StreamUtils.copyToByteArray(stream);

                stream.close();
                return bytes;
            } else {
                throw new RuntimeException("Không thể đọc file " + fileId);
            }

        } catch (Exception e) {
            throw new RuntimeException("Không thể đọc file " + fileId);
        }
    }

    // Lay danh sach file
    public List<String> getFilesByProductId(Long id) {
//        // Lấy đường dẫn file tương ứng với product_id
//        Path userPath = rootPath.resolve(String.valueOf(id));
//
//        // Kiểm tra đường dẫn file có tồn tại hay không
//        // Nếu không tồn tại -> user chưa upload ảnh -> trả về danh sách rỗng
//        if (!Files.exists(userPath)) {
//            return new ArrayList<>();
//        }
//        List<File> files = List.of(userPath.toFile().listFiles());
//
//        // Tra ve ds duong dan voi tung file
//        List<String> filesPath = files
//                .stream()
//                .map(File::getName)
//                .sorted(Comparator.reverseOrder())
//                .map(file -> "/admin/product/" + id + "/images/" + file)
//                .toList();
        List<ImageEntity> imageList = imageRepository.findByProduct(id);
        List<String> filesPath = new ArrayList<>();
        imageList.forEach(img -> filesPath.add("/api/v1/product/images/" + img.getUrl()));
        return filesPath;
    }

    // Xoa file
    public void deleteFile(Long imgId) {
//        // Lấy ra đường dẫn file tương ứng với user_id
//        Path userPath = rootPath.resolve(String.valueOf(id));
//
//        // Kiểm tra folder chứa file có tồn tại hay không
//        if(!Files.exists(userPath)) {
//            throw new NotFoundException("File " + fileId + " không tồn tại");
//        }

        // Lấy ra đường dẫn file tương ứng với user_id và file_id
        ImageEntity image = imageRepository.findById(imgId).get();
        String fileId = image.getUrl();

        Path file = rootPath.resolve(fileId);

        // Kiểm tra file có tồn tại hay không
        if(!file.toFile().exists()) {
            throw new NotFoundException("File " + fileId + " không tồn tại");
        }

        // Tiến hành xóa file
        file.toFile().delete();
        imageRepository.delete(image);
    }

    // Upload nhiều ảnh
    public List<ImageEntity> uploadImageForProduct(Long id, MultipartFile[] files){
        ProductEntity product = productRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Không tồn tại sản phẩm có id = " + id);
        });
        List<ImageEntity> list = new ArrayList<>();
        if(files.length > 0){
            Arrays.stream(files).toList().forEach(img -> {
                String pathFile = uploadFile(img);
                pathFile = pathFile.replace("/api/v1/product/images/", "");
                ImageEntity image = ImageEntity.builder()
                        .url(pathFile)
                        .productEntity(product)
                        .build();
                imageRepository.save(image);
                list.add(image);
            });
        }
        return list;
    }
}
