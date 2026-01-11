/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import javax.swing.JOptionPane;
import Model.LoginModel;
import View.AdminDashboard;
import View.Login;
import View.UserView;

/**
 *
 * @author user
 */
public class LoginController {
    // Reference to the Login view (JFrame with CardLayout).
    private final Login View;
    
    // shared application context that holds the in-memory repository.
    private final LoginModel.AppContext ctx = new LoginModel.AppContext();

    // 
    private static final String CARD_LOGIN = "login";
    private static final String CARD_SIGNUP = "signup";

    //the controller constructor recieves the view and keeps a reference to it
    public LoginController(Login view) {
        this.View = view;
    }

    //shows the signup panel
    public void openSignup() {
        View.showCard(CARD_SIGNUP);
    }

    //shows the login panel
    public void openLogin() {
        View.showCard(CARD_LOGIN);
    }

    /*
      Handles signup: reads signup fields from the view,
      validates them, creates a new UserAccount, and saves it
      in the in-memory LinkedList repository if valid.
     */
    public void signup() {
        // read signup input from view
        String username = View.getSignupUsername().trim();
        String pass1 = View.getSignupPassword();
        String pass2 = View.getSignupConfirmPassword();
        String role = View.getSignupRole(); // from Signup_Role ButtonGroup

        // validation to check if the fields are empty or not
        if (username.isEmpty() || pass1.isEmpty() || pass2.isEmpty() || role == null) {
            View.showError("Fill all fields and select role.");
            return;
        }
        
        // validates if the password and confirm password match
        if (!pass1.equals(pass2)) {
            View.showError("Passwords do not match.");
            return;
        }

        // creates new account object with given username, passsword and role
        LoginModel.UserAccount acc = new LoginModel.UserAccount(username, pass1, role);

        // add account to repository and check if the username already exists 
        // display error if user already exists
        if (!ctx.getRepo().addAccount(acc)) {
            View.showError("Username already exists.");
            return;
        }

        // inform user that signup was successfull
        View.showInfo("Signup successful. Please login.");
        
        //clear signup fields for the next signup
        View.clearSignupForm();
        
        //switch back to login panel
        openLogin();         
        
        // clear the login fields for the new login
        View.clearLoginForm(); 

    }

    /*
      Handles signup: reads signup fields from the view,
      validates them, creates a new UserAccount, and saves it
      in the in-memory LinkedList repository if valid.
     */
    public void login() {
        // read login input from view
        String username = View.getUsername().trim();
        String password = View.getPassword();
        String role = View.getSelectedRole(); // from Login_Role ButtonGroup

        // validates if the fields are empty or not
        if (username.isEmpty() || password.isEmpty() || role == null) {
            View.showError("Enter username/password and select role.");
            return;
        }

        // validatees if the given credentials match the account in the selected role list
        LoginModel.UserAccount acc = ctx.getRepo().validateLogin(username, password, role);
        
        //if no account matches username/password/role the displays the error message
        if (acc == null) {
            View.showError("Invalid credentials for the selected role.");
            return;
        }

        // inform user that login was successful
        View.showInfo("Login successful.");

        // opens the correct dashboard based on the account's role
        // if the role is ADMIN then opens admindashboard else opens the user dashboard
        if ("ADMIN".equals(acc.getRole())) {
            new AdminDashboard().setVisible(true);
        } else {
            new UserView().setVisible(true);
        }
        
        //closes the login window after successful login
        View.dispose();
    }

}
