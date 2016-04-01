package com.opensource.leo.hash;

import com.opensource.leo.util.AnnotationInitial;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashAlgoMapper {
    private static final Map<String, HashAlgo> mapping = new HashMap<String, HashAlgo>();

    private static MD5 defaultAlgo = new MD5();

    static {
        mapping.put(defaultAlgo.name(), defaultAlgo);
        // other algo with HashPlugin
        List<HashAlgo> hashAlgos = AnnotationInitial.create(HashAlgo.class, HashPlugin.class, "com.opensourse.leo");
        for (HashAlgo algo : hashAlgos) {
            mapping.put(algo.name(), algo);
        }
    }

    public static HashAlgo getHashAlg(String name) {
        return mapping.get(name);
    }
}
