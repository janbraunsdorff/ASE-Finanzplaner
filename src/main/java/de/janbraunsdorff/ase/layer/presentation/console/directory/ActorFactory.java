package de.janbraunsdorff.ase.layer.presentation.console.directory;

public record ActorFactory<T extends Actor>(T actor) {

    public ActorFactory<T> addBuilder(String trigger, CommandBuilder builder) {
        actor.addBuilder(trigger, builder);
        return this;
    }

    public T build() {
        return actor;
    }
}
