package com.hekiraku.gemini.utils;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/4
 * 功能说明：
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.LongConverter;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ReflectionUtils;
import sun.reflect.generics.tree.Tree;

@Slf4j
public class BeanUtils {

    public static ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
    private static BeanUtilsBean beanUtilsBean;

    public BeanUtils() {
    }
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        } else {
            if (o instanceof String) {
                if (((String)o).trim().length() == 0) {
                    return true;
                }
            } else if (o instanceof Collection) {
                if (((Collection)o).size() == 0) {
                    return true;
                }
            } else if (o.getClass().isArray()) {
                if (((Object[])((Object[])o)).length == 0) {
                    return true;
                }
            } else if (o instanceof Map && ((Map)o).size() == 0) {
                return true;
            }

            return false;
        }
    }

    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    public static boolean isNumber(Object o) {
        if (o == null) {
            return false;
        } else if (o instanceof Number) {
            return true;
        } else if (o instanceof String) {
            try {
                Double.parseDouble((String)o);
                return true;
            } catch (NumberFormatException var2) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static Object populateEntity(Map<?, ?> map, Object entity) throws IllegalAccessException, InvocationTargetException {
        beanUtilsBean.populate(entity, (Map<String, ? extends Object>) map);
        return entity;
    }

    public static boolean validClass(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException var2) {
            return false;
        }
    }

    public static boolean isInherit(Class cls, Class parentClass) {
        return parentClass.isAssignableFrom(cls);
    }

    public static Object cloneBean(Object bean) {
        try {
            return beanUtilsBean.cloneBean(bean);
        } catch (Exception var2) {
            handleReflectionException(var2);
            return null;
        }
    }

    public static List<String> scanPackages(String basePackages) throws IllegalArgumentException {
        ResourcePatternResolver rl = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(rl);
        List result = new ArrayList();
        String[] arrayPackages = basePackages.split(",");

        try {
            for(int j = 0; j < arrayPackages.length; ++j) {
                String packageToScan = arrayPackages[j];
                String packagePart = packageToScan.replace('.', '/');
                String classPattern = "classpath*:/" + packagePart + "/**/*.class";
                Resource[] resources = rl.getResources(classPattern);

                for(int i = 0; i < resources.length; ++i) {
                    Resource resource = resources[i];
                    MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                    String className = metadataReader.getClassMetadata().getClassName();
                    result.add(className);
                }
            }
        } catch (Exception var14) {
            new IllegalArgumentException("scan pakcage class error,pakcages:" + basePackages);
        }

        return result;
    }

    public static void copyNotNullProperties(Object dest, Object orig) {
        if (dest == null) {
            log.error("No destination bean specified");
        } else if (orig == null) {
            log.error("No origin bean specified");
        } else {
            try {
                int i;
                String name;
                Object value;
                if (orig instanceof DynaBean) {
                    DynaProperty[] origDescriptors = ((DynaBean)orig).getDynaClass().getDynaProperties();

                    for(i = 0; i < origDescriptors.length; ++i) {
                        name = origDescriptors[i].getName();
                        if (beanUtilsBean.getPropertyUtils().isReadable(orig, name) && beanUtilsBean.getPropertyUtils().isWriteable(dest, name)) {
                            value = ((DynaBean)orig).get(name);
                            beanUtilsBean.copyProperty(dest, name, value);
                        }
                    }
                } else if (orig instanceof Map) {
                    Iterator entries = ((Map)orig).entrySet().iterator();

                    while(entries.hasNext()) {
                        Entry entry = (Entry)entries.next();
                        name = (String)entry.getKey();
                        if (beanUtilsBean.getPropertyUtils().isWriteable(dest, name)) {
                            beanUtilsBean.copyProperty(dest, name, entry.getValue());
                        }
                    }
                } else {
                    PropertyDescriptor[] origDescriptors = beanUtilsBean.getPropertyUtils().getPropertyDescriptors(orig);

                    for(i = 0; i < origDescriptors.length; ++i) {
                        name = origDescriptors[i].getName();
                        if (!"class".equals(name) && beanUtilsBean.getPropertyUtils().isReadable(orig, name) && beanUtilsBean.getPropertyUtils().isWriteable(dest, name)) {
                            try {
                                value = beanUtilsBean.getPropertyUtils().getSimpleProperty(orig, name);
                                if (value != null) {
                                    beanUtilsBean.copyProperty(dest, name, value);
                                }
                            } catch (NoSuchMethodException var6) {
                                var6.printStackTrace();
                            }
                        }
                    }
                }
            } catch (Exception var7) {
                handleReflectionException(var7);
            }

        }
    }

    public static <T> T copyProperties(Class<T> destClass, Object orig) {
        Object target = null;

        try {
            target = destClass.newInstance();
            copyProperties(target, orig);
            return (T) target;
        } catch (Exception var4) {
            handleReflectionException(var4);
            return null;
        }
    }

    public static void copyProperties(Object dest, Object orig) {
        try {
            beanUtilsBean.copyProperties(dest, orig);
        } catch (Exception var3) {
            handleReflectionException(var3);
        }

    }

    public static void copyProperty(Object bean, String name, Object value) {
        try {
            beanUtilsBean.copyProperty(bean, name, value);
        } catch (Exception var4) {
            handleReflectionException(var4);
        }

    }

    public static Map<?, ?> describe(Object bean) {
        try {
            return beanUtilsBean.describe(bean);
        } catch (Exception var2) {
            handleReflectionException(var2);
            return null;
        }
    }

    public static String[] getArrayProperty(Object bean, String name) {
        try {
            return beanUtilsBean.getArrayProperty(bean, name);
        } catch (Exception var3) {
            handleReflectionException(var3);
            return null;
        }
    }

    public static ConvertUtilsBean getConvertUtils() {
        return beanUtilsBean.getConvertUtils();
    }

    public static String getIndexedProperty(Object bean, String name, int index) {
        try {
            return beanUtilsBean.getIndexedProperty(bean, name, index);
        } catch (Exception var4) {
            handleReflectionException(var4);
            return null;
        }
    }

    public static String getIndexedProperty(Object bean, String name) {
        try {
            return beanUtilsBean.getIndexedProperty(bean, name);
        } catch (Exception var3) {
            handleReflectionException(var3);
            return null;
        }
    }

    public static String getMappedProperty(Object bean, String name, String key) {
        try {
            return beanUtilsBean.getMappedProperty(bean, name, key);
        } catch (Exception var4) {
            handleReflectionException(var4);
            return null;
        }
    }

    public static String getMappedProperty(Object bean, String name) {
        try {
            return beanUtilsBean.getMappedProperty(bean, name);
        } catch (Exception var3) {
            handleReflectionException(var3);
            return null;
        }
    }

    public static String getNestedProperty(Object bean, String name) {
        try {
            return beanUtilsBean.getNestedProperty(bean, name);
        } catch (Exception var3) {
            handleReflectionException(var3);
            return null;
        }
    }

    public static String getProperty(Object bean, String name) {
        try {
            return beanUtilsBean.getProperty(bean, name);
        } catch (Exception var3) {
            handleReflectionException(var3);
            return null;
        }
    }

    public static PropertyUtilsBean getPropertyUtils() {
        try {
            return beanUtilsBean.getPropertyUtils();
        } catch (Exception var1) {
            handleReflectionException(var1);
            return null;
        }
    }

    public static String getSimpleProperty(Object bean, String name) {
        try {
            return beanUtilsBean.getSimpleProperty(bean, name);
        } catch (Exception var3) {
            handleReflectionException(var3);
            return null;
        }
    }

    public static void populate(Object bean, Map<?, ?> properties) {
        try {
            beanUtilsBean.populate(bean, (Map<String, ? extends Object>) properties);
        } catch (Exception var3) {
            handleReflectionException(var3);
        }

    }

    public static void setProperty(Object bean, String name, Object value) {
        try {
            beanUtilsBean.setProperty(bean, name, value);
        } catch (Exception var4) {
            handleReflectionException(var4);
        }

    }

    private static void handleReflectionException(Exception e) {
        ReflectionUtils.handleReflectionException(e);
    }

    public static Object getValue(Object instance, String fieldName) throws IllegalAccessException, NoSuchFieldException {
        Field field = getField(instance.getClass(), fieldName);
        field.setAccessible(true);
        return field.get(instance);
    }

    public static Object convertByActType(String typeName, String value) {
        Object o = null;
        if (typeName.equals("int")) {
            o = Integer.parseInt(value);
        } else if (typeName.equals("short")) {
            o = Short.parseShort(value);
        } else if (typeName.equals("long")) {
            o = Long.parseLong(value);
        } else if (typeName.equals("float")) {
            o = Float.parseFloat(value);
        } else if (typeName.equals("double")) {
            o = Double.parseDouble(value);
        } else if (typeName.equals("boolean")) {
            o = Boolean.parseBoolean(value);
        } else if (typeName.equals("java.lang.String")) {
            o = value;
        } else {
            o = value;
        }

        return o;
    }

    public static Field getField(Class thisClass, String fieldName) throws NoSuchFieldException {
        if (fieldName == null) {
            throw new NoSuchFieldException("Error field !");
        } else {
            Field field = thisClass.getDeclaredField(fieldName);
            return field;
        }
    }

    public static void mergeObject(Object srcObj, Object desObj) {
        if (srcObj != null && desObj != null) {
            Field[] fs1 = srcObj.getClass().getDeclaredFields();
            Field[] fs2 = desObj.getClass().getDeclaredFields();

            for(int i = 0; i < fs1.length; ++i) {
                try {
                    fs1[i].setAccessible(true);
                    Object value = fs1[i].get(srcObj);
                    fs1[i].setAccessible(false);
                    if (null != value) {
                        fs2[i].setAccessible(true);
                        fs2[i].set(desObj, value);
                        fs2[i].setAccessible(false);
                    }
                } catch (Exception var6) {
                    log.error("mergeObject" + var6.getMessage());
                }
            }

        }
    }

    static {
        beanUtilsBean = new BeanUtilsBean(convertUtilsBean, new PropertyUtilsBean());
        convertUtilsBean.register(new DateConverter(), Date.class);
        convertUtilsBean.register(new LongConverter((Object)null), Long.class);
    }

    public static class Ren {
        private String name;
        private String email;

        public Ren() {
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Ren{");
            sb.append("name='").append(name).append('\'');
            sb.append(", email='").append(email).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}

