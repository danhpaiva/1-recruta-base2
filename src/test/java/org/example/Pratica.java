package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Pratica {
    @Test
    public void CadastrarNovaOcorrencia(){
        WebDriver driver = new ChromeDriver();

        driver.navigate().to("https://mantis-prova.base2.com.br/");

        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));

        WebElement loginButton = driver.findElement(By.xpath("//input[@Value=\"Login\"]"));

        usernameField.sendKeys("treinamento02");
        passwordField.sendKeys("123456");
        loginButton.click();

        WebElement userLoggedText = driver.findElement(By.xpath("//td[@class='login-info-left']/span[@class='italic']"));

        String userLogged = userLoggedText.getText();

        Assert.assertEquals("Treinamento02", userLogged);

        WebElement relatarCaso = driver.findElement(By.linkText("Relatar Caso"));
        relatarCaso.click();

        WebElement categoria = driver.findElement(By.name("category_id"));
        Select comboCategoria = new Select(categoria);
        comboCategoria.selectByVisibleText("[Todos os Projetos] 7EI2PODHPN");

        WebElement frequencia = driver.findElement(By.name("reproducibility"));
        Select comboFrequencia = new Select(frequencia);
        comboFrequencia.selectByVisibleText("sempre");

        WebElement gravidade = driver.findElement(By.name("severity"));
        Select comboGravidade = new Select(gravidade);
        comboGravidade.selectByVisibleText("grande");

        WebElement prioridade = driver.findElement(By.name("priority"));
        Select comboPrioridade = new Select(prioridade);
        comboPrioridade.selectByVisibleText("alta");

        WebElement perfil = driver.findElement(By.name("profile_id"));
        Select comboPerfil = new Select(perfil);
        comboPerfil.selectByVisibleText("PC Windows 7");

        WebElement atribuicao = driver.findElement(By.name("handler_id"));
        Select comboAtribuicao = new Select(atribuicao);
        comboAtribuicao.selectByVisibleText("administrator");

        WebElement summary = driver.findElement(By.name("summary"));
        summary.sendKeys("TESTE - Daniel Paiva");

        WebElement description = driver.findElement(By.name("description"));
        description.sendKeys("TESTE - Daniel Paiva");

        WebElement steps_to_reproduce = driver.findElement(By.name("steps_to_reproduce"));
        steps_to_reproduce.sendKeys("TESTE - Daniel Paiva");

        WebElement additional_info = driver.findElement(By.name("additional_info"));
        additional_info.sendKeys("TESTE - Daniel Paiva");

        WebElement buttonSend = driver.findElement(By.xpath("//input[@class='button']"));
        buttonSend.click();

        // System.out.println(driver.getPageSource()); Para conseguir pegar o sucess da página!

        WebElement success = driver.findElement(By.xpath("//div[@align='center']"));
        String successToString = success.getText();
;       Assert.assertTrue(successToString.contains("Operação realizada com sucesso."));

        successToString = successToString.replace("Operação realizada com sucesso.\n" +
                "[ Visualizar Caso Enviado ", "");
        String numberCase = successToString.replace(" ] [ Ver Casos ]", "");

        WebElement acessNumberCase = driver.findElement(By.cssSelector("a[href$='" + numberCase + "']"));
        acessNumberCase.click();

        WebElement validarCategoria = driver.findElement(By.xpath("//tr[@class='row-1']/td[3]"));
        Assert.assertEquals("[Todos os Projetos] 7EI2PODHPN", validarCategoria.getText());

        WebElement validarFrequencia = driver.findElement(By.xpath("//tr[@class='row-2']/td[6]"));
        Assert.assertEquals("sempre", validarFrequencia.getText());

        WebElement validarGravidade = driver.findElement(By.xpath("//tr[@class='row-2']/td[4]"));
        Assert.assertEquals("grande", validarGravidade.getText());

        WebElement validarResumo = driver.findElement(By.xpath("//tr[@class='row-1']/td[@colspan='5']"));
        String validarResumoSemChamado = validarResumo.getText();
        validarResumoSemChamado = validarResumoSemChamado.replace("000" + numberCase + ": TESTE - Daniel Paiva", "TESTE - Daniel Paiva");
        Assert.assertEquals("TESTE - Daniel Paiva", validarResumoSemChamado);

        WebElement validarDescricao = driver.findElement(By.xpath("//tr[@class='row-2']/td[@colspan='5']"));
        Assert.assertEquals("TESTE - Daniel Paiva", validarDescricao.getText());

        WebElement validarPassos = driver.findElement(By.xpath("//td[text()='Passos para Reproduzir']/following-sibling::td"));
        Assert.assertEquals("TESTE - Daniel Paiva", validarPassos.getText());
    }
}
