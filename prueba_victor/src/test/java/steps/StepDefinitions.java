package steps;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import static org.junit.Assert.*;

public class StepDefinitions {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void SetUp() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("que el usuario abre el navegador y navega a {string}")
    public void AbrirNavegador(String url) {
        driver.get(url);
    }

    @When("el usuario inicia sesión con usuario {string} y contraseña {string}")
    public void IniciarSesion(String user, String password) {
        driver.findElement(By.id("user-name")).sendKeys(user);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    @Then("debería ver la lista de productos")
    public void ValidarInicioSesion() {
        WebElement inventoryContainer = wait.until(driver -> driver.findElement(By.id("inventory_container")));
        assertTrue(inventoryContainer.isDisplayed());
    }

    @When("el usuario selecciona {string} y lo agrega al carrito")
    public void SeleccionarProducto(String producto) {
        driver.findElement(By.xpath("//div[text()='" + producto + "']/ancestor::div[@class='inventory_item']//button")).click();
    }

    @When("va al carrito")
    public void IrAlCarrito() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    @Then("debería ver {string} en el carrito")
    public void ValidarProductoEnCarrito(String producto) {
        WebElement item = wait.until(driver -> driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + producto + "']")));
        assertTrue(item.isDisplayed());
    }

    @When("el usuario procede al checkout")
    public void IrAlCheckout() {
        driver.findElement(By.id("checkout")).click();
    }

    @When("ingresa sus datos {string}, {string}, {string}")
    public void IngresarDatosCheckout(String nombre, String apellido, String codigoPostal) {
        driver.findElement(By.id("first-name")).sendKeys(nombre);
        driver.findElement(By.id("last-name")).sendKeys(apellido);
        driver.findElement(By.id("postal-code")).sendKeys(codigoPostal);
        driver.findElement(By.id("continue")).click();
    }

    @When("finaliza la compra")
    public void FinalizarCompra() {
        driver.findElement(By.id("finish")).click();
    }

    @Then("deberia ver el mensaje {string}")
    public void ValidarMensajeFinal(String mensajeEsperado) {
        WebElement mensaje = wait.until(driver -> driver.findElement(By.className("complete-header")));
        assertEquals(mensajeEsperado, mensaje.getText());
    }

    @And("se cierra el navegador")
    public void CerrarNavegador() {
        driver.quit();
    }

    @After
    public void TearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}