package pageObject;

import configuration.BaseMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObject.footer.FooterBottom;
import pageObject.footer.FooterPrimary;
import pageObject.footer.FooterSecondary;
import pageObject.header.Header;
import pageObject.menu.Menu;

public class PageObjectInitializer extends BaseMethod {

    protected WebDriver driver;

    public PageObjectInitializer(WebDriver driver) {
        this.driver = driver;
    }

    public Header Header() {
        return PageFactory.initElements(driver, Header.class);
    }

    public Menu Menu() {
        return PageFactory.initElements(driver, Menu.class);
    }

    public FooterSecondary FooterSecondary() {
        return PageFactory.initElements(driver, FooterSecondary.class);
    }

    public FooterPrimary FooterPrimary() {
        return PageFactory.initElements(driver, FooterPrimary.class);
    }

    public FooterBottom FooterBottom() {
        return PageFactory.initElements(driver, FooterBottom.class);
    }

    public SignIn SignIn() {
        return PageFactory.initElements(driver, SignIn.class);
    }

    public SignUp SignUp() {
        return PageFactory.initElements(driver, SignUp.class);
    }

    public Account Account() {
        return PageFactory.initElements(driver, Account.class);
    }

    public AccountInformation AccountInformation() {
        return PageFactory.initElements(driver, AccountInformation.class);
    }

    public MyStoredPaymentAccounts MyStoredPaymentAccounts() {
        return PageFactory.initElements(driver, MyStoredPaymentAccounts.class);
    }

    public AddressBook AddressBook() {
        return PageFactory.initElements(driver, AddressBook.class);
    }

    public Home Home() {
        return PageFactory.initElements(driver, Home.class);
    }

    public Cart Cart() {
        return PageFactory.initElements(driver, Cart.class);
    }

    public MenuPageWithProducts MenuPageWithProducts() {
        return PageFactory.initElements(driver, MenuPageWithProducts.class);
    }

    public Wishlist Wishlist() {
        return PageFactory.initElements(driver, Wishlist.class);
    }

    public Search Search() {
        return PageFactory.initElements(driver, Search.class);
    }

    public AdvancedSearch AdvancedSearch() {
        return PageFactory.initElements(driver, AdvancedSearch.class);
    }

    public SearchTerms SearchTerms() {
        return PageFactory.initElements(driver, SearchTerms.class);
    }

    public Subscribe Subscribe() {
        return PageFactory.initElements(driver, Subscribe.class);
    }

    public ContactUs ContactUs() {
        return PageFactory.initElements(driver, ContactUs.class);
    }

    public CompareLeftSideBar CompareLeftSideBar() {
        return PageFactory.initElements(driver, CompareLeftSideBar.class);
    }

    public WishlistLeftSideBar WishlistLeftSideBar() {
        return PageFactory.initElements(driver, WishlistLeftSideBar.class);
    }

    public CompareProducts CompareProducts() {
        return PageFactory.initElements(driver, CompareProducts.class);
    }

    public ShoppingOptions ShoppingOptions() {
        return PageFactory.initElements(driver, ShoppingOptions.class);
    }

    public Checkout Checkout() {
        return PageFactory.initElements(driver, Checkout.class);
    }

    public EditCurrentProduct EditCurrentProduct() {
        return PageFactory.initElements(driver, EditCurrentProduct.class);
    }

    public CartPopUp CartPopUp() {
        return PageFactory.initElements(driver, CartPopUp.class);
    }

    public MyOrders MyOrders() {
        return PageFactory.initElements(driver, MyOrders.class);
    }

    public ViewOrder ViewOrder() {
        return PageFactory.initElements(driver, ViewOrder.class);
    }

    public WishListSharing WishListSharing() {
        return PageFactory.initElements(driver, WishListSharing.class);
    }

    public ProductDetails ProductDetails() {
        return PageFactory.initElements(driver, ProductDetails.class);
    }

    public NewsletterSubscriptions NewsletterSubscriptions() {
        return PageFactory.initElements(driver, NewsletterSubscriptions.class);
    }

    public MyReward MyReward() {
        return PageFactory.initElements(driver, MyReward.class);
    }

    public MyReferral MyReferral() {
        return PageFactory.initElements(driver, MyReferral.class);
    }

    public Transactions Transactions() {
        return PageFactory.initElements(driver, Transactions.class);
    }

    public AccountDashboard AccountDashboard() {
        return PageFactory.initElements(driver, AccountDashboard.class);
    }

    public GiftCardList GiftCardList() {
        return PageFactory.initElements(driver, GiftCardList.class);
    }

    public MembershipList MembershipList() {
        return PageFactory.initElements(driver, MembershipList.class);
    }

    public GiftCard GiftCard() {
        return PageFactory.initElements(driver, GiftCard.class);
    }

    public EmailToFriend EmailToFriend() {
        return PageFactory.initElements(driver, EmailToFriend.class);
    }

    public ProductDetailsReview ProductDetailsReview() {
        return PageFactory.initElements(driver, ProductDetailsReview.class);
    }

    public MyProductReview MyProductReview() {
        return PageFactory.initElements(driver, MyProductReview.class);
    }

    public MyGiftCards MyGiftCards() {
        return PageFactory.initElements(driver, MyGiftCards.class);
    }

    public Membership Membership() {
        return PageFactory.initElements(driver, Membership.class);
    }

    public ServicesList ServicesList() {
        return PageFactory.initElements(driver, ServicesList.class);
    }

    public SiteMap SiteMap() {
        return PageFactory.initElements(driver, SiteMap.class);
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public void clearCart() {
        if (CartPopUp().isElementsInCartPresent()) {
            CartPopUp().clickViewEditCart();
            Cart().waitUntilCartElementsLoaded();
            Cart().clearShoppingCartButtonClick();
            CartPopUp().verifyCartPopUpIsEmpty();
        }
    }


}
