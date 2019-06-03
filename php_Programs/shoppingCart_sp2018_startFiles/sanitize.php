<?php

function sanitizeString($type, $field) {
            
    $output = filter_input($type, $field, FILTER_SANITIZE_STRING);

    return $output;

}
