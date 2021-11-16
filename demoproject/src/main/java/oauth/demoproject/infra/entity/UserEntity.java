package oauth.demoproject.infra.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import oauth.demoproject.domain.dmainobject.UserDomain;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Data
@Builder
public class UserEntity {

    @Id
    @Column(name = "user_id")
    private int userId;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    public static UserEntity build(UserDomain domain) {
        return UserEntity.builder()
                .userId(domain.getUserId())
                .name(domain.getName())
                .age(domain.getAge())
                .build();
    }

    public static UserDomain toDomain(UserEntity entity) {
        return UserDomain.builder()
                .userId(entity.getUserId())
                .name(entity.getName())
                .age(entity.getAge())
                .build();
    }
}
