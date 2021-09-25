package com.suraj;

import com.google.auto.service.AutoService;

@AutoService(MyInterface.class)
public class ImplementationTwo implements MyInterface {
    @Override
    public String className() {
        return getClass().getCanonicalName();
    }
}
