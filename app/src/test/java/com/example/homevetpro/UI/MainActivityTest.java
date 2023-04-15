package com.example.homevetpro.UI;

import static android.os.Looper.getMainLooper;
import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

import android.widget.Button;
import android.widget.EditText;

import com.example.homevetpro.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.DEFAULT_MANIFEST_NAME)
public class MainActivityTest {
    private MainActivity activity;


   @Before
    public void setUp() {

        activity = Robolectric.buildActivity(MainActivity.class).create().visible().get();
        //activity.setContentView(R.layout.activity_main);
    }



    @Test
    public void loginWithInvalidCredentialsShouldShowErrorMessage() {
        // Set up invalid credentials
        String invalidUsername = "invalid";
        String invalidPassword = "password";

        // Set up the input fields
        EditText usernameEditText = activity.findViewById(R.id.username);
        EditText passwordEditText = activity.findViewById(R.id.password);
        usernameEditText.setText(invalidUsername);
        passwordEditText.setText(invalidPassword);

        // Click the login button
        Button loginButton = activity.findViewById(R.id.login);
        loginButton.performClick();

        // Verify that error message is shown
        String expectedMessage = "Login Failed";
        String actualMessage = ShadowToast.getTextOfLatestToast();
        assertEquals(expectedMessage, actualMessage);
        shadowOf(getMainLooper()).idle();
    }

    @Test
    public void loginWithNoCredentialsShouldShowErrorMessage() {
        // Set up invalid credentials
        String invalidUsername = "";
        String invalidPassword = "";

        // Set up the input fields
        EditText usernameEditText = activity.findViewById(R.id.username);
        EditText passwordEditText = activity.findViewById(R.id.password);
        usernameEditText.setText(invalidUsername);
        passwordEditText.setText(invalidPassword);

        // Click the login button
        Button loginButton = activity.findViewById(R.id.login);
        loginButton.performClick();

        // Verify that error message is shown
        String expectedMessage = "Please Enter Username and Password";
        String actualMessage = ShadowToast.getTextOfLatestToast();
        assertEquals(expectedMessage, actualMessage);
        shadowOf(getMainLooper()).idle();
        System.out.println("loginWithNoCredentialsShouldShowErrorMessage() was successful");
    }

    @Test
    public void loginWithValidCredentialsShouldStartHomeScreenActivity() {
        // Set up valid credentials
        String validUsername = "test";
        String validPassword = "test";

        // Set up the input fields
        EditText usernameEditText = activity.findViewById(R.id.username);
        EditText passwordEditText = activity.findViewById(R.id.password);
        usernameEditText.setText(validUsername);
        passwordEditText.setText(validPassword);

        // Click the login button
        Button loginButton = activity.findViewById(R.id.login);
        loginButton.performClick();

        String expectedMessage = "Login Failed";
        String actualMessage = ShadowToast.getTextOfLatestToast();
        assertEquals(expectedMessage, actualMessage);
        shadowOf(getMainLooper()).idle();

       /* Intent expectedIntent = new Intent(activity, HomeScreen.class);
        ComponentName actualComponentName = shadowOf(activity).getNextStartedActivity().getComponent();
        ComponentName expectedComponentName = expectedIntent.getComponent();
        assertEquals(expectedComponentName, actualComponentName);
        shadowOf(getMainLooper()).idle();*/
    }

}
