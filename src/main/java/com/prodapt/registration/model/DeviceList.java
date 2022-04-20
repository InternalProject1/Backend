package com.prodapt.registration.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "device_list")
public class DeviceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_of_device")
    private String nameOfDevice;

    @Column(name = "year_of_release")
    private String yearOfRelease;

    @Column(name = "price")
    private Double price;

    @Column(name = "model")
    private String model;

    @Column(name = "file_name")
    private String filename;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "image_of_the_device")
    @Lob
    private byte[] imageOfTheDevice;

//    @Column(name = "image_of_the_device")
//    private String imageOfTheDevice;

    @Column(name = "memory")
    private String memory;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DeviceList that = (DeviceList) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
