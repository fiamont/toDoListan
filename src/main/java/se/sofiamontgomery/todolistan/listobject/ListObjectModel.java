package se.sofiamontgomery.todolistan.listobject;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "listobjectmodel")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListObjectModel {
    private Long userId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long listObjectId;
    private String listObjectName;

    public ListObjectModel(String listObjectName) {
        this.listObjectName = listObjectName;
    }
}
