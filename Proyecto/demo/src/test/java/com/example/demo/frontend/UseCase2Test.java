package com.example.demo.frontend;

import java.time.Duration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
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

        wait.until(ExpectedConditions.urlToBe(BASE_URL + "/portal-admin"));
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(BASE_URL + "/portal-admin");

        // 2. Ingreso al dashboard
        WebElement btnDashboard = driver.findElement(By.id("btnDashboard"));
        btnDashboard.click();
        wait.until(ExpectedConditions.urlToBe(BASE_URL + "/dashboard"));
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(BASE_URL + "/dashboard");

        WebElement Quantity = driver.findElement(By.id("Quantity"));
        WebElement Revenue = driver.findElement(By.id("Revenue"));

        String quantityText = Quantity.getText();
        int initialQuantity = Integer.parseInt(Quantity.getText());

        String revenueText = Revenue.getText().replace("Col", "").replace(",", "").trim();
        double initialRevenue = Double.parseDouble(revenueText);

        System.out.println("Quantity: " + quantityText);
        System.out.println("Revenue: " + revenueText);
        Assertions.assertThat(quantityText).isNotEmpty();
        Assertions.assertThat(revenueText).isNotEmpty();

        // 3. Ir a login de veterinario
        WebElement btnLoginVet = driver.findElement(By.id("btnLoginVet"));
        btnLoginVet.click();
        wait.until(ExpectedConditions.urlToBe(BASE_URL + "/login/vet"));
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(BASE_URL + "/login/vet");

        WebElement inputCorreo = driver.findElement(By.id("correo"));
        WebElement inputContrasena = driver.findElement(By.id("contrasena"));
        WebElement btnLogin = driver.findElement(By.id("btnLogin"));
        inputCorreo.sendKeys("correo@gmail.com");
        inputContrasena.sendKeys("12345");
        btnLogin.click();

        wait.until(ExpectedConditions.urlToBe(BASE_URL + "/portal-veterinario"));
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(BASE_URL + "/portal-veterinario");

        // 4. Sección de tratamientos
        WebElement btnTratamientos = driver.findElement(By.id("btnTratamientos"));
        btnTratamientos.click();
        wait.until(ExpectedConditions.urlToBe(BASE_URL + "/veterinario-tratamiento"));
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(BASE_URL + "/veterinario-tratamiento");

        // 5. Dar tratamiento a la mascota
        Select selectMascota = new Select(driver.findElement(By.id("pet")));
        Select selectMedicamento = new Select(driver.findElement(By.id("medicamento")));
        WebElement inputCantidad = driver.findElement(By.id("cantidad"));
        WebElement btnTratamiento = driver.findElement(By.id("btnTratamiento"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cantidad")));
        selectMascota.selectByIndex(0);
        selectMedicamento.selectByIndex(0);
        inputCantidad.clear();  
        inputCantidad.sendKeys("1");
        btnTratamiento.click();

        // 6. Lista de mascotas
        WebElement btnMascota = driver.findElement(By.id("btnMascota"));
        btnMascota.click();

        wait.until(ExpectedConditions.urlToBe(BASE_URL + "/pets")); // Cambia a la URL de las mascotas
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(BASE_URL + "/pets");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".pet-list table tbody tr")));

        // Selecciona el botón "Detalles" en la primera fila de la tabla
        WebElement firstPetDetailsButton = driver.findElement(By.cssSelector(".pet-list table tbody tr:first-child .actions .butons"));
        firstPetDetailsButton.click();

        wait.until(ExpectedConditions.urlToBe(BASE_URL + "/pets/1")); // Cambia a la URL de las mascotas
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(BASE_URL + "/pets/1");

        WebElement btnLogearAdmin = driver.findElement(By.id("btnLogearAdmin"));
        btnLogearAdmin.click();
        wait.until(ExpectedConditions.urlToBe(BASE_URL + "/login/admin"));
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(BASE_URL + "/login/admin");

        WebElement inputCorreoAdmin2 = driver.findElement(By.id("correo"));
        WebElement inputContrasenaAdmin2 = driver.findElement(By.id("contrasena"));   
        WebElement btnLoginAdmin2 = driver.findElement(By.id("btnLogin"));
        inputCorreoAdmin2.sendKeys("samuel@example.com");
        inputContrasenaAdmin2.sendKeys("12345");
        btnLoginAdmin2.click();

        wait.until(ExpectedConditions.urlToBe(BASE_URL + "/portal-admin"));
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(BASE_URL + "/portal-admin");
        WebElement btnDashboard2 = driver.findElement(By.id("btnDashboard"));
        btnDashboard2.click();
        wait.until(ExpectedConditions.urlToBe(BASE_URL + "/dashboard"));
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(BASE_URL + "/dashboard");


        WebElement Quantity2 = driver.findElement(By.id("Quantity"));
        int finalQuantity = Integer.parseInt(Quantity2.getText());

        WebElement Revenue2 = driver.findElement(By.id("Revenue"));
        String finalRevenueText = Revenue2.getText().replace("Col", "").replace(",", "").trim();
        double finalRevenue = Double.parseDouble(finalRevenueText);

        Assertions.assertThat(initialQuantity).isLessThan(finalQuantity);
        Assertions.assertThat(initialRevenue).isLessThan(finalRevenue);

        System.out.println("Initial Quantity: " + initialQuantity);
        System.out.println("Final Quantity: " + finalQuantity);
        System.out.println("Initial Revenue: " + initialRevenue);
        System.out.println("Final Revenue: " + finalRevenue);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
