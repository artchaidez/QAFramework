package pages;

import autoFramework.UIBase;

public class Pages extends UIBase {

    public PodiumBubble podiumBubble;
    public PodiumModal podiumModal;

    public PodiumTermsOfService podiumTermsOfService;

    public TrelloSignInPage trelloSignInPage;


    public Pages()
    {
        podiumBubble = new PodiumBubble();
        podiumModal = new PodiumModal();
        podiumTermsOfService = new PodiumTermsOfService();
        trelloSignInPage = new TrelloSignInPage();
    }
}
