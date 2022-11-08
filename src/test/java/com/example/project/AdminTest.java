package com.example.project;

import com.example.project.entity.AccountEntity;
import com.example.project.entity.CartItemEntity;
import com.example.project.entity.CategoryEntity;
import com.example.project.entity.RoleEntity;
import com.example.project.repository.AccountRepository;
import com.example.project.repository.CartItemRepository;
import com.example.project.repository.CategoryRepository;
import com.example.project.repository.RoleRepository;
import com.example.project.request.CategoryRequest;
import com.example.project.service.AccountService;
import com.example.project.service.CategoryService;
import com.example.project.service.ProductService;
import com.example.project.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class AdminTest {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;
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

    @Autowired
    private CartItemRepository cartItemRepository;

    // Tạo nhóm tk mới
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

    // Lấy danh sách nhóm tk nhân viên
    @Test
    void get_role_not_user(){
        List<RoleEntity> list = roleService.getRolesExceptCustomer();
        list.forEach(el -> System.out.println(el.getName()));
    }

    // Tạo tài khoản mới
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

    // Tạo nhóm sản phẩm
    @Test
    void create_category(){
        CategoryRequest request = CategoryRequest.builder()
                .name("Discount")
                .description("Sản phẩm khuyến mãi")
                .build();
        categoryService.createCategory(request);
        if(categoryRepository.findByNameContainingIgnoreCase("Discount").isPresent()){
            System.out.println("Done");
        }
    }

    // Xóa cart_item (Đã xóa thành công lần 1 nên chạy lại báo fail)
    @Test
    void delete_cart_item(){
        CartItemEntity item = cartItemRepository.findById(9L).get();
        cartItemRepository.delete(item);
    }

    // Xóa tài khoản
    @Test
    void delete_account(){
        accountService.deleteAccount(45L);
        if(accountRepository.findById(45L).isPresent()){
            System.out.println("false");
        } else {
            System.out.println("true");
        }
    }

    // Lấy thông tin KH của đơn hàng
    @Test
    void get_customer_by_order(){
        AccountEntity account = accountService.getByOrder("DH-25");
        System.out.println(account.getId() + " - " + account.getName());
    }

    // Lấy danh sách nhóm sp
    @Test
    void get_all_category(){
        List<CategoryEntity> list = categoryService.getAllCategories();
        list.forEach(el -> System.out.println(el.getName()));
    }

    // Lấy nhóm sp theo id
    @Test
    void get_category_by_id(){
        CategoryEntity category = categoryService.getById(1L);
        System.out.println(category.getName());
    }

    // Xóa nhóm sp
    @Test
    void delete_category(){
        categoryService.deleteCategory(17L);
        if(categoryRepository.findById(17L).isPresent()){
            System.out.println("false");
        }
        else {
            System.out.println("true");
        }
    }

    // Cập nhật nhóm sp
    @Test
    void update_category(){
        CategoryRequest request = CategoryRequest.builder()
                .name("Discount")
                .description("Sản phẩm thời trang giảm giá").build();
        categoryService.updateCategory(18L , request);
        System.out.println(categoryService.getById(18L).getName() + " - " +  categoryService.getById(18L).getDescription());
    }
}
