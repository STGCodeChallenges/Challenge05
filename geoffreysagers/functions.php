<?php
function text2number($input){
	error_reporting(E_ALL & ~E_NOTICE);
	$input = strtolower($input);
	$input = str_replace("-"," ",$input);
	$dividedtext = explode(" ", $input);
	for($x=0;$x<count($dividedtext);$x++){
		$dividedtext[$x] = str_replace(",","",$dividedtext[$x]);
		if($dividedtext[$x] == "and"){
			$dividedtext[$x] = "";
			}
		}
			
	$dividedtext = array_values(array_filter($dividedtext));		
	$numericarray = array();
	for($y=0;$y<count($dividedtext);$y++){
		switch($dividedtext[$y]){
			case "one":
				$numericarray[$y] = 1;
			break;
			case "two":
				$numericarray[$y] = 2;
			break;
			case "three":
				$numericarray[$y] = 3;
			break;
			case "four":
				$numericarray[$y] = 4;
			break;
			case "five":
				$numericarray[$y] = 5;
			break;
			case "six":
				$numericarray[$y] = 6;
			break;
			case "seven":
				$numericarray[$y] = 7;
			break;
			case "eight":
				$numericarray[$y] = 8;
			break;
			case "nine":
				$numericarray[$y] = 9;
			break;
			case "ten":
				$numericarray[$y] = 10;
			break;
			case "eleven":
				$numericarray[$y] = 11;
			break;
			case "twelve":
				$numericarray[$y] = 12;
			break;
			case "thirteen":
				$numericarray[$y] = 13;
			break;
			case "fourteen":
				$numericarray[$y] = 14;
			break;
			case "fifteen":
				$numericarray[$y] = 15;
			break;
			case "sixteen":
				$numericarray[$y] = 16;
			break;
			case "seventeen":
				$numericarray[$y] = 17;
			break;
			case "eighteen":
				$numericarray[$y] = 18;
			break;
			case "nineteen":
				$numericarray[$y] = 19;
			break;
			case "twenty":
				$numericarray[$y] = 20;
			break;
			case "thirty":
				$numericarray[$y] = 30;
			break;
			case "forty":
				$numericarray[$y] = 40;
			break;
			case "fifty":
				$numericarray[$y] = 50;
			break;
			case "sixty":
				$numericarray[$y] = 60;
			break;
			case "seventy":
				$numericarray[$y] = 70;
			break;
			case "eighty":
				$numericarray[$y] = 80;
			break;
			case "ninety":
				$numericarray[$y] = 90;
			break;
			case "hundred":
				$numericarray[$y] = 100;
			break;
			case "thousand":
				$numericarray[$y] = 1000;
			break;
			case "million":
				$numericarray[$y] = 1000000;
			break;
			case "billion":
				$numericarray[$y] = 1000000000;
			break;
			case "trillion":
				$numericarray[$y] = 1000000000000;
			break;
			default:
			}
		}
	
	for ($z=0;$z<count($numericarray);$z++){
		if($z<count($numericarray)-1){
			if($numericarray[$z]<10 && $numericarray[$z+1]==100){
				$numericarray[$z] = $numericarray[$z] * 100;
				$numericarray[$z+1]="";
				}
			}
		}
	
	$numericarray = array_reverse(array_values(array_filter($numericarray)));

	$numericarray[] = "";
	$m=0;
	$hundreds=array();
	$thousands=array();
	$millions=array();
	$billions=array();
	$trillions=array();
	
	if($m<count($numericarray)){
		while ($numericarray[$m]<$numericarray[$m+1]) {
			$hundreds[]=$numericarray[$m];
			$m++;
			} 
			if($m==count($numericarray)-2){$hundreds[]=$numericarray[$m];}
			$m++;
	}
	if($m+1<count($numericarray) && in_array(1000,$numericarray)){
		while ($numericarray[$m]<$numericarray[$m+1]) {
			$thousands[]=$numericarray[$m];
			$m++;
			} 
			if($m==count($numericarray)-2){$thousands[]=$numericarray[$m];}
			$m++;
	}
	if($m+1<count($numericarray) && in_array(1000000,$numericarray)){
		while ($numericarray[$m]<$numericarray[$m+1]) {
			$millions[]=$numericarray[$m];
			$m++;
			} 
			if($m==count($numericarray)-2){$millions[]=$numericarray[$m];}
			$m++;	
	}
	if($m+1<count($numericarray) && in_array(1000000000,$numericarray)){
		while ($numericarray[$m]<$numericarray[$m+1]) {
			$billions[]=$numericarray[$m];
			$m++;
			} 
			if($m==count($numericarray)-2){$billions[]=$numericarray[$m];}
	}
	if($m+1<count($numericarray) && in_array(1000000000000,$numericarray)){
		while ($numericarray[$m]<$numericarray[$m+1]) {
			$trillions[]=$numericarray[$m];
			$m++;
			} 
			if($m==count($numericarray)-2){$trillions[]=$numericarray[$m];}
	}
	
	$finalnumber = array_sum($trillions)*1000000000000 + array_sum($billions)*1000000000 + array_sum($millions) * 1000000 + array_sum($thousands) * 1000 + array_sum($hundreds);
	return $finalnumber;
	}
	
	
	
?>