package bridge.model.domains;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.util.Lists.newArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

class RemainingStepsTest {

    @DisplayName("잘못된 인자값으로 다리 생성 시 예외 발생(null, 범위 밖 길이, 잘못된 칸 정보)")
    @ParameterizedTest
    @MethodSource("generateIllegalArgumentSteam")
    void createBridgeWithIllegalArgument(List<String> bridge) {
        assertThatThrownBy(() -> new RemainingSteps(bridge))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<List<String>> generateIllegalArgumentSteam() {
        List<List<String>> arguments = new LinkedList<>();
        arguments.add(null);
        arguments.add(newArrayList());
        arguments.add(newArrayList("U", "U"));
        arguments.add(newArrayList(
                "U", "U", "U", "U", "U", "U", "U", "U", "U", "U",
                "U", "U", "U", "U", "U", "U", "U", "U", "U", "U", "U"
        ));
        arguments.add(newArrayList(null, "U", "D"));
        arguments.add(newArrayList("A", "U", "D"));
        arguments.add(newArrayList("A", "B", "C"));
        arguments.add(newArrayList("U", "A", "D"));
        return arguments.stream();
    }
}