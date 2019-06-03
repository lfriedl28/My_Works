<?php

// Start a session if one is not already in progress...
if (!session_id()) {
    session_start();
}

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Shopping Cart Catalog - Education Project Only</title>

    <link rel="stylesheet" href="css/minismall.css">
</head>
<body>
    <h2>Product Catalog</h2>
    <?php
    ini_set('display_errors', 1);
    error_reporting(-1);

    require 'sanitize.php';
    require 'callQuery.php';
    require 'dbConnect.php';

    // if a session variable named 'numItems' has never been
    // set, initialize it to a value of 0
    if (!isset($_SESSION['numItems'])) {
        $_SESSION['numItems'] = 0;
    }
    ?>

    <a href="cart_lfriedl.php" >View your cart</a>
    <a href="index.html">Back to product categories</a>

    <?php
    $query = "SELECT catid FROM categories";
    $error = "Error fetching category info.";

    $categoryResult = callQuery($pdo, $query, $error);
    $catIDs = Array();
    $ctr = 0;

    // Step through the resultset and store each category id 
    // in an array named $catIDs.
    while($row = $categoryResult->fetch()) {

        $catIDs[$ctr] = $row['catid'];
        $ctr +=1;
    }

    // Check if the incoming category the user chose is valid
    //
    $incomingCategory = sanitizeString(INPUT_GET, 'cat');

    if (!isset($incomingCategory) || !in_array($incomingCategory, $catIDs)) {
        $incomingCategory = 1;
    }

    //echo "<h3>Incoming category is $incomingCategory</h3>";

    

    // Save the category ID for future reference
    $_SESSION['cat'] = $incomingCategory;

    //
    // Query for all products in the selected category and display them in a table
    // 1 row for a product.
    //
    // We will also display some form elements as well...
    $query = "SELECT * FROM products WHERE category=$incomingCategory";
    $error = "Error fetching product info: ";

    $itemsResult = callQuery($pdo, $query, $error);

    ?>
    <p>Your shopping cart contains <?= $_SESSION['numItems']?> item(s)</p>
    <br><br>
    <form action="cart_lfriedl.php" method="post">
        <table>
            <tr class="header">
                <th>Image</th>
                <th>Description</th>
                <th>Price - USD</th>
                <th style="background-color: #fff">&nbsp;</th>
            </tr>
            <?php
            // Step through the result set one row at a time displaying
            // the table row for each result
            while($row = $itemsResult->fetch()) {
                
                // convert any special HTML characters to their HTML entity codes
                // Example: & --> &amp;
                //
                // Also, strip out any html tags found in the data.
                //
                // Note: could use sanitization function(s) to strip out tags.
                $imageLocation = htmlspecialchars(strip_tags($row['loc']));
                $description = htmlspecialchars(strip_tags($row['description']));
                $price = htmlspecialchars(strip_tags($row['price']));

                //$price = "$" . number_format($price, 2);

                $price = sprintf("$%.2f", $price);

                $productID = strip_tags($row['prodid']);

                //
                // Set $qty to contain what is in our session cart array variable.
                //
                // If your session cart array element (which will be an element of $_SESSION) for this product
                // is empty, set the $qty variable to its default value of 0.
                //
                // If the cart element for this product is not empty, then grab it quantity
                // for display on this page.
                //
                // $_SESSION['cart'] will be an array
                if (isset($_SESSION['cart'][$productID])) {
                    $qty = $_SESSION['cart'][$productID];
                } else {
                    $qty = 0;
                }

                // Build and display a table row for this product
                echo <<<TABLEROW

                    <tr>
                        <td><img src="$imageLocation" alt="image of $description"></td>
                        <td class="desc">$description</td>
                        <td class="price">$price</td>
                        <td class="qty">
                            <label for="quantityForProduct$productID">Qty</label>
                            <input type="text" name="$productID" id="quantityForProduct$productID" value="$qty" size="1em">
                        </td>
                    </tr>    

TABLEROW;

            } // End while next row
            ?>

            <tr>
                <td colspan="4" id="submitCell">
                    <input type="submit" name="addCart" value="Add Items to Cart">
                </td>
            </tr>

        </table>
    </form>

    <br>
    <a href="cart_lfriedl.php" >View your cart</a>
    <a href="index.html">Back to product categories</a>
    
</body>
</html>