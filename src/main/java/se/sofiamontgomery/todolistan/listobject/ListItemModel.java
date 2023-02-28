package se.sofiamontgomery.todolistan.listobject;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty
    private String listItemName;

    private boolean done;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userid", nullable = false)
    private UserModel userModel;

    public ListItemModel(String listItemName, boolean done) {
        this.listItemName = listItemName;
        this.done = done;
    }
}
