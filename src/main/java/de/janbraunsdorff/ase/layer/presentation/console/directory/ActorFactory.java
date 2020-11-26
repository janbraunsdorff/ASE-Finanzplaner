package de.janbraunsdorff.ase.layer.presentation.console.directory;

public class ActorFactory <T extends Actor> {
    private final T actor;

    public ActorFactory(T actor) {
        this.actor = actor;
    }

    public ActorFactory<T> addBuilder(String trigger, CommandBuilder builder){
        actor.addBuilder(trigger, builder);
        return this;
    }

    public T build(){
        return actor;
    }
}
