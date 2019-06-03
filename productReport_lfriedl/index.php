<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Minisworld Product Report - Luke Friedl</title>
        
        <link href="supportData/css/prodRep.css" rel="stylesheet">
        
    </head>
    <body>
        <?php
        
        $myForm = <<<MYFORM
        <div class="myForm">

            <h2>Minisworld Company Product Report</h2>

            <form action="$_SERVER[PHP_SELF]" method="post">

                <label for="fName">First Name:</label>
                <input type="text" name="fName" id="fName" size="20">
                <br><br>

                <label for="lName">Last Name:</label>
                <input type="text" name="lName" id="lName" size="20">
                <br><br>

                <label for="choices">Product Report Choices</label><br>
                <select name="choices[]" multiple>
                    <option value="figs" selected="yes">Painted Figures</option>
                    <option value="scenery">Town Square Scenery</option>
                    <option value="supplies">Misc Scenic Supplies</option>
                </select>
                <br><br>

                <input type="submit" name="go" id="go" value="Generate Product Report">

            </form>

        </div>
MYFORM;
        
        /*Psuedocode
        if form not submitted
        
            display stored form
        
        else form was submitted
        
           display greeting to user
           
           if user didn't select a product category
                
                display a 'no categories selected' message
                display stored form
           
           else user did select a category
           
                initialize empty productCount array
                
                set up reportName associative array that associates descriptive report names with each selected product category (filenames)
                
                open product category file for reading
                
                while not end of file product category file
                
                    read next line
                    
                    if valid line
                    
                        Use read line as a key into the product count associative array setting its value to 0
                        
                    endif
                
                endwhile
                    
                set default time zone --already set in code--
                
                for each selected product category
                
                    display header text for selected category
                    
                    open input file associated with current product category (from foreach)
                    
                    while not EOF for current category file
                    
                        read next line which represents a product in this category
                        
                        if valid line
                        
                           increment the product count for this category (productCount array element whose key is related to this category)
                           
                           break up the read line into individual field names/values
                           
                           output a table row for this product
                           - Calculate the values (total revenue and inventory)
                           - Determine which items go in each table
                           - Generate the row
                        
                        endif
                    
                    end while
                
                end foreach selected product category
                
                display a bulleted list for each product category (TODO: Subtasks)
                    
           endif
        
        endif
        

        */
        
        //
        // Decide if we should display the form OR the resulting product report page
        //
        if (!isset($_POST['go'])) {   // form was NOT submitted, so display form
            // display the form here
            echo $myForm;
        }
        else {   // else form was submitted, process and display selected product's reports
            
            if (isset($_POST['fName'])) {
                $fName = $_POST['fName'];
            }
            else {
                $fName = "";
            }
            
            if (isset($_POST['lName'])) {
                $lName = $_POST['lName'];
            }
            else {
                $lName = "";
            }
            
			// greet user here as an <h2> using both first and last name from form
			// hint: you will need to use a span tag with an id value already set up in 
			// your .css file
            
            echo "<h2><span id='greeting'>Hello $fName $lName!</span></h2>";
            
            
            // Step through all selected product categories - no change needed here
            if (isset($_POST['choices'])) {
                $choices = $_POST['choices'];   // shortcut
            }
            else {
                $choices = "";
            }
            
            // check if user did NOT select a product category
            if ($choices == "") {
                print "\t<h2>No product categories were chosen</h2>\n\n";
                
                // now, redisplay the form
                echo $myForm;
                
            }
            else {  // user selected at least one category
                
                $productCount = array();
                
                // associate report name with our categories
                $reportName["figs"] = "Painted Figures";
                $reportName["scenery"] = "Town Square Scenery";
                $reportName["supplies"] = "Misc Scenic Supplies";
                
                // open and read file containing product category descriptive names
                // assigning each category name as a key in the $productCount
                // associative array - store the returned file pointer in $fp
                
                $fp = fopen('supportData/prodCategories.txt', 'r');
                
                
                
				// Use a while loop to read each line in the product category file and store 
				// each line in a variable named $category and remove any trailing \n 
				// from $category.  If the line contains valid data, store a zero in an 
				// element of the $productCount array whose key is the category name.
                while(!feof($fp)) {
                    $category = rtrim(fgets($fp));
                    
                    if($category != "") {
                        $productCount[$category] = 0;
                    }
                } // end while eof
                
                fclose($fp);	
				
                
                // Set default timezone
                date_default_timezone_set('America/Chicago');
                
                // loop through the selected categories from the form and produce a heading
                // and a table for each of them - use a foreach loop and store the value
				// from each element of the selected choices from the form in a variable 
				// named $inputFileName.
                
                foreach ($choices as $key => $inputFileName) {
                                      
                    
                    
                    // generate report header for this category using your $reportName
					// array and generate the current year (4 digits) using PHP's date() function.
					// Replace the areas in the output below with the ***'s.
                    print "\t<h2>$reportName[$inputFileName] Product Report for " . date('Y') . "</h2>\n\n";
                    
                    
                    // obtain product info for all products in this selected category
                    // and display info for each product on its row of a table
                    print "\t<div class=\"ieCenter\">\n\n";
                    print "\t<table>\n";
                    
                    // open the input file associated with this selected category and store
					// returned file pointer in $fp
                    $fp = fopen("supportData/$inputFileName", "r");
                    
                    
                        
                    
                    
					// Read each line from the opened file using a while loop until you
					// reach end-of-file storing each line in a variable named $product. 
					// Be sure to remove any trailing \n character.
			while(!feof($fp)) {	// while not EOF
                            $product = rtrim(fgets($fp));
                        // read next line
                        

                        if ($product != "") {  // we have a valid product
                            
                            // increment this category's product count in the $productCount array
                            $productCount[$reportName[$inputFileName]]++;
                            
                            // break up the line into its individual field values storing 
							// them from left-to-right in variables named: $productID, $numSold, 
							// $price, $numAvail, $cost, $desc, and $imgPath.
                            list($productID, $numSold, $price, $numAvail, $cost, $desc, $imgPath) = explode(":", $product);
                            
                            // generate table row for this product's info by jumping out
							// of PHP delimiters...
                            ?>
                            
                 <tr>
                     
                     <!-- first column -->
                     <td class="diff">
                         
                         <blockquote>
                             Item description:<br>
                             <strong><?=$desc?></strong>
                             <br><br>
                             Part Number = <strong><?=$productID?></strong>
                         </blockquote>
                         
                     </td>
                     
                     <!-- second column -->
                     <td>
                         
                         <!-- insert image here -->
                         <img src="<?=$imgPath?>">
                         
                     </td>
                     
                     <!-- third column -->
                     <td class="diff">
                         
                         <blockquote>
                             
                             Price: <strong><?=$price?></strong>
                             <br>
                             Number Sold: <strong><?=$numSold?></strong>
                             <br><br>
                             Cost:<strong><?=$cost?></strong>
                             <br>
                             Left in Inventory: <strong><?=$numAvail?></strong>
                             <br>
                             Cost of Inventory: <strong><?=($cost * $numAvail)?></strong>
                             
                         </blockquote>    
						 
						 
                         
                     </td>
                     
                 </tr>
        
                            <?php
                        }  // end if we have a valid product

                                }// end while not EOF
                    
                    
                    print "\t</table>\n";
                    print "\t</div>\n\n";
                    
                }// end foreach selected category
                
                
                // Display a bulleted list of product categories showing
                // number of products in each selected category.  Use a foreach
				// loop to step through this associative array obtaining both the 
				// key ($categoryName) and value ($numProducts) for each element in the array.
                print "\t<strong>PRODUCT COUNT BY CATEGORY</strong>\t<ul>\t";
                foreach($productCount as $categoryName => $numProducts) {
                    ?>
                 <li><?=$categoryName?>: <?=$numProducts?></li>
                 <?php
                } //end foreach categoryName in ProductCount
                print "\t</ul>";
                
            }  // end else user selected at least one category
            
            
        }  // end else form was submitted
        
        ?>
		
		<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
        <script src="supportData/js/fadePic.js"></script>
    </body>
</html>
