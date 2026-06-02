<?php
/**
 * Created by PhpStorm.
 * User: Fernando
 * Date: 24/04/2022
 * Time: 21:48
 */
//Make a date
$fecha = new \DateTime();
$fecha->setDate(); //Set the value (year, month, day)

  Param: "array(3) { ["date"]=> string(19) "2022-01-01 15:33:15" ["timezone_type"]=> string(1) "3" ["timezone"]=> string(3) "UTC" }"
date_create(Param);
  Answer: object(DateTime)#391 (3) { ["date"]=> string(19) "2022-01-01 15:33:15" ["timezone_type"]=> int(3) ["timezone"]=> string(3) "UTC" }