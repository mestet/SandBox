import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class DiceRoller {

    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    private static final int rollAmount = 40000;

    private static final int shootAmount = 20;
    private static final int ap = 1;
    private static final int dmg = 1;

    private static final int toHit = 3;
    private static final int toWound = 4;
    private static final int save = 6;

    private static final int targetWounds = 1;


    public static void main(String[] args) {
        List<Long> successHits = new ArrayList<>();

        List<Integer> toHitRoll = rollD6(shootAmount * rollAmount);
        log("rollResult", toHitRoll.toString());

        long toHitResult = rollResult(toHitRoll, toHit);
        log("toHitResult", toHitResult);

        List<Integer> toWoundRoll = rollD6(toHitResult);
        log("toWoundRollResult", toWoundRoll);

        long toWoundResult = rollResult(toWoundRoll, toWound);
        log("toWoundResult", toWoundResult);

        List<Integer> saveRoll = rollD6(toWoundResult);
        log("saveRoll", saveRoll);

        long saveResult = rollResult(saveRoll, save + ap);
        log("saveResult", saveResult);

        successHits.add(toWoundResult - saveResult);

        Integer slainModels = allocateWounds(toWoundResult, saveResult);

        log("allocatedHits", successHits);
        log("slainModels", slainModels);

        Long allocatedHitsSum = successHits.stream().reduce(0L, Long::sum);
        BigDecimal averageHits = BigDecimal.valueOf(allocatedHitsSum, 0)
                .divide(BigDecimal.valueOf(rollAmount), 2, RoundingMode.HALF_UP);
        BigDecimal averageSlainModels = BigDecimal.valueOf(slainModels, 0)
                .divide(BigDecimal.valueOf(rollAmount), 2, RoundingMode.HALF_UP);
        log("Average success hit amount", averageHits);
        log("Average slain models", averageSlainModels);
    }

    public static Integer allocateWounds(long toWoundResult, long saveResult) {
        long successWounds = toWoundResult - saveResult;
        int slainModels = 0;
        do {
            int remain = targetWounds;
            while (remain > 0) {
                remain -= dmg;
                successWounds--;
            }
            slainModels++;
        } while (successWounds > 0);

        return slainModels;
    }

    public static Long rollResult(List<Integer> roll, int bound) {
        return roll.stream().filter(dice -> dice >= bound).count();
    }

    public static Integer rollD6() {
        return random.nextInt(6) + 1;
    }

    public static List<Integer> rollD6(long amount) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            result.add(rollD6());
        }
        return result;
    }

    public static void log(String msg, Object obj) {
        System.out.println(msg + ": " + obj.toString());
    }
}