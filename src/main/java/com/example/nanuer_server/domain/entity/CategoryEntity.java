package com.example.nanuer_server.domain.entity;

import com.example.nanuer_server.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "CategoryEntity", indexes = {
        @Index(name = "idx_categoryentity", columnList = "category_entity, post_entity")
})
public class CategoryEntity extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_entity")
    private int categoryId;

    @Column(nullable = false)
    private String categoryName;

    @OneToOne
    @JoinColumn(name="post_entity")
    private PostEntity postEntity;
}
