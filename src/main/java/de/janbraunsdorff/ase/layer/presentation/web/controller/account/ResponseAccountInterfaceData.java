package de.janbraunsdorff.ase.layer.presentation.web.controller.account;

public class ResponseAccountInterfaceData {
    private final String key;
    private final int value;

    public ResponseAccountInterfaceData(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }
}
