package org.jahom.demo;

import java.io.Serializable;
import java.util.Arrays;

/*
 *@author jahom
 *@date 2020/3/24 12:35
 */
public class ClientRequst implements Serializable {

    private static final long serialVersionUID = 5939469098469501607L;
    private String className;
    private String methodName;
    private Object[] params;
    private Class<?>[] paramsType;



    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Class<?>[] getParamsType() {
        return paramsType;
    }

    public void setParamsType(Class<?>[] paramsType) {
        this.paramsType = paramsType;
    }
}
