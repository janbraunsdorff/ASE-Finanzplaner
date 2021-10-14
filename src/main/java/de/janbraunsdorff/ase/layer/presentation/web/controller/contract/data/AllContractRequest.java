package de.janbraunsdorff.ase.layer.presentation.web.controller.contract.data;

public class AllContractRequest {
    public boolean withInactive;

    public AllContractRequest(boolean withInActives) {
        this.withInactive = withInActives;
    }

    public AllContractRequest() {
    }

    public void setWithInactive(boolean withInactive) {
        this.withInactive = withInactive;
    }

    public boolean isWithInactive() {
        return withInactive;
    }
}
