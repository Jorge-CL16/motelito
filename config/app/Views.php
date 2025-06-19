<?php
class Views{
    public function getView($vista, $data=""){
        
        /*
        $vista = 'views/' . $ruta . '/' . $vista . '.php';
        require $vista;*/
        require 'views/' . $vista . '.php';

                
    }
}
?>