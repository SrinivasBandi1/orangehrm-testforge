import { Page, expect } from '@playwright/test';

/**
 * LoginPage — consolidated by TestForge from executed runs.
 * Page URL: https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
 */
export class LoginPage {
  constructor(private page: Page) {}

  async enterUsernameInputField(value: string) {
    await this.page.locator('input[name="username"]').first().fill(value);
  }

  async expectUsernameInputFieldVisible() {
    await expect(this.page.locator('input[name="username"]').first()).toBeVisible();
  }

  async enterPasswordInputField(value: string) {
    await this.page.locator('input[name="password"]').first().fill(value);
  }

  async expectPasswordInputFieldVisible() {
    await expect(this.page.locator('input[name="password"]').first()).toBeVisible();
  }

  async clickLoginSubmitButton() {
    await this.page.locator('button:has-text("Login"), a:has-text("Login"), [role=button]:has-text("Login")').first().click();
  }

  async expectLoginSubmitButton2Visible() {
    await expect(this.page.locator('h5.oxd-text.orangehrm-login-title').first()).toBeVisible();
  }

  async expectOrangeHRMIncFooterLinkVisible() {
    await expect(this.page.locator('text="OrangeHRM, Inc"').first()).toBeVisible();
  }

}
