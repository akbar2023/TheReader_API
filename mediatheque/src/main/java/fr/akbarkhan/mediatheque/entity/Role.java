package fr.akbarkhan.mediatheque.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String role;

/* ***** not necessary *****
    @ManyToMany(mappedBy = "roles")
    private Collection<MyUser> users;
*/


    public Role(String role) {
        this.role = role;
    }

    public Role() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setName(String role) {
        this.role = role;
    }

}
