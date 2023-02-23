package pages;

import autoFramework.UIBase;

public class Pages extends UIBase {

    public PodiumBubble PodiumBubble;
    public PodiumModal PodiumModal;

    public PodiumTermsOfServicePage PodiumTermsOfService;

    public TrelloSignInPage TrelloSignInPage;

    public InvoiceCloudPage InvoiceCloudPage;

    public ElementalSeleniumPage ElementalSeleniumPage;


    public Pages()
    {
        PodiumBubble = new PodiumBubble();
        PodiumModal = new PodiumModal();
        PodiumTermsOfService = new PodiumTermsOfServicePage();
        TrelloSignInPage = new TrelloSignInPage();
        InvoiceCloudPage = new InvoiceCloudPage();
        ElementalSeleniumPage = new ElementalSeleniumPage();
    }
}
