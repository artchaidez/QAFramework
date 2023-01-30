package pages;

import autoFramework.UIBase;

public class Pages extends UIBase {

    public PodiumBubble podiumBubble;
    public PodiumModal podiumModal;

    public PodiumTermsOfServicePage podiumTermsOfService;

    public TrelloSignInPage trelloSignInPage;


    public Pages()
    {
        podiumBubble = new PodiumBubble();
        podiumModal = new PodiumModal();
        podiumTermsOfService = new PodiumTermsOfServicePage();
        trelloSignInPage = new TrelloSignInPage();
    }
}
