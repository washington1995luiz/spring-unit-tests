package br.com.washington.unittests.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@SQLDelete(sql = "UPDATE tb_user SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false, name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public User(final String name, final String username, final String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public User updateName(final String name) {
        this.name = name;
        this.updatedAt = LocalDateTime.now();
        return this;
    }
    public User updateUsername(final String username){
        this.username = username;
        this.updatedAt = LocalDateTime.now();
        return this;
    }
    public User updatePassword(final String password){
        this.password = password;
        this.updatedAt = LocalDateTime.now();
        return this;
    }
    public void setDeletedAt(){
        this.deletedAt = LocalDateTime.now();
    }
}
