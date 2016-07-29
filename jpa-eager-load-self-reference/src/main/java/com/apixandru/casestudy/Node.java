package com.apixandru.casestudy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * @author Alexandru-Constantin Bledea
 * @since Jul 29, 2016
 */
@Entity
public class Node {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Node> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public boolean canReach(Node that) {
        if (this.getId().equals(that.getId())) {
            return true;
        }
        for (Node other : children) {
            if (other.canReach(that)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return name + " (" + children + ")";
    }
}
