package org.roysez.app.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Represents an Article entity
 * Used { @Code Lombok }for getter, setter and etc.
 *
 * Created by roysez on 12.05.2017.
 * 23:17
 * Package : org.roysez.app.model
 */

@Entity
@Table(name="APP_ARTICLE")
public @Data class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    @Lob
    private String content;

    @Column(name = "DATE")
    private Date date;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

}
