package de.janbraunsdorff.ase.layer.presentation.web.controller.account;

import de.janbraunsdorff.ase.layer.domain.account.AccountDetailDTO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ResponseAccountOverview {
    private final String name;
    private final String acronym;
    private final String value;
    private final String last7;
    private final String last30;
    private final String max;
    private final String lastPosting;
    private final List<ResponseAccountInterfaceData> course;

    public ResponseAccountOverview(String name, String acronym, String value, String last7, String last30, String max, String lastPosting, List<ResponseAccountInterfaceData> course) {
        this.name = name;
        this.acronym = acronym;
        this.value = value;
        this.last7 = last7;
        this.last30 = last30;
        this.max = max;
        this.lastPosting = lastPosting;
        this.course = course;
    }

    public ResponseAccountOverview(AccountDetailDTO dto){
        this.name = dto.name();
        this.acronym = dto.acronym();
        this.value = dto.value();
        this.last7 = dto.last7();
        this.last30 = dto.last30();
        this.max = dto.max();
        this.lastPosting = dto.lastPosting();
        this.course = dto.course().stream().map(a -> new ResponseAccountInterfaceData("", a)).collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public String getValue() {
        return value;
    }

    public String getLast7() {
        return last7;
    }

    public String getLast30() {
        return last30;
    }

    public String getMax() {
        return max;
    }

    public String getLastPosting() {
        return lastPosting;
    }

    public List<ResponseAccountInterfaceData> getCourse() {
        return course;
    }
}
