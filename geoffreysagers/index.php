<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Text string to Number</title>
<link href="css/styles.css" rel="stylesheet" type="text/css">
<?php include ("functions.php"); ?>
</head>
<body>
<div class="input">
	<h3>Text string to Number</h3>
	<p>This form will convert a text string to numeric representation.</p>
	<form method="post" action="result.php">
			<label>Enter String to Parse</label><br>
			<textarea name="stringtoparse" id="stringtoparse"></textarea><br>
			<input type="submit" name="Submit" value="Submit"/>
	</form>
</div>
</body>
</html>
