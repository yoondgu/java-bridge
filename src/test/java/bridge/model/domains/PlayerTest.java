package bridge.model.domains;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.util.Lists.newArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("이동 결과에 따라 사용자 생존 상태 갱신")
    @Test
    void addOneStep() {
        Player player = new Player(newArrayList("U", "D", "U"));
        player.addOneStep("U");
        assertThat(player.hasSurvived()).isTrue();
        player.addOneStep("U");
        assertThat(player.hasSurvived()).isFalse();
    }

    @DisplayName("사용자가 이미 다리를 모두 건넜을 경우 예외 발생")
    @Test
    void addOneStepForFailedPlayer() {
        Player player = new Player(newArrayList("U", "D", "U"));
        player.addOneStep("U");
        player.addOneStep("U");
        assertThatThrownBy(() -> player.addOneStep("U"))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("사용자가 이미 다리 건너기를 실패했을 경우 예외 발생")
    @Test
    void addOneStepForDonePlayer() {
        Player player = new Player(newArrayList("U", "D", "U"));
        player.addOneStep("U");
        player.addOneStep("D");
        player.addOneStep("U");
        assertThatThrownBy(() -> player.addOneStep("U"))
                .isInstanceOf(IllegalStateException.class);
    }
}