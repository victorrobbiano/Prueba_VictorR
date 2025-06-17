Feature: Compra en SauceDemo

  Scenario: Comprar un producto exitosamente
    Given que el usuario abre el navegador y navega a "https://www.saucedemo.com/"
    When el usuario inicia sesión con usuario "standard_user" y contraseña "secret_sauce"
    Then debería ver la lista de productos
    When el usuario selecciona "Sauce Labs Backpack" y lo agrega al carrito
    And va al carrito
    Then debería ver "Sauce Labs Backpack" en el carrito
    When el usuario procede al checkout
    And ingresa sus datos "John", "Doe", "12345"
    And finaliza la compra
    Then deberia ver el mensaje "Thank you for your order!"
    And se cierra el navegador