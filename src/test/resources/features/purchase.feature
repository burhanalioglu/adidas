Feature: Laptop Purchase feature

  Background:
    Given The user is on the main page
    When The user selects "Laptops" as category

    @wip
  Scenario Outline: Navigate to product detail page
    And The user clicks on "<product name>" in Main page
    Then The user should be on "<product name>" detail page
    Examples:
      | product name |
      | Sony vaio ii5 |
      | Dell i7 8gb  |

  Scenario: Add product to cart and delete
    And The user clicks on "Dell i7 8gb" in Main page
    And The user adds the product to cart
    And The user navigates to "Cart"
    Then The user should see "Dell i7 8gb" on the list
    When The user deletes "Dell i7 8gb" from cart
    Then The user should not see "Dell i7 8gb" on the list

  Scenario: Purchase functionality
    And The user clicks on "Dell i7 8gb" in Main page
    And The user adds the product to cart
    And The user navigates to "Cart"
    And The user clicks on Place Order in Cart page
    When The user fills out fields
    And The user clicks on Purchase in Purchase Detail page
    Then Thank you for your purchase message should be shown

  Scenario: Extended scenario with two product to verify purchase amount
    And The user clicks on "Sony vaio i5" in Main page
    And The user adds the product to cart
    And The user navigates to "Home"
    And The user selects "Laptops" as category
    And The user clicks on "Dell i7 8gb" in Main page
    And The user adds the product to cart
    And The user navigates to "Cart"
    And The user deletes "Dell i7 8gb" from cart
    And The user clicks on Place Order in Cart page
    When The user fills out fields
    And The user clicks on Purchase in Purchase Detail page
    Then Bill amount should match with expected amount
    And The user clicks on OK in Purchase Detail page