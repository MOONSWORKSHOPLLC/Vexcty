package com.moonsworkshop.vexcty.report;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReportState {

    WAITING("Pending"),
    DONE("Done");

    private String name;

}