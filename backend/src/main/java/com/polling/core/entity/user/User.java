package com.polling.core.entity.user;

import com.querydsl.core.annotations.QueryEntity;
import com.polling.core.entity.common.BaseTimeEntity;
import com.polling.core.entity.user.status.OAuthType;
import com.polling.core.entity.user.status.UserRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_USER")
@Entity
@QueryEntity
public class User extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String phoneNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column
    @Enumerated(EnumType.STRING)
    private OAuthType oauthType;

    @Column
    private Long oauthId;

    @Builder
    public User(String name, String email, String password, String phoneNumber, UserRole userRole/*, OAuthType oauthType, Long oauthId*/){
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
//        this.oauthType = oauthType;
//        this.oauthId = oauthId;
    }

    public void nameUpdate(String name){
        if(name != null){
            this.name = name;
        }
    }

    public void emailUpdate(String email){
        if(email != null){
            this.email = email;
        }
    }

    public void passwordUpdate(String password){
        if(password != null){
            this.password = password;
        }
    }

    public void phoneNumberUpdate(String phoneNumber){
        if(phoneNumber != null){
            this.phoneNumber = phoneNumber;
        }
    }

    public void userRoleUpdate(UserRole userRole){
        if(userRole != null){
            this.userRole = userRole;
        }

    }

}
