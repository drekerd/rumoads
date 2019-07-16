<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="/js/form.js"></script>
<title>Add new add</title>
</head>
<body>  
<div class="centered">
 
    <form method="POST" action='newAdd' name="newAdd" id="newAddForm">
              <!-- Add ID : <input type="text" readonly="readonly" name="addId" value="${lastAddId}"/> -->
        
              Add Title : <input type="text" name="addName" required/>
        
              Add Description : <input type="text" name="addDescription" required/>
        
              Price : <input type="number" name="addPrice" required />
       
              <input type="submit" value="Submit" />
     </form>
    </form>
    </div>
</body>
</html>