<?php
    if($_SERVER["REQUEST_METHOD"]=="POST"){
        $nombre = $_POST['nombre'];
        $direccion = $_POST['direccion'];
        $distrito = $_POST['distrito'];
        $jugada = $_POST['jugada'];
        $dni = $_POST['dni'];
        $estado = $_POST['estado'];
        $fecha = $_POST['fecha'];
        
        require_once ('conexion.php');
        
        $cnx = new Conexion();
		
		$conexion = $cnx->AbrirConexion();
		$consulta = "call SP_RegistrarCliente('$jugada', '$estado', '$dni','$nombre', '$direccion', '$distrito','$fecha')";
		
		$resultado = mysqli_query($conexion, $consulta);
		echo $resultado;
		
		$cnx->CerrarConexion($conexion);
    }
?>