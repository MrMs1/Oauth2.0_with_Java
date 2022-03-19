package oauth.demoproject.infra.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_subdata1")
@IdClass(value = UserSubdataKey.class)
@Data
@Builder
public class UserSubdata_1_Entity {

    @Id
    @Column(name = "user_data_id")
    private int userDataId;

    @Id
    @Column(name = "user_subdata_id")
    private int userSubdataId;
}
