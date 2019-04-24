package com.hekiraku.gemini.aop.swagger;

import io.swagger.models.properties.ArrayProperty;
import io.swagger.models.properties.RefProperty;
import io.swagger.models.refs.GenericRef;
import io.swagger.models.refs.RefType;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/23
 * 功能说明：
 */
public class ArrayRefProperty extends ArrayProperty {
    private GenericRef genericRef;

    public String get$ref() {
        return genericRef.getRef();
    }

    public void set$ref(String ref) {
        this.genericRef = new GenericRef(RefType.DEFINITION, ref);

        // $ref
        RefProperty items = new RefProperty();
        items.setType(ref);
        items.set$ref(ref);
        this.items(items);
    }
}
