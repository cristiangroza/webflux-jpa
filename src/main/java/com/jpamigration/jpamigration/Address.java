package com.jpamigration.jpamigration;

import org.springframework.data.relational.core.mapping.Table;

@Table("address")
public class Address {
    private Long id;
    private String content;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
