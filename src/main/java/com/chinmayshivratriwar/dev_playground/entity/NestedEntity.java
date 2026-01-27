package com.chinmayshivratriwar.dev_playground.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "nested_entity")
public class NestedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* basic fields */
    private String externalId;
    private String timestamp;
    private String type;
    private Boolean isActive;

    /* many extra fields to create a large parent entity */
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    private String field6;
    private String field7;
    private String field8;
    private String field9;
    private String field10;
    private String field11;
    private String field12;
    private String field13;
    private String field14;
    private String field15;
    private Long numeric1;
    private Long numeric2;
    private Double metric1;
    private Double metric2;
    private String longText;

    /* list of child InnerEntity objects */
    @OneToMany(mappedBy = "parentList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InnerEntity> innerEntities = new ArrayList<>();

    /* map of child InnerEntity objects keyed by InnerEntity.mapKey */
    @OneToMany(mappedBy = "parentMap", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "mapKey")
    private Map<String, InnerEntity> innerEntityMap = new HashMap<>();

    /* simple attribute map (extra bulk data) */
    @ElementCollection
    @CollectionTable(name = "nested_attributes", joinColumns = @JoinColumn(name = "nested_id"))
    @MapKeyColumn(name = "attr_key")
    @Column(name = "attr_value", length = 2000)
    private Map<String, String> attributes = new HashMap<>();

    public NestedEntity() {}

    /* helper convenience methods to manage both relations */
    public void addToList(InnerEntity child) {
        child.parentList = this;
        innerEntities.add(child);
    }

    public void removeFromList(InnerEntity child) {
        innerEntities.remove(child);
        child.parentList = null;
    }

    public void putToMap(String key, InnerEntity child) {
        child.mapKey = key;
        child.parentMap = this;
        innerEntityMap.put(key, child);
    }

    public void removeFromMap(String key) {
        InnerEntity removed = innerEntityMap.remove(key);
        if (removed != null) {
            removed.parentMap = null;
            removed.mapKey = null;
        }
    }

    /* getters/setters omitted for brevity */
}