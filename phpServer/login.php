<?php
	
	
	include_once 'conexao.php';
	
	$nome=$_GET['nome'];
	$senha=$_GET['senha'];
	
	$sql = $dbcon->query("SELECT * FROM tblogin WHERE nome='$nome' AND senha='$senha'");
	
	if(mysqli_num_rows($sql)>0){
		echo " Login_ok";
	}else{
		echo "Login_erro";
	}



?>