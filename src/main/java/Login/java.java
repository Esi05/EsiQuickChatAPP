/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

/**
 *
 * @author MakaB
 */
public class java {
    public class Login {

    // Stored credentials set during registration
    private String username;
    private String password;
    private String phonenumber;

    // Default constructor
    public Login() {
    }

    // -------------------------------------------------------
    // VALIDATION METHODS
    // -------------------------------------------------------

    /**
     * Checks that the username contains an underscore (_)
     * and is no more than five characters long.
     *
     * @param username the username to check
     * @return true if valid, false otherwise
     */
    public boolean checkUserName(String username) {
        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    /**
     * Checks that the password meets complexity rules:
     * - At least 8 characters long
     * - Contains at least one capital letter
     * - Contains at least one number
     * - Contains at least one special character
     *
     * @param password the password to check
     * @return true if valid, false otherwise
     */
    public boolean checkPasswordComplexity(String password) {
        if (password == null) return false;

        boolean hasCapital = false;
        boolean hasNumber  = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c))        hasCapital = true;
            else if (Character.isDigit(c))       hasNumber  = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }

        return password.length() >= 8 && hasCapital && hasNumber && hasSpecial;
    }

    /**
     * Checks that the cell phone number is in valid South African format.
     * Must start with +27 and be followed by exactly 9 digits.
     * Reference: standard E.164 format for South African numbers.
     *
     * @param phone the phone number to check
     * @return true if valid, false otherwise
     */
    public static boolean isValidPhoneNumber(String phone) {
        return phone != null && phone.matches("\\+27\\d{9}");
    }

    // -------------------------------------------------------
    // REGISTRATION
    // -------------------------------------------------------

    /**
     * Registers a user after validating username, password, and cell number.
     * Stores credentials only if all checks pass.
     *
     * @param username    the chosen username
     * @param password    the chosen password
     * @param phoneNumber the user's South African cell number
     * @return appropriate registration status message
     */
    public String registerUser(String username, String password, String phoneNumber) {
        if (!checkUserName(username)) {
            return "Wrong username: must contain '_' and be <= 5 characters";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password incorrect: must be >=8 chars, include capital letter, number, and special character";
        }
        if (!isValidPhoneNumber(phoneNumber)) {
            return "Cell number is incorrect; must start with +27 and have 10 digits total";
        }

        // Save details only if all validation passes
        this.username    = username;
        this.password    = password;
        this.phonenumber = phoneNumber;

        return "User registered successfully.";
    }

    // -------------------------------------------------------
    // LOGIN
    // -------------------------------------------------------

    /**
     * Verifies that the entered credentials match the stored registered credentials.
     *
     * @param enteredUsername the username entered at login
     * @param enteredPassword the password entered at login
     * @return true if both match, false otherwise
     */
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return username != null
                && password != null
                && username.equals(enteredUsername)
                && password.equals(enteredPassword);
    }

    /**
     * Returns the appropriate login status message.
     * Success: "Welcome <first>, <last> it is great to see you."
     * Failure: "Username or password incorrect, please try again."
     *
     * @param loggedIn the result from loginUser()
     * @return status message string
     */
    public String returnLoginStatus(boolean loggedIn) {
        if (loggedIn) {
            // Split username on underscore to produce first and last name parts
            String[] parts     = username.split("_", 2);
            String firstName   = parts[0];
            String lastName    = parts.length > 1 ? parts[1] : "";
            return "Welcome " + firstName + ", " + lastName + " it is great to see you.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // -------------------------------------------------------
    // ACCESSORS AND MUTATORS
    // -------------------------------------------------------

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhonenumber() { return phonenumber; }
    public void setPhonenumber(String phonenumber) { this.phonenumber = phonenumber; }
}
    
}
