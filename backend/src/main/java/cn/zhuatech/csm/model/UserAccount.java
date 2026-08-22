/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.csm.model;
import jakarta.persistence.*;
@Entity @Table(name="csm_user")
public class UserAccount extends BaseEntity {
    public enum Role { ADMIN, CSM_DIRECTOR, SUCCESS_MANAGER, QUALITY }
    @Column(nullable=false,unique=true,length=32) private String username; @Column(nullable=false) private String password;
    @Column(nullable=false,length=50) private String fullName; @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Role role;
    @Column(name="customer_portfolio_code",length=32) private String customerPortfolioCode; @Column(nullable=false) private boolean enabled=true;
    protected UserAccount(){}
    public UserAccount(String username,String password,String fullName,Role role,String customerPortfolioCode){this.username=username;this.password=password;this.fullName=fullName;this.role=role;this.customerPortfolioCode=customerPortfolioCode;}
    public String getUsername(){return username;} public String getPassword(){return password;} public String getFullName(){return fullName;} public Role getRole(){return role;} public String getCustomerPortfolioCode(){return customerPortfolioCode;} public boolean isEnabled(){return enabled;}
}
