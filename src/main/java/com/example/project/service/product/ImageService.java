package com.example.project.service.product;

import com.example.project.entity.product.ImageEntity;
import com.example.project.entity.product.ProductEntity;
import com.example.project.exception.BadRequestException;
import com.example.project.exception.NotFoundException;
import com.example.project.repository.product.ImageRepository;
import com.example.project.repository.product.ProductRepository;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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

    private MinioClient minioClient;

    public ImageService() {
        minioClient = MinioClient.builder()
                        .endpoint("https://assets.miinho.click")
                        .credentials("protripadmin", "protrippass")
                        .build();
    }

    // Lưu ảnh vào DB
    public ImageEntity saveToDB(ImageEntity image){
        return imageRepository.save(image);
    }


    // Upload file
    public String uploadFile(MultipartFile file) {

        validateFile(file);

        String fileName = file.getOriginalFilename();
        // Tao fileId
        String fileId = String.valueOf(System.currentTimeMillis());
        // todo
        //file.transferTo(serverFile);
        try {
            InputStream targetStream = new BufferedInputStream(file.getInputStream());

            minioClient.putObject(
                    PutObjectArgs.builder().bucket("images").object(fileId +  getExtensionFile(fileName))
                            .stream(targetStream, targetStream.available(), -1)
                            .build());

            return "https://assets.miinho.click" + "/images/" + fileId + getExtensionFile(fileName);
        } catch (Exception e){
            throw new BadRequestException("Không thể tải file");
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
        return fileName.substring(lastIndex);
    }

    // Kiem tra duoi file
    public boolean checkFileExtension(String fileExtension){
        List<String> extensions = new ArrayList<>(List.of(".png", ".jpeg", ".jpg"));
        return extensions.contains(fileExtension);
    }


    // Xoa file
    public void deleteFile(Long imgId) {
        // Lấy ra đường dẫn file tương ứng với user_id và file_id
        ImageEntity image = imageRepository.findById(imgId).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy ảnh tương ứng");
        });
        String url = image.getUrl();
        String fileName = url.replace("https://assets.miinho.click/images/", "");

        try {
            // Tiến hành xóa file
            minioClient.removeObject(
                    RemoveObjectArgs.builder().bucket("images").object(fileName).build());
            imageRepository.delete(image);
        } catch (Exception e) {
            throw new BadRequestException("Đã có lỗi xảy ra");
        }
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
