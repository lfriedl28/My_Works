<!DOCTYPE html>
<html>
    <head>
        <!--<meta http-equiv="Content-Type" content="text/html; charset=iso-8859">-->
        <meta http-equiv="Content-Type" content="text/html">
        <meta charset="iso-8859">
        <title>Ascii Table Generator</title>
        
        <link href="css/styles.css" rel="stylesheet">
    </head>
    <body>
        <?php
        //Error2
        $myForm = <<<DATAROW
        
        <h3>Generate ASCII Table</h3>
        
        <form action="$_SERVER[PHP_SELF]" method="post">
            
            <label>Number of rows:</label>
            <input type="text" name="numRows" id="numRows" value="32">
            <br><br>
            <input type="submit" name="go" value="Create ASCII Table">

        </form>
DATAROW;
            //error3 
            $numRows = filter_input(INPUT_POST, 'numRows', FILTER_SANITIZE_NUMBER_INT); // error 1
            if($numRows <= 0){ //Error 7
                echo $myForm;
            } else {
        
                
                $endAscii = 256;

                $numColumns = floor($endAscii / $numRows);

                if ($endAscii % $numRows) {
                    $numColumns++;
                }

                

                // Generate table
            ?>
        <table>
            
            <?php
            // for each column, display two <th> cells
            for ($cols = 0; $cols < $numColumns; $cols++) {
            ?>
                <th class="num">ASCII</th>
                <th class="chr">CHR</th>
            <?php
            }
            ?>
            </tr>
            
            <?php
            
            // produce the remaining rows in the table
            //
            // outer loop for rows
            
            for ($rows = 0; $rows < $numRows; $rows++) {
                
            ?>
            <tr>
            
            <?php
                // generate columns (<td>'s) for this row
                // using an inner for loop
            
                //Error 4 and 5
                for ($cols = 0; $cols < $numColumns; $cols++) {
                    $asciiNum = ($cols * $numRows) + $rows; //Error 10
                    
                    if ($asciiNum < $endAscii) {
            ?>
                
                
                <td class="num"><?= $asciiNum ?></td>
                <td class="chr"><?= chr($asciiNum) /*Error 9*/?></td>
                
                <?php
                    }
                    
                }  // for each logical column
            } //Error 6
            //Error 8 - Deleteing "? >"
            ?> 
            </tr>
        </table>
        <?php
            
         } //end if rows is specified
            
      
        
        ?>
    </body>
</html>
