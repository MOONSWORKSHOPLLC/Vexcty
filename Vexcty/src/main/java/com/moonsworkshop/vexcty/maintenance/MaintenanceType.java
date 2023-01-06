package com.moonsworkshop.vexcty.maintenance;

public enum MaintenanceType {

    ONLINE("Online"),
    OFFLINE("Under Maintenance");

    private String name;

    MaintenanceType(String name) {
        this.name = name;
    }

}