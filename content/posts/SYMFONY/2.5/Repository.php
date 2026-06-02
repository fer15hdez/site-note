<?php
/**
 * Created by PhpStorm.
 * User: Fernando
 * Date: 24/04/2022
 * Time: 22:02
 */
public function findFiltroFecha($fechaInicial,$fechaFinal)
{
    $em = $this->getEntityManager();

    $consulta = $em->createQuery('SELECT c FROM FrontedBundle:Cliente c WHERE
        c.fechaDeIngreso >= :fechaInicial AND
        c.fechaDeIngreso <= :fechaFinal ORDER BY c.fechaDeIngreso ASC');

    $consulta->setParameters(array(
        'fechaInicial' => $fechaInicial,
        'fechaFinal' => $fechaFinal
    ));

    $oferta = $consulta->getResult();

    return $oferta;


}

// PAGINATOR
// @fechaInicial Fecha inicial
// @fechaFinal Fecha final
// @sexo Genero 
public function findFiltroFechaSexoPaginator($fechaInicial,$fechaFinal, $sexo)
    {
        $em = $this->getEntityManager();
// Just a DQL query
        $consulta = $em->createQuery('SELECT c FROM FrontedBundle:Cliente c WHERE
        c.fechaDeIngreso >= :fechaInicial AND
        c.fechaDeIngreso <= :fechaFinal AND c.sexo = :sexo ORDER BY c.fechaDeIngreso ASC');

        $consulta->setParameters(array(
            'fechaInicial' => $fechaInicial,
            'fechaFinal' => $fechaFinal,
            'sexo' => $sexo
        ));
// Just return the query without the "$consulta->getResult();"
//        $oferta = $consulta->getResult();

        return $consulta;
    }