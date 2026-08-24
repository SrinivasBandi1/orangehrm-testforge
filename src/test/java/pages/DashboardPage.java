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
 * DashboardPage — consolidated by TestForge from EXECUTED test runs.
 * Page URL: https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index
 * Every locator below resolved successfully in a real browser execution.
 */
public class DashboardPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public DashboardPage(WebDriver driver) {
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

    @FindBy(css = "li.oxd-main-menu-item-wrapper:nth-of-type(8) > a.oxd-main-menu-item > span.oxd-text")
    private java.util.List<WebElement> dashboardNavLinkMatches;

    @FindBy(css = "nav[aria-label=\"Sidepanel\"]")
    private java.util.List<WebElement> sidepanelNavigationMatches;

    @FindBy(css = "nav[aria-label=\"Topbar Menu\"]")
    private java.util.List<WebElement> topbarMenuNavigationMatches;

    @FindBy(css = "li.oxd-main-menu-item-wrapper:nth-of-type(1) > a.oxd-main-menu-item > span.oxd-text")
    private java.util.List<WebElement> adminSidebarLinkMatches;

    @FindBy(css = "li.oxd-main-menu-item-wrapper:nth-of-type(2) > a.oxd-main-menu-item > span.oxd-text")
    private java.util.List<WebElement> pIMSidebarLinkMatches;

    @FindBy(css = "li.oxd-main-menu-item-wrapper:nth-of-type(3) > a.oxd-main-menu-item > span.oxd-text")
    private java.util.List<WebElement> leaveSidebarLinkMatches;

    @FindBy(css = "li.oxd-main-menu-item-wrapper:nth-of-type(5) > a.oxd-main-menu-item > span.oxd-text")
    private java.util.List<WebElement> recruitmentSidebarLinkMatches;

    public boolean isDashboardNavLinkVisible() {
        try {
            return Boolean.TRUE.equals(readStable(d -> {
                firstDisplayed(dashboardNavLinkMatches, "dashboardNavLink");
                return Boolean.TRUE;
            }));
        } catch (Exception e) {
            return false;   // genuinely absent after retrying — an honest false
        }
    }

    public String getDashboardNavLinkText() {
        // Resolve AND read inside one retried block. Doing these as two statements
        // is what breaks on AJAX-rendered content: the element is replaced between
        // the wait and the read, and getText() then hits a dead reference.
        return readStable(d -> {
            String t = firstDisplayed(dashboardNavLinkMatches, "dashboardNavLink").getText();
            return (t == null || t.isEmpty()) ? null : t;
        });
    }

    public boolean isSidepanelNavigationVisible() {
        try {
            return Boolean.TRUE.equals(readStable(d -> {
                firstDisplayed(sidepanelNavigationMatches, "sidepanelNavigation");
                return Boolean.TRUE;
            }));
        } catch (Exception e) {
            return false;   // genuinely absent after retrying — an honest false
        }
    }

    public String getSidepanelNavigationText() {
        // Resolve AND read inside one retried block. Doing these as two statements
        // is what breaks on AJAX-rendered content: the element is replaced between
        // the wait and the read, and getText() then hits a dead reference.
        return readStable(d -> {
            String t = firstDisplayed(sidepanelNavigationMatches, "sidepanelNavigation").getText();
            return (t == null || t.isEmpty()) ? null : t;
        });
    }

    public boolean isTopbarMenuNavigationVisible() {
        try {
            return Boolean.TRUE.equals(readStable(d -> {
                firstDisplayed(topbarMenuNavigationMatches, "topbarMenuNavigation");
                return Boolean.TRUE;
            }));
        } catch (Exception e) {
            return false;   // genuinely absent after retrying — an honest false
        }
    }

    public String getTopbarMenuNavigationText() {
        // Resolve AND read inside one retried block. Doing these as two statements
        // is what breaks on AJAX-rendered content: the element is replaced between
        // the wait and the read, and getText() then hits a dead reference.
        return readStable(d -> {
            String t = firstDisplayed(topbarMenuNavigationMatches, "topbarMenuNavigation").getText();
            return (t == null || t.isEmpty()) ? null : t;
        });
    }

    public boolean isAdminSidebarLinkVisible() {
        try {
            return Boolean.TRUE.equals(readStable(d -> {
                firstDisplayed(adminSidebarLinkMatches, "adminSidebarLink");
                return Boolean.TRUE;
            }));
        } catch (Exception e) {
            return false;   // genuinely absent after retrying — an honest false
        }
    }

    public String getAdminSidebarLinkText() {
        // Resolve AND read inside one retried block. Doing these as two statements
        // is what breaks on AJAX-rendered content: the element is replaced between
        // the wait and the read, and getText() then hits a dead reference.
        return readStable(d -> {
            String t = firstDisplayed(adminSidebarLinkMatches, "adminSidebarLink").getText();
            return (t == null || t.isEmpty()) ? null : t;
        });
    }

    public boolean isPIMSidebarLinkVisible() {
        try {
            return Boolean.TRUE.equals(readStable(d -> {
                firstDisplayed(pIMSidebarLinkMatches, "pIMSidebarLink");
                return Boolean.TRUE;
            }));
        } catch (Exception e) {
            return false;   // genuinely absent after retrying — an honest false
        }
    }

    public String getPIMSidebarLinkText() {
        // Resolve AND read inside one retried block. Doing these as two statements
        // is what breaks on AJAX-rendered content: the element is replaced between
        // the wait and the read, and getText() then hits a dead reference.
        return readStable(d -> {
            String t = firstDisplayed(pIMSidebarLinkMatches, "pIMSidebarLink").getText();
            return (t == null || t.isEmpty()) ? null : t;
        });
    }

    public boolean isLeaveSidebarLinkVisible() {
        try {
            return Boolean.TRUE.equals(readStable(d -> {
                firstDisplayed(leaveSidebarLinkMatches, "leaveSidebarLink");
                return Boolean.TRUE;
            }));
        } catch (Exception e) {
            return false;   // genuinely absent after retrying — an honest false
        }
    }

    public String getLeaveSidebarLinkText() {
        // Resolve AND read inside one retried block. Doing these as two statements
        // is what breaks on AJAX-rendered content: the element is replaced between
        // the wait and the read, and getText() then hits a dead reference.
        return readStable(d -> {
            String t = firstDisplayed(leaveSidebarLinkMatches, "leaveSidebarLink").getText();
            return (t == null || t.isEmpty()) ? null : t;
        });
    }

    public boolean isRecruitmentSidebarLinkVisible() {
        try {
            return Boolean.TRUE.equals(readStable(d -> {
                firstDisplayed(recruitmentSidebarLinkMatches, "recruitmentSidebarLink");
                return Boolean.TRUE;
            }));
        } catch (Exception e) {
            return false;   // genuinely absent after retrying — an honest false
        }
    }

    public String getRecruitmentSidebarLinkText() {
        // Resolve AND read inside one retried block. Doing these as two statements
        // is what breaks on AJAX-rendered content: the element is replaced between
        // the wait and the read, and getText() then hits a dead reference.
        return readStable(d -> {
            String t = firstDisplayed(recruitmentSidebarLinkMatches, "recruitmentSidebarLink").getText();
            return (t == null || t.isEmpty()) ? null : t;
        });
    }

}
