package flow.work.extension.entity;

import java.util.Arrays;
import java.util.Optional;

public enum FixedExtensionType {
    BAT, CMD, COM, CPL, EXE, SCR, JS;

    public static Optional<FixedExtensionType> from(String extension) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(extension))
                .findFirst();
    }
}
