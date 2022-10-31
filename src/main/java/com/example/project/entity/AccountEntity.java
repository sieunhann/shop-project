package com.example.project.entity;

import com.example.project.dto.AccountDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.*;

// Lấy danh sách nhân viên (Account) và phân trang
@NamedNativeQuery(
        name = "getAccounts",
        query = "SELECT a.id, a.name, a.email, a.phone, a.address, a.city, " +
                "json_arrayagg(r.name) AS roles, a.created_at, a.updated_at\n" +
                "FROM account a LEFT JOIN account_role ar ON a.id = ar.account_id\n" +
                "LEFT JOIN role r ON r.id = ar.role_id WHERE r.id != 1 " +
                "AND (?1 IS NULL OR a.name LIKE CONCAT('%', ?1 ,'%')) \n" +
                "GROUP BY a.id ORDER BY a.updated_at DESC",
        resultSetMapping = "Accounts"
)
@NamedNativeQuery(
        name = "getAccounts.count",
        query = "SELECT COUNT(a.id) AS count FROM account a " +
                "LEFT JOIN account_role ar ON a.id = ar.account_id\n" +
                "LEFT JOIN role r ON r.id = ar.role_id WHERE r.id != 1 " +
                "AND (?1 IS NULL OR a.name LIKE CONCAT('%', ?1 ,'%')) \n" +
                "GROUP BY a.id",
        resultSetMapping = "countAccounts"
)

@SqlResultSetMapping(
        name = "Accounts",
        classes = @ConstructorResult(
                targetClass = AccountDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name ="email", type = String.class),
                        @ColumnResult(name ="phone", type = String.class),
                        @ColumnResult(name = "address", type = String.class),
                        @ColumnResult(name = "city", type = String.class),
                        @ColumnResult(name ="roles", type = String.class),
                        @ColumnResult(name ="created_at", type = LocalDateTime.class),
                        @ColumnResult(name ="updated_at", type = LocalDateTime.class)
                }
        )
)
@SqlResultSetMapping(
        name = "countAccounts",
        classes = @ConstructorResult(
                targetClass = java.lang.Long.class,
                columns = {
                        @ColumnResult(name = "count", type = Long.class)
                }
        )
)

// Lấy danh sách tất cả khách hàng (Customer) và phân trang

@NamedNativeQuery(
        name = "getCustomers",
        query = "SELECT a.id, a.name, a.email, a.phone, a.address, a.city, " +
                "json_arrayagg(r.name) AS roles, a.created_at, a.updated_at\n" +
                "FROM account a LEFT JOIN account_role ar ON a.id = ar.account_id\n" +
                "LEFT JOIN role r ON r.id = ar.role_id WHERE r.id = 1 " +
                "AND (?1 IS NULL OR a.phone LIKE CONCAT('%', ?1 ,'%'))\n" +
                "GROUP BY a.id ORDER BY a.updated_at DESC",
        resultSetMapping = "Customers"
)
@NamedNativeQuery(
        name = "getCustomers.count",
        query = "SELECT count(a.id) AS count FROM account a LEFT JOIN account_role ar ON a.id = ar.account_id\n" +
        "LEFT JOIN role r ON r.id = ar.role_id WHERE r.id = 1\n" +
        "AND (?1 IS NULL OR a.phone LIKE CONCAT('%', ?1 ,'%')) GROUP BY a.id",
        resultSetMapping = "countCustomers"
)

@SqlResultSetMapping(
        name = "Customers",
        classes = @ConstructorResult(
                targetClass = AccountDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name ="email", type = String.class),
                        @ColumnResult(name ="phone", type = String.class),
                        @ColumnResult(name = "address", type = String.class),
                        @ColumnResult(name = "city", type = String.class),
                        @ColumnResult(name ="roles", type = String.class),
                        @ColumnResult(name ="created_at", type = LocalDateTime.class),
                        @ColumnResult(name ="updated_at", type = LocalDateTime.class)
                }
        )
)
@SqlResultSetMapping(
        name = "countCustomers",
        classes = @ConstructorResult(
                targetClass = java.lang.Long.class,
                columns = {
                        @ColumnResult(name = "count", type = Long.class)
                }
        )
)

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "account")
public class AccountEntity extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Z][\\w]+@([\\w]+\\.[\\w]{2,}|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$")
    private String email;

    @Column(name = "phone", unique = true)
    @Pattern(regexp = "^(0|\\+84)[1-9][0-9]{8}$")
    private String phone;

    @Column(name = "password")
    @Length(min = 3)
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "enabled")
    private Boolean enabled = false;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_role",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roleEntities = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "accountEntity", orphanRemoval = true)
    private List<OrderEntity> orderEntities = new ArrayList<>();

    @OneToMany(mappedBy = "accountEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TokenEntity> tokenEntities = new LinkedHashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        this.roleEntities.forEach(r -> roles.add(new SimpleGrantedAuthority("ROLE_" + r.getName()))); // ROLE_ADMIN, ROLE_USER
        return roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public AccountEntity(String name, String email, String phone, String password, String address, String city, List<RoleEntity> roleEntities) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.city = city;
        this.roleEntities = roleEntities;
    }
}