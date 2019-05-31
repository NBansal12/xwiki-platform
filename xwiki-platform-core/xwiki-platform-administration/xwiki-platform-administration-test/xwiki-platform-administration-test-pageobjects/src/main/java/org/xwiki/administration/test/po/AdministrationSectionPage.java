/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.administration.test.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.xwiki.model.reference.DocumentReference;
import org.xwiki.model.reference.SpaceReference;
import org.xwiki.test.ui.po.FormContainerElement;
import org.xwiki.test.ui.po.ViewPage;

/**
 * Represents common actions available in all Administration pages.
 *
 * @version $Id$
 * @since 4.2M1
 */
public class AdministrationSectionPage extends ViewPage
{
    @FindBy(xpath = "//input[@type='submit'][@name='formactionsac']")
    private WebElement saveButton;

    /**
     * The admin-page-content div is being treated as a form since it may contain multiple forms and we want to be able
     * to access elements in them all.
     */
    @FindBy(xpath = "//div[@id='admin-page-content']")
    private WebElement formContainer;

    private final String section;

    public AdministrationSectionPage(String section)
    {
        this.section = section;
    }

    public static AdministrationSectionPage gotoPage(String section)
    {
        getUtil().gotoPage(getURL(section));
        return new AdministrationSectionPage(section);
    }

    /**
     * Go to the administration section of a given space reference.
     * @since 11.3RC1
     */
    public static AdministrationSectionPage gotoSpaceAdministration(SpaceReference spaceReference, String section)
    {
        getUtil().gotoPage(getURL(section, spaceReference));
        return new AdministrationSectionPage(section);
    }

    /**
     * @param section the section ID
     * @return the URL of the administration section corresponding to the current {@link AdministrationSectionPage}
     *         instance
     * @since 6.3M1
     */
    public static String getURL(String section)
    {
        return getURL(section, null);
    }

    /**
     * @param section the section ID
     * @param spaceReference the space where we want to get the admin page
     * @return the URL of the administration section corresponding to the current {@link AdministrationSectionPage}
     *         instance
     * @since 11.3RC1
     */
    public static String getURL(String section, SpaceReference spaceReference)
    {
        String url;
        if (spaceReference == null) {
            url = getUtil().getURL("XWiki", "XWikiPreferences", "admin", String.format("section=%s", section));
        } else {
            DocumentReference documentReference = new DocumentReference("WebPreferences", spaceReference);
            url = getUtil().getURL(documentReference, "admin", String.format("editor=spaceadmin&section=%s", section));
        }

        return url;
    }

    public String getURL()
    {
        return getURL(this.section);
    }

    public void clickSave()
    {
        this.saveButton.click();
    }

    public FormContainerElement getFormContainerElement()
    {
        return new FormContainerElement(this.formContainer);
    }

    public FormContainerElement getFormContainerElement(String section, String documentClass)
    {
        String formContainerId = String.format("%s_%s", section, documentClass);
        return new FormContainerElement(By.id(formContainerId));
    }

    public boolean hasLink(String linkName)
    {
        String xPathSelector = String.format("//form/fieldset//a[@href='%s']", linkName);
        return getDriver().hasElementWithoutWaiting(By.xpath(xPathSelector));
    }

    public boolean hasHeading(int level, String headingId)
    {
        String xPath = String.format("//div[@id='admin-page-content']/h%s[@id='%s']/span", level, headingId);
        return getDriver().hasElementWithoutWaiting(By.xpath(xPath));
    }
}
