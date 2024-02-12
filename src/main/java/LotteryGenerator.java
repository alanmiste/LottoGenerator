import java.util.Set;

public interface LotteryGenerator {
    String generateNumbers(Set<Integer> excludedNumbers);
}
//
//public class LottoGenerator implements LotteryGenerator {
//    @Override
//    public String generateNumbers(Set<Integer> excludedNumbers) {
//        // Generate Lotto numbers logic while excluding excludedNumbers
//    }
//}
//
//public class EurojackpotGenerator implements LotteryGenerator {
//    @Override
//    public String generateNumbers(Set<Integer> excludedNumbers) {
//        // Generate Eurojackpot numbers logic while excluding excludedNumbers
//    }
//}
