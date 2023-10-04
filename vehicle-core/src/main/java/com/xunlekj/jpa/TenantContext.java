package com.xunlekj.jpa;

public class TenantContext {
    private static final ThreadLocal<String> tenantContext = new ThreadLocal<>();

    public static void setCurrentTenant(String tenantId) {
        tenantContext.set(tenantId);
    }

    public static String getCurrentTenant() {
        return tenantContext.get();
    }

    public static void clear() {
        tenantContext.remove();
    }
}
