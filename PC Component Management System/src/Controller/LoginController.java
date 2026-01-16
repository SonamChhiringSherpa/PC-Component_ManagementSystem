/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import javax.swing.JOptionPane;
import Model.LoginModel;
import Model.UserSession;
import View.Admin;
import View.Login;
import View.User;


/**
 *
 * @author user
 */
public class LoginController {
   private final Login view;
    private final LoginModel.AppContext ctx = LoginModel.AppContext.getInstance();

    private static final String CARD_LOGIN = "login";
    private static final String CARD_SIGNUP = "signup";

    public LoginController(Login view) {
        this.view = view;
    }

    public void openSignup() {
        view.showCard(CARD_SIGNUP);
    }

    public void openLogin() {
        view.showCard(CARD_LOGIN);
    }

    public void signup() {
        String username = view.getSignupUsername().trim();
        String pass1 = view.getSignupPassword();
        String pass2 = view.getSignupConfirmPassword();
        String role = view.getSignupRole();

        if (username.length() == 0 || pass1.length() == 0 || pass2.length() == 0 || role == null) {
            view.showError("Fill all fields and select role.");
            return;
        }
        if (!pass1.equals(pass2)) {
            view.showError("Passwords do not match.");
            return;
        }

        LoginModel.UserAccount acc = new LoginModel.UserAccount(username, pass1, role);

        if (!ctx.getRepo().addAccount(acc)) {
            view.showError("Username already exists.");
            return;
        }

        view.showInfo("Signup successful. Please login.");
        view.clearSignupForm();
        openLogin();
        view.clearLoginForm();
    }

    public void login() {
        String username = view.getUsername().trim();
        String password = view.getPassword();
        String role = view.getSelectedRole();

        if (username.length() == 0 || password.length() == 0 || role == null) {
            view.showError("Enter username/password and select role.");
            return;
        }

        LoginModel.UserAccount acc = ctx.getRepo().validateLogin(username, password, role);
        if (acc == null) {
            view.showError("Invalid credentials for the selected role.");
            return;
        }

        // set session (this is the key for per-user cart persistence)
        UserSession.login(acc.getUsername(), acc.getRole());

        view.showInfo("Login successful.");

        if ("ADMIN".equals(acc.getRole())) {
            Admin a = new Admin();
            a.setVisible(true);
        } else {
            User u = new User();
            u.setVisible(true);
        }

        view.dispose();
    }

}
