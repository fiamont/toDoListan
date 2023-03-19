package se.sofiamontgomery.todolistan.post;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.sofiamontgomery.todolistan.user.UserModel;

@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "postid")
    private Long postId;
    @NotEmpty
    private String description;

    private boolean done;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userid", nullable = false)
    private UserModel userModel;

    public PostModel(String description, boolean done) {
        this.description = description;
        this.done = done;
    }
}
