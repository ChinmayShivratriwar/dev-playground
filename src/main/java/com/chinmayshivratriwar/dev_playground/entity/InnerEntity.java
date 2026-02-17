//package com.chinmayshivratriwar.dev_playground.entity;
//
//import jakarta.persistence.*;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "inner_entity")
//public class InnerEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    /* lots of normal/simple fields to make the entity big */
//    private String name;
//    private String value;
//    private String description;
//    private String type;
//    private String status;
//    private Integer count;
//    private Boolean activeFlag;
//    private BigDecimal amount;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
//    private String metadataJson;
//    private String comment;
//    private String extraField1;
//    private String extraField2;
//    private String extraField3;
//    private String extraField4;
//    private String extraField5;
//    private String extraField6;
//    private String extraField7;
//    private String extraField8;
//    private String extraField9;
//    private String extraField10;
//
//    /* reference used when this InnerEntity belongs to the parent's list */
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "nested_list_id")
//    public NestedEntity parentList;
//
//    /* reference used when this InnerEntity belongs to the parent's map */
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "nested_map_id")
//    public NestedEntity parentMap;
//
//    /* key to use in the parent's map (nullable if this instance is only in the list) */
//    @Column(name = "map_key")
//    public String mapKey;
//
//    /* JPA requires a no-arg constructor */
//    public InnerEntity() {}
//
//    /* getters/setters omitted for brevity; JPA field access will work with these annotated fields */
//}