package oauth.demoproject.infra.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import oauth.demoproject.domain.dmainobject.UserDataDomain;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_data")
@Data
@Builder
public class UserDataEntity {

    @Id
    @Column(name = "user_data_id")
    private int userDataId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_subdata_type")
    private String userSubdataType;

    public static UserDataEntity build(UserDataDomain domain) {
        return UserDataEntity.builder()
                .userDataId(domain.getUserDataId())
                .userId(domain.getUserId())
                .userSubdataType(domain.getUserSubdataType())
                .build();
    }

    public static UserDataDomain toDomain(UserDataEntity entity) {
        return UserDataDomain.builder()
                .userDataId(entity.getUserDataId())
                .userId(entity.getUserId())
                .userSubdataType(entity.getUserSubdataType())
                .build();
    }
}
