package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

/**
 * LoginPage — consolidated by TestForge from EXECUTED test runs.
 * Page URL: https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
 * Every locator below resolved successfully in a real browser execution.
 */
public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // A PageFactory field is a lazy proxy. On a page that re-renders (any AJAX
        // list, filter or grid) the node it resolved to can be replaced between the
        // wait and the read, which surfaces as StaleElementReferenceException. Telling
        // the wait to ignore it makes the condition re-resolve instead of failing.
        this.wait.ignoring(StaleElementReferenceException.class);
        PageFactory.initElements(driver, this);
    }

    /**
     * Run a read against an element, retrying while it is stale or missing.
     *
     * Resolving an element and then reading it as two separate statements is unsafe on
     * any page that re-renders: the element can be replaced in between, and the read
     * then throws. Both steps happen inside this single retried block so a re-render
     * simply causes another attempt rather than a failure.
     */
    private <T> T readStable(Function<WebDriver, T> read) {
        return wait.until(d -> {
            try {
                return read.apply(d);
            } catch (StaleElementReferenceException | NoSuchElementException
                   | ElementClickInterceptedException e) {
                return null;   // not settled yet — WebDriverWait will retry
            }
        });
    }

    /**
     * The first DISPLAYED element among a locator's matches.
     *
     * Throws when nothing visible matched, so the surrounding retry treats it as
     * not-settled-yet and tries again rather than acting on a hidden node.
     */
    private WebElement firstDisplayed(java.util.List<WebElement> matches, String name) {
        for (WebElement candidate : matches) {
            try {
                if (candidate.isDisplayed()) return candidate;
            } catch (StaleElementReferenceException ignored) {
                // replaced mid-scan — the retry will re-resolve
            }
        }
        throw new NoSuchElementException("No visible element for " + name
                + " (" + matches.size() + " match(es) found, none displayed)");
    }

    @FindBy(css = "input[name=\"username\"]")
    private java.util.List<WebElement> usernameInputFieldMatches;

    @FindBy(css = "input[name=\"password\"]")
    private java.util.List<WebElement> passwordInputFieldMatches;

    @FindBy(xpath = "//button[normalize-space()='Login'] | //a[normalize-space()='Login'] | //*[@role='button'][normalize-space()='Login']")
    private java.util.List<WebElement> loginSubmitButtonMatches;

    @FindBy(css = "h5.oxd-text.orangehrm-login-title")
    private java.util.List<WebElement> loginSubmitButton2Matches;

    @FindBy(xpath = "//*[normalize-space()='OrangeHRM, Inc']")
    private java.util.List<WebElement> orangeHRMIncFooterLinkMatches;

    public void enterUsernameInputField(String value) {
        readStable(d -> {
            WebElement el = firstDisplayed(usernameInputFieldMatches, "usernameInputField");
            el.clear();
            el.sendKeys(value);
            return Boolean.TRUE;
        });
    }

    public boolean isUsernameInputFieldVisible() {
        try {
            return Boolean.TRUE.equals(readStable(d -> {
                firstDisplayed(usernameInputFieldMatches, "usernameInputField");
                return Boolean.TRUE;
            }));
        } catch (Exception e) {
            return false;   // genuinely absent after retrying — an honest false
        }
    }

    public String getUsernameInputFieldText() {
        // Resolve AND read inside one retried block. Doing these as two statements
        // is what breaks on AJAX-rendered content: the element is replaced between
        // the wait and the read, and getText() then hits a dead reference.
        return readStable(d -> {
            String t = firstDisplayed(usernameInputFieldMatches, "usernameInputField").getText();
            return (t == null || t.isEmpty()) ? null : t;
        });
    }

    public void enterPasswordInputField(String value) {
        readStable(d -> {
            WebElement el = firstDisplayed(passwordInputFieldMatches, "passwordInputField");
            el.clear();
            el.sendKeys(value);
            return Boolean.TRUE;
        });
    }

    public boolean isPasswordInputFieldVisible() {
        try {
            return Boolean.TRUE.equals(readStable(d -> {
                firstDisplayed(passwordInputFieldMatches, "passwordInputField");
                return Boolean.TRUE;
            }));
        } catch (Exception e) {
            return false;   // genuinely absent after retrying — an honest false
        }
    }

    public String getPasswordInputFieldText() {
        // Resolve AND read inside one retried block. Doing these as two statements
        // is what breaks on AJAX-rendered content: the element is replaced between
        // the wait and the read, and getText() then hits a dead reference.
        return readStable(d -> {
            String t = firstDisplayed(passwordInputFieldMatches, "passwordInputField").getText();
            return (t == null || t.isEmpty()) ? null : t;
        });
    }

    public void clickLoginSubmitButton() {
        readStable(d -> {
            WebElement el = firstDisplayed(loginSubmitButtonMatches, "loginSubmitButton");
            if (!el.isEnabled()) return null;
            // Selenium clicks the centre point without scrolling first, so a control
            // below the fold or under a sticky header is intercepted by whatever sits
            // on top. Playwright scrolls and waits for actionability; this matches it.
            ((JavascriptExecutor) d).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", el);
            el.click();
            return Boolean.TRUE;
        });
    }

    public boolean isLoginSubmitButton2Visible() {
        try {
            return Boolean.TRUE.equals(readStable(d -> {
                firstDisplayed(loginSubmitButton2Matches, "loginSubmitButton2");
                return Boolean.TRUE;
            }));
        } catch (Exception e) {
            return false;   // genuinely absent after retrying — an honest false
        }
    }

    public String getLoginSubmitButton2Text() {
        // Resolve AND read inside one retried block. Doing these as two statements
        // is what breaks on AJAX-rendered content: the element is replaced between
        // the wait and the read, and getText() then hits a dead reference.
        return readStable(d -> {
            String t = firstDisplayed(loginSubmitButton2Matches, "loginSubmitButton2").getText();
            return (t == null || t.isEmpty()) ? null : t;
        });
    }

    public boolean isOrangeHRMIncFooterLinkVisible() {
        try {
            return Boolean.TRUE.equals(readStable(d -> {
                firstDisplayed(orangeHRMIncFooterLinkMatches, "orangeHRMIncFooterLink");
                return Boolean.TRUE;
            }));
        } catch (Exception e) {
            return false;   // genuinely absent after retrying — an honest false
        }
    }

    public String getOrangeHRMIncFooterLinkText() {
        // Resolve AND read inside one retried block. Doing these as two statements
        // is what breaks on AJAX-rendered content: the element is replaced between
        // the wait and the read, and getText() then hits a dead reference.
        return readStable(d -> {
            String t = firstDisplayed(orangeHRMIncFooterLinkMatches, "orangeHRMIncFooterLink").getText();
            return (t == null || t.isEmpty()) ? null : t;
        });
    }

}
