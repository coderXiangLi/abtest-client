package com.opensource.leo.util;


import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class AnnotationInitial {

    private AnnotationInitial() {
    }

    /**
     * Create a instance of by a class which has the specific annotation
     *
     * @param tClass     class of instance which wants to be created
     * @param annotation annotation that class must has
     * @return
     */
    public static <T> List<T> create(Class<T> tClass, Class<? extends Annotation> annotation, String packageName) {
        List<T> obj = new ArrayList<T>();
        ClassPath classPath;
        try {
            classPath = ClassPath.from(tClass.getClassLoader());
        } catch (IOException e) {
            return obj;
        }
        ImmutableSet<ClassPath.ClassInfo> immutableSet = classPath.getTopLevelClasses(packageName);
        for (ClassPath.ClassInfo classInfo : immutableSet) {
            Class clazz = classInfo.load();
            if (clazz != null) {
                Annotation controller = clazz.getAnnotation(annotation);
                if (controller != null) {
                    Object reward = null;
                    try {
                        reward = clazz.newInstance();
                    } catch (InstantiationException e) {
                    } catch (IllegalAccessException e) {
                    }
                    if (reward != null && tClass.isAssignableFrom(clazz)) {
                        obj.add((T) reward);
                    }
                }
            }
        }
        return obj;
    }
}
