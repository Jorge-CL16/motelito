<?php
class Principal extends Controller{
    public function __construct() {
        parent :: __construct();
    }
    public function index() {
        $data['title'] = 'Pagina principal';
        //TRAER SLIDERS
        $data['sliders'] = $this->model->getSliders();
        //TREAR HABITACIONES
        $data['habitaciones'] = $this->model->getHabitaciones();
        /*
        print_r($data);
        exit;
        */
        $this->views->getView('index', $data);
    }
}
?>