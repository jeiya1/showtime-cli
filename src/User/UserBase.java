package User;

public abstract class UserBase {
    protected String userRole;

    public abstract void displayMenu();
    public abstract void run();

    public void displayWelcome() {
        System.out.println("Welcome, " + userRole);
    }
    public void displayGoodbye() {
        System.out.println("Thank you for using the system, " + userRole + "!");
    }

    public String getUserRole() {
        return userRole;
    }

    protected void setUserRole(String role) {
        this.userRole = role;
    }
}
