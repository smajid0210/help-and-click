<?php

require_once '../include/DbHandler.php';
require_once '../include/PassHash.php';
require '.././libs/Slim/Slim.php';

\Slim\Slim::registerAutoloader();

$app = new \Slim\Slim();

// User id from db - Global Variable
$user_id = NULL;

/**
 * Adding Middle Layer to authenticate every request
 * Checking if the request has valid api key in the 'Authorization' header
 */
function authenticate(\Slim\Route $route)
{
    // Getting request headers
    $headers = apache_request_headers();
    $response = array();
    $app = \Slim\Slim::getInstance();

    // Verifying Authorization Header
    if (isset($headers['Authorization'])) {
        $db = new DbHandler();

        // get the api key
        $api_key = $headers['Authorization'];
        // validating api key
        if (!$db->isValidApiKey($api_key)) {
            // api key is not present in users table
            $response["error"] = true;
            $response["message"] = "Access Denied. Invalid Api key";
            echoRespnse(401, $response);
            $app->stop();
        } else {
            global $user_id;
            // get user primary key id
            $user_id = $db->getUserId($api_key);
        }
    } else {
        // api key is missing in header
        $response["error"] = true;
        $response["message"] = "Api key is misssing";
        echoRespnse(400, $response);
        $app->stop();
    }
}

/**
 * ----------- METHODS WITHOUT AUTHENTICATION ---------------------------------
 */
/**
 * User Registration
 * url - /register
 * method - POST
 * params - username, phone number
 */
$app->post('/register', function () use ($app) {
    // check for required params
    verifyRequiredParams(array('username', 'password'));

    $response = array();

    // reading post params
    $username = $app->request->post('username');

    $password = $app->request->post('password');

    // validating email address
    //validateEmail($phonenumber);

    $db = new DbHandler();
    $res = $db->createUser($username, $password);

    if ($res == USER_CREATED_SUCCESSFULLY) {
        $response["error"] = false;
        $response["message"] = "You are successfully registered";
    } else if ($res == USER_CREATE_FAILED) {
        $response["error"] = true;
        $response["message"] = "Oops! An error occurred while registereing";
    } else if ($res == USER_ALREADY_EXISTED) {
        $response["error"] = true;
        $response["message"] = "Sorry, this email already existed";
    }
    // echo json response
    echoRespnse(201, $response);
});

/**
 * User Login
 * url - /login
 * method - POST
 * params - email, password
 */
$app->post('/login', function() use ($app) {
            // check for required params
            verifyRequiredParams(array('username', 'password'));

            // reading post params
            $username = $app->request()->post('username');
            $password = $app->request()->post('password');
            $response = array();

            $db = new DbHandler();
            // check for correct email and password
            if ($db->checkLogin($username, $password)) {
                // get the user by email
                //$user = $db->getUserByEmail($email);

                
                    $response["error"] = false;
                    //$response['name'] = $users['username'];
                    //$response['email'] = $user['email'];
                    //$response['apiKey'] = $user['api_key'];
                    //$response['createdAt'] = $user['created_at'];
                
            } else {
                // user credentials are wrong
                $response['error'] = true;
                $response['message'] = 'Login failed. Incorrect credentials';
            }

            echoRespnse(200, $response);
        });

/*
 * ------------------------ METHODS WITH AUTHENTICATION ------------------------
 */

/**
 * Listing all tasks of particual user
 * method GET
 * url /tasks/:username
 */
$app->get('/tasksfull/:id', function ($id) {

    $response = array();
    $db = new DbHandler();

    // fetching all user tasks
    $result = $db->getAllUserPostsfull($id);

    $response["error"] = false;
    $response["incidents"] = array();
    $response["incidents"]=$result;
       
    // looping through result and preparing tasks array
  //  while ($task = $result->fetch_assoc()) {
  //      $tmp = array();
  //      $tmp["username"] = $task["username"];
  //      $tmp["location"] = $task["location"];
  //      $tmp["bigimage"] = $task["bigimage"];
  //      $tmp["age"] = $task["age"];
  //      $tmp["gender"] = $task["gender"];
  //      $tmp["id"] = $task["id"];
  //      $tmp["date of incident"] = $task["date"];

//        $tmp["location details"] = $task["location_details"];
 //       $tmp["contact"] = $task["contact"];
   //     $tmp["description"] = $task["description"];
     //   array_push($response["incidents"], $tmp);
   // }

    echoRespnse(200, $response);
});

$app->get('/notesfull/:id', function ($id) {

    $response = array();
    $db = new DbHandler();

    // fetching all user tasks
    $result = $db->getAllUserNotsfull($id);

    $response["error"] = false;
    $response["notices"] = array();
    $response["notices"] = $result;

    // looping through result and preparing tasks array
   // while ($task = $result->fetch_assoc()) {
     //   $tmp = array();
      //  $tmp["name"] = $task["name"];
      //  $tmp["username"] = $task["username"];
      //  $tmp["location"] = $task["location"];
      //  $tmp["bigimage"] = $task["bigimage"];
      //  $tmp["age"] = $task["age"];
     //   $tmp["gender"] = $task["gender"];
      //  $tmp["id"] = $task["id"];
      //  $tmp["date of incident"] = $task["date"];

      //  $tmp["occupation"] = $task["occupation"];
      //  $tmp["appearance"] = $task["appearance"];
      //  $tmp["contact"] = $task["contact"];
       // $tmp["description"] = $task["addition"];
      //  array_push($response["notices"], $tmp);
  //   }

    echoRespnse(200, $response);
});

$app->get('/tasksall/:username', function ($username) {

    $response = array();
    $db = new DbHandler();

    // fetching all user tasks
    $result = $db->getAllPosts($username);

    $response["error"] = false;
    $response["incidents"] = array();

    // looping through result and preparing tasks array

    $response["incidents"] = $result;

//    while ($task = $result->fetch_assoc()) {
//        $tmp = array();
//        $tmp["username"] = $task["username"];
//        $tmp["location"] = $task["location"];
//        $tmp["bigimage"] = $task["bigimage"];
//        $tmp["age"] = $task["age"];
//        $tmp["gender"] = $task["gender"];
//        $tmp["id"] = $task["id"];
//        $tmp["date of incident"] = $task["date"];
//
//        $tmp["location details"] = $task["location_details"];
//        $tmp["contact"] = $task["contact"];
//        $tmp["description"] = $task["description"];
//        array_push($response["incidents"], $tmp);
//    }

    echoRespnse(200, $response);
});


$app->get('/notesall/:username', function ($username) {

    $response = array();
    $db = new DbHandler();

    // fetching all user tasks
    $result = $db->getAllNotices($username);

    $response["error"] = false;
    $response["notices"] = array();
    $response["notices"] = $result;

    // looping through result and preparing tasks array

    //while ($task = $result->fetch_assoc()) {
   //     $tmp = array();
    //   $tmp["username"] = $task["username"];
      // $tmp["location"] = $task["location"];
       //$tmp["bigimage"] = $task["bigimage"];
        // $tmp["age"] = $task["age"];
       // $tmp["gender"] = $task["gender"];
       // $tmp["id"] = $task["id"];
        // $tmp["name"] = $task["name"];
        // $tmp["date of incident"] = $task["date"];

        // $tmp["location details"]= $task["location_details"];
        // $tmp["contact"]=$task["contact"];
        // $tmp["description"]=$task["description"];
//        array_push($response["notices"], $tmp);
  //  }

    echoRespnse(200, $response);
});


$app->get('/tasks/:username', function ($username) {

    $response = array();
    $db = new DbHandler();

    // fetching all user tasks
    $result = $db->getAllUserPosts($username);

    $response["error"] = false;
    $response["incidents"] = array();
     $response["incidents"] = $result;

    // looping through result and preparing tasks array
//    while ($task = $result->fetch_assoc()) {
//        $tmp = array();
//        $tmp["username"] = $task["username"];
//        $tmp["location"] = $task["location"];
//        $tmp["bigimage"] = $task["bigimage"];
//        $tmp["age"] = $task["age"];
//        $tmp["gender"] = $task["gender"];
//        $tmp["id"] = $task["id"];
//        array_push($response["incidents"], $tmp);
//    }

    echoRespnse(200, $response);
});


/**
 * Listing all tasks of particual user
 * method GET
 * url /tasks/:username
 */
$app->get('/notes/:username', function ($username) {

    $response = array();
    $db = new DbHandler();
    
    // fetching all user tasks
    $result = $db->getAllUserNots($username);
    

    $response["error"] = false;
    $response["notices"] = array();
    $response["notices"] = $result;

    // looping through result and preparing tasks array
    //while ($task = $result->fetch_assoc()) {
    //    $tmp = array();
    //    $tmp["username"] = $task["username"];
     //   $tmp["name"] = $task["name"];
     //   $tmp["bigimage"] = $task["bigimage"];
     //   $tmp["age"] = $task["age"];
     //   $tmp["gender"] = $task["gender"];
     //   $tmp["id"] = $task["id"];
     //   array_push($response["notices"], $tmp);
   // }

    echoRespnse(200, $response);
});

$app->get('/commentsinc/:id', function ($id) {

    $response = array();
    $db = new DbHandler();
    

    // fetching all user tasks
    $result = $db->getAllIncidentComments($id);

    $response["error"] = false;
    $response["comments_posts"] = array();
    $response["comments_posts"] = $result;

    // looping through result and preparing tasks array
   // while ($task = $result->fetch_assoc()) {
   //     $tmp = array();
   //     $tmp["username"] = $task["username"];
    //    $tmp["comment"] = $task["comment"];
        //$tmp["bigimage"] = $task["bigimage"];
        //$tmp["age"] = $task["age"];
    //    $tmp["post time"] = $task["created_at"];
        //$tmp["id"] = $task["id"];
   //     array_push($response["comments_posts"], $tmp);
   // }

    echoRespnse(200, $response);
});
$app->get('/commentsnot/:id', function ($id) {

    $response = array();
    $db = new DbHandler();
    
    // fetching all user tasks
    $result = $db->getAllNoticeComments($id);

    $response["error"] = false;
    $response["comments_notices"] = array();
    $response["comments_notices"] = $result;

    // looping through result and preparing tasks array
  //  while ($task = $result->fetch_assoc()) {
    //    $tmp = array();
    //    $tmp["username"] = $task["username"];
     //   $tmp["comment"] = $task["comment"];
        //$tmp["bigimage"] = $task["bigimage"];
        //$tmp["age"] = $task["age"];
      //  $tmp["post time"] = $task["created_at"];
        //$tmp["id"] = $task["id"];
     //   array_push($response["comments_notices"], $tmp);
 //   }

    echoRespnse(200, $response);
});

/**
 * Listing single task of particual user
 * method GET
 * url /tasks/:
 * Will return 404 if the task doesn't belongs to user
 */
$app->get('/tasks/:', function () {
    global $user_id;
    $response = array();
    $db = new DbHandler();

    // fetch task
    $result = $db->getTask($task_id, $user_id);

    if ($result != NULL) {
        $response["error"] = false;
        $response["id"] = $result["id"];
        $response["task"] = $result["task"];
        $response["status"] = $result["status"];
        $response["createdAt"] = $result["created_at"];
        echoRespnse(200, $response);
    } else {
        $response["error"] = true;
        $response["message"] = "The requested resource doesn't exists";
        echoRespnse(404, $response);
    }
});

/**
 * Creating new task in db
 * method POST
 * params - name
 * url - /tasks/
 */
$app->post('/tasks', function () use ($app) {
    // check for required params
    verifyRequiredParams(array('username', 'image', 'imgmap','age', 'gender', 'date', 'location', 'location_details', 'contact', 'description'));

    $response = array();
    $username = $app->request->post('username');
    $image = $app->request->post('image');
     $imgmap = $app->request->post('imgmap');   
    $age = $app->request->post('age');
    $gender = $app->request->post('gender');
    $date = $app->request->post('date');
    $location = $app->request->post('location');
    $location_details = $app->request->post('location_details');
    $contact = $app->request->post('contact');


    $description = $app->request->post('description');

    $db = new DbHandler();

    // creating new task
    $task_id = $db->createIncident($username, $image, $imgmap, $age, $gender, $date, $location, $location_details, $contact, $description);

    if ($task_id != 0) {
        $response["error"] = false;
        $response["message"] = "Task created successfully";
        $response["task_id"] = $task_id;
        echoRespnse(201, $response);
    } else {
        $response["error"] = true;
        $response["message"] = "Failed to create task. Please try again";
        echoRespnse(200, $response);
    }
});

$app->post('/notes', function () use ($app) {
    // check for required params
    verifyRequiredParams(array('username', 'image', 'imgmap', 'name', 'age', 'gender', 'date', 'location', 'occupation', 'appearance', 'contact', 'addition'));

    $response = array();
    $username = $app->request->post('username');
    $image = $app->request->post('image');
    $imgmap = $app->request->post('imgmap');
    $name = $app->request->post('name');
    $age = $app->request->post('age');
    $gender = $app->request->post('gender');
    $date = $app->request->post('date');
    $location = $app->request->post('location');
    $occupation = $app->request->post('occupation');
    $appearance = $app->request->post('appearance');
    $contact = $app->request->post('contact');


    $addition = $app->request->post('addition');

    $db = new DbHandler();

    // creating new task
    $task_id = $db->createnotice($username, $image, $imgmap,$name, $age, $gender, $date, $location, $occupation, $appearance, $contact, $addition);

    if ($task_id != 0) {
        $response["error"] = false;
        $response["message"] = "Task created successfully";
        $response["task_id"] = $task_id;
         
         
        echoRespnse(201, $response);
    } else {
        $response["error"] = true;
        $response["message"] = "Failed to create task. Please try again";
        echoRespnse(200, $response);
    }
});

$app->post('/commentsinc', function () use ($app) {
    // check for required params
    verifyRequiredParams(array('username', 'post_id', 'comment'));

    $response = array();
    $username = $app->request->post('username');
    //$image= $app->request->post('image');
    $post_id = $app->request->post('post_id');

    $comment = $app->request->post('comment');


    $db = new DbHandler();

    // creating new task
    $task_id = $db->createIncidentComments($username, $post_id, $comment);

    if ($task_id != 0) {
        $response["error"] = false;
        $response["message"] = "Task created successfully";
        $response["task_id"] = $task_id;
        echoRespnse(201, $response);
    } else {
        $response["error"] = true;
        $response["message"] = "Failed to create task. Please try again";
        echoRespnse(200, $response);
    }
});
$app->post('/commentsnots', function () use ($app) {
    // check for required params
    verifyRequiredParams(array('username', 'post_id', 'comment'));

    $response = array();
    $username = $app->request->post('username');
    //$image= $app->request->post('image');
    $post_id = $app->request->post('post_id');

    $comment = $app->request->post('comment');


    $db = new DbHandler();

    // creating new task
    $task_id = $db->createNoticeComments($username, $post_id, $comment);

    if ($task_id != 0) {
        $response["error"] = false;
        $response["message"] = "Task created successfully";
        $response["task_id"] = $task_id;
        echoRespnse(201, $response);
    } else {
        $response["error"] = true;
        $response["message"] = "Failed to create task. Please try again";
        echoRespnse(200, $response);
    }
});

/**
 * Updating existing task
 * method PUT
 * params task, status
 * url - /tasks/:id
 */
$app->put('/tasks/:id', 'authenticate', function ($task_id) use ($app) {
    // check for required params
    verifyRequiredParams(array('task', 'status'));

    global $user_id;
    $task = $app->request->put('task');
    $status = $app->request->put('status');

    $db = new DbHandler();
    $response = array();

    // updating task
    $result = $db->updateTask($user_id, $task_id, $task, $status);
    if ($result) {
        // task updated successfully
        $response["error"] = false;
        $response["message"] = "Task updated successfully";
    } else {
        // task failed to update
        $response["error"] = true;
        $response["message"] = "Task failed to update. Please try again!";
    }
    echoRespnse(200, $response);
});

/**
 * Deleting task. Users can delete only their tasks
 * method DELETE
 * url /tasks
 */
$app->delete('/tasksdel/:id',  function ($task_id) use ($app) {
   // global $user_id;


    $db = new DbHandler();
    $response = array();
    $response["tsk"] = array();
    
    $result = $db->deletePost($task_id);

    if ($result) {
        // task deleted successfully
        $response["error"] = false;
        $response["message"] = "Task deleted succesfully";
    } else {
        // task failed to delete
        $response["error"] = true;
        $response["message"] = "Task failed to delete. Please try again!";
    }
    echoRespnse(200, $response);
});
$app->delete('/notesdel/:id',  function ($task_id) use ($app) {
   // global $user_id;

    $db = new DbHandler();
    $response["tsk"] = array();
    $result = $db->deleteNotice($task_id);
    if ($result) {
        // task deleted successfully
        $response["error"] = false;
        $response["message"] = "Task deleted succesfully";
    } else {
        // task failed to delete
        $response["error"] = true;
        $response["message"] = "Task failed to delete. Please try again!";
    }
    echoRespnse(200, $response);
});

/**
 * Verifying required params posted or not
 */
function verifyRequiredParams($required_fields)
{
    $error = false;
    $error_fields = "";
    $request_params = array();
    $request_params = $_REQUEST;
    // Handling PUT request params
    if ($_SERVER['REQUEST_METHOD'] == 'PUT') {
        $app = \Slim\Slim::getInstance();
        parse_str($app->request()->getBody(), $request_params);
    }
    foreach ($required_fields as $field) {
        if (!isset($request_params[$field]) || strlen(trim($request_params[$field])) <= 0) {
            $error = true;
            $error_fields .= $field . ', ';
        }
    }

    if ($error) {
        // Required field(s) are missing or empty
        // echo error json and stop the app
        $response = array();
        $app = \Slim\Slim::getInstance();
        $response["error"] = true;
        $response["message"] = 'Required field(s) ' . substr($error_fields, 0, -2) . ' is missing or empty';
        echoRespnse(400, $response);
        $app->stop();
    }
}

/**
 * Validating email address
 */
function validateEmail($email)
{
    $app = \Slim\Slim::getInstance();
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        $response["error"] = true;
        $response["message"] = 'Email address is not valid';
        echoRespnse(400, $response);
        $app->stop();
    }
}

/**
 * Echoing json response to client
 * @param String $status_code Http response code
 * @param Int $response Json response
 */
function echoRespnse($status_code, $response)
{
    $app = \Slim\Slim::getInstance();
    // Http response code
    $app->status($status_code);

    // setting response content type to json
    $app->contentType('application/json');

    echo json_encode($response);
}

$app->run();
?>