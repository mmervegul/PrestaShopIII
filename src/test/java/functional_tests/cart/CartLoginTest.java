package functional_tests.cart;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utilities.ConfigurationReader;
import utilities.TestBase;

public class CartLoginTest extends TestBase {

    public String cartExpected;
    public String cartActual;

    @Test(priority = 0)
    public void cartLoginTest(){
        HomePage homePage = new HomePage();

        // 2.Go to http://automationpractice.com/index.php
        homePage.openUrl();

        // 3.Add any product in the homepage to the cart
        actions.moveToElement(homePage.hoverOver("Blouse", 1)).perform();
        homePage.addToCard(2).click();

        // 4.Hover over the cart icon
        actions.moveToElement(homePage.hoverOver).perform();

        // 5.Verify that cart contains the product
        cartActual = homePage.checkProductInTheCart("Blouse").getText();
        cartExpected= "Blouse";
        Assert.assertEquals(cartActual, cartExpected);

        // 6.Login as any valid user
        homePage.signIn();

        String username = ConfigurationReader.getProperty("username");
        String password =ConfigurationReader.getProperty("password");
        homePage.login(username, password);

        actions.moveToElement(homePage.hoverOver).perform();

        cartActual = homePage.checkProductInTheCart("Blouse").getText();
        cartExpected = "Blouse";
        Assert.assertEquals(cartActual, cartExpected);

    }

    @Test(priority = 1)
    public void cartLogoutTest(){
        HomePage homePage = new HomePage();

        homePage.openUrl();

        // 3.Login as any valid user
        homePage.signIn();

        String username = ConfigurationReader.getProperty("username");
        String password = ConfigurationReader.getProperty("password");
        homePage.login(username, password);

        // 4.Go back to home page
        homePage.goToHomePage.click();

        // 5.Add any product in the homepage to the cart
        actions.moveToElement(homePage.hoverOver("Blouse", 1)).perform();
        homePage.addToCard(2).click();

        // 6.Hover over the cart icon
        actions.moveToElement(homePage.hoverOver).perform();

        // 7.Verify that cart contains the product
        cartActual = homePage.checkProductInTheCart("Blouse").getText();
        cartExpected = "Blouse";
        Assert.assertEquals(cartActual, cartExpected);

        // 8.Logout
        homePage.logout.click();

        // 9.Verify the cart contains the word empty
        cartExpected = "empty";
        cartActual = homePage.hoverOver.getText();
        Assert.assertTrue(cartActual.contains(cartExpected));
    }

    @Test(priority = 2)
    public void cartIconDeleteTest(){
        HomePage homePage = new HomePage();
        homePage.openUrl();

        // 3.Add any product in the homepage to the cart
        actions.moveToElement(homePage.hoverOver("Blouse", 1)).perform();
        homePage.addToCard(2).click();

        // 4.Click on Continue shopping
        homePage.continueShopping.click();

        // 5.Hover over the cart icon
        actions.moveToElement(homePage.hoverOver).perform();

        // 6.Click the x to delete the product
        homePage.deleteFromCart.click();

        // 7.Verify word empty is displayed in the Cart icon
        homePage.hoverOver.click();
        cartExpected = "empty";
        cartActual = homePage.hoverOver.getText();
        Assert.assertTrue(cartActual.contains(cartExpected));

    }

    @Test
    public void cartCheckoutDeleteTest(){
        HomePage homePage = new HomePage();
        homePage.openUrl();

        // 3.Add any product in the homepage to the cart
        actions.moveToElement(homePage.hoverOver("Blouse", 1)).perform();
        homePage.addToCard(2).click();

        // 4.Click on Continue shopping
        homePage.continueShopping.click();

        // 5.Add another product in the homepage to the cart
        actions.moveToElement(homePage.hoverOver("Faded Short Sleeve T-shirts", 1)).perform();
        homePage.addToCard(1).click();

        // 6.Click on Proceed to checkout
        homePage.proceedToCheckout.click();

        // 7.Verify message Your shopping cart contains: 2 Products
        cartExpected = "Your shopping cart contains: 2 Products";
        cartActual = homePage.verifyMessageforProduct.getText();
        Assert.assertEquals(cartActual, cartExpected);

        // 8.Click the delete icon to delete one of the products
        homePage.cartQuantityDelete(1).click();

        // 9.Verify message Your shopping cart contains: 1 Product
        homePage.hoverOver.click();
        cartExpected = "Your shopping cart contains: 1 Product";
        cartActual = homePage.verifyMessageforProduct.getText();
        Assert.assertEquals(cartActual, cartExpected);

        // 10.Click the delete icon to delete the second product
        homePage.cartQuantityDelete(1).click();

        // 11.Verify message Your shopping cart is empty
        homePage.hoverOver.click();
        cartExpected = "Your shopping cart is empty.";
        cartActual = homePage.getVerifyMessageForEmpty.getText();
        Assert.assertEquals(cartActual, cartExpected);


    }

}
