<?php
require_once "vendor/autoload.php";
use GuzzleHttp\Client;

$client = new GuzzleHttp\Client(['verify' => false]);

$res = $client->request('POST', 'http://localhost:8083/loan/loanApproval',[
    'json' => [
        'id' => 2,
        'nom' => 'test1',
        'somme' => 50
    ]
]);

echo $res->getStatusCode();
echo $res->getHeader('content-type')[0]."<br>";
echo $res->getBody()."<br>";

$res = $client->request('POST', 'http://localhost:8083/loan/loanApproval',[
    'json' => [
        'id' => 1,
        'nom' => 'test2',
        'somme' => 5000000
    ]
]);

echo $res->getStatusCode();
echo $res->getHeader('content-type')[0]."<br>";
echo $res->getBody();

?>