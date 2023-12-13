package miu.edu.restapipractice.model;

import jakarta.persistence.*;
import lombok.Data;
import miu.edu.restapipractice.config.Constant;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String photos;


    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;

        return "/" + Constant.CUSTOMER_PHOTO_PATH + "/" + id + "/" + photos;
    }
}
