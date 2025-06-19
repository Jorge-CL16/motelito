<?php
class Login extends Controller
{
    public function __construct()
    {
        parent::__construct();
    }

    public function index()
    {
        $data['title'] = 'Login';
        $data['subtitle'] = 'Inicio de Sesión';
        $this->views->getView('principal/login', $data);
    }

    
}
