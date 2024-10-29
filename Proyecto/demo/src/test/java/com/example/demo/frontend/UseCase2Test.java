package com.example.demo.frontend;

import java.time.Duration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UseCase2Test {

    private final String BASE_URL = "http://localhost:4200"; 
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void init() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-extensions");
        this.driver = new ChromeDriver(chromeOptions);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void casoUso2() {

        // 0. Administrador se logea al Dashboard
        driver.get(BASE_URL + "/login/admin");
        wait.until(ExpectedConditions.urlToBe(BASE_URL + "/login/admin"));
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(BASE_URL + "/login/admin");

        // 1. Se logea el Administrador 
        WebElement inputCorreoAdmin = driver.findElement(By.id("correo"));
        WebElement inputContrasenaAdmin = driver.findElement(By.id("contrasena"));   
        WebElement btnLoginAdmin = driver.findElement(By.id("btnLogin"));
        inputCorreoAdmin.sendKeys("samuel@example.com");
        inputContrasenaAdmin.sendKeys("12345");
        btnLoginAdmin.click();

        wait.until(ExpectedConditions.urlToBe(BASE_URL + "/portal-admin")); // Cambia a la URL del portal del administrador
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(BASE_URL + "/portal-admin");

        // 2. Se ingresa al dashboard de la veterinaria
        WebElement btnDashboard = driver.findElement(By.id("btnDashboard"));
        btnDashboard.click();

        wait.until(ExpectedConditions.urlToBe(BASE_URL + "/dashboard")); // Cambia a la URL del dashboard
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(BASE_URL + "/dashboard");
        WebElement Quantity = driver.findElement(By.id("Quantity"));
        WebElement Revenue = driver.findElement(By.id("Revenue"));
        String quantityText = Quantity.getText();
        String revenueText = Revenue.getText();

        
        WebElement inputCorreo = driver.findElement(By.id("correo"));
        WebElement inputContrasena = driver.findElement(By.id("contrasena"));
        WebElement btnLogin = driver.findElement(By.id("btnLogin"));
        inputCorreo.sendKeys("correo@gmail.com"); // Cambia a un usuario válido
        inputContrasena.sendKeys("12345"); // Cambia a una contraseña válida
        btnLogin.click();
    }

    @AfterEach
    void tearDown() {
        //driver.quit();
    }
}
