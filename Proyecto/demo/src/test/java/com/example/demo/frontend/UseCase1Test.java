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
import java.lang.Thread;



import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UseCase1Test {

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
        // Maximizar la ventana del navegador
        driver.manage().window().maximize();
    }

    @Test
    public void casoUso1() throws InterruptedException {
        // 1. El veterinario intenta ingresar con credenciales incorrectas
        driver.get(BASE_URL + "/login/vet");
        WebElement inputCorreo = driver.findElement(By.id("correo"));
        WebElement inputContrasena = driver.findElement(By.id("contrasena"));
        WebElement btnLogin = driver.findElement(By.id("btnLogin"));

        // Intentar con credenciales incorrectas
        inputCorreo.sendKeys("usuarioIncorrecto");
        inputContrasena.sendKeys("contrasenaIncorrecta");
        btnLogin.click();

        // Verifica que el mensaje de error aparece
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mensajeError")));
        Assertions.assertThat(driver.findElement(By.id("mensajeError")).getText()).isEqualTo("Credenciales incorrectas.");

        // 2. Intentar ingresar con credenciales correctas
        inputCorreo.clear();
        inputContrasena.clear();
        inputCorreo.sendKeys("correo@gmail.com"); // Cambia a un usuario válido
        inputContrasena.sendKeys("12345"); // Cambia a una contraseña válida
        btnLogin.click();

        // Verifica que se ingresa correctamente
        wait.until(ExpectedConditions.urlToBe(BASE_URL + "/portal-veterinario")); // Cambia a la URL del dashboard
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(BASE_URL + "/portal-veterinario");
        
        // 3. Ir a la sección de clientes
        WebElement btnClientes = driver.findElement(By.id("btnClientes"));
        btnClientes.click();

        // 4. Ir a la sección de registro de clientes
        WebElement btnRegistroClientes = driver.findElement(By.id("btnRegistroClientes"));
        btnRegistroClientes.click();
        // 5. Registrar un nuevo cliente
        WebElement inputNombre = driver.findElement(By.id("nombre"));
        WebElement inputCedula = driver.findElement(By.id("cedula"));
        WebElement inputCorreoOwner = driver.findElement(By.id("correo"));
        WebElement inputTelefono = driver.findElement(By.id("celular"));

        // Rellenar el formulario del dueño con datos erroneos
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nombre")));
        inputNombre.sendKeys("Goku");
        inputCedula.sendKeys("123456789");
        inputCorreoOwner.sendKeys("dbzgmail.com");
        inputTelefono.sendKeys("(3390) 458-3742");

        // Primero, realiza un scroll hacia abajo en la página
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btnRegistrarCliente = driver.findElement(By.id("btnRegistrarCliente"));
        
        // Forzar el desplazamiento hasta que el botón sea visible en la pantalla
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000);");
        Thread.sleep(100);

        // Esperar que el botón esté listo para hacer clic y ejecutar el clic
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnRegistrarCliente")));
        btnRegistrarCliente.click();

        inputNombre.clear();
        inputCedula.clear();
        inputCorreoOwner.clear();
        inputTelefono.clear();

        // Rellenar el formulario del dueño
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nombre")));
        inputNombre.sendKeys("Goku");
        inputCedula.sendKeys("123456789");
        inputCorreoOwner.sendKeys("dbz@gmail.com");
        inputTelefono.sendKeys("(3390) 458-3742");
        btnRegistrarCliente.click();

        wait.until(ExpectedConditions.urlToBe(BASE_URL + "/owners")); // Cambia a la URL del dashboard
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(BASE_URL + "/owners");

        // 6. Ir a la sección de mascotas
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -2000);");
        Thread.sleep(100);
        WebElement btnMascota = driver.findElement(By.id("btnMascota"));
        btnMascota.click();

        wait.until(ExpectedConditions.urlToBe(BASE_URL + "/pets")); // Cambia a la URL del dashboard
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(BASE_URL + "/pets");
        
        // 7. Registrar una mascota asociada al dueño
        
        WebElement btnRegistrarMascota = driver.findElement(By.id("btnRegistrarMascota"));
        btnRegistrarMascota.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nombre")));
        WebElement inputNombremascota = driver.findElement(By.id("nombre"));
        WebElement inputRaza = driver.findElement(By.id("raza"));
        WebElement inputImageUrl = driver.findElement(By.id("imageUrl"));
        WebElement inputEdad = driver.findElement(By.id("edad"));
        WebElement inputPeso = driver.findElement(By.id("peso"));
        WebElement inputEnfermedad = driver.findElement(By.id("enfermedad"));
        Select selectOwner = new Select(driver.findElement(By.id("owner")));
        

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nombre")));
        inputNombremascota.sendKeys("Ozaru");
        inputRaza.sendKeys("Saiyan");
        inputImageUrl.sendKeys("https://example.com/img.jpg");
        inputEdad.clear();
        inputPeso.clear();
        inputEdad.sendKeys("5");
        inputPeso.sendKeys("25.0");
        inputEnfermedad.sendKeys("Dermatitis");
        
        // Seleccionar un dueño
        selectOwner.selectByValue("56");
        // 8. Enviar el formulario
        WebElement btnAgregarMascota = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnAgregarMascota"))); // Asegúrate de que el ID sea correcto

        // Opción 1: Esperar a que el botón esté visible y clickeable
        btnAgregarMascota = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnAgregarMascota"))); // Opción de espera
         
        // Opción 2: Desplazarse al botón
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnAgregarMascota);
        Thread.sleep(300);
        btnAgregarMascota.click();
        
        wait.until(ExpectedConditions.urlToBe(BASE_URL + "/pets")); // Cambia a la URL de lista de mascotas
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(BASE_URL + "/pets");

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -2000);");
        Thread.sleep(100);
        // 9. Ir a al login de los propietarios
        WebElement btnLoginOwner = driver.findElement(By.id("btnLoginOwner"));
        btnLoginOwner.click();

        wait.until(ExpectedConditions.urlToBe(BASE_URL + "/login/owner")); // Cambia a la URL del login
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(BASE_URL + "/login/owner");

        // 10. Iniciar sesión con un propietario existente
        WebElement inputCedulaOwner = driver.findElement(By.id("cedula"));
        WebElement btnIniciarSesion = driver.findElement(By.id("btnIniciarSesion"));

        // Rellenar el formulario de inicio de sesión
        inputCedulaOwner.sendKeys("123456789");
        btnIniciarSesion.click();
        
        wait.until(ExpectedConditions.urlToBe(BASE_URL + "/owner-pets-list/56")); // Cambia a la URL del dashboard
        Assertions.assertThat(driver.getCurrentUrl()).isEqualTo(BASE_URL + "/owner-pets-list/56");
    }

    @AfterEach
    void tearDown() {
        //driver.quit(); // Cierra el navegador después de cada prueba  
    }
}
