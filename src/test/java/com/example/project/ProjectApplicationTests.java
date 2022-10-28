package com.example.project;

import com.example.project.entity.AccountEntity;
import com.example.project.entity.RoleEntity;
import com.example.project.repository.AccountRepository;
import com.example.project.repository.CategoryRepository;
import com.example.project.repository.RoleRepository;
import com.example.project.request.CategoryRequest;
import com.example.project.service.CategoryService;
import com.example.project.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class ProjectApplicationTests {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    void save_role() {
        RoleEntity role6 = RoleEntity.builder()
                .name("CUSTOMER")
                .build();
        RoleEntity role = RoleEntity.builder()
                .name("ADMIN")
                .build();
        RoleEntity role1 = RoleEntity.builder()
                .name("ORDER")
                .build();
        RoleEntity role2 = RoleEntity.builder()
                .name("INVENTORY")
                .build();
        RoleEntity role3 = RoleEntity.builder()
                .name("CUSTOMER_CARE")
                .build();
        roleRepository.saveAll(List.of(role6, role, role1, role2, role3));
    }


    @Test
    void save_account(){
        AccountEntity account = AccountEntity.builder()
                .name("Ngọc Mai")
                .email("macie@gmail.com")
                .phone("0933001996")
                .password(encoder.encode("1111"))
                .build();
        AccountEntity account1 = AccountEntity.builder()
                .name("Minh Huy")
                .email("huy@gmail.com")
                .phone("0908970986")
                .password(encoder.encode("1111"))
                .build();
        AccountEntity account2 = AccountEntity.builder()
                .name("Anh Tuấn")
                .email("tuan@gmail.com")
                .phone("0909780976")
                .password(encoder.encode("1111"))
                .build();
        AccountEntity account3 = AccountEntity.builder()
                .name("Công Minh")
                .email("minh@gmail.com")
                .phone("0399567908")
                .password(encoder.encode("1111"))
                .build();
        AccountEntity account4 = AccountEntity.builder()
                .name("Vũ Quang")
                .email("quangvu@gmail.com")
                .phone("0905679080")
                .password(encoder.encode("1111"))
                .build();
        accountRepository.saveAll(List.of(account, account1, account2, account3, account4));
    }

    @Test
    void create_category(){
        CategoryRequest request = CategoryRequest.builder()
                .name("Kid")
                .description("Thời trang trẻ em")
                .build();
        categoryService.createCategory(request);
        if(categoryRepository.findByNameContainingIgnoreCase("Kid").isPresent()){
            System.out.println("Done");
        }
    }

//    @Test
//    void create_product(){
//        ProductCreateRequest request = ProductCreateRequest.builder()
//                .name("Áo T-Shirt nữ cổ tròn tay bồng")
//                .content("Áo T-Shirt  nữ cổ tròn tay bồng\n" +
//                        "\n" +
//                        "- Áo T-shirt chất liệu cotton nhẹ, co dãn 4 chiều.\n" +
//                        "- Thiết kế phom basic dễ mặc, thoải mái trong mọi hoạt động.\n" +
//                        "- Kiểu dáng và màu sắc trẻ trung, hiện đại phù hợp với cô nàng năng động.")
//                .description("Áo T-Shirt cổ tim dáng ngắn")
//                .categoryId(List.of(2L, 4L)).build();
//        productService.createProduct(request);
//    }
}
