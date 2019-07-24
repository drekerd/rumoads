<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <title>Rumos Adds</title>
</head>
<body>
<table class="table table-hover table-dark">
<thead>
        <tr>
          <th scope="col">Insert New Course</th>
        </tr>
      </thead>
</table>
<form method="POST" action='/admin' name="newAdd" id="newAddForm" target="_self">

           <!-- Add ID : <input type="text" readonly="readonly" name="addId"/> -->
      
            Add Title : <input type="text" name="addName" required/>
      
            Add Description : <input type="text" name="addDescription" required/>

            Category : <select name="addCategory">
                            <#list categoryList as category>
                                <option name="addCategory" value="${category.categoryName}">${category.categoryName}</option>
                            </#list>
                      </select>
            Price : <input type="number" step="0.01" name="addPrice" required/>
     
    <input type="submit" value="Submit"/>
</form>

</br>

<#if lastSync??>
    <p>Last synced at ${lastSync}</p>

<!--    <div>Never synced. If automatic data is not synced, please try it manually</div>-->
</#if>
</br>
</br>
<table class="table table-hover table-dark">
<thead>
        <tr>
          <th scope="col">Current Courses Available</th>
        </tr>
      </thead>
</table>

<div class="centered">
    <#if addsFromBE?has_content>
    <table class="table table-hover">
      <thead class="thead-dark">
        <tr>
          <th scope="col">#</th>
          <th scope="col">Course</th>
          <th scope="col">Description</th>
          <th scope="col">Category</th>
          <th scope="col">Price</th>
          <th>
              <form method="post" action="/admin/sync" target="_self">
                  <input type="submit" value="Sync Courses">
              </form>
          </th>
          <th></th>
        </tr>
      </thead>
      <tbody>
       <#list addsFromBE as item>
            <tr>
              <th scope="row">${item.addId?string.computer}</th>
              <td>${item.addName}</td>
             <td>${item.addDescription}</td>
             <td>${item.addCategory}</td>
             <td>${item.addPrice?string.computer}</td>
             <td>
                 <form method="post" action="/admin/delete?id=${item.addId}" target="_self">
                     <input type="submit" value="Delete">
                 </form>
                 </td>
                 <td>
                     <form method="post" action="/admin/edit-page?id=${item.addId}" target="_self">
                         <input type="submit" value="Update">
                     </form>
                 </td>
            </tr>
        </#list>
      </tbody>
    </table>
    <#else>

            <!--table if list is null-->
            <h1>No Adds To Display</h1>
        </#if>
</div>
</body>
</html>