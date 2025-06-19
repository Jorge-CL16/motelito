<?php
require_once 'config/config.php';
require_once 'helpers/funciones.php';
//Verificar si existe la ruta admin
$isAdmin = strpos($_SERVER['REQUEST_URI'], '/' . ADMIN) !== false;
//Comprobar si exite GET para crear url amigables
$ruta = empty($_GET['url']) ? 'principal/index' : $_GET['url'];
//Crear un array a partir de la ruta
$array = explode('/',$ruta);
//VALIDAR SI NOS ENCONTRAMOS EN LA RUTA
if ($isAdmin && (count($array) == 1 
|| (count($array) == 2 && empty($array[1]))) 
&& $array[0] == ADMIN) {
    //CREAR CONTROLADOR
    $controller = 'Admin';
    $metodo = 'login';
} else{
    $indiceUrl = ($isAdmin) ? 1 : 0;
    $controller = ucfirst($array[$indiceUrl]);
    $metodo = 'index';
}
//Validar metodos
$metodoIndice = ($isAdmin) ? 2 : 1;
if (!empty($array[$metodoIndice]) && $array[$metodoIndice] != '') {
    $metodo = $array[$metodoIndice];
}
//Validar parametros
$parametro = '';
$parametroIndice = ($isAdmin) ? 3 : 2;
if (!empty($array[$parametroIndice]) && $array[$parametroIndice] != '') {
    for($i = $parametroIndice; $i < count($array); $i++){
        $parametro .= $array[$i] . ',';
    }
    $parametro = trim($parametro, ',');
}
//LLAMAR AUTOLOAD
require_once 'config/app/Autoload.php';
//Validar directorio de controladores
$dirControllers = ($isAdmin) ? 'controllers/admin/' . $controller . '.php' : 'controllers/principal/' . $controller . '.php';
if (file_exists($dirControllers)) {
    require_once $dirControllers;
    $controller = new $controller();
    if(method_exists($controller, $metodo)){
        $controller->$metodo($parametro);
    }else{
        echo 'METODO NO EXISTE';
    }
} else{
    echo 'CONTROLADOR NO EXISTE';
}
?>