package User;

import Admin.AdminMain;
import Cashier.CashierMain;

public class UserFactory {
    public static UserBase createUser(int userType) {
        return switch (userType) {
            case 1 -> new AdminMain();
            case 2 -> new CashierMain();
            default -> null;
        };
    }
}
