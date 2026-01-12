/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.LinkedList;

/**
 *
 * @author user
 */
public class LoginModel {

    // Represents one user record (one row/account)
    // stores username, password and role
    public static class UserAccount {

        private String username;
        private String password;
        private String role; // "ADMIN" or "USER"

        //constructor used when creating a new account during signup (or predefined users)
        public UserAccount(String username, String password, String role) {
            this.username = username;
            this.password = password;
            this.role = role;
        }

        // getter and setter that allows controller to read and updated the values
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }

    // Stores accounts in LinkedLists
    // separate lists to maintain addmin and user
    public static class AccountRepository {

        // private lists that holds accounts
        private final LinkedList<UserAccount> adminAccounts = new LinkedList<>();
        private final LinkedList<UserAccount> userAccounts = new LinkedList<>();

        // checks if a username already exists in either list
        public boolean usernameExists(String username) {
            return findByUsername(username) != null;
        }

        // finds an account by username 
        public UserAccount findByUsername(String username) {
            // searches the admin list
            for (UserAccount a : adminAccounts) {
                if (a.getUsername().equalsIgnoreCase(username)) {
                    return a;
                }
            }
            // searches the user list
            for (UserAccount a : userAccounts) {
                if (a.getUsername().equalsIgnoreCase(username)) {
                    return a;
                }
            }
            return null;
        }

        // adds a new account if the username is not already taken.
        public boolean addAccount(UserAccount account) {
            // returns true if added successfully and false if username exists.
            if (usernameExists(account.getUsername())) {
                return false;
            }

            // stores the new account in the correct list based on the role
            if ("ADMIN".equals(account.getRole())) {
                adminAccounts.add(account);
            } else {
                userAccounts.add(account);
            }

            return true;
        }

        // valiidates login using the username, password and selected role.
        public UserAccount validateLogin(String username, String password, String role) {
            // checks if the selected role is admin or user
            LinkedList<UserAccount> target
                    = "ADMIN".equals(role) ? adminAccounts : userAccounts;

            // searches only the list that matches the selected role
            for (UserAccount a : target) {
                if (a.getUsername().equalsIgnoreCase(username) && a.getPassword().equals(password)) {
                    return a;
                }
            }
            return null;
        }
    }

    // app context provides one shared repository instance
    // logincontroller creates appcontext once and uses the same repo for signup and login
    public static class AppContext {

        private final AccountRepository repo = new AccountRepository();

        // constructor that holds the predefinied accounts to login without signup
        public AppContext() {
            // predefined admin
            repo.adminAccounts.add(new UserAccount("admin", "1234", "ADMIN"));
            // predefined user
            repo.userAccounts.add(new UserAccount("user", "1234", "USER"));
        }

        // gives controller access to the shared repository
        public AccountRepository getRepo() {
            return repo;
        }
    }
}
