<?php
class PrincipalModel extends Query{


    private $con;

    public function __construct() {
       parent::__construct(); 
    }

    //RECUPERAR LOS SLIDERS
    public function getSliders(){
        return $this->selectALL("SELECT * FROM sliders");
    }

    public function getHabitaciones(){
        return $this->selectALL("SELECT * FROM habitaciones WHERE estado = 1");
    }

}
?>