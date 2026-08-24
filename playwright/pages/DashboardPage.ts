import { Page, expect } from '@playwright/test';

/**
 * DashboardPage — consolidated by TestForge from executed runs.
 * Page URL: https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index
 */
export class DashboardPage {
  constructor(private page: Page) {}

  async expectDashboardNavLinkVisible() {
    await expect(this.page.locator('li.oxd-main-menu-item-wrapper:nth-of-type(8) > a.oxd-main-menu-item > span.oxd-text').first()).toBeVisible();
  }

  async expectSidepanelNavigationVisible() {
    await expect(this.page.locator('nav[aria-label="Sidepanel"]').first()).toBeVisible();
  }

  async expectTopbarMenuNavigationVisible() {
    await expect(this.page.locator('nav[aria-label="Topbar Menu"]').first()).toBeVisible();
  }

  async expectAdminSidebarLinkVisible() {
    await expect(this.page.locator('li.oxd-main-menu-item-wrapper:nth-of-type(1) > a.oxd-main-menu-item > span.oxd-text').first()).toBeVisible();
  }

  async expectPIMSidebarLinkVisible() {
    await expect(this.page.locator('li.oxd-main-menu-item-wrapper:nth-of-type(2) > a.oxd-main-menu-item > span.oxd-text').first()).toBeVisible();
  }

  async expectLeaveSidebarLinkVisible() {
    await expect(this.page.locator('li.oxd-main-menu-item-wrapper:nth-of-type(3) > a.oxd-main-menu-item > span.oxd-text').first()).toBeVisible();
  }

  async expectRecruitmentSidebarLinkVisible() {
    await expect(this.page.locator('li.oxd-main-menu-item-wrapper:nth-of-type(5) > a.oxd-main-menu-item > span.oxd-text').first()).toBeVisible();
  }

}
