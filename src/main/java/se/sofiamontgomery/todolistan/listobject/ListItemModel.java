package se.sofiamontgomery.todolistan.listobject;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.sofiamontgomery.todolistan.user.UserModel;

@Entity
@Table(name = "listitems")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long listItemId;
    private String listItemName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userid", nullable = false)
    private UserModel userModel;

    public ListItemModel(String listItemName, UserModel userModel) {
        this.listItemName = listItemName;
        this.userModel = userModel;
    }
}
