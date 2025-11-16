package User;

import Utils.Colors;

public abstract class UserBase implements UserActions {
    protected String userRole;

    public abstract void displayMenu();
    public abstract void run();

    public void displayWelcome() {
        System.out.println(Colors.WHITE_BOLD + "\n\t   Welcome, " + userRole + Colors.RESET);
    }
    public void displayGoodbye() {
        System.out.println(Colors.WHITE_BOLD + "\nThank you for using the system, " + userRole + "!" + Colors.RESET);
    }

    public String getUserRole() {
        return userRole;
    }

    protected void setUserRole(String role) {
        this.userRole = role;
    }
}
