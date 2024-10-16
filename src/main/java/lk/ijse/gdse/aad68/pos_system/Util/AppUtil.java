package lk.ijse.gdse.aad68.pos_system.Util;

import java.util.concurrent.atomic.AtomicInteger;

public class AppUtil {

    public static String createOrderDetailIndex(String currentMaxId){
        int newId;

        if (currentMaxId == null) {
            newId = 1;
        } else {
            String numericPart = currentMaxId.replace("OD-", "");
            newId = Integer.parseInt(numericPart) + 1;
        }
        return String.format("OD-%04d", newId);
    }
}
