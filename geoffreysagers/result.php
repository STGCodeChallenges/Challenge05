<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>AlphaNumeric String Parsing</title>
<link href="css/styles.css" rel="stylesheet" type="text/css">
<?php include('functions.php'); ?>
</head>
<body>
	<div class="results">
		<?php
			$stringtoparse = $_POST['stringtoparse'];
			//$stringtoparse = "three hundred and sixty eight billion seven hundred forty eight million two hundred seventeen thousand seven hundred sixty three";
			//$stringtoparse = "seven hundred fifteen";
			//echo($stringtoparse . "<br />");
			echo("The number: <br />" .  $stringtoparse . "<br /> in numeric format is: <br />");
			echo(number_format(text2number($stringtoparse)));
			//echo(array_sum(text2number($stringtoparse)));
			
		?>
	</div>
	<div class="button"><a href="index.php">Parse another string &raquo;</a></div>
</body>
</html>