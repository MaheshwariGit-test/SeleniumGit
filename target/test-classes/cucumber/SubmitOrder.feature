Feature: Purchase the order from Ecommerce Website
 i want to use this templete for my feature 
 
 Background:
 Given I landed on Ecommerce page 

@submitOrder
  Scenario: Submit the Order positive 
    Given Logged in with userName "<userName>" and passWord "<passWord>"
    When Add the product "<productName>" to cart
    And Checkout Product "<productName>"  and submit the order
    Then Conformation message is displayed on conformation page
    
    
     Examples:
      | userName         | passWord    | productName       |
      |suguki@gmail.com  | Dummy@123   | ZARA COAT 3       |
      |advith@gmail.com  | Advith@123  | ADIDAS ORIGINAL   |