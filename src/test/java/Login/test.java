/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Login;

import Login.java.Login;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author MakaB
 */
public class test {
    
    public test() {
     

/**
 *
 * @author MakaB
 */


    // Create a fresh Login object before each test
    private Login login;

    public void setUp() {
        login = new Login();
    }

    // -------------------------------------------------------
    // checkUserName tests
    // -------------------------------------------------------

    /**
     * Valid username — "ky_le" contains underscore and is <= 5 characters
     */
    @Test
    public void testValidUsername() {
        assertTrue(login.checkUserName("ky_le"));
    }

    /**
     * Invalid username — no underscore present
     */
    @Test
    public void testInvalidUsername_NoUnderscore() {
        assertFalse(login.checkUserName("kyle!!!!"));
    }

    /**
     * Invalid username — too long (more than 5 characters)
     */
    @Test
    public void testInvalidUsername_TooLong() {
        assertFalse(login.checkUserName("kyleeeee"));
    }

    // -------------------------------------------------------
    // checkPasswordComplexity tests
    // -------------------------------------------------------

    /**
     * Valid password — meets all complexity rules
     */
    @Test
    public void testValidPassword() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99"));
    }

    /**
     * Invalid password — no capital letter
     */
    @Test
    public void testInvalidPassword_NoCapital() {
        assertFalse(login.checkPasswordComplexity("password1!"));
    }

    /**
     * Invalid password — no number
     */
    @Test
    public void testInvalidPassword_NoNumber() {
        assertFalse(login.checkPasswordComplexity("Password!"));
    }

    /**
     * Invalid password — no special character
     */
    @Test
    public void testInvalidPassword_NoSpecialChar() {
        assertFalse(login.checkPasswordComplexity("Passw0rd"));
    }

    /**
     * Invalid password — too short (fewer than 8 characters)
     */
    @Test
    public void testInvalidPassword_TooShort() {
        assertFalse(login.checkPasswordComplexity("P0!a"));
    }

    // -------------------------------------------------------
    // isValidPhoneNumber tests
    // -------------------------------------------------------

    /**
     * Valid phone number — correct +27 format with 9 digits
     */
    @Test
    public void testValidPhoneNumber() {
        assertTrue(Login.isValidPhoneNumber("+27838968976"));
    }

    /**
     * Invalid phone number — missing international country code
     */
    @Test
    public void testInvalidPhoneNumber_NoCountryCode() {
        assertFalse(Login.isValidPhoneNumber("0838968976"));
    }

    /**
     * Invalid phone number — too short after country code
     */
    @Test
    public void testInvalidPhoneNumber_TooShort() {
        assertFalse(Login.isValidPhoneNumber("+2783896"));
    }

    // -------------------------------------------------------
    // registerUser tests
    // -------------------------------------------------------

    /**
     * Successful registration — all inputs valid
     */
    @Test
    public void testRegisterUser_Success() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("User registered successfully.", result);
    }

    /**
     * Registration fails — invalid username
     */
    @Test
    public void testRegisterUser_InvalidUsername() {
        String result = login.registerUser("kyle!!!", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(result.contains("Wrong username"));
    }

    /**
     * Registration fails — invalid password
     */
    @Test
    public void testRegisterUser_InvalidPassword() {
        String result = login.registerUser("kyl_1", "password", "+27838968976");
        assertTrue(result.contains("Password incorrect"));
    }

    /**
     * Registration fails — invalid phone number
     */
    @Test
    public void testRegisterUser_InvalidPhone() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "0838968976");
        assertTrue(result.contains("Cell number is incorrect"));
    }

    // -------------------------------------------------------
    // loginUser tests
    // -------------------------------------------------------

    /**
     * Successful login — credentials match registered details
     */
    @Test
    public void testLoginUser_Success() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    /**
     * Failed login — wrong password
     */
    @Test
    public void testLoginUser_WrongPassword() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "wrongpass"));
    }

    /**
     * Failed login — wrong username
     */
    @Test
    public void testLoginUser_WrongUsername() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("ky_l2", "Ch&&sec@ke99!"));
    }

    // -------------------------------------------------------
    // returnLoginStatus tests
    // -------------------------------------------------------

    /**
     * Login status success — "Welcome kyl, 1 it is great to see you."
     */
    @Test
    public void testReturnLoginStatus_Success() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean loggedIn = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        String expected = "Welcome kyl, 1 it is great to see you.";
        assertEquals(expected, login.returnLoginStatus(loggedIn));
    }

    /**
     * Login status failure — "Username or password incorrect, please try again."
     */
    @Test
    public void testReturnLoginStatus_Fail() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean loggedIn = login.loginUser("kyl_1", "wrongpass");
        String expected = "Username or password incorrect, please try again.";
        assertEquals(expected, login.returnLoginStatus(loggedIn));
    }

    }
    

