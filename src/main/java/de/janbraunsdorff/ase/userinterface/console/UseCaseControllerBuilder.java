package de.janbraunsdorff.ase.userinterface.console;


public class UseCaseControllerBuilder {
    private final UseCaseController controller;

    public UseCaseControllerBuilder(){
        this.controller = new UseCaseController();
    }

    public UseCaseControllerBuilder addUseCase(String command, Distributor distributor){
        this.controller.addUseCase(command, distributor);
        return this;
    }

    public UseCaseController build(){
        return this.controller;
    }
}
