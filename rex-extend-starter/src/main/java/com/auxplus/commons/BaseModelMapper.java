package com.auxplus.commons;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface BaseModelMapper<D, M> {
    D fromModel(M model);
    List<D> fromModelList(List<M> modelList);
    M toModel(D domain);
    List<M> toModelList(List<D> domainList);
    default <T> Map<String, Object> beanToMap(T bean, boolean ignoreNullValue) {
        return BeanUtil.beanToMap(bean, false, ignoreNullValue);
    }
    default <T> List<Map<String, Object>> beanToMap(List<T> dataList, boolean ignoreNullValue) {
        if (CollUtil.isEmpty(dataList)) {
            return new LinkedList<>();
        }
        return dataList.stream()
                .map(o -> BeanUtil.beanToMap(o, false, ignoreNullValue))
                .collect(Collectors.toList());
    }
    default <T> T mapToBean(Map<String, Object> map, Class<T> beanClazz) {
        return BeanUtil.toBeanIgnoreError(map, beanClazz);
    }
    default <T> List<T> mapToBean(List<Map<String, Object>> mapList, Class<T> beanClazz) {
        if (CollUtil.isEmpty(mapList)) {
            return new LinkedList<>();
        }
        return mapList.stream()
                .map(m -> BeanUtil.toBeanIgnoreError(m, beanClazz))
                .collect(Collectors.toList());
    }
    default Map<String, Object> mapToMap(Map<String, Object> map) {
        return map;
    }
}
