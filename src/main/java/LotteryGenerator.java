import java.util.Set;

public interface LotteryGenerator {
    String generateNumbers(Set<Integer> excludedNumbers);
}
