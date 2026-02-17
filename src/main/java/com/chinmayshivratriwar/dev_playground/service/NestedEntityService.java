//package com.chinmayshivratriwar.dev_playground.service;
//
//import com.chinmayshivratriwar.dev_playground.entity.InnerEntity;
//import com.chinmayshivratriwar.dev_playground.entity.NestedEntity;
//import com.chinmayshivratriwar.dev_playground.repository.NestedEntityRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.lang.reflect.Field;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.Map;
//import java.util.UUID;
//import java.util.HashMap;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class NestedEntityService {
//
//    private final NestedEntityRepository nestedRepo;
//
//
//    @Transactional
//    public NestedEntity save(NestedEntity nested) {
//        return nestedRepo.save(nested);
//    }
//
//    @Transactional
//    public List<NestedEntity> saveAll(List<NestedEntity> list) {
//        return nestedRepo.saveAll(list);
//    }
//
//    /**
//     * Create a large NestedEntitiy with many children and attributes and persist it.
//     * Uses reflection to populate several private fields on the entities so the
//     * generated object is "big".
//     *
//     * @param listSize number of children in the list relation
//     * @param mapSize number of children in the map relation
//     * @param attributesCount number of key/value entries in the element-collection map
//     * @return the persisted NestedEntitiy
//     */
//    @Transactional
//    public NestedEntity createAndSaveLargeNested(int listSize, int mapSize, int attributesCount) {
//        try {
//            NestedEntity nested = new NestedEntity();
//
//            // Populate a few private fields on NestedEntitiy via reflection
//            setFieldIfPresent(nested, "externalId", "ext-" + UUID.randomUUID());
//            setFieldIfPresent(nested, "timestamp", LocalDateTime.now().toString());
//            setFieldIfPresent(nested, "type", "bulk-test");
//            setFieldIfPresent(nested, "isActive", Boolean.TRUE);
//            setFieldIfPresent(nested, "field1", "f1-" + UUID.randomUUID());
//            setFieldIfPresent(nested, "numeric1", 12345L);
//            setFieldIfPresent(nested, "metric1", 12.345);
//
//            // fill attributes map
//            Object attrsObj = getFieldValue(nested, "attributes");
//            if (attrsObj instanceof Map) {
//                @SuppressWarnings("unchecked")
//                Map<String, String> attrs = (Map<String, String>) attrsObj;
//                for (int i = 0; i < attributesCount; i++) {
//                    attrs.put("attr_" + i, "value_" + UUID.randomUUID());
//                }
//            }
//
//            // create list children
//            for (int i = 0; i < listSize; i++) {
//                InnerEntity child = new InnerEntity();
//                setInnerCommonFields(child, "list-" + i);
//                nested.addToList(child);
//            }
//
//            // create map children
//            for (int i = 0; i < mapSize; i++) {
//                String key = "mapKey-" + i;
//                InnerEntity child = new InnerEntity();
//                setInnerCommonFields(child, "map-" + i);
//                nested.putToMap(key, child);
//            }
//
//            // persist in one call (cascades should save children)
//            return nestedRepo.save(nested);
//
//        } catch (Exception ex) {
//            throw new RuntimeException("Failed to create and save large NestedEntitiy", ex);
//        }
//    }
//
//    // helper to set some InnerEntity private fields via reflection
//    private void setInnerCommonFields(InnerEntity child, String seed) throws Exception {
//        setFieldIfPresent(child, "name", "name-" + seed);
//        setFieldIfPresent(child, "value", "value-" + seed);
//        setFieldIfPresent(child, "description", "desc-" + seed);
//        setFieldIfPresent(child, "status", "ok");
//        setFieldIfPresent(child, "count", 1);
//        setFieldIfPresent(child, "activeFlag", Boolean.TRUE);
//        setFieldIfPresent(child, "amount", new BigDecimal("99.99"));
//        setFieldIfPresent(child, "createdAt", LocalDateTime.now());
//        setFieldIfPresent(child, "metadataJson", "{\"seed\":\"" + seed + "\"}");
//        // extra fields
//        setFieldIfPresent(child, "extraField1", "ex1-" + seed);
//        setFieldIfPresent(child, "extraField2", "ex2-" + seed);
//        setFieldIfPresent(child, "extraField3", "ex3-" + seed);
//    }
//
//    // reflection helpers
//    private void setFieldIfPresent(Object target, String fieldName, Object value) {
//        try {
//            Field f = findField(target.getClass(), fieldName);
//            if (f != null) {
//                f.setAccessible(true);
//                f.set(target, value);
//            }
//        } catch (Exception ignored) {
//        }
//    }
//
//    private Object getFieldValue(Object target, String fieldName) {
//        try {
//            Field f = findField(target.getClass(), fieldName);
//            if (f != null) {
//                f.setAccessible(true);
//                return f.get(target);
//            }
//        } catch (Exception ignored) {
//        }
//        return null;
//    }
//
//    private Field findField(Class<?> cls, String name) {
//        Class<?> cur = cls;
//        while (cur != null && cur != Object.class) {
//            try {
//                return cur.getDeclaredField(name);
//            } catch (NoSuchFieldException e) {
//                cur = cur.getSuperclass();
//            }
//        }
//        return null;
//    }
//}
