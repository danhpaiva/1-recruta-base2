package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExemplosSelenium {
    @Test
    public void helloWorld(){
        WebDriver driver = new ChromeDriver();

        driver.navigate().to("http://google.com");

        Assert.assertEquals("Google", driver.getTitle());

        driver.quit();
    }

    /* Acessar o sistema Mantis
    preencher usuário
    preencher senha
    clicar em login
    Usuário autenticado com sucesso! */
    @Test
    public void efetuarLoginComSucesso(){
    WebDriver driver = new ChromeDriver();

    driver.navigate().to("https://mantis-prova.base2.com.br/");

        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        // Não utilizar a árvore abaixo pois qualquer modificação na url irá quebrar o teste
        //WebElement loginButton = driver.findElement(By.xpath("/html/body/div[3]/form/table/tbody/tr[6]/td/input"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@Value=\"Login\"]"));

        usernameField.sendKeys("treinamento01");
        passwordField.sendKeys("123456");
        loginButton.click();

        WebElement userLoggedText = driver.findElement(By.xpath("//td[@class='login-info-left']/span[@class='italic']"));

        String userLogged = userLoggedText.getText();

        Assert.assertEquals("Treinamento01", userLogged);
    }

    @Test
    public void exemploSinconizacao(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("http://blackmirror.crowdtest.me.s3-website-us-east-1.amazonaws.com/auth");

        WebElement prosseguirButton = driver.findElement(By.linkText("Prosseguir"));
        prosseguirButton.click();

        WebElement emailField = driver.findElement(By.id("login"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement entrarButton = driver.findElement(By.xpath("//button[text() = \"ENTRAR\"]"));

        emailField.sendKeys("danpaiva@live.com");
        passwordField.sendKeys("123456");
        entrarButton.click();

        //Instancia elemento de espera
        WebDriverWait wait = new WebDriverWait(driver, 10);

        //Esperar elemento existir no html
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@class='login-error']/span[@class='error-msg']")));

        //Existe? Se sim, instancia Element
        WebElement erroText = driver.findElement(By.xpath("//p[@class='login-error']/span[@class='error-msg']"));

        wait.until(ExpectedConditions.visibilityOf(erroText));

        String erroExibido = erroText.getText();

        Assert.assertEquals("E-mail ou senha inválidos.", erroExibido);
    }
}
