package de.janbraunsdorff.ase.userinterface.console.result;

import java.util.List;
import java.util.function.ToIntFunction;

public interface TypedResult<T> extends Result {

    default int getMax(ToIntFunction<? super T> mapper, List<T> result){
        if (!result.isEmpty()) {
            return Math.max(result
                    .stream()
                    .mapToInt(mapper)
                    .max().orElse(10), 10);
        }
        return 10;
    }
}
