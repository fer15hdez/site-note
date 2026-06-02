<?php

public function indexAction(Request $request)
{
    //Entity Manager
    $em = $this->getDoctrine()->getManager();

    $request->isXmlHttpRequest(); // is it an Ajax request?

    $request->getPreferredLanguage(array('en', 'fr'));

    $request->query->get('page'); // get a $_GET parameter

    $request->request->get('page'); // get a $_POST parameter
    $data = $request->request->all(); //get all $_POST parameter

//    Using the repository of the entity
    $em->getRepository('FrontedBundle:Tarea')->findPorFecha();



//FORM
//Change the action and method in the Form inside the Controller
    $form = $this->createFormBuilder($task)
        ->setAction($this->generateUrl('target_route'))
        ->setMethod('GET')
        ->add('task', 'text')
        ->add('dueDate', 'date')
        ->add('save', 'submit')
        ->getForm();

//Mix the data send by the user with
    $form->handleRequest($request);
//If is used a external class to create the form is possible to pass in arg the Action and Method
    $form = $this->createForm(new TaskType(), $task, array(
        'action' => $this->generateUrl('target_route'),
        'method' => 'GET',
    ));

//  Paginator
    $paginator = new Paginator($consulta); //Objet paginator with the DQL query $consulta
    $maxPages = ceil($paginator->count() / $maxResults); //Make the total pages
    $thisPage = $page;
    //setFirstResult: Show the element that start the result.
    //setMaxResults: Define the total of tuple that will show.
    $paginator->getQuery()->setFirstResult($firstResult)->setMaxResults($maxResults); 


}
