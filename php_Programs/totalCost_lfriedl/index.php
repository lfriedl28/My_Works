<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Total Cost</title>
    </head>
    <body>
       
        <?php
        $costPerWidget = 20;
        $taxRate = .06;
        $discountRate = .25;
        $quantity = $_POST['quantity'];
        $totalCost = 0;
        $preTaxTotal = $quantity * $costPerWidget;

        if ($quantity <= 0) {
            echo 'Please make sure that you have entered a quantity and then resubmit';
            exit();
        }
        
        // Add inline header and style tags on the echos (taking in the submitted form values
        if ($preTaxTotal >= 50){
            $totalCost += round(($preTaxTotal * (1-$discountRate)) * (1 + $taxRate), 2);
            $yearlyCost = round(($totalCost / 12),2);
            echo 'You requested ' . $quantity . " widgets at $" . $costPerWidget . ' each<br>';
            echo 'Your total with tax, minus the discount rate of $' . round($discountRate * $totalCost, 2) . ' is $' .  round($totalCost,2) . '0 each.<br>';
            echo 'You may purchase the widget with a 12-month plan(yearly) for the cost of $' . $yearlyCost . ' each.<br>';
        } else {
            $totalCost += round(($preTaxTotal * (1 + $taxRate)),2);
            $yearlyCost = round(($totalCost / 12),2);
            echo 'You requested ' . $quantity . " widgets at $" . $costPerWidget . ' each<br>';
            echo 'Your total with tax is $' .  round($totalCost,2) . '0 each.<br>';
            echo 'You may purchase the widget with a 12-month plan(yearly) for the cost of $' . $yearlyCost . ' each.<br>';
        }
       
        ?>
    </body>
</html>
