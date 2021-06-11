<?php
class Conexion
{
	private $usuario;
	private $clave;
	private $servidor;
	private $basedatos;
	
	function __construct()
	{
		$this->usuario = "3652723_vam";
		$this->clave= "Elunic357";
		$this->servidor= "fdb28.awardspace.net";
		$this->basedatos = "3652723_vam";
	}
	
	function AbrirConexion()
	{
		$cadena = mysqli_connect($this->servidor,$this->usuario,$this->clave,$this->basedatos);
		
		if($cadena)
		{
			return $cadena;
		}
		else
		{
			return "Error ".mysqli_error();
		}
	}
	
	function CerrarConexion($cadena)
	{
		mysqli_close($cadena);
		$cadena = null;
	}
}
?>