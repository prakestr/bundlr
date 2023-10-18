package rahulshettyacademy.automationexercise.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;

public class UserRegistrationPage {

    WebDriver driver;

    @FindBy(id = "gender-male")
    WebElement maleGenderRadioButton;

    @FindBy(id = "gender-female")
    WebElement femaleGenderRadioButton;

    @FindBy(id = "FirstName")
    WebElement firstNameTextbox;

    @FindBy(id = "LastName")
    WebElement lastNameTextbox;

    @FindBy(name = "DateOfBirthDay")
    WebElement dayDropdown;

    @FindBy(name = "DateOfBirthMonth")
    WebElement monthDropdown;

    @FindBy(name = "DateOfBirthYear")
    WebElement yearDropdown;

    @FindBy(id = "Email")
    WebElement emailTextbox;

    @FindBy(id = "Company")
    WebElement companyTextbox;

    @FindBy(id = "Password")
    WebElement passwordTextbox;

    @FindBy(id = "ConfirmPassword")
    WebElement confirmPasswordTextbox;

    @FindBy(id = "register-button")
    WebElement registerButton;

    @FindBy(xpath = "//div[@class='result']")
    private WebElement registrationCompletedMessage;

   @FindBy(xpath = "//a[@class='button-1 register-continue-button']")
    private WebElement continueButton;

    public UserRegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectMaleGender() {
        maleGenderRadioButton.click();
    }

    public void selectFemaleGender() {
        femaleGenderRadioButton.click();
    }

    public void setFirstName(String firstName) {
        firstNameTextbox.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameTextbox.sendKeys(lastName);
    }

    public void selectDay(String day) {
        dayDropdown.sendKeys(day);
    }

    public void selectMonth(String month) {
        monthDropdown.sendKeys(month);
    }

    public void selectYear(String year) {
        yearDropdown.sendKeys(year);
    }

    public void setEmail(String email) {
        emailTextbox.sendKeys(email);
    }
    public void setCompany(String company) {
        companyTextbox.sendKeys(company);
    }
    public void setPassword(String password) {
        passwordTextbox.sendKeys(password);
    }
    public void setConfirmPassword(String confirmPassword) {
        confirmPasswordTextbox.sendKeys(confirmPassword);
    }

    public void clickContinueButton() {
        continueButton.click();
    }
    public boolean isRegistrationCompletedDisplayed() {
        return registrationCompletedMessage.getText().equals("Your registration completed")
                && registrationCompletedMessage.isDisplayed();
    }

    public void fillForm(HashMap<String, String> userData) {
        if("Male".equalsIgnoreCase(userData.get("gender"))) {
            selectMaleGender();
        } else if("Female".equalsIgnoreCase(userData.get("gender"))) {
            selectFemaleGender();
        }
        setFirstName(userData.get("firstName"));
        setLastName(userData.get("lastName"));
        selectDay(userData.get("day"));
        selectMonth(userData.get("month"));
        selectYear(userData.get("year"));
        setEmail(userData.get("email"));
        setCompany(userData.get("company"));
        setPassword(userData.get("password"));
        setConfirmPassword(userData.get("confPassword"));
    }

    // Method to click the Register button
    public void clickRegisterButton() {
        registerButton.click();
    }

}
