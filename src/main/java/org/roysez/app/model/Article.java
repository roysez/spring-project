package org.roysez.app.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.roysez.app.util.JsonDateSerializer;
import org.roysez.app.util.JsonUserSerializer;

import javax.persistence.*;
import java.util.Date;

/**
 * Represents an Article entity
 * Used { @Code Lombok }for getter, setter and etc.
 *
 * @author roysez
 */

@Entity
@Table(name = "APP_ARTICLE",
indexes = {
        @Index(columnList = "TITLE",name = "TITLE_INDEX")
})
public
@Data
class Article implements Comparable<Article> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    @Lob
    private String content;

    @Column(name = "DATE")
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonSerialize(using = JsonUserSerializer.class)
    private User user;

    public int compareTo(Article second) {
        return second.date.compareTo(this.date);
    }
}
