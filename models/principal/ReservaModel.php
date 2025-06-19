<?php
class ReservaModel extends Query{


    private $con;

    public function __construct() {
       parent::__construct(); 
    }

    public function getDisponible($f_llegada, $f_salida, $habitacion){
        return $this->selectALL("SELECT * FROM reservas 
        WHERE fecha_ingreso <= '$f_salida' 
        AND
        fecha_salida >='$f_llegada' AND id_habitacion = $habitacion");      
    }
    public function getReservasHabitacion($habitacion){
        return $this->selectALL("SELECT * FROM reservas 
        WHERE id_habitacion = $habitacion");    
    }
    public function getHabitaciones(){
        return $this->selectALL("SELECT * FROM habitaciones WHERE estado = 1");
    }

    public function getHabitacion($id_habitacion){
        return $this->select("SELECT * FROM habitaciones WHERE id = $id_habitacion");
    }
}
?>